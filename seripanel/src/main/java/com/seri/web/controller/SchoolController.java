package com.seri.web.controller;

import com.seri.web.dao.DepartmentDao;
import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.StandardDao;
import com.seri.web.dao.TeacherDao;
import com.seri.web.dao.daoImpl.DepartmentDaoImpl;
import com.seri.web.dao.daoImpl.STandardDaoImpl;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.dao.daoImpl.TeacherDaoImpl;
import com.seri.web.model.*;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 23/04/16.
 */
@Controller
@RequestMapping(value = "school")
public class SchoolController {
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private StandardDao standardDao = new STandardDaoImpl();

    @RequestMapping(value = "/addschool**", method = RequestMethod.GET)
    public ModelAndView addSchoolPage(@ModelAttribute("schoolForm") School schoolForm) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        model.addObject("schoolForm", schoolForm);
        model.addObject("formAction", "add");
        model.setViewName("school/add_update");
        return model;
    }

    @RequestMapping(value = "/add**", method = RequestMethod.POST)
    public ModelAndView addPage(@ModelAttribute("schoolForm") School schoolForm) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        Boolean errFlag = false;
        try {
            if (schoolDao.getSchoolUsingEmail(schoolForm.getSchoolEmail()) != null) {
                errFlag = true;
                model.addObject("emailErr", true);
                model.addObject("emailErrrrMsg", "School with same email id already exists");
            }
            if (schoolDao.getSchoolUsingContact(schoolForm.getSchoolContact()) != null) {
                errFlag = true;
                model.addObject("contactErr", true);
                model.addObject("contactErrrrMsg", "School with same Contact No already exists");
            }

            if (!errFlag) {
                schoolForm.setCreatedDate(globalFunUtils.getDateTime());
                schoolForm.setCreatedBy(sessUser.getLogin());
                schoolForm.setLastUpdatedDate(globalFunUtils.getDateTime());
                schoolForm.setLastUpdatedBy(sessUser.getLogin());
                errFlag = schoolDao.create(schoolForm);
            }
        } catch (Exception e){
            model.addObject("otherErr", true);
            model.addObject("otherErrrrMsg", "Data Error!!! Please verify and try again...");
            errFlag = true;
        }
        if (errFlag) {
            model.addObject("schoolForm", schoolForm);
            model.addObject("formAction", "add");
            model.setViewName("school/add_update");
            return model;
        }
        return new ModelAndView("redirect:addschool?addschool=success");
    }

    @RequestMapping(value = "/manage**", method = RequestMethod.GET)
    public ModelAndView manageSchoolPage() {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        List<School> schoolList = schoolDao.getAllSchool();
        List<Standard> standardList = standardDao.getPrimaryStandard();
        model.addObject("standardList", standardList);
        model.addObject("schoolList", schoolList);
        model.setViewName("school/manage_school");
        return model;
    }

    @RequestMapping(value = "/editschool**", method = RequestMethod.GET)
    public ModelAndView editSchoolPage(HttpServletRequest request) {
        try {
            User sessUser = globalFunUtils.getLoggedInUserDetail();
            String schoolLogin = "";
            if (sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
                schoolLogin = sessUser.getLogin();
            } else {
                int tempSchoolId = Integer.parseInt(request.getParameter("id"));
                School tempSchool = schoolDao.getSchoolUsingId(tempSchoolId);
                schoolLogin=tempSchool.getPrincipalUserLogin();
            }
            School schoolForm = schoolDao.getSchoolUsingPrincipalEmail(schoolLogin);
            if(schoolForm == null)
                throw new Exception();
            ModelAndView model = new ModelAndView();

            model.addObject("schoolForm", schoolForm);
            model.addObject("formAction", "edit");
            model.setViewName("school/add_update");
            return model;
        } catch(Exception e) {
            return new ModelAndView("redirect:addschool");
        }

    }

    @RequestMapping(value = "/edit**", method = RequestMethod.POST)
    public ModelAndView editPage(@ModelAttribute("schoolForm") School schoolForm) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        Boolean errFlag = false;
        try {
            if (schoolDao.getSchoolNotUsingSameEmail(schoolForm.getSchoolEmail(), schoolForm.getSchoolId()) != null) {
                errFlag = true;
                model.addObject("emailErr", true);
                model.addObject("emailErrrrMsg", "School with same email id already exists");
            }
            if (schoolDao.getSchoolNotUsingSameSchoolContact(schoolForm.getSchoolContact(), schoolForm.getSchoolId()) != null) {
                errFlag = true;
                model.addObject("contactErr", true);
                model.addObject("contactErrrrMsg", "School with same Contact No already exists");
            }

            if (!errFlag) {
                schoolForm.setLastUpdatedDate(globalFunUtils.getDateTime());
                schoolForm.setLastUpdatedBy(sessUser.getLogin());
                errFlag = schoolDao.update(schoolForm);
            }
        } catch (Exception e){
            model.addObject("otherErr", true);
            model.addObject("otherErrrrMsg", "Data Error!!! Please verify and try again...");
            errFlag = true;
        }
        if (errFlag) {
            model.addObject("schoolForm", schoolForm);
            model.addObject("formAction", "editschool");
            model.setViewName("school/add_update");
            return model;
        }
        return new ModelAndView("redirect:editschool?update=success&id="+schoolForm.getSchoolId());
    }

    @RequestMapping(value = "updateadmin", method = RequestMethod.GET)
    public ModelAndView updateSchoolAdmin(){
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/departmentlistctrl/", method = RequestMethod.GET)
    public @ResponseBody
    String getDepartmentListing(HttpServletRequest request) {
        String schoolId = request.getParameter("schoolId");
        String ctrl = "";
        /*if(schoolId != null)
            ctrl = getDepartmentListing();*/
        return "";
    }

    public String getDepartmentListing(String ctrlName, String ctrlId, String ctrlClass, String selectedDepartment) {
        List<Department> departmentList = departmentDao.getDepartmentList();
        String ctrl = "";

        ctrl = "<select name='"+ctrlName+"' id='"+ctrlId+"' class='"+ctrlClass+"'><option value='0'>-Select Department-</option>";
        if(departmentList!=null) {
            for (Department department : departmentList) {
                ctrl += "<option value=" + department.getDepartmentId() + " " + (((department.getDepartmentId()) == Integer.parseInt(selectedDepartment)) ? "selected='selected'" : "") + ">" + department.getDepartmentName() + "</option>";
            }
        }
        ctrl+="</select>";
        return ctrl;
    }
}
