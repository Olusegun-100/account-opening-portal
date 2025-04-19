/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acct.models;

/**
 *
 * @author User
 */
public class ContactPersonModel {

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
     * @return the fullname
     */
    public String getFullname() {
        return fullname;
    }

    /**
     * @param fullname the fullname to set
     */
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    /**
     * @return the jobtitle
     */
    public String getJobtitle() {
        return jobtitle;
    }

    /**
     * @param jobtitle the jobtitle to set
     */
    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the mobilenumber
     */
    public String getMobilenumber() {
        return mobilenumber;
    }

    /**
     * @param mobilenumber the mobilenumber to set
     */
    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    /**
     * @return the officeaddress
     */
    public String getOfficeaddress() {
        return officeaddress;
    }

    /**
     * @param officeaddress the officeaddress to set
     */
    public void setOfficeaddress(String officeaddress) {
        this.officeaddress = officeaddress;
    }

    /**
     * @return the general_id
     */
    public String getGeneral_id() {
        return general_id;
    }

    /**
     * @param general_id the general_id to set
     */
    public void setGeneral_id(String general_id) {
        this.general_id = general_id;
    }
    
    private String id;
    private String fullname;
    private String jobtitle;
    private String email;
    private String mobilenumber;
    private String officeaddress;
    private String general_id;
}
