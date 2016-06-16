package com.seri.web.utils.tags;

import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.model.School;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by puneet on 25/04/16.
 */
public class AllActiveSchoolListTag extends SimpleTagSupport {

    private String ctrlName;
    private String selectedSchool;
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    SchoolDao schoolDao = new SchoolDaoImpl();

    public void setCtrlName(String ctrlName) {
        this.ctrlName = ctrlName;
    }

    public void setSelectedSchool(String selectedSchool) {
        this.selectedSchool = selectedSchool;
    }

    public void doTag() throws IOException {
        User sessUser = globalFunUtils.getLoggedInUserDetail();
        String selectSchoolCtrl = "";
        if(sessUser.getRole().equals("ROLE_SUP_ADMIN") || sessUser.getRole().equals("ROLE_SUB_ADMIN")) {
            List<School> schoolList = schoolDao.getAllActiveSchool();
            selectSchoolCtrl = "<select name='" + ctrlName + "' id='" + ctrlName + "'>";
            if (schoolList != null) {
                selectSchoolCtrl += "<option value='0'>-SELECT SCHOOL-</option>";
                for (School school : schoolList) {
                    selectSchoolCtrl += "<option value='" + school.getSchoolId() + "' " + ((String.valueOf(school.getSchoolId()).equals(selectedSchool)) ? "selected='selected'" : "") + ">" + school.getSchoolName() + "</option>";
                }
            } else {
                selectSchoolCtrl += "<option value='0'>No Active School Found</option>";
            }
        } else {
            School school = schoolDao.getSchoolUsingPrincipalEmail(sessUser.getLogin());
            if(school!=null){
                selectSchoolCtrl="<input type='hidden' name='"+ctrlName+"' id='" + ctrlName + "' value='"+school.getSchoolId()+"' /><span>"+school.getSchoolName()+"</span>";
            }
        }
        selectSchoolCtrl += "</select>";
        JspWriter out = getJspContext().getOut();
        out.println(selectSchoolCtrl);
    }
}
