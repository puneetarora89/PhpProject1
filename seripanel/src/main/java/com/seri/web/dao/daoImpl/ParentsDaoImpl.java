package com.seri.web.dao.daoImpl;

import com.seri.web.dao.ParentsDao;
import com.seri.web.model.Parents;
import com.seri.web.model.School;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * Created by puneet on 30/04/16.
 */
public class ParentsDaoImpl implements ParentsDao {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    @Override
    public boolean create(Parents parents) {
        try{
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            em.persist(parents);
            em.getTransaction().commit();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean update(Parents parents) {

        try {
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            Parents parents1 = em.find(Parents.class, parents.getParentId());
            parents1.setParentLoginId(parents.getParentLoginId());
            parents1.setfName(parents.getfName());
            parents1.setlName(parents.getlName());
            parents1.setmName(parents.getmName());

            parents1.setStudentId(parents.getStudentId());
            parents1.setDob(parents.getDob());
            parents1.setGender(parents.getGender());
            parents1.setPhoto(parents.getPhoto());

            parents1.setfOccupation(parents.getfOccupation());
            parents1.setfDesignation(parents.getfDesignation());
            parents1.setfQualification(parents.getfQualification());
            parents1.setfMobNo(parents.getfMobNo());

            parents1.setmOccupation(parents.getmOccupation());
            parents1.setmDesignation(parents.getmDesignation());
            parents1.setmQualification(parents.getmQualification());
            parents1.setmMobNo(parents.getmMobNo());

            parents1.setMonthlyIncome(parents.getMonthlyIncome());

            parents1.setLastUpdatedBy(parents.getLastUpdatedBy());
            parents1.setLastUpdatedDate(parents.getLastUpdatedDate());
            em.getTransaction().commit();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean delete(Parents parents) {
        return false;
    }

    @Override
    public Parents getProfileUsingLoginId(String LoginId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Parents c where c.parentLoginId='"+LoginId+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return (Parents) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public Parents getProfileUsingParentsId(int parentsId) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui =  em.createQuery("select c from Parents c where c.parentId="+parentsId);
            em.clear();
            if(ui.getResultList().size()>0)
                return (Parents) ui.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }
}
