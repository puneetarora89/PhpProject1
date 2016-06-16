package com.seri.web.utils.tags;

import com.seri.web.dao.HodDao;
import com.seri.web.dao.TeacherDao;
import com.seri.web.dao.daoImpl.HodDaoImpl;
import com.seri.web.dao.daoImpl.TeacherDaoImpl;
import com.seri.web.model.Hod;
import com.seri.web.model.Teacher;
import com.seri.web.utils.GlobalFunUtils;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 24/05/16.
 */
public class SchoolHodCountTag extends SimpleTagSupport {
    private String schoolId;
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    private HodDao hodDao = new HodDaoImpl();

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public void doTag() throws IOException {
        Map<String, Integer> params = new HashMap<String, Integer>();
        params.put("schoolid", Integer.parseInt(schoolId));
        List<Hod> hodList = hodDao.getAllHodByFilters(params, true);
        int hodCount = 0;
        if(hodList!=null)
            hodCount = hodList.size();
        JspWriter out = getJspContext().getOut();
        out.println(hodCount);
    }
}
