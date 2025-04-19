/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acct.models;

import javax.xml.bind.annotation.XmlRootElement;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author User
 */
@XmlRootElement
@JsonSerialize(include=JsonSerialize.Inclusion.ALWAYS)
public class CustomerModel {
        private String id;
        private String acct_name;
        private String approvaluser_id;
        private String customer_id;
        private String dateopen;
        private String firstname;
        private String full_name;
        private String lastname;
        private String middlename;
        private String rcno;
        private String timestamp;
        private String tin;
//        primary key (id)

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
     * @return the acct_name
     */
    public String getAcct_name() {
        return acct_name;
    }

    /**
     * @param acct_name the acct_name to set
     */
    public void setAcct_name(String acct_name) {
        this.acct_name = acct_name;
    }

    /**
     * @return the approvaluser_id
     */
    public String getApprovaluser_id() {
        return approvaluser_id;
    }

    /**
     * @param approvaluser_id the approvaluser_id to set
     */
    public void setApprovaluser_id(String approvaluser_id) {
        this.approvaluser_id = approvaluser_id;
    }

    /**
     * @return the customer_id
     */
    public String getCustomer_id() {
        return customer_id;
    }

    /**
     * @param customer_id the customer_id to set
     */
    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    /**
     * @return the dateopen
     */
    public String getDateopen() {
        return dateopen;
    }

    /**
     * @param dateopen the dateopen to set
     */
    public void setDateopen(String dateopen) {
        this.dateopen = dateopen;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the full_name
     */
    public String getFull_name() {
        return full_name;
    }

    /**
     * @param full_name the full_name to set
     */
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * @return the middlename
     */
    public String getMiddlename() {
        return middlename;
    }

    /**
     * @param middlename the middlename to set
     */
    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    /**
     * @return the rcno
     */
    public String getRcno() {
        return rcno;
    }

    /**
     * @param rcno the rcno to set
     */
    public void setRcno(String rcno) {
        this.rcno = rcno;
    }

    /**
     * @return the timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the tin
     */
    public String getTin() {
        return tin;
    }

    /**
     * @param tin the tin to set
     */
    public void setTin(String tin) {
        this.tin = tin;
    }
}
