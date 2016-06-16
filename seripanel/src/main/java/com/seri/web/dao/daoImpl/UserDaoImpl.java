package com.seri.web.dao.daoImpl;

import com.seri.web.dao.UserDao;
import com.seri.web.model.User;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by puneet on 04/04/16.
 */
public class UserDaoImpl implements UserDao {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    @Override
    public void create(User userDetails) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        em.persist(userDetails);
        em.getTransaction().commit();em.clear();
    }

    @Override
    public User getAuth(String email, String password) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from User c where c.login='"+email+"' and c.password = '"+password+"' and c.firstReset!=0");
        return (User) ui.getResultList().get(0);
    }

    @Override
    public User getUserUsingEmail(String email) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from User c where c.login='"+email+"'");
        em.clear();
        if(ui.getResultList().size()>0)
            return (User) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public List<User> getUnregisterUser() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from User c where c.firstReset=0 and c.status=1");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public void update(User userDetails) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        User userDetails1 = em.find(User.class, userDetails.getUserId());
        userDetails1.setPassword(userDetails.getPassword());
        userDetails1.setLogin(userDetails.getLogin());
        userDetails1.setLastUpdatedDate(userDetails.getLastUpdatedDate());
        userDetails1.setLastUpdatedBy(userDetails.getLastUpdatedBy());
        userDetails1.setDob(userDetails.getDob());
        userDetails1.setRole(userDetails.getRole());
        userDetails1.setfName(userDetails.getfName());
        userDetails1.setlName(userDetails.getlName());
        userDetails1.setmName(userDetails.getmName());
        userDetails1.setStatus(userDetails.getStatus());
        userDetails1.setFirstReset(userDetails.getFirstReset());
        userDetails1.setPhoto(userDetails.getPhoto());
        userDetails1.setGender(userDetails.getGender());
        userDetails1.setPasswordToken(userDetails.getPasswordToken());
        em.getTransaction().commit();
    }
}
