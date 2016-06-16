package com.seri.web.controller;

import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.StandardDao;
import com.seri.web.dao.daoImpl.STandardDaoImpl;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.model.*;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 28/05/16.
 */
@Controller
@RequestMapping(value = "standard")
public class StandardController {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private StandardDao standardDao = new STandardDaoImpl();

    @RequestMapping(value = "/addstandard**", method = RequestMethod.GET)
    public ModelAndView addStandardPage(@ModelAttribute("standardForm") Standard standardForm) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        model.addObject("standardForm", standardForm);
        model.addObject("formAction", "standard/standardadd");
        model.setViewName("standard/add_update");
        return model;
    }

    @RequestMapping(value = "/standardadd**", method = RequestMethod.POST)
    public ModelAndView standardAddPage(@ModelAttribute("standardForm") Standard standardForm) {
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        String dateTime = globalFunUtils.getDateTime();
        standardForm.setLastUpdatedDate(dateTime);
        standardForm.setLastUpdatedBy(sessUser.getLogin());
        standardForm.setCreatedDate(dateTime);
        standardForm.setCreatedBy(sessUser.getCreatedBy());
        standardForm.setStatus(1);
        standardDao.create(standardForm);
        return new ModelAndView("redirect:manage?added=true");
    }

    @RequestMapping(value = "/update**", method = RequestMethod.GET)
    public ModelAndView editStandardPage(@ModelAttribute("standardForm") Standard standardForm, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        if(request.getParameter("id") == null)
            return new ModelAndView("redirect:manage?invalidselection=true");

        int id = Integer.parseInt(request.getParameter("id"));
        Standard standard = standardDao.getStandardById(id);

        model.addObject("standardForm", standard);
        model.addObject("formAction", "standard/updatestandard");
        model.setViewName("standard/add_update");
        return model;
    }

    @RequestMapping(value = "/updatestandard**", method = RequestMethod.POST)
    public ModelAndView standardUpdatePage(@ModelAttribute("standardForm") Standard standardForm) {
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        String dateTime = globalFunUtils.getDateTime();
        standardForm.setLastUpdatedDate(dateTime);
        standardForm.setLastUpdatedBy(sessUser.getLogin());
        standardDao.update(standardForm);
        return new ModelAndView("redirect:manage?update=true");
    }


    @RequestMapping(value = "/manage**", method = RequestMethod.GET)
    public ModelAndView manageStandardPage(@ModelAttribute("schoolForm") School schoolForm) {
        ModelAndView model = new ModelAndView();
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        model.setViewName("standard/manage_standard");
        return model;
    }

    @RequestMapping(value = "/standardlisting/", method = RequestMethod.GET)
    public @ResponseBody
    String getStandardListing(HttpServletRequest request) {
        int rpp = 10;
        int p=1;
        int offset = 0;
        String retHtml = "";

        User sessUser = globalFunUtils.getLoggedInUserDetail();
        List<Standard> standardList = null;
        List<Standard> countStandardList = null;
        Map<String, Integer> params = new HashMap<String, Integer>();

        if(request.getParameter("rpp") != null)
            rpp = Integer.valueOf(request.getParameter("rpp"));

        if(request.getParameter("p")!=null) {
            p = Integer.valueOf(request.getParameter("p"));
            offset = rpp*(p-1);
        }

        if(request.getParameter("schoolid")!=null && request.getParameter("schoolid")!="") {
            //params.put("schoolid", Integer.valueOf(request.getParameter("schoolid")));
        }

        params.put("offset", offset);
        params.put("rpp", rpp);


        if(sessUser.getRole().equals("ROLE_SCHOOL_ADMIN")) {
            School school = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
            //params.put("schoolid", school.getSchoolId());
        }

        standardList = standardDao.getStandardUsingFilters(params, false);
        countStandardList = standardDao.getStandardUsingFilters(params, true);

        if(standardList != null && standardList.size()>0){
            retHtml = "<table class=\"table table-bordered responsive\">\n" +
                    "                <thead>\n" +
                    "                <tr>\n" +
                    "                    <th></th>\n" +
                    "                    <th>Standard Name</th>\n" +
                    "                    <th>Subjects</th>\n" +
                    "                    <th>Syllabus</th>\n" +
                    "                    <th>Event / Notification</th>\n" +
                    "                    <th>Tasks / Assignment</th>\n" +
                    "                    <th>&nbsp;</th>\n" +
                    "                </tr>\n" +
                    "                </thead>\n" +
                    "                <tbody>\n";

            for (Standard standard:standardList) {
                retHtml+="<tr><td></td>";
                retHtml+="<td>"+standard.getStandardName()+"</td>";
                retHtml+="<td class='td-action'><div class=\"btn-group\">\n" +
                        "                            <button class=\"btn dropdown-toggle\" data-toggle=\"dropdown\">Action <span class=\"caret\"></span></button>\n" +
                        "                            <ul class=\"dropdown-menu standardSubs\" data-id='"+standard.getStandardId()+"' id='subs-"+standard.getStandardId()+"'>\n" +
                        "                                <li><a href=\"#\">Action</a></li><li class=\"divider\"></li>\n" +
                        "                                <li><a class=\"\" href=\"#standardAddForm\" data-action='add' data-toggle=\"modal\">Add Standard</a></li>\n" +
                        "                                <li><a href=\"#standardManageContainer\" data-action='manage' data-toggle=\"modal\">Manage / Edit</a></li>\n" +
                        "                            </ul>\n" +
                        "                        </div></td>";
                retHtml+="<td class='td-action'><a href='/syllabus/content/?standardid="+standard.getStandardId()+"'>Add Syllabus</a></td>";
                retHtml+="<td class='td-action'><div class=\"btn-group\">\n" +
                        "                            <button class=\"btn dropdown-toggle\" data-toggle=\"dropdown\">Action <span class=\"caret\"></span></button>\n" +
                        "                            <ul class=\"dropdown-menu\">\n" +
                        "                                <li><a href=\"#\">Action</a></li>\n" +
                        "                                <li><a href=\"#\">Another action</a></li>\n" +
                        "                                <li><a href=\"#\">Something else here</a></li>\n" +
                        "                                <li class=\"divider\"></li>\n" +
                        "                                <li><a href=\"#\">Separated link</a></li>\n" +
                        "                            </ul>\n" +
                        "                        </div></td>";
                retHtml+="<td class='td-action'>"+standard.getStandardName()+"</td>";
                retHtml+="<td>"+"<a href=\"/standard/update?id="+standard.getStandardId()+"&p="+p+"&rpp="+rpp+"\" class=\"editStandard\"><span class=\"icon-edit\"></span></a></td>";
                retHtml+="</tr>";
            }

            retHtml+="</tbody>\n" +
                    "</table>";

            if(countStandardList.size() > standardList.size()){
                retHtml+= "<ul class=\"pager\" data-p='"+p+"'>\n";
                if(p==1)
                    retHtml+="<li class='disabled'><a href=\"#\">Previous</a></li>\n";
                else
                    retHtml+="<li class=''><a href=\"#\" data-p='"+(p-1)+"'>Previous</a></li>\n";

                if(p < countStandardList.size()/rpp)
                    retHtml+="<li><a href=\"javascript:void(0)\" data-p='"+(p+1)+"'>Next</a></li></ul>";
                else
                    retHtml+="<li class='disabled'><a href=\"javascript:void(0)\" data-p='"+(p+1)+"'>Next</a></li></ul>";


            }
        }
        return retHtml;
    }

    public Map<Integer, String> getStandardMap(){
        Map<Integer, String> tempList = new HashMap<Integer, String>();
        List<Standard> standardList = standardDao.getPrimaryStandard();
        if(standardList.size()>0){
            int i=0;
            for (Standard standard:standardList) {
                tempList.put(standard.getStandardId(), standard.getStandardName());
            }
        }
        return tempList;
    }
}
