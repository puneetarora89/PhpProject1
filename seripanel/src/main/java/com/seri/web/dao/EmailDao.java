package com.seri.web.dao;

import com.seri.web.model.Email;
import com.seri.web.model.Teacher;

import java.util.List;

/**
 * Created by puneet on 10/04/16.
 */
public interface EmailDao {
    public boolean create(Email emailContent);

    public boolean update(Email emailContent);

    public boolean delete(Email emailContent);

    public Email getEmailUsingId(int id);

    public List<Email> getEmailUsingTo(String email);

    public List<Email> getEmailUsingFrom(String email);
}
