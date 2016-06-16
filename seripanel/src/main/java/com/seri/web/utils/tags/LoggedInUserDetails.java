package com.seri.web.utils.tags;

import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by puneet on 22/05/16.
 */
public class LoggedInUserDetails extends SimpleTagSupport {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private String propName;

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public void doTag() throws IOException {
        User user = globalFunUtils.getLoggedInUserDetail();
        JspWriter out = getJspContext().getOut();
        if(propName.equals("name"))
            out.println(user.getfName()+" "+user.getmName()+" "+user.getlName());
        if(propName.equals("email"))
            out.println(user.getLogin());
    }
}
