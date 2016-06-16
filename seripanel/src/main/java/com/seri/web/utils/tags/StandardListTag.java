package com.seri.web.utils.tags;

import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.StandardDao;
import com.seri.web.dao.daoImpl.STandardDaoImpl;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.model.School;
import com.seri.web.model.Standard;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by puneet on 25/04/16.
 */
public class StandardListTag extends SimpleTagSupport {

    private String ctrlName;
    private String selectedStandard;
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private StandardDao standardDao = new STandardDaoImpl();
    SchoolDao schoolDao = new SchoolDaoImpl();

    public void setCtrlName(String ctrlName) {
        this.ctrlName = ctrlName;
    }

    public void setSelectedStandard(String selectedStandard) {
        this.selectedStandard = selectedStandard;
    }

    public void doTag() throws IOException {
//selectedStandard="1";
        if(selectedStandard==null || selectedStandard=="" || Integer.parseInt(selectedStandard)<1)
        {
            selectedStandard="0";
        }
        List<Standard> standardList = standardDao.getPrimaryStandard();
        String selectCtrl = "<select name='"+ctrlName+"' id='"+ctrlName+"'><option value='0'>-SELECT STANDARD-</option>";
        if(standardList.size()>0) {
            for (Standard standard:standardList) {
                selectCtrl += "<option value='"+standard.getStandardId()+"' "+((Integer.parseInt(selectedStandard)==standard.getStandardId())?"selected='selected'":"")+">"+standard.getStandardName()+"</option>";
            }

        }
        selectCtrl += "</select>";
        JspWriter out = getJspContext().getOut();
        out.println(selectCtrl);
    }
}
