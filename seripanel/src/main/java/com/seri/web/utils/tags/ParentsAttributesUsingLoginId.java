package com.seri.web.utils.tags;

import com.seri.web.dao.ParentsDao;
import com.seri.web.dao.daoImpl.ParentsDaoImpl;
import com.seri.web.model.Parents;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by puneet on 28/05/16.
 */
public class ParentsAttributesUsingLoginId extends SimpleTagSupport {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private ParentsDao parentsDao = new ParentsDaoImpl();
    private String propName;
    private String loginId;

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void doTag() throws IOException {
        Parents parents = parentsDao.getProfileUsingLoginId(loginId);
        JspWriter out = getJspContext().getOut();
        if(propName.equals("name"))
            out.println(parents.getfName()+" "+parents.getmName()+" "+parents.getlName());
        if(propName.equals("email"))
            out.println(parents.getParentLoginId());
    }

}
