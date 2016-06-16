package com.seri.web.controller;

import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.StandardDao;
import com.seri.web.dao.SyllabusDao;
import com.seri.web.dao.UserDao;
import com.seri.web.dao.daoImpl.STandardDaoImpl;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.dao.daoImpl.SyllabusDaoImpl;
import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.School;
import com.seri.web.model.Standard;
import com.seri.web.model.Syllabus;
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
import org.json.simple.JSONObject;

/**
 * Created by puneet on 29/05/16.
 */
@Controller
@RequestMapping(value = "syllabus")
public class SyllabusController {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private UserDao userDao = new UserDaoImpl();
    private SyllabusDao syllabusDao = new SyllabusDaoImpl();
    private StandardDao standardDao = new STandardDaoImpl();

    @RequestMapping(value = "/content**", method = RequestMethod.GET)
    public ModelAndView manageStudentPage(HttpServletRequest request) {
        try {
            ModelAndView model = new ModelAndView();
            User sessUser = globalFunUtils.getLoggedInUserDetail();
            int schoolId = 0;
            int standardId = 0;
            int subjectId = 0;
            if (sessUser.getRole().equals("ROLE_TEACHER")) {

            } else if (sessUser.getRole().equals("ROLE_HOD")) {

            } else if (sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
                if (request.getParameter("standardid") != null){
                    standardId = Integer.parseInt(request.getParameter("standardid"));
                }
                School school = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
                schoolId = school.getSchoolId();

            } else if (sessUser.getRole().equals("ROLE_SUB_ADMIN") || sessUser.getRole().equals("ROLE_SUP_ADMIN")) {
                if (request.getParameter("standardid") != null){
                    standardId = Integer.parseInt(request.getParameter("standardid"));
                }
                if (request.getParameter("schoolid") != null){
                    schoolId = Integer.parseInt(request.getParameter("schoolid"));
                }
            } else {
                throw new Exception();
            }

            if(request.getParameter("subjectid") != null){
                subjectId = Integer.parseInt(request.getParameter("subjectid"));
            }


            Syllabus syllabus = null;
            if(schoolId>0 && standardId>0 && subjectId>0) {
                Map<String, String> params = new HashMap<String, String>();
                params.put("syllabusDueDate", globalFunUtils.getDateTime());
                params.put("schoolId", String.valueOf(schoolId));
                params.put("standardId", String.valueOf(standardId));
                params.put("subjectId", String.valueOf(subjectId));
                syllabus = syllabusDao.getSyllabusBySyllabusFilters(params);
            }
            model.addObject("schoolId", schoolId);
            model.addObject("standardId", standardId);
            model.addObject("subjectId", subjectId);

            model.addObject("syllabusForm", syllabus);
            if (syllabus == null)
                model.addObject("formAction", "/syllabus/addsyllabus");
            else
                model.addObject("formAction", "/syllabus/editsyllabus");
            model.setViewName("syllabus/add_update");
            return model;
        } catch (Exception e) {
            return new ModelAndView("redirect:standard/manage?token=invalidselection&");
        }
    }

    @RequestMapping(value = "/addsyllabus**", method = RequestMethod.POST)
    public ModelAndView addSyllabusPage(@ModelAttribute("syllabusForm") Syllabus syllabusForm) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        String dateTime = globalFunUtils.getDateTime();
        syllabusForm.setCreatedBy(sessUser.getLogin());
        syllabusForm.setCreatedDate(dateTime);
        syllabusForm.setLastUpdatedBy(sessUser.getLogin());
        syllabusForm.setLastUpdatedDate(dateTime);
        syllabusForm.setTaskName("syllabus");
        syllabusDao.create(syllabusForm);
        model.setViewName("redirect:content?token=success&schoolid="+syllabusForm.getSchoolId()+"&standardid="+syllabusForm.getStandardId()+"&subjectid="+syllabusForm.getSubjectId());
        return model;
    }

    @RequestMapping(value = "/editsyllabus**", method = RequestMethod.POST)
    public ModelAndView editSyllabusPage(@ModelAttribute("syllabusForm") Syllabus syllabusForm, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        String dateTime = globalFunUtils.getDateTime();
        syllabusForm.setTaskId(Integer.parseInt(request.getParameter("taskId1")));
        syllabusForm.setLastUpdatedBy(sessUser.getLogin());
        syllabusForm.setLastUpdatedDate(dateTime);
        syllabusForm.setTaskName("syllabus");
        syllabusDao.update(syllabusForm);
        model.setViewName("redirect:content?token=success&schoolid="+syllabusForm.getSchoolId()+"&standardid="+syllabusForm.getStandardId()+"&subjectid="+syllabusForm.getSubjectId());
        return model;
    }

    @RequestMapping(value = "/findlatestsyllabus/", method = RequestMethod.GET)
    public @ResponseBody
    String getLatestSyllabus(HttpServletRequest request) {
        JSONObject obj = new JSONObject();

        Map<String, String> params = new HashMap<String, String>();
        try {
            if (request.getParameter("schoolId") != null && request.getParameter("standardId") != null && request.getParameter("subjectId") != null){
                params.put("syllabusDueDate", globalFunUtils.getDateTime());
                params.put("schoolId", request.getParameter("schoolId"));
                params.put("standardId", request.getParameter("standardId"));
                params.put("subjectId", request.getParameter("subjectId"));
                Syllabus syllabus = syllabusDao.getSyllabusBySyllabusFilters(params);
                if(syllabus != null) {
                    obj.put("result", true);
                    obj.put("id", syllabus.getTaskId());
                    obj.put("content", syllabus.getContent());
                    obj.put("dueDate", syllabus.getTaskDueDate());
                } else {
                    obj.put("result", false);
                }
            }

        } catch (Exception e) {
            obj.put("result", false);
        }
        return obj.toJSONString();
    }
}
