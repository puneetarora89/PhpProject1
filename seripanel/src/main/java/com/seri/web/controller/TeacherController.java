package com.seri.web.controller;

import com.seri.web.dao.HodDao;
import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.UserDao;
import com.seri.web.dao.daoImpl.HodDaoImpl;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.dao.daoImpl.TeacherDaoImpl;
import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.Hod;
import com.seri.web.model.School;
import com.seri.web.model.Teacher;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 10/04/16.
 */
@Controller
@RequestMapping(value = "teacher")
public class TeacherController {

    private TeacherDaoImpl teacherDao = new TeacherDaoImpl();
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private HodDao hodDao = new HodDaoImpl();
    private StandardController standardController = new StandardController();

    @RequestMapping(value = "/manage**", method = RequestMethod.GET)
    public ModelAndView manage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        model.setViewName("teacher/manage_teacher");
        return model;
    }

    @RequestMapping(value = "/teacherlisting/", method = RequestMethod.GET)
    public @ResponseBody
    String getTeacherListing(HttpServletRequest request) {
        int rpp = 10;
        int p=1;
        int offset = 0;
        String retHtml = "";

        User sessUser = globalFunUtils.getLoggedInUserDetail();
        List<Teacher> teacherList = null;
        List<Teacher> countTeacherList = null;
        Map<String, Integer> params = new HashMap<String, Integer>();

        if(request.getParameter("rpp") != null)
            rpp = Integer.valueOf(request.getParameter("rpp"));

        if(request.getParameter("p")!=null) {
            p = Integer.valueOf(request.getParameter("p"));
            offset = rpp*(p-1);
        }

        if(request.getParameter("deptId")!=null && request.getParameter("deptId")!="") {
            params.put("departmentId", Integer.valueOf(request.getParameter("deptId")));
        }

        params.put("offset", offset);
        params.put("rpp", rpp);


        if(sessUser.getRole().equals("ROLE_SUP_ADMIN")) {
            if(request.getParameter("schoolid") != null)
                params.put("schoolid", Integer.valueOf(request.getParameter("schoolid")));

        } else if(sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
            School school = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
            params.put("schoolid", school.getSchoolId());
        } else if(sessUser.getRole().equals("ROLE_HOD")) {
            Hod hod = hodDao.getHodByLoginId(sessUser.getLogin());
            params.put("schoolid", hod.getHodSchoolId());
            params.put("departmentId", hod.getHodDepartmentId());
        }

        teacherList = teacherDao.getTeacherListUsingSchoolId(params, false);
        countTeacherList = teacherDao.getTeacherListUsingSchoolId(params, true);

        if(teacherList != null && teacherList.size()>0){
            Map<Integer, String> standardListMap = standardController.getStandardMap();
            retHtml = "<table class=\"table table-bordered responsive\">\n" +
                    "                <thead>\n" +
                    "                <tr>\n" +
                    "                    <th></th>\n" +
                    "                    <th>Teacher Name</th>\n" +
                    "                    <th>Email (LoginId)</th>\n" +
                    "                    <th>Standard</th>\n" +
                    "                    <th>Address</th>\n" +
                    "                    <th>Specialization</th>\n" +
                    "                    <th>&nbsp;</th>\n" +
                    "                </tr>\n" +
                    "                </thead>\n" +
                    "                <tbody>\n";

            for (Teacher teacher:teacherList) {
                retHtml+="<tr><td></td>";
                retHtml+="<td>"+teacher.getfName()+" "+teacher.getlName() + " <em>( "+teacher.gettMobNo()+" )</em></td>";
                retHtml+="<td>"+teacher.getTeacherLoginId()+"</td>";
                retHtml+="<td>"+standardListMap.get(teacher.getTeacherStandardId())+"</td>";
                retHtml+="<td>"+teacher.gettHomeAdress()+"</td>";
                retHtml+="<td>"+teacher.gettSpecialization()+"</td>";
                retHtml+="<td>"+"<a href=\"/teacher/update?id="+teacher.getTeacherId()+"&p="+p+"&rpp="+rpp+"\" class=\"editSchool\"><span class=\"icon-edit\"></span></a></td>";
                retHtml+="</tr>";
            }

            retHtml+="</tbody>\n" +
                    "</table>";

            if(countTeacherList.size() > teacherList.size()){
                retHtml+= "<ul class=\"pager\" data-p='"+p+"'>\n";
                if(p==1)
                    retHtml+="<li class='disabled'><a href=\"#\">Previous</a></li>\n";
                else
                    retHtml+="<li class=''><a href=\"#\" data-p='"+(p-1)+"'>Previous</a></li>\n";

                if(p < countTeacherList.size()/rpp)
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
            return new ModelAndView("redirect:manage_teacher");

        int id = Integer.parseInt(request.getParameter("id"));

        Teacher teacherProfile = teacherDao.getTeacherUsingTeacherId(id);
        if(teacherProfile==null)
            return new ModelAndView("redirect:manage_teacher");

        if(sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
            School tempSchool = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
            if(tempSchool.getSchoolId()!=teacherProfile.getTeacherSchoolId()) {
                return new ModelAndView("redirect:manage_teacher");
            }
        } else if(sessUser.getRole().equals("ROLE_HOD")) {
            Hod tempHod = hodDao.getHodByLoginId(sessUser.getLogin());
            if(tempHod.getHodSchoolId()!=teacherProfile.getTeacherSchoolId() || tempHod.getHodDepartmentId()!=teacherProfile.gettDeptName()) {
                return new ModelAndView("redirect:manage_teacher");
            }
        }

        User userForm = userDao.getUserUsingEmail(teacherProfile.getTeacherLoginId());

        model.addObject("userForm", userForm);
        model.addObject("teacher", teacherProfile);
        model.addObject("formAction", "teacherUpdate");

        model.setViewName("teacher/admin_update");
        return model;
    }

    @RequestMapping(value = "/viewprofile**", method = RequestMethod.GET)
    public ModelAndView viewProfile(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        Teacher teacherProfile = null;
        int teacherId = 0;
        if(sessUser.getRole().equals("ROLE_TEACHER")) {
            teacherProfile = teacherDao.getTeacherUsingLoginId(sessUser.getUserId());
        } else {
            if(request.getParameter("id") == null)
                return new ModelAndView("redirect:manage_teacher");

            teacherId = Integer.parseInt(request.getParameter("id"));
            teacherProfile = teacherDao.getTeacherUsingTeacherId(teacherId);
        }

        if(teacherProfile==null)
            return new ModelAndView("redirect:manage_teacher");

        model.addObject("teacher", teacherProfile);

        model.setViewName("teacher/viewProfile");
        return model;
    }

    public Boolean addUpdate(Teacher teacher, User sessUser) {
        Boolean flag = false;
        String dateTime = globalFunUtils.getDateTime();

        teacher.settLastUpdateBy(sessUser.getLogin());
        teacher.settLastUpdateDate(dateTime);
        Teacher tempDetails = teacherDao.getTeacherUsingLoginId(teacher.gettLoginId());
        if(tempDetails != null)
        {
            teacher.setTeacherLoginId(tempDetails.getTeacherLoginId());
            teacher.setTeacherStandardId(tempDetails.getTeacherStandardId());
            teacher.setTeacherSchoolId(tempDetails.getTeacherSchoolId());
            teacher.setTeacherId(tempDetails.getTeacherId());
            teacherDao.update(teacher);
        } else {
            teacher.settCreatedDate(dateTime);
            teacher.settCreatedBy(sessUser.getLogin());
            teacherDao.create(teacher);
        }
        return flag;
    }
}
