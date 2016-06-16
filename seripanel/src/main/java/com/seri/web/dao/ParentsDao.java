package com.seri.web.dao;

import com.seri.web.model.Parents;
import com.seri.web.model.School;

/**
 * Created by puneet on 30/04/16.
 */
public interface ParentsDao {

    public boolean create(Parents parents);

    public boolean update(Parents parents);

    public boolean delete(Parents parents);

    public Parents getProfileUsingLoginId(String LoginId);

    public Parents getProfileUsingParentsId(int parentsId);

}
