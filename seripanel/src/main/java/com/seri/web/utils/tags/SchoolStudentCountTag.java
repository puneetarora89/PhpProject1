package com.seri.web.utils.tags;

import com.seri.web.dao.StudentDao;
import com.seri.web.dao.daoImpl.StudentDaoImpl;
import com.seri.web.model.Student;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;

/**
 * Created by puneet on 13/04/16.
 */
public class SchoolStudentCountTag extends SimpleTagSupport {

    private String schoolId;
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private StudentDao studentDao = new StudentDaoImpl();

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void doTag() throws IOException {
        List<Student> studentList = studentDao.getStudentListUsingSchoolId(Integer.parseInt(schoolId));
        int stuCount = 0;
        if(studentList!=null)
            stuCount = studentList.size();
        JspWriter out = getJspContext().getOut();
        out.println(stuCount);
    }
}
