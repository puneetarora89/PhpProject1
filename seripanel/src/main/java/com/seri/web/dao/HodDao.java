package com.seri.web.dao;

import com.seri.web.model.Hod;

import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 24/05/16.
 */
public interface HodDao {
    public boolean create(Hod hod);

    public boolean update(Hod hod);

    public List<Hod> getAllHodByFilters(Map<String, Integer> params, boolean count);

    public List<Hod> getAllHod();

    public Hod getHodByHodId(int hodId);

    public Hod getHodByUserId(int userId);

    public Hod getHodByLoginId(String loginId);
}
