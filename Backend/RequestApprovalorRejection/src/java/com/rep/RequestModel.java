/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rep;

/**
 *
 * @author User
 */
public class RequestModel {

    /**
     * @return the acct_officer
     */
    public String getAcct_officer() {
        return acct_officer;
    }

    /**
     * @param acct_officer the acct_officer to set
     */
    public void setAcct_officer(String acct_officer) {
        this.acct_officer = acct_officer;
    }
    
    private String id;
    private String decision;
    private String staffId;
    private String approverId;
    private String comment;
    private String despondent;
    private String acct_officer;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the decision
     */
    public String getDecision() {
        return decision;
    }

    /**
     * @param decision the decision to set
     */
    public void setDecision(String decision) {
        this.decision = decision;
    }

    /**
     * @return the staffId
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * @return the approverId
     */
    public String getApproverId() {
        return approverId;
    }

    /**
     * @param approverId the approverId to set
     */
    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the despondent
     */
    public String getDespondent() {
        return despondent;
    }

    /**
     * @param despondent the despondent to set
     */
    public void setDespondent(String despondent) {
        this.despondent = despondent;
    }
    
}
