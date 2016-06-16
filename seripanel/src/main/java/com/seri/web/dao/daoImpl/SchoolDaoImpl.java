package com.seri.web.dao.daoImpl;

import com.seri.web.dao.SchoolDao;
import com.seri.web.model.School;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by puneet on 23/04/16.
 */
public class SchoolDaoImpl implements SchoolDao {
    GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    @Override
    public boolean create(School school) {
        try{
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            em.persist(school);
            em.getTransaction().commit();
            return false;
        } catch (Exception e) {
            return true;
        }

    }

    @Override
    public boolean update(School school) {
        try {
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            School school1 = em.find(School.class, school.getSchoolId());
            school1.setSchoolName(school.getSchoolName());
            school1.setPrincipalUserLogin(school.getPrincipalUserLogin());
            school1.setSchoolAddress(school.getSchoolAddress());
            school1.setSchoolContact(school.getSchoolContact());
            school1.setSchoolEmail(school.getSchoolEmail());
            school1.setStatus(school.getStatus());
            school1.setSchoolAddress(school.getSchoolAddress());
            school1.setLastUpdatedBy(school.getLastUpdatedBy());
            school1.setLastUpdatedDate(school.getLastUpdatedDate());
            school1.setCountry(school.getCountry());
            school1.setState(school.getState());
            em.getTransaction().commit();
            return false;
        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean delete(School school) {
        return false;
    }

    @Override
    public School getSchoolUsingEmail(String emailId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.schoolEmail='"+emailId+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return (School) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public School getSchoolUsingPrincipalEmail(String emailId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.principalUserLogin='"+emailId+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return (School) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public School getSchoolNotUsingSameEmail(String emailId, int schoolId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.schoolEmail='"+emailId+"' and c.schoolId != "+schoolId);
        em.clear();
        if(ui.getResultList().size()>0)
            return (School) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public School getSchoolUsingId(int id) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.schoolId="+id);
        em.clear();
        if(ui.getResultList().size()>0)
            return (School) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public School getSchoolUsingContact(String contact) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.schoolContact='"+contact+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return (School) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public School getSchoolNotUsingSameSchoolContact(String contact, int schoolId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.schoolContact='"+contact+"' and c.schoolId != "+schoolId);
        em.clear();
        if(ui.getResultList().size()>0)
            return (School) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public List<School> getAllSchool() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<School> getAllActiveSchool() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.status=1");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<School> getSchoolUsingAddedBy(String addedBy) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.createdBy='"+addedBy+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<School> getSchoolUsingCountry(String country) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.country='"+country+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<School> getSchoolUsingState(String state) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.state='"+state+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<School> getSchoolwithoutPrincipal() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.status!=3 and (c.principalUserLogin is null or c.principalUserLogin='')");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<School> getActiveSchools() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from School c where c.status=1 and c.principalUserLogin!=''");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }
}
