package com.seri.web.dao;

import com.seri.web.model.Department;

import java.util.List;

/**
 * Created by puneet on 23/05/16.
 */
public interface DepartmentDao {

    public List<Department> getDepartmentList();

    public Department getDepartmentUsingId(int id);
}
