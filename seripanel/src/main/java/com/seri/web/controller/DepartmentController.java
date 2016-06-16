package com.seri.web.controller;

import com.seri.web.dao.DepartmentDao;
import com.seri.web.dao.daoImpl.DepartmentDaoImpl;
import com.seri.web.utils.GlobalFunUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by puneet on 24/05/16.
 */
@Controller
@RequestMapping(value = "department")
public class DepartmentController {
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private DepartmentDao departmentDao = new DepartmentDaoImpl();

}
