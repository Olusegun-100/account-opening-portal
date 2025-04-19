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
public class AccountModel {
    
        
        private String id; 
        private String status ;
        private String user_id; 
        private String acct_description; 
        private String acct_no; 
        private String acct_type; 
        private String currency_code; 

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
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    /**
     * @return the acct_description
     */
    public String getAcct_description() {
        return acct_description;
    }

    /**
     * @param acct_description the acct_description to set
     */
    public void setAcct_description(String acct_description) {
        this.acct_description = acct_description;
    }

    /**
     * @return the acct_no
     */
    public String getAcct_no() {
        return acct_no;
    }

    /**
     * @param acct_no the acct_no to set
     */
    public void setAcct_no(String acct_no) {
        this.acct_no = acct_no;
    }

    /**
     * @return the acct_type
     */
    public String getAcct_type() {
        return acct_type;
    }

    /**
     * @param acct_type the acct_type to set
     */
    public void setAcct_type(String acct_type) {
        this.acct_type = acct_type;
    }

    /**
     * @return the currency_code
     */
    public String getCurrency_code() {
        return currency_code;
    }

    /**
     * @param currency_code the currency_code to set
     */
    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
        
 
    
}
