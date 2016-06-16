package com.seri.web.dao;

import com.seri.web.model.Standard;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 28/05/16.
 */
public interface StandardDao {

    public Boolean create(Standard standard);

    public Boolean update(Standard standard);

    public Standard getStandardById(int id);

    public List<Standard> getPrimaryStandard();

    public List<Standard> getSecoundryStandardBySchoolId(int schoolId);

    public List<Standard> getSecoundryStandardByPrimaryStandardlId(int standardId);

    public List<Standard> getStandardUsingFilters(Map<String, Integer> params, boolean count);
}
