package com.seri.web.dao.daoImpl;

import com.seri.web.dao.TeacherDao;
import com.seri.web.model.Teacher;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 10/04/16.
 */
public class TeacherDaoImpl implements TeacherDao {

    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();

    @Override
    public boolean create(Teacher teacher) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        em.persist(teacher);
        em.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Teacher teacher) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        Teacher teacherDetails = em.find(Teacher.class, teacher.getTeacherId());

        teacherDetails.settLoginId(teacher.gettLoginId());

        teacherDetails.setTeacherSchoolId(teacher.getTeacherSchoolId());
        teacherDetails.setTeacherLoginId(teacher.getTeacherLoginId());
        teacherDetails.setTeacherStandardId(teacher.getTeacherStandardId());
        teacherDetails.setfName(teacher.getfName());
        teacherDetails.setmName(teacher.getmName());
        teacherDetails.setlName(teacher.getlName());
        teacherDetails.setDob(teacher.getDob());
        teacherDetails.setGender(teacher.getGender());
        teacherDetails.setPhoto(teacher.getPhoto());

        teacherDetails.settLastUpdateDate(teacher.gettLastUpdateDate());
        teacherDetails.settLastUpdateBy(teacher.gettLastUpdateBy());
        teacherDetails.settCategory(teacher.gettCategory());
        teacherDetails.settDeptName(teacher.gettDeptName());
        teacherDetails.settDescription(teacher.gettDescription());
        teacherDetails.settDesignation(teacher.gettDesignation());
        teacherDetails.settExperiance(teacher.gettExperiance());
        teacherDetails.settGradMetadata(teacher.gettGradMetadata());
        teacherDetails.settHomeAdress(teacher.gettHomeAdress());
        teacherDetails.settJoiningDate(teacher.gettJoiningDate());
        teacherDetails.settLangSpeak(teacher.gettLangSpeak());
        teacherDetails.settLangWrite(teacher.gettLangWrite());
        teacherDetails.settLastOrgMetadata(teacher.gettLastOrgMetadata());
        teacherDetails.settMobNo(teacher.gettMobNo());
        teacherDetails.settNationality(teacher.gettNationality());
        teacherDetails.settOthLang(teacher.gettOthLang());
        teacherDetails.settOthQualificationMetadata(teacher.gettOthQualificationMetadata());
        teacherDetails.settOthSkills(teacher.gettOthSkills());
        teacherDetails.settOthSpecialization(teacher.gettOthSpecialization());
        teacherDetails.settPersonalEmail(teacher.gettPersonalEmail());
        teacherDetails.settPgMetadata(teacher.gettPgMetadata());
        teacherDetails.settPhNo(teacher.gettPhNo());
        teacherDetails.settRef1Metadata(teacher.gettRef1Metadata());
        teacherDetails.settRef2Metadata(teacher.gettRef2Metadata());
        teacherDetails.settSecMarks(teacher.gettSecMarks());
        teacherDetails.settSenSecMarks(teacher.gettSenSecMarks());
        teacherDetails.settSpecialization(teacher.gettSpecialization());


        em.getTransaction().commit();
        return true;
    }

    @Override
    public Teacher getTeacherUsingTeacherId(int id) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Teacher c where c.teacherId="+id);
        em.clear();
        if(ui.getResultList().size()>0)
            return (Teacher) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public Teacher getTeacherUsingLoginId(int loginId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Teacher c where c.tLoginId="+loginId);
        em.clear();
        if(ui.getResultList().size()>0)
            return (Teacher) ui.getResultList().get(0);
        else
            return null;
    }


    public List<Teacher> getTeacherListUsingSchoolId(Map<String, Integer> params, boolean count, boolean temp) {
        EntityManager em = DbCon.getEntityManager();
        Query ui = null;
        if(params.containsKey("schoolid") && params.get("schoolid") != null)
            ui =  em.createQuery("select c from Teacher c where c.teacherSchoolId="+params.get("schoolid"));
        else
            ui =  em.createQuery("select c from Teacher c");

        if(!count)
            ui.setFirstResult(params.get("offset")).setMaxResults(params.get("rpp"));

        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<Teacher> getTeacherListUsingSchoolId(Map<String, Integer> params, boolean count) {
        EntityManager em = DbCon.getEntityManager();
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Teacher> criteriaQuery = criteriaBuilder.createQuery(Teacher.class);
        Teacher teacher;

        Root<Teacher> teacherRoot = criteriaQuery.from(Teacher.class);
        List<Predicate> predList = new LinkedList<Predicate>();
        Predicate p = null;
        if(params.containsKey("schoolid") && params.get("schoolid") != null) {
            p = criteriaBuilder.equal(teacherRoot.get("teacherSchoolId"), params.get("schoolid"));
            predList.add(p);
        }

        if(params.containsKey("departmentId") && params.get("departmentId") != null) {
            p = criteriaBuilder.equal(teacherRoot.get("tDeptName"), params.get("departmentId"));
            predList.add(p);
        }

        Predicate[] predArray = new Predicate[predList.size()];
        predList.toArray(predArray);

        criteriaQuery.where(predArray);

        CriteriaQuery<Teacher> select = criteriaQuery.select(teacherRoot);
        TypedQuery<Teacher> typedQuery = em.createQuery(select);

        if(!count)
            typedQuery.setFirstResult(params.get("offset")).setMaxResults(params.get("rpp"));

        List<Teacher> resultlist = typedQuery.getResultList();
        return resultlist;
    }

}
