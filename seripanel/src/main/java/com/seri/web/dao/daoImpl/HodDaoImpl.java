package com.seri.web.dao.daoImpl;

import com.seri.web.dao.HodDao;
import com.seri.web.model.Hod;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 24/05/16.
 */
public class HodDaoImpl implements HodDao {
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    @Override
    public boolean create(Hod hod) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        em.persist(hod);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Hod hod) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        Hod hodDetails = em.find(Hod.class, hod.getHodId());

        hodDetails.setfName(hod.getfName());
        hodDetails.setmName(hod.getmName());
        hodDetails.setlName(hod.getlName());
        hodDetails.setDob(hod.getDob());

        hodDetails.setGender(hod.getGender());
        hodDetails.setHodAddress(hod.getHodAddress());
        hodDetails.setHodSchoolId(hod.getHodSchoolId());
        hodDetails.setPhoto(hod.getPhoto());

        hodDetails.setDob(hod.getDob());
        hodDetails.setHodLoginId(hod.getHodLoginId());
        hodDetails.setHodDepartmentId(hod.getHodDepartmentId());

        hodDetails.setCreatedBy(hod.getCreatedBy());
        hodDetails.setCreatedDate(hod.getCreatedDate());
        hodDetails.setLastUpdatedBy(hod.getLastUpdatedBy());
        hodDetails.setLastUpdatedDate(hod.getLastUpdatedDate());


        em.getTransaction().commit();
        return true;
    }

    @Override
    public List<Hod> getAllHodByFilters(Map<String, Integer> params, boolean count) {
        EntityManager em = DbCon.getEntityManager();
        Query ui = null;
        if(params.containsKey("schoolid") && params.get("schoolid") != null)
            ui =  em.createQuery("select c from Hod c where c.hodSchoolId="+params.get("schoolid"));
        else
            ui =  em.createQuery("select c from Hod c");

        if(!count) {
            if(params.containsKey("offset") && params.get("offset") != null && params.containsKey("rpp") && params.get("rpp") != null)
                ui.setFirstResult(params.get("offset")).setMaxResults(params.get("rpp"));
        }

        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<Hod> getAllHod() {
        return null;
    }

    @Override
    public Hod getHodByHodId(int hodId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Hod c where c.hodId="+hodId);
        em.clear();
        if(ui.getResultList().size()>0)
            return (Hod) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public Hod getHodByUserId(int userId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Hod c where c.hodUserId="+userId);
        em.clear();
        if(ui.getResultList().size()>0)
            return (Hod) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public Hod getHodByLoginId(String loginId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Hod c where c.hodLoginId='"+loginId+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return (Hod) ui.getResultList().get(0);
        else
            return null;
    }
}
