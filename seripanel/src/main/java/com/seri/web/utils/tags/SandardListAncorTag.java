package com.seri.web.utils.tags;

import com.seri.web.dao.StandardDao;
import com.seri.web.dao.daoImpl.STandardDaoImpl;
import com.seri.web.model.Standard;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by puneet on 04/06/16.
 */
public class SandardListAncorTag extends SimpleTagSupport {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private StandardDao standardDao = new STandardDaoImpl();

    public void doTag() throws IOException {
        List<Standard> standardList = standardDao.getPrimaryStandard();
        JspWriter out = getJspContext().getOut();
        String outPut = "";
        if(standardList.size()>0)
        {
            for (Standard standard:standardList) {
                outPut="<a href='/syllabus/content/?standardid="+standard.getStandardId()+"'>"+standard.getStandardName()+"</a>";
            }
        } else {
            outPut = "No Standard Found in Record";
        }
        out.println(outPut);
    }
}
