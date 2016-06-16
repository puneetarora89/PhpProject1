package com.seri.web.controller;

import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 07/04/16.
 */
public class UserController {
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    private UserDaoImpl userDao = new UserDaoImpl();

    public Boolean createUser(User user, HttpServletRequest request) {
        try {
            if(userDao.getUserUsingEmail(user.getLogin()) == null) {
                String dateTime = globalFunUtils.getDateTime();
                String passwordToken = globalFunUtils.getMd5Hex(user.getLogin() + user.getRole() + dateTime+".0");
                //System.out.println(user.getLogin() +" :: "+ user.getRole() +" :: "+ dateTime +" :: "+passwordToken);
                user.setCreatedDate(dateTime);
                user.setCreatedBy("admin");
                user.setLastUpdatedDate(dateTime);
                user.setLastUpdatedBy("admin");
                user.setFirstReset(0);
                user.setPasswordToken(passwordToken);
                user.setPassword(passwordToken);

                userDao.create(user);

                Map<String, String> map = new HashMap<String, String>();
                String siteUrl = globalFunUtils.getSiteUrl(request);

                map.put("to", user.getLogin());
                map.put("from", "noreply@seri.com");
                map.put("subject", "SERI ACCOUNT CREATED");
                map.put("body", "Create your password using below link <br>" + siteUrl + "/createpassword/?token=" + passwordToken);
                //globalFunUtils.sendMail(map);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public User checkPasswordToken(HttpServletRequest request) {
        try {
            String passwordToken = request.getParameter("token");
            List<User> unregisterUser = userDao.getUnregisterUser();
            for (User userList : unregisterUser) {
                String userToken = globalFunUtils.getMd5Hex(userList.getLogin() + userList.getRole() + userList.getCreatedDate());
                if(userToken.equals(passwordToken))
                    return userList;
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    public User createPassword(HttpServletRequest request){
        try {
            String email = request.getParameter("login");
            Map<String, String> loggedInUser = globalFunUtils.getLoggedInUserDetails();
            User user = userDao.getUserUsingEmail(email);
            user.setPassword(globalFunUtils.getMd5Hex(request.getParameter("password")));
            user.setLastUpdatedBy(loggedInUser.get("userEmail"));
            user.setLastUpdatedDate(globalFunUtils.getDateTime());
            user.setFirstReset(1);
            userDao.update(user);
            return user;
        } catch(Exception e) {
            return null;
        }
    }

    public User setFormAttributes(User dbUser, User formUser){
        dbUser.setfName(formUser.getfName());
        dbUser.setmName(formUser.getmName());
        dbUser.setlName(formUser.getlName());
        dbUser.setDob(formUser.getDob());
        //dbUser.setGender(formUser.getGender());
        dbUser.setFirstReset(2);
        dbUser.setLastUpdatedDate(globalFunUtils.getDateTime());

        return dbUser;
    }
}
