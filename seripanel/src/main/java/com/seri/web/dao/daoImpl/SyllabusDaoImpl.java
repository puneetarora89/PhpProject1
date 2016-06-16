package com.seri.web.dao.daoImpl;

import com.seri.web.dao.SyllabusDao;
import com.seri.web.model.School;
import com.seri.web.model.Standard;
import com.seri.web.model.Syllabus;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by puneet on 29/05/16.
 */
public class SyllabusDaoImpl implements SyllabusDao {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    @Override
    public Boolean create(Syllabus syllabus) {
        try{
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            em.persist(syllabus);
            em.getTransaction().commit();
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public Boolean update(Syllabus syllabus) {
        try {
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            Syllabus syllabus1 = em.find(Syllabus.class, syllabus.getTaskId());

            syllabus1.setSchoolId(syllabus.getSchoolId());
            syllabus1.setSubjectId(syllabus.getSubjectId());
            syllabus1.setStandardId(syllabus.getStandardId());
            syllabus1.setStudentId(syllabus.getStudentId());
            syllabus1.setpId(syllabus.getpId());

            syllabus1.setContent(syllabus.getContent());
            syllabus1.setTaskDueDate(syllabus.getTaskDueDate());
            syllabus1.setTaskName(syllabus.getTaskName());

            syllabus1.setLastUpdatedBy(syllabus.getLastUpdatedBy());
            syllabus1.setLastUpdatedDate(syllabus.getLastUpdatedDate());


            em.getTransaction().commit();
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Syllabus getSyllabusBySyllabusId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Syllabus c where c.taskId="+id);
            em.clear();
            if (ui.getResultList().size() > 0)
                return (Syllabus) ui.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Syllabus> getSyllabusBySyllabusSchoolId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Syllabus c where c.schoolId="+id);
            em.clear();
            if (ui.getResultList().size() > 0)
                return ui.getResultList();
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Syllabus> getAllSyllabusBySyllabusStandardId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Syllabus c where c.standardId="+id);
            em.clear();
            if (ui.getResultList().size() > 0)
                return ui.getResultList();
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Syllabus getRecentSyllabusBySyllabusStandardId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Syllabus c where c.standardId="+id+" order by createdDate desc");
            em.clear();
            if (ui.getResultList().size() > 0)
                return (Syllabus) ui.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Syllabus> getAllSyllabusBySyllabusStudentId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Syllabus c where c.studentId="+id);
            em.clear();
            if (ui.getResultList().size() > 0)
                return ui.getResultList();
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Syllabus getRecentSyllabusBySyllabusStudentId(int id) {
        try {
            EntityManager em = DbCon.getEntityManager();
            Query ui = em.createQuery("select c from Syllabus c where c.studentId="+id+" order by createdDate desc");
            em.clear();
            if (ui.getResultList().size() > 0)
                return (Syllabus) ui.getResultList().get(0);
            else
                return null;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Syllabus getSyllabusBySyllabusFilters(Map<String, String> params){
        EntityManager em = DbCon.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Syllabus> criteriaQuery = criteriaBuilder.createQuery(Syllabus.class);

        Root<Syllabus> syllabusRoot = criteriaQuery.from(Syllabus.class);
        List<Predicate> predList = new LinkedList<Predicate>();
        Predicate p = null;
        if(params.containsKey("schoolId") && params.get("schoolId") != null) {
            p = criteriaBuilder.equal(syllabusRoot.get("schoolId"), params.get("schoolId"));
            predList.add(p);
        }

        if(params.containsKey("subjectId") && params.get("subjectId") != null) {
            p = criteriaBuilder.equal(syllabusRoot.get("subjectId"), params.get("subjectId"));
            predList.add(p);
        }

        if(params.containsKey("standardId") && params.get("standardId") != null) {
            p = criteriaBuilder.equal(syllabusRoot.get("standardId"), params.get("standardId"));
            predList.add(p);
        }

        if(params.containsKey("pId") && params.get("pId") != null) {
            p = criteriaBuilder.equal(syllabusRoot.get("pId"), params.get("pId"));
            predList.add(p);
        }

        if(params.containsKey("studentId") && params.get("studentId") != null) {
            p = criteriaBuilder.equal(syllabusRoot.get("studentId"), params.get("studentId"));
            predList.add(p);
        }

        if(params.containsKey("syllabusDueDate") && params.get("syllabusDueDate") != null) {

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = null;
            try {
                startDate = df.parse(params.get("syllabusDueDate"));
            } catch (ParseException e) {
                e.printStackTrace();
            }

            p = criteriaBuilder.greaterThanOrEqualTo(syllabusRoot.<Date>get("taskDueDate"), startDate);
            predList.add(p);
        }


        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);

        criteriaQuery.where(predArray);

        CriteriaQuery<Syllabus> select = criteriaQuery.select(syllabusRoot);
        TypedQuery<Syllabus> typedQuery = em.createQuery(select);

        List<Syllabus> resultList = typedQuery.getResultList();
        if(resultList.size()>0)
            return resultList.get(0);
        else
            return null;
    }
}


