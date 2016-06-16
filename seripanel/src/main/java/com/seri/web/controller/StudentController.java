package com.seri.web.controller;

import com.seri.web.dao.ParentsDao;
import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.StudentDao;
import com.seri.web.dao.UserDao;
import com.seri.web.dao.daoImpl.ParentsDaoImpl;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.dao.daoImpl.StudentDaoImpl;
import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.*;
import com.seri.web.utils.GlobalFunUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 22/05/16.
 */
@Controller
@RequestMapping(value = "student")
public class StudentController {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private ParentsDao parentsDao = new ParentsDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();

    @RequestMapping(value = "/manage**", method = RequestMethod.GET)
    public ModelAndView manageStudentPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        model.setViewName("student/manage_student");
        return model;
    }

    @RequestMapping(value = "/studentlisting/", method = RequestMethod.GET)
    public @ResponseBody
    String getStudentListing(HttpServletRequest request) {
        int rpp = 10;
        int p=1;
        int offset = 0;
        String retHtml = "";

        User sessUser = globalFunUtils.getLoggedInUserDetail();
        List<Student> studentList = null;
        List<Student> countStudentList = null;
        Map<String, Integer> params = new HashMap<String, Integer>();

        if(request.getParameter("rpp") != null)
            rpp = Integer.valueOf(request.getParameter("rpp"));

        if(request.getParameter("p")!=null) {
            p = Integer.valueOf(request.getParameter("p"));
            offset = rpp*(p-1);
        }

        params.put("offset", offset);
        params.put("rpp", rpp);

        int schoolId = 0;

        if(sessUser.getRole().equals("ROLE_SUP_ADMIN")) {
            if(request.getParameter("schoolid") != null) {
                params.put("schoolid", Integer.valueOf(request.getParameter("schoolid")));
                schoolId = Integer.valueOf(request.getParameter("schoolid"));
            }

        } else if(sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
            School school = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
            params.put("schoolid", school.getSchoolId());
            schoolId = school.getSchoolId();
        }

        studentList = studentDao.getStudentListUsingSchoolId(params, false);
        countStudentList = studentDao.getStudentListUsingSchoolId(params, true);

        Parents parents;

        if(studentList != null && studentList.size()>0){

            retHtml = "<table class=\"table table-bordered responsive\">\n" +
                    "                <thead>\n" +
                    "                <tr>\n" +
                    "                    <th></th>\n" +
                    "                    <th>Student Name</th>\n" +
                    "                    <th>Email (LoginId)</th>\n" +
                    "                    <th>Application No</th>\n" +
                    "                    <th>Standard</th>\n" +
                    "                    <th>Address</th>\n" +
                    "                    <th>Parent Profile</th>\n" +
                    "                    <th>&nbsp;</th>\n" +
                    "                </tr>\n" +
                    "                </thead>\n" +
                    "                <tbody>\n";

            for (Student student:studentList) {
                retHtml+="<tr><td></td>";
                retHtml+="<td>"+student.getfName()+" "+student.getlName() + " <em>( "+student.getMobNo()+" )</em></td>";
                retHtml+="<td>"+student.getStudentLoginId()+"</td>";
                retHtml+="<td>"+student.getApplicationNo()+"</td>";
                retHtml+="<td>"+student.getStuStandardId()+"</td>";
                retHtml+="<td>"+student.getAddress()+"<br>"+student.getState()+" "+student.getCity()+"<br>"+student.getCountry()+"</td>";
                retHtml+="<td>"+parentProfileLink(student.getParentLoginId(), p, rpp, schoolId)+"</td>";
                retHtml+="<td>"+"<a href=\"/student/update?id="+student.getStudentId()+"&p="+p+"&rpp="+rpp+"\" class=\"editStudent\"><span class=\"icon-edit\"></span></a></td>";
                retHtml+="</tr>";
            }

            retHtml+="</tbody>\n" +
                    "</table>";

            if(countStudentList.size() > studentList.size()){
                retHtml+= "<ul class=\"pager\" data-p='"+p+"'>\n";
                if(p==1)
                    retHtml+="<li class='disabled'><a href=\"#\">Previous</a></li>\n";
                else
                    retHtml+="<li class=''><a href=\"#\" data-p='"+(p-1)+"'>Previous</a></li>\n";

                if(p < countStudentList.size()/rpp)
                    retHtml+="<li><a href=\"javascript:void(0)\" data-p='"+(p+1)+"'>Next</a></li></ul>";
                else
                    retHtml+="<li class='disabled'><a href=\"javascript:void(0)\" data-p='"+(p+1)+"'>Next</a></li></ul>";


            }
        }
        return retHtml;
    }

    @RequestMapping(value = "/update**", method = RequestMethod.GET)
    public ModelAndView updateTeacher(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();

        if(request.getParameter("id") == null)
            return new ModelAndView("redirect:manage_student");

        int id = Integer.parseInt(request.getParameter("id"));

        Student studentProfile = studentDao.getStudentUsingStudentId(id);
        if(studentProfile==null)
            return new ModelAndView("redirect:manage_student");

        if(sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
            School tempSchool = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
            if(tempSchool.getSchoolId() != studentProfile.getStuSchoolId()) {
                return new ModelAndView("redirect:manage_student");
            }
        }

        User userForm = userDao.getUserUsingEmail(studentProfile.getStudentLoginId());

        model.addObject("userForm", userForm);
        model.addObject("studentForm", studentProfile);
        model.addObject("formAction", "studentUpdate");

        model.setViewName("student/student_update");
        return model;
    }

    public Boolean addUpdate(Student student) {
        Boolean flag = false;
        String dateTime = globalFunUtils.getDateTime();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        student.setLastUpdatedBy(sessUser.getLogin());
        student.setLastUpdatedDate(dateTime);

        Student tempDetails = studentDao.getStudentUsingStudentId(student.getStudentId());
        if(tempDetails != null)
        {
            student.setStudentLoginId(tempDetails.getStudentLoginId());
            student.setCreatedBy(tempDetails.getCreatedBy());
            student.setCreatedDate(tempDetails.getCreatedDate());
            student.setParentLoginId(tempDetails.getParentLoginId());
            studentDao.update(student);
        } else {
            student.setCreatedDate(dateTime);
            student.setCreatedBy(sessUser.getLogin());
            studentDao.create(student);
        }
        return flag;
    }

    private String parentProfileLink(String loginId, int p, int rpp, int schoolId){
        if(loginId!=null && loginId!="") {
            Parents parents = parentsDao.getProfileUsingLoginId(loginId);
            if (parents != null) {
                String fNo = "--", mNo = "--";
                if (parents.getfMobNo() != null && parents.getfMobNo() != "" && !parents.getfMobNo().equals("0"))
                    fNo = parents.getfMobNo();
                if (parents.getmMobNo() != null && parents.getmMobNo() != "" && !parents.getmMobNo().equals("0"))
                    mNo = parents.getmMobNo();

                String aTxt = parents.getfName() + " " + parents.getmName() + " " + parents.getlName() + " <br>Father No: " + fNo + " // Mother No: " + mNo + "<br>Email Id: "+parents.getParentLoginId();

                return "<a href='/parents/update/?id=" + parents.getParentId() + "&p="+ p +"&rpp="+rpp+"&cid="+schoolId+"'>" + aTxt + "</a>";
            }
        }
        return " -- ";
    }
}
