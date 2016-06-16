package com.seri.web.dao.daoImpl;

import com.seri.web.dao.EmailDao;
import com.seri.web.model.Email;
import com.seri.web.model.Teacher;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by puneet on 11/04/16.
 */
public class EmailDaoImpl implements EmailDao {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    @Override
    public boolean create(Email emailContent) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        em.persist(emailContent);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Email emailContent) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        Email emailContent1 = em.find(Email.class, emailContent.getEmailId());
        emailContent1.setBccId(emailContent.getBccId());
        emailContent1.setCcId(emailContent.getCcId());
        emailContent1.setEmailId(emailContent.getEmailId());
        emailContent1.setFromId(emailContent.getFromId());
        emailContent1.setGroupMailId(emailContent.getGroupMailId());
        emailContent1.setMsg(emailContent.getMsg());
        emailContent1.setReadDate(emailContent.getReadDate());
        emailContent1.setReadStatus(emailContent.getReadStatus());
        emailContent1.setReciverDelDate(emailContent.getReciverDelDate());
        emailContent1.setReciverDelStatus(emailContent.getReciverDelStatus());
        emailContent1.setRepliedMailId(emailContent.getRepliedMailId());
        emailContent1.setToId(emailContent.getToId());
        emailContent1.setSubject(emailContent.getSubject());
        emailContent1.setSentDate(emailContent.getSentDate());
        emailContent1.setSenderDelStatus(emailContent.getSenderDelStatus());
        emailContent1.setSenderDelDate(emailContent.getSenderDelDate());
        emailContent1.setRepliedStatus(emailContent.getRepliedStatus());
        em.getTransaction().commit();
        return false;
    }

    @Override
    public boolean delete(Email teacher) {
        return false;
    }

    @Override
    public Email getEmailUsingId(int id) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Email c where c.id="+id);
        em.clear();
        if(ui.getResultList().size()>0)
            return (Email) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public List<Email> getEmailUsingTo(String email) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Email c where c.toId='"+email+"' and c.reciverDelStatus=0 order by c.sentDate desc");
        em.clear();
        if(ui.getResultList().size()>0)
            return (List<Email>) ui.getResultList();
        else
            return null;
    }

    @Override
    public List<Email> getEmailUsingFrom(String email) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Email c where c.fromId='"+email+"' and c.senderDelStatus=0 order by c.sentDate desc");
        em.clear();
        if(ui.getResultList().size()>0)
            return (List<Email>) ui.getResultList();
        else
            return null;
    }
}
