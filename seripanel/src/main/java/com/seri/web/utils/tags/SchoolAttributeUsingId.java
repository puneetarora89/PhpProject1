package com.seri.web.utils.tags;

import com.seri.web.dao.SchoolDao;
import com.seri.web.dao.daoImpl.SchoolDaoImpl;
import com.seri.web.model.School;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by puneet on 22/05/16.
 */
public class SchoolAttributeUsingId extends SimpleTagSupport {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private SchoolDao schoolDao = new SchoolDaoImpl();
    private String propName;
    private String schoolId;

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void doTag() throws IOException {
        School school = schoolDao.getSchoolUsingId(Integer.parseInt(schoolId));
        JspWriter out = getJspContext().getOut();
        if(propName.equals("name"))
            out.println(school.getSchoolName());
        if(propName.equals("email"))
            out.println(school.getSchoolEmail());
    }
}
