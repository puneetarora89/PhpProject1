package com.seri.web.controller;

import com.seri.web.dao.UserDao;
import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by puneet on 01/05/16.
 */
@Controller
@RequestMapping(value = "supadmin")
public class SuperAdminController {
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private UserDao userDao = new UserDaoImpl();

}
