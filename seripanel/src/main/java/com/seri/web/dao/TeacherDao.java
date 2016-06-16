package com.seri.web.dao;

import com.seri.web.model.Teacher;

import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 10/04/16.
 */
public interface TeacherDao {
    public boolean create(Teacher teacher);

    public boolean update(Teacher teacher);

    public Teacher getTeacherUsingTeacherId(int id);

    public Teacher getTeacherUsingLoginId(int email);

    public List<Teacher> getTeacherListUsingSchoolId(Map<String, Integer> params, boolean count);
}
