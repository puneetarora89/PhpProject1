package com.seri.web.dao;

import com.seri.web.model.School;

import java.util.List;

/**
 * Created by puneet on 23/04/16.
 */
public interface SchoolDao {

    public boolean create(School school);

    public boolean update(School school);

    public boolean delete(School school);

    public School getSchoolUsingEmail(String emailId);

    public School getSchoolUsingPrincipalEmail(String emailId);

    public School getSchoolNotUsingSameEmail(String emailId, int schoolId);

    public School getSchoolUsingId(int id);

    public School getSchoolUsingContact(String contact);

    public School getSchoolNotUsingSameSchoolContact(String contact, int schoolId);

    public List<School> getAllSchool();

    public List<School> getAllActiveSchool();

    public List<School> getSchoolUsingAddedBy(String addedBy);

    public List<School> getSchoolUsingCountry(String country);

    public List<School> getSchoolUsingState(String state);

    public List<School> getSchoolwithoutPrincipal();

    public List<School> getActiveSchools();
}
