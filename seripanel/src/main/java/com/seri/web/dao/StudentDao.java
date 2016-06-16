package com.seri.web.dao;

import com.seri.web.model.School;
import com.seri.web.model.Student;
import com.seri.web.model.Teacher;

import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 28/04/16.
 */
public interface StudentDao {

    public boolean create(Student student);

    public boolean update(Student student);

    public Student getStudentUsingStudentId(int id);

    public List<Student> getStudentListUsingSchoolId(int schoolId);

    public List<Student> getStudentWithoutParentProfile();

    public List<Student> getStudentListUsingSchoolId(Map<String, Integer> params, boolean count);
}
