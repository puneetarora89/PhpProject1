package com.seri.web.dao.daoImpl;

import com.seri.web.dao.CountryDao;
import com.seri.web.model.Country;
import com.seri.web.model.Email;
import com.seri.web.utils.DbCon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by puneet on 21/05/16.
 */
public class CountryDaoImpl implements CountryDao {
    @Override
    public List<Country> getAllCountries() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Country c order by c.countryName asc");
        em.clear();
        if(ui.getResultList().size()>0)
            return (List<Country>) ui.getResultList();
        else
            return null;
    }
}
