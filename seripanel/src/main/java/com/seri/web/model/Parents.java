package com.seri.web.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by puneet on 30/04/16.
 */
@Entity
@Table(name = "PARENTS")
public class Parents implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "PARENT_ID")
    private int parentId;
    @Column(name = "PARENT_LOGIN_ID")
    private String parentLoginId;
    @Column(name = "STUDENT_ID")
    private int studentId;
    @Column(name = "F_NAME")
    private String fName;
    @Column(name = "M_NAME")
    private String mName;
    @Column(name = "L_NAME")
    private String lName;
    @Column(name = "DOB")
    private String dob;
    @Column(name = "PHOTO")
    private String photo;
    @Column(name = "GENDER")
    private String gender;

    @Column(name = "f_occupation")
    private String fOccupation;
    @Column(name = "f_designation")
    private String fDesignation;
    @Column(name = "f_qualification")
    private String fQualification;
    @Column(name = "f_mob_no")
    private String fMobNo;

    @Column(name = "m_occupation")
    private String mOccupation;
    @Column(name = "m_designation")
    private String mDesignation;
    @Column(name = "m_qualification")
    private String mQualification;
    @Column(name = "m_mob_no")
    private String mMobNo;

    @Column(name = "monthly_income")
    private String monthlyIncome;

    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    private String createdDate;
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private String lastUpdatedDate;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getParentLoginId() {
        return parentLoginId;
    }

    public void setParentLoginId(String parentLoginId) {
        this.parentLoginId = parentLoginId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getfOccupation() {
        return fOccupation;
    }

    public void setfOccupation(String fOccupation) {
        this.fOccupation = fOccupation;
    }

    public String getfDesignation() {
        return fDesignation;
    }

    public void setfDesignation(String fDesignation) {
        this.fDesignation = fDesignation;
    }

    public String getfQualification() {
        return fQualification;
    }

    public void setfQualification(String fQualification) {
        this.fQualification = fQualification;
    }

    public String getfMobNo() {
        return fMobNo;
    }

    public void setfMobNo(String fMobNo) {
        this.fMobNo = fMobNo;
    }

    public String getmOccupation() {
        return mOccupation;
    }

    public void setmOccupation(String mOccupation) {
        this.mOccupation = mOccupation;
    }

    public String getmDesignation() {
        return mDesignation;
    }

    public void setmDesignation(String mDesignation) {
        this.mDesignation = mDesignation;
    }

    public String getmQualification() {
        return mQualification;
    }

    public void setmQualification(String mQualification) {
        this.mQualification = mQualification;
    }

    public String getmMobNo() {
        return mMobNo;
    }

    public void setmMobNo(String mMobNo) {
        this.mMobNo = mMobNo;
    }

    public String getMonthlyIncome() {
        return monthlyIncome;
    }

    public void setMonthlyIncome(String monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
