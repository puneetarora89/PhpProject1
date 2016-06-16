package com.seri.web.dao;

import com.seri.web.model.User;

import java.util.List;

/**
 * Created by puneet on 04/04/16.
 */
public interface UserDao {

    public void create(User userDetails);

    public User getAuth(String email, String password);

    public User getUserUsingEmail(String email);

    public List getUnregisterUser();

    public void update(User userDetails);
}
