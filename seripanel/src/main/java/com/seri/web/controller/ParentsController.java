package com.seri.web.controller;

import com.seri.web.dao.*;
import com.seri.web.dao.daoImpl.*;
import com.seri.web.model.*;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by puneet on 28/05/16.
 */
@Controller
@RequestMapping(value = "parents")
public class ParentsController {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private ParentsDao parentsDao = new ParentsDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private HodDao hodDao = new HodDaoImpl();
    private UserDao userDao = new UserDaoImpl();

    @RequestMapping(value = "/update**", method = RequestMethod.GET)
    public ModelAndView updateParent(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();

        if(request.getParameter("id") == null)
            return new ModelAndView("redirect:manage_student");

        int id = Integer.parseInt(request.getParameter("id"));

        Parents parentsProfile = parentsDao.getProfileUsingParentsId(id);
        if(parentsProfile==null)
            return new ModelAndView("redirect:manage_student");

        Student student = studentDao.getStudentUsingStudentId(parentsProfile.getStudentId());

        if(sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
            School tempSchool = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
            if(student==null || tempSchool.getSchoolId()!=student.getStuSchoolId()) {
                return new ModelAndView("redirect:manage_student");
            }
        }

        User userForm = userDao.getUserUsingEmail(parentsProfile.getParentLoginId());

        model.addObject("userForm", userForm);
        model.addObject("parentsForm", parentsProfile);
        model.addObject("formAction", "parentUpdate");

        model.setViewName("parent/parent_update");
        return model;
    }
}
