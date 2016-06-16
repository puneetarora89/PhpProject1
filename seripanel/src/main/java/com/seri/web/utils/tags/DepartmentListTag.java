package com.seri.web.utils.tags;

import com.seri.web.controller.SchoolController;
import com.seri.web.dao.DepartmentDao;
import com.seri.web.dao.UserDao;
import com.seri.web.dao.daoImpl.DepartmentDaoImpl;
import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.Department;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * Created by puneet on 23/05/16.
 */
public class DepartmentListTag extends SimpleTagSupport {

    private SchoolController schoolController = new SchoolController();
    private String ctrlName;
    private String ctrlId;
    private String ctrlClass;
    private String selectedDepartment;
    private UserDao userDao = new UserDaoImpl();
    private DepartmentDao departmentDao = new DepartmentDaoImpl();
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    public void setCtrlName(String ctrlName) {
        this.ctrlName = ctrlName;
    }

    public void setCtrlId(String ctrlId) {
        this.ctrlId = ctrlId;
    }

    public void setCtrlClass(String ctrlClass) {
        this.ctrlClass = ctrlClass;
    }

    public void setSelectedDepartment(String selectedDepartment) {
        this.selectedDepartment = selectedDepartment;
    }

    public void doTag() throws IOException {
        if (ctrlClass == null || ctrlClass == "")
            ctrlClass = "departmentList";

        if (ctrlId == null || ctrlId == "")
            ctrlId = "departmentList";

        if (ctrlName == null || ctrlName == "")
            ctrlName = "departmentList";

        if (selectedDepartment == null || selectedDepartment == "")
            selectedDepartment = "0";

        String ctrl = schoolController.getDepartmentListing(ctrlName,ctrlId,ctrlClass,selectedDepartment);

        JspWriter out = getJspContext().getOut();
        User sessUser = globalFunUtils.getLoggedInUserDetail();

        if(sessUser.getRole().equals("ROLE_HOD")) {
            Department department = departmentDao.getDepartmentUsingId(Integer.parseInt(selectedDepartment));
            //ctrl = "<span>"+department.getDepartmentName()+"</span><input type='hidden' id='departmentId' value='"+department.getDepartmentId()+"' />";
        }
        out.println(ctrl);

    }
}
