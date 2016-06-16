package com.seri.web.dao.daoImpl;

import com.seri.web.dao.StandardDao;
import com.seri.web.model.Standard;
import com.seri.web.model.Student;
import com.seri.web.model.Teacher;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 28/05/16.
 */
public class STandardDaoImpl implements StandardDao {
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    @Override
    public Boolean create(Standard standard) {
        try{
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            em.persist(standard);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean update(Standard standard) {
        try {
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            Standard standard1 = em.find(Standard.class, standard.getStandardId());
            standard1.setStatus(standard.getStatus());
            standard1.setStandardName(standard.getStandardName());
            standard1.setSchoolId(standard.getSchoolId());
            standard1.setPid(standard.getPid());
            standard1.setLastUpdatedBy(standard.getLastUpdatedBy());
            standard1.setLastUpdatedDate(standard.getLastUpdatedDate());
            em.getTransaction().commit();
        }
        catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public Standard getStandardById(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Standard c where c.standardId=" + id);
            em.clear();
            if (ui.getResultList().size() > 0)
                return (Standard) ui.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Standard> getPrimaryStandard() {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Standard c where c.pid=0");
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
    public List<Standard> getSecoundryStandardBySchoolId(int schoolId) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Standard c where c.schoolId="+schoolId);
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
    public List<Standard> getSecoundryStandardByPrimaryStandardlId(int standardId) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Standard c where c.pid="+standardId);
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
    public List<Standard> getStandardUsingFilters(Map<String, Integer> params, boolean count) {
        EntityManager em = DbCon.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Standard> criteriaQuery = criteriaBuilder.createQuery(Standard.class);
        Standard standard;

        Root<Standard> standardRoot = criteriaQuery.from(Standard.class);
        List<Predicate> predList = new LinkedList<Predicate>();
        Predicate p = null;
        if(params.containsKey("schoolid") && params.get("schoolid") != null) {
            p = criteriaBuilder.equal(standardRoot.get("schoolId"), params.get("schoolid"));
            predList.add(p);
        }


        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);

        criteriaQuery.where(predArray);

        CriteriaQuery<Standard> select = criteriaQuery.select(standardRoot);
        TypedQuery<Standard> typedQuery = em.createQuery(select);

        if(!count)
            typedQuery.setFirstResult(params.get("offset")).setMaxResults(params.get("rpp"));

        List<Standard> resultlist = typedQuery.getResultList();
        return resultlist;
    }
}
