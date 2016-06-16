package com.seri.web.utils.tags;

import com.seri.web.dao.StudentDao;
import com.seri.web.dao.TeacherDao;
import com.seri.web.dao.daoImpl.StudentDaoImpl;
import com.seri.web.dao.daoImpl.TeacherDaoImpl;
import com.seri.web.dao.daoImpl.UserDaoImpl;
import com.seri.web.model.Student;
import com.seri.web.model.Teacher;
import com.seri.web.model.User;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 13/04/16.
 */
public class SchoolTeacherCountTag extends SimpleTagSupport {

    private String schoolId;
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private TeacherDao teacherDao = new TeacherDaoImpl();

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void doTag() throws IOException {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("schoolid", Integer.parseInt(schoolId));
        List<Teacher> teacherList = teacherDao.getTeacherListUsingSchoolId(params, true);
        int teacherCount = 0;
        if(teacherList!=null)
            teacherCount = teacherList.size();
        JspWriter out = getJspContext().getOut();
        out.println(teacherCount);
    }
}
