package com.seri.web.controller;

import com.seri.web.dao.SubjectDao;
import com.seri.web.dao.UserDao;
import com.seri.web.dao.daoImpl.SubjectDaoImpl;
import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.School;
import com.seri.web.model.Standard;
import com.seri.web.model.Subject;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 29/05/16.
 */
@Controller
@RequestMapping(value = "subject")
public class SubjectController {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private UserDao userDao = new UserDaoImpl();
    private SubjectDao subjectDao = new SubjectDaoImpl();

    @RequestMapping(value = "/addsubject/", method = RequestMethod.GET)
    public @ResponseBody
    String addSubject(HttpServletRequest request) {
        try {
            String name = request.getParameter("name");
            int standardId = Integer.parseInt(request.getParameter("standardId"));
            int isCompulsary = Integer.parseInt(request.getParameter("isCompulsary"));
            int status = Integer.parseInt(request.getParameter("status"));
            User sessUser = globalFunUtils.getLoggedInUserDetail();
            String dateTime = globalFunUtils.getDateTime();

            Subject subject = new Subject();
            subject.setIsCompulsary(isCompulsary);
            subject.setStandardId(standardId);
            subject.setSubjectName(name);
            subject.setStatus(status);
            subject.setCreatedBy(sessUser.getLogin());
            subject.setCreatedDate(dateTime);
            subject.setLastUpdatedBy(sessUser.getLogin());
            subject.setLastUpdatedDate(dateTime);

            if(request.getParameter("subjectId") != null) {
                subject.setSubjectId(Integer.parseInt(request.getParameter("subjectId")));
                subjectDao.update(subject);
            }
            else
                subjectDao.create(subject);
            return String.valueOf(subject.getSubjectId());

        } catch (Exception e) {
            return "false";
        }
    }

    @RequestMapping(value = "/subjectlisting/", method = RequestMethod.GET)
    public @ResponseBody
    String getSubjectListing(HttpServletRequest request) {
        try {
            if(request.getParameter("standardId")==null)
                return "<tr><td colspan='3'><h4>No Subject Found</h4></td>";
            int standardId = Integer.parseInt(request.getParameter("standardId"));
            List<Subject> subjectList = subjectDao.getSubjectByStandardId(standardId);
            String retHtml = "";
            if(subjectList != null) {
                for (Subject subject:subjectList) {
                    retHtml+= "<tr><td></td><td>"+subject.getSubjectName()+"</td><td><a href='#standardAddForm' data-sub-id='"+subject.getSubjectId()+"' data-standard-id='"+subject.getStandardId()+"' class='sub-edit-a' data-toggle='modal'>Edit</a></td></tr>";
                }

            } else {
                return "<tr><td colspan='3'><h4>No Subject Found</h4></td>";
            }
            return retHtml;
        } catch (Exception e) {
            return "<tr><td colspan='3'><h4>No Subject Found</h4></td>";
        }
    }

    @RequestMapping(value = "/getschoolsubjects/", method = RequestMethod.GET)
    public @ResponseBody
    String getSchoolSubjectCtrl(HttpServletRequest request) {
        String ctrlName  = request.getParameter("ctrlName");
        String ctrlId  = request.getParameter("ctrlId");
        String ctrlClass  = request.getParameter("ctrlClass");
        int selectedSubject = Integer.parseInt(request.getParameter("selectedSubjectVal"));

        if(ctrlName==null)
            ctrlClass="subs_standard";
        if(ctrlId==null)
            ctrlId = ctrlClass;
        if(ctrlClass==null)
            ctrlClass=ctrlName;

        String retHtml = "<select name='"+ctrlName+"' class='"+ctrlClass+"' id='"+ctrlId+"'><option value='0'>--Select Subject--</option>";

        try {
            if(request.getParameter("standardId")==null)
                throw new Exception();
            int standardId = Integer.parseInt(request.getParameter("standardId"));



            List<Subject> subjectList = subjectDao.getSubjectByStandardId(standardId);
            if(subjectList != null) {
                for (Subject subject:subjectList) {
                    retHtml+= "<option value='"+subject.getSubjectId()+"' " + ((subject.getSubjectId()==selectedSubject) ? "selected='selected'" : "") +">"+subject.getSubjectName()+"</option>";
                }

            } else {
                throw new Exception();
            }
            return retHtml;
        } catch (Exception e) { }

        retHtml+= "</select>";
        return retHtml;
    }
}
