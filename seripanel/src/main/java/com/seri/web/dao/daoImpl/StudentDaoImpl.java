package com.seri.web.dao.daoImpl;

import com.seri.web.dao.StudentDao;
import com.seri.web.model.Email;
import com.seri.web.model.Student;
import com.seri.web.model.Teacher;
import com.seri.web.utils.DbCon;
import com.seri.web.utils.GlobalFunUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Created by puneet on 28/04/16.
 */
public class StudentDaoImpl implements StudentDao {
    private GlobalFunUtils globalFunUtils = new GlobalFunUtils();
    @Override
    public boolean create(Student student) {
        try{
            EntityManager em = DbCon.getEntityManager();
            globalFunUtils.inActiveAllTransaction(em);
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean update(Student student) {
        EntityManager em = DbCon.getEntityManager();
        globalFunUtils.inActiveAllTransaction(em);
        em.getTransaction().begin();
        Student student1 = em.find(Student.class, student.getStudentId());

        student1.setCreatedBy(student.getCreatedBy());
        student1.setLastUpdatedBy(student.getLastUpdatedBy());
        student1.setLastUpdatedDate(student.getLastUpdatedDate());
        student1.setCreatedDate(student.getCreatedDate());
        student1.setParentLoginId(student.getParentLoginId());
        student1.setUserId(student.getUserId());
        student1.setDob(student.getDob());
        student1.setfName(student.getfName());
        student1.setGender(student.getGender());
        student1.setlName(student.getlName());
        student1.setmName(student.getmName());
        student1.setPhoto(student.getPhoto());
        student1.setStudentLoginId(student.getStudentLoginId());
        student1.setStuStandardId(student.getStuStandardId());
        student1.setStuSchoolId(student.getStuSchoolId());
        student1.setUserId(student.getUserId());
        student1.setBirthPlace(student.getBirthPlace());
        student1.setHobbies(student.getHobbies());
        student1.setBloodGroup(student.getBloodGroup());
        student1.setAdmissionDate(student.getAdmissionDate());
        student1.setApplicationNo(student.getApplicationNo());
        student1.setStream(student.getStream());
        student1.setSession(student.getSession());
        student1.setSection(student.getSection());
        student1.setNationality(student.getNationality());
        student1.setCategory(student.getCategory());
        student1.setReligion(student.getReligion());
        student1.setAddress(student.getAddress());
        student1.setCountry(student.getCountry());
        student1.setState(student.getState());
        student1.setCity(student.getCity());
        student1.setPincode(student.getPincode());
        student1.setMobNo(student.getMobNo());
        em.getTransaction().commit();

        return false;
    }

    @Override
    public Student getStudentUsingStudentId(int id) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Student c where c.studentId="+id);
        em.clear();
        if(ui.getResultList().size()>0)
            return (Student) ui.getResultList().get(0);
        else
            return null;
    }

    @Override
    public List<Student> getStudentListUsingSchoolId(int schoolId) {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Student c where c.stuSchoolId="+schoolId);
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<Student> getStudentWithoutParentProfile() {
        EntityManager em = DbCon.getEntityManager();
        Query ui =  em.createQuery("select c from Student c where c.parentLoginId is null or c.parentLoginId='' order by c.fName desc");
        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }

    @Override
    public List<Student> getStudentListUsingSchoolId(Map<String, Integer> params, boolean count) {
        EntityManager em = DbCon.getEntityManager();
        Query ui = null;
        if(params.containsKey("schoolid") && params.get("schoolid") != null)
            ui =  em.createQuery("select c from Student c where c.stuSchoolId="+params.get("schoolid"));
        else
            ui =  em.createQuery("select c from Student c");

        if(!count)
            ui.setFirstResult(params.get("offset")).setMaxResults(params.get("rpp"));

        em.clear();
        if(ui.getResultList().size()>0)
            return ui.getResultList();
        else
            return null;
    }
}
