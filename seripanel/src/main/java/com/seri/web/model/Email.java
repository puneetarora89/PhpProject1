package com.seri.web.model;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.io.Serializable;

/**
 * Created by puneet on 11/04/16.
 */

@Entity
@Table(name = "email")
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private int emailId;
    @Column(name = "REPLIED_MAIL_ID")
    private String repliedMailId;
    @Column(name = "TO_ID")
    private String toId;
    @Column(name = "FROM_ID")
    private String fromId;
    @Column(name = "BCC")
    private String bccId;
    @Column(name = "CC")
    private String ccId;
    @Column(name = "subject")
    private String subject;
    @Column(name = "MSG")
    private String msg;
    @Column(name = "READ_STATUS")
    private int readStatus;
    @Column(name = "REPLIED_STATUS")
    private int repliedStatus;
    @Column(name = "SENDER_DEL_STATUS")
    private int senderDelStatus;
    @Column(name = "GROUP_MAIL_ID")
    private String groupMailId;
    @Column(name = "SENT_DATE")
    private String sentDate;
    @Column(name = "READ_DATE")
    private String readDate;
    @Column(name = "SENDER_DEL_DATE")
    private String senderDelDate;
    @Column(name = "RECIVER_DEL_STATUS")
    private int reciverDelStatus;
    @Column(name = "RECIVER_DEL_DATE")
    private String reciverDelDate;

    public int getEmailId() {
        return emailId;
    }

    public void setEmailId(int emailId) {
        this.emailId = emailId;
    }

    public String getRepliedMailId() {
        return repliedMailId;
    }

    public void setRepliedMailId(String repliedMailId) {
        this.repliedMailId = repliedMailId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getBccId() {
        return bccId;
    }

    public void setBccId(String bccId) {
        this.bccId = bccId;
    }

    public String getCcId() {
        return ccId;
    }

    public void setCcId(String ccId) {
        this.ccId = ccId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public int getRepliedStatus() {
        return repliedStatus;
    }

    public void setRepliedStatus(int repliedStatus) {
        this.repliedStatus = repliedStatus;
    }

    public String getGroupMailId() {
        return groupMailId;
    }

    public void setGroupMailId(String groupMailId) {
        this.groupMailId = groupMailId;
    }

    public String getSentDate() {
        return sentDate;
    }

    public void setSentDate(String sentDate) {
        this.sentDate = sentDate;
    }

    public String getReadDate() {
        return readDate;
    }

    public void setReadDate(String readDate) {
        this.readDate = readDate;
    }

    public int getSenderDelStatus() {
        return senderDelStatus;
    }

    public void setSenderDelStatus(int senderDelStatus) {
        this.senderDelStatus = senderDelStatus;
    }

    public String getSenderDelDate() {
        return senderDelDate;
    }

    public void setSenderDelDate(String senderDelDate) {
        this.senderDelDate = senderDelDate;
    }

    public int getReciverDelStatus() {
        return reciverDelStatus;
    }

    public void setReciverDelStatus(int reciverDelStatus) {
        this.reciverDelStatus = reciverDelStatus;
    }

    public String getReciverDelDate() {
        return reciverDelDate;
    }

    public void setReciverDelDate(String reciverDelDate) {
        this.reciverDelDate = reciverDelDate;
    }
}
