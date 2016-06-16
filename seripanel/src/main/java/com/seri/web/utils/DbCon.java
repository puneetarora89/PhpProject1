package com.seri.web.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by puneet on 31/03/16.
 */
public class DbCon {
    private static EntityManager entityManager = null;

    public static EntityManager getEntityManager() {
        if(entityManager==null){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("haysDbCon");
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

}
