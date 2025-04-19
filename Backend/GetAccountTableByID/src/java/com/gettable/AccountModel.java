/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gettable;

/**
 *
 * @author User
 */
public class AccountModel {

    /**
     * @return the companyname
     */
    public String getCompanyname() {
        return companyname;
    }

    /**
     * @param companyname the companyname to set
     */
    public void setCompanyname(String companyname) {
        this.companyname = companyname;
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
     * @return the accttypes
     */
    public String getAccttypes() {
        return accttypes;
    }

    /**
     * @param accttypes the accttypes to set
     */
    public void setAccttypes(String accttypes) {
        this.accttypes = accttypes;
    }
    private String cust_id;
    private String firstname;
    private String lastname;
    private String phonenumber;
    private String email;
    private String accttypes;
    private String companyname;
    private String rcno;
    
        @Override
    public String toString (){
        return "{"
                + "\"cust_id\":\"" + cust_id + "\",\n"
                + "\"firstname\":\"" + firstname + "\",\n"
                + "\"lastname\":\""+ lastname + "\",\n"
                + "\"phonenumber\":\""+ phonenumber + "\",\n"
                + "\"email\":\""+ email + "\",\n"
                + "\"accttypes\":\""+ accttypes + "\",\n"
                + "\"companyname\":\""+ companyname + "\",\n"
                + "\"rcno\":\"" + rcno + "\"\n"
            + "}";
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
     * @return the phonenumber
     */
    public String getPhonenumber() {
        return phonenumber;
    }

    /**
     * @param phonenumber the phonenumber to set
     */
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
     * @return the cust_id
     */
    public String getCust_id() {
        return cust_id;
    }

    /**
     * @param cust_id the cust_id to set
     */
    public void setCust_id(String cust_id) {
        this.cust_id = cust_id;
    }

}
