package com.seri.web.dao;

import com.seri.web.model.Standard;
import com.seri.web.model.Subject;

import java.util.List;

/**
 * Created by puneet on 29/05/16.
 */
public interface SubjectDao {

    public Boolean create(Subject subject);

    public Boolean update(Subject subject);

    public Subject getSubjectBySubjectId(int id);

    public List<Subject> getSubjectByStandardId(int id);

    public List<Subject> getSubjectByCompulsary(int flag);

    public Subject getSubjectByName(String name);
}
