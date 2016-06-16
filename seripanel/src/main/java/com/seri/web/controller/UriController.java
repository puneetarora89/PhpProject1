package com.seri.web.controller;

import com.seri.web.dao.*;
import com.seri.web.dao.daoImpl.*;
import com.seri.web.model.*;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by puneet on 04/04/16.
 */
@Controller
public class UriController {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private UserController userController = new UserController();
    private TeacherController teacherController = new TeacherController();
    private StudentController studentController = new StudentController();
    private HodController hodController = new HodController();

    private UserDaoImpl userDao = new UserDaoImpl();
    private TeacherDaoImpl teacherDao = new TeacherDaoImpl();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private StudentDao studentDao = new StudentDaoImpl();
    private ParentsDao parentsDao = new ParentsDaoImpl();
    private HodDao hodDao = new HodDaoImpl();

    @RequestMapping(value = "/login**", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("login");
        return model;
    }

    @RequestMapping(value = "/adduser**", method = RequestMethod.GET)
    public ModelAndView adminPage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/adduser");
        return model;

    }

    @RequestMapping(value = "/addadmin**", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView adminAdd(@ModelAttribute("userForm") User userForm, @ModelAttribute("schoolForm") School schoolForm, @ModelAttribute("studentForm") Student studentForm, @ModelAttribute("teacherForm") Teacher teacherForm, @ModelAttribute("parentsForm") Parents parentsForm, @ModelAttribute("hodForm") Hod hodForm, HttpServletRequest request) throws IOException, NoSuchAlgorithmException {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        if(userController.createUser(userForm, request))
        {

            if(userForm.getRole().equals("ROLE_SCHOOL_ADMIN")) {

                School school = schoolDao.getSchoolUsingId(schoolForm.getSchoolId());
                school.setPrincipalUserLogin(userForm.getLogin());
                school.setLastUpdatedBy(sessUser.getLogin());
                school.setLastUpdatedDate(globalFunUtils.getDateTime());
                schoolDao.update(school);

            } else if(userForm.getRole().equals("ROLE_STUDENT")) {

                studentForm.setStudentLoginId(userForm.getLogin());
                studentForm.setUserId(userForm.getUserId());
                studentForm.setCreatedBy(sessUser.getLogin());
                studentForm.setLastUpdatedBy(sessUser.getLogin());
                studentForm.setLastUpdatedDate(globalFunUtils.getDateTime());
                studentForm.setCreatedDate(globalFunUtils.getDateTime());
                studentDao.create(studentForm);

            } else if(userForm.getRole().equals("ROLE_TEACHER")) {

                teacherForm.setTeacherLoginId(userForm.getLogin());
                teacherForm.settLoginId(userForm.getUserId());
                teacherForm.settCreatedBy(sessUser.getLogin());
                teacherForm.settLastUpdateBy(sessUser.getLogin());
                teacherForm.settLastUpdateDate(globalFunUtils.getDateTime());
                teacherForm.settCreatedDate(globalFunUtils.getDateTime());
                teacherDao.create(teacherForm);

            }  else if(userForm.getRole().equals("ROLE_PARENT")) {

                parentsForm.setParentLoginId(userForm.getLogin());
                parentsForm.setCreatedBy(sessUser.getLogin());
                parentsForm.setLastUpdatedBy(sessUser.getLogin());
                parentsForm.setLastUpdatedDate(globalFunUtils.getDateTime());
                parentsForm.setCreatedDate(globalFunUtils.getDateTime());
                parentsDao.create(parentsForm);

                Student student = studentDao.getStudentUsingStudentId(parentsForm.getStudentId());
                student.setParentLoginId(userForm.getLogin());
                studentDao.update(student);

            } else if(userForm.getRole().equals("ROLE_HOD")) {
                hodForm.setHodLoginId(userForm.getLogin());
                hodForm.setHodUserId(userForm.getUserId());
                hodForm.setCreatedBy(sessUser.getLogin());
                hodForm.setLastUpdatedBy(sessUser.getLogin());
                hodForm.setLastUpdatedDate(globalFunUtils.getDateTime());
                hodForm.setCreatedDate(globalFunUtils.getDateTime());
                hodDao.create(hodForm);
            }
            return new ModelAndView("redirect:adduser?token=success&");
        } else {
            model.addObject("userForm", userForm);
            model.addObject("emailErrrrMsg", "User with same email already registered");
            model.addObject("emailErr", true);
            model.setViewName("user/adduser");
        }


        return model;
    }

    @RequestMapping(value = "/createpassword**", method = RequestMethod.GET)
    public ModelAndView createPasswordPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User userDetails = userController.checkPasswordToken(request);
        if(userDetails!=null) {
            model.addObject("userDetails", userDetails);
            model.setViewName("user/createpassword");
        }
        else {
            model.addObject("errMsg", "Opps!!! Page not valid");
            model.setViewName("common/error");
        }

        return model;

    }

    @RequestMapping(value = "/addpassword**", method = RequestMethod.POST)
    public ModelAndView addPasswordFirstTimeUser(@ModelAttribute("userForm") User userForm, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        userController.createPassword(request);
        model.addObject("userDetails", userForm);
        model.setViewName("user/createpassword");
        return model;
    }

    @RequestMapping(value = "/updateprofile**", method = RequestMethod.GET)
    public ModelAndView updateUserPage() {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        model.addObject("userForm", sessUser);
        if(sessUser.getRole().equals("ROLE_SUP_ADMIN"))
        {
            model.addObject("formAction", "adminUpdate");
        }
        else if(sessUser.getRole().equals("ROLE_SUB_ADMIN"))
        {
            model.addObject("formAction", "adminUpdate");
        }
        else if(sessUser.getRole().equals("ROLE_SCHOOL_ADMIN"))
        {
            model.addObject("formAction", "schoolUpdate");
        }
        else if(sessUser.getRole().equals("ROLE_TEACHER"))
        {
            Teacher teacherProfile = teacherDao.getTeacherUsingLoginId(sessUser.getUserId());
            model.addObject("teacher", teacherProfile);
            model.addObject("formAction", "teacherUpdate");
        }
        else if(sessUser.getRole().equals("ROLE_HOD"))
        {
            Hod hodProfile = hodDao.getHodByLoginId(sessUser.getLogin());
            model.addObject("hodForm", hodProfile);
            model.addObject("formAction", "hodUpdate");
        }
        model.setViewName("user/updateprofile");
        return model;
    }

    @RequestMapping(value = "/teacherUpdate**", method = RequestMethod.POST)
    public ModelAndView teacherUpdate(@ModelAttribute("teacher") Teacher teacher, @ModelAttribute("userForm") User user, HttpServletRequest request) {
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        sessUser = userController.setFormAttributes(sessUser, user);
        userDao.update(sessUser);

        teacherController.addUpdate(teacher, sessUser);
        if(sessUser.getRole().equals("ROLE_TEACHER"))
            return new ModelAndView("redirect:updateprofile?token=success&");
        else
            return new ModelAndView("redirect:teacher/manage?token=success&p="+request.getParameter("p")+"&rpp="+request.getParameter("rpp"));
    }

    @RequestMapping(value = "/adminUpdate**", method = RequestMethod.POST)
    public ModelAndView adminUpdate(@ModelAttribute("userForm") User user) {
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        sessUser = userController.setFormAttributes(sessUser, user);
        sessUser.setLastUpdatedBy(sessUser.getLogin());
        userDao.update(sessUser);
        return new ModelAndView("redirect:updateprofile?token=success&");
    }

    @RequestMapping(value = "/studentUpdate**", method = RequestMethod.POST)
    public ModelAndView studentUpdate(@ModelAttribute("studentForm") Student student, @ModelAttribute("userForm") User user, HttpServletRequest request) {
        User updateUser = userDao.getUserUsingEmail(user.getLogin());
        updateUser = userController.setFormAttributes(updateUser, user);
        userDao.update(updateUser);

        User sessUser = globalFunUtils.getLoggedInUserDetail();

        studentController.addUpdate(student);
        if(sessUser.getRole().equals("ROLE_STUDENT"))
            return new ModelAndView("redirect:updateprofile?token=success&");
        else
            return new ModelAndView("redirect:student/manage?token=success&p="+request.getParameter("p")+"&rpp="+request.getParameter("rpp"));
    }

    @RequestMapping(value = "/hodUpdate**", method = RequestMethod.POST)
    public ModelAndView hodUpdate(@ModelAttribute("hodForm") Hod hod, @ModelAttribute("userForm") User user, HttpServletRequest request) {
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        User updateUser = userDao.getUserUsingEmail(user.getLogin());
        updateUser = userController.setFormAttributes(updateUser, user);
        userDao.update(updateUser);

        hodController.addUpdate(hod);
        if(sessUser.getRole().equals("ROLE_HOD"))
            return new ModelAndView("redirect:updateprofile?token=success&");
        else
            return new ModelAndView("redirect:hod/manage?token=success&schoolid="+hod.getHodSchoolId()+"&p="+request.getParameter("p")+"&rpp="+request.getParameter("rpp"));
    }

    @RequestMapping(value = "/parentUpdate**", method = RequestMethod.POST)
    public ModelAndView parentUpdate(@ModelAttribute("parentsForm") Parents parents, @ModelAttribute("userForm") User user, HttpServletRequest request) {
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        User updateUser = userDao.getUserUsingEmail(user.getLogin());
        updateUser = userController.setFormAttributes(updateUser, user);
        userDao.update(updateUser);

        String dateTime = globalFunUtils.getDateTime();
        parents.setGender(updateUser.getGender());
        parents.setLastUpdatedBy(sessUser.getLogin());
        parents.setLastUpdatedDate(dateTime);
        parents.setParentLoginId(updateUser.getLogin());

        parentsDao.update(parents);

        //hodController.addUpdate(hod);
        if(sessUser.getRole().equals("ROLE_HOD"))
            return new ModelAndView("redirect:updateprofile?token=success&");
        else
            return new ModelAndView("redirect:student/manage?token=success&schoolid="+request.getParameter("schoolid")+"&p="+request.getParameter("p")+"&rpp="+request.getParameter("rpp"));
    }

}
