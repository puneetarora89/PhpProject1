package com.seri.web.dao.daoImpl;

import com.seri.web.dao.DepartmentDao;
import com.seri.web.model.Department;
import com.seri.web.utils.DbCon;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by puneet on 23/05/16.
 */
public class DepartmentDaoImpl implements DepartmentDao {
    @Override
    public List<Department> getDepartmentList() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Department c");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public Department getDepartmentUsingId(int id) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Department c where c.departmentId="+id);
        em.clear();
        if(ui.getResultList().size()>0)
            return (Department) ui.getResultList().get(0);
        else
            return null;
    }
}
