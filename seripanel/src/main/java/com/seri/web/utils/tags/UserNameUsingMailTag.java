package com.seri.web.utils.tags;

import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by puneet on 13/04/16.
 */
public class UserNameUsingMailTag extends SimpleTagSupport {

    private String loginId;
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private UserDaoImpl userDao = new UserDaoImpl();

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public void doTag() throws IOException {
        JspWriter out = getJspContext().getOut();
        if(loginId==null || loginId == "") {
            out.println("No User Found");
        } else {
            User user = userDao.getUserUsingEmail(loginId);
            out.println(user.getfName() +" "+ user.getlName());
        }

    }
}
