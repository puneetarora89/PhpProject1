package com.seri.web.dao.daoImpl;

import com.seri.web.dao.SubjectDao;
import com.seri.web.model.Standard;
import com.seri.web.model.Subject;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by puneet on 29/05/16.
 */
public class SubjectDaoImpl implements SubjectDao {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    @Override
    public Boolean create(Subject subject) {
        try{
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            em.persist(subject);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Subject subject) {
        try {
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            Subject subject1 = em.find(Subject.class, subject.getSubjectId());

            subject1.setStatus(subject.getStatus());
            subject1.setLastUpdatedBy(subject.getLastUpdatedBy());
            subject1.setLastUpdatedDate(subject.getLastUpdatedDate());
            subject1.setIsCompulsary(subject.getIsCompulsary());
            subject1.setSubjectName(subject.getSubjectName());
            subject1.setStandardId(subject.getStandardId());

            em.getTransaction().commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Subject getSubjectBySubjectId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Subject c where c.subjectId=" + id);
            em.clear();
            if (ui.getResultList().size() > 0)
                return (Subject) ui.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Subject> getSubjectByStandardId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Subject c where c.standardId="+id);
            em.clear();
            if (ui.getResultList().size() > 0)
                return ui.getResultList();
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Subject> getSubjectByCompulsary(int flag) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Subject c where c.isCompulsary="+flag);
            em.clear();
            if (ui.getResultList().size() > 0)
                return ui.getResultList();
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Subject getSubjectByName(String name) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Subject c where c.subjectName='"+name+"'");
            em.clear();
            if (ui.getResultList().size() > 0)
                return (Subject) ui.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
