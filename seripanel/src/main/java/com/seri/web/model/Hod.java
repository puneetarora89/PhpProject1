package com.seri.web.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by puneet on 24/05/16.
 */
@Entity
@Table(name = "HOD")
public class Hod implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "HOD_ID")
    private int hodId;
    @Column(name = "HOD_USER_ID")
    private int hodUserId;
    @Column(name = "HOD_LOGIN_ID")
    private String hodLoginId;
    @Column(name = "HOD_SCHOOL_ID")
    private int hodSchoolId;
    @Column(name = "DEPARTMENT_ID")
    private int hodDepartmentId;
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
    @Column(name = "ADDRESS")
    private String hodAddress;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    private String createdDate;
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private String lastUpdatedDate;

    public int getHodId() {
        return hodId;
    }

    public void setHodId(int hodId) {
        this.hodId = hodId;
    }

    public int getHodUserId() {
        return hodUserId;
    }

    public void setHodUserId(int hodUserId) {
        this.hodUserId = hodUserId;
    }

    public String getHodLoginId() {
        return hodLoginId;
    }

    public void setHodLoginId(String hodLoginId) {
        this.hodLoginId = hodLoginId;
    }

    public int getHodSchoolId() {
        return hodSchoolId;
    }

    public void setHodSchoolId(int hodSchoolId) {
        this.hodSchoolId = hodSchoolId;
    }

    public int getHodDepartmentId() {
        return hodDepartmentId;
    }

    public void setHodDepartmentId(int hodDepartmentId) {
        this.hodDepartmentId = hodDepartmentId;
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

    public String getHodAddress() {
        return hodAddress;
    }

    public void setHodAddress(String hodAddress) {
        this.hodAddress = hodAddress;
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
