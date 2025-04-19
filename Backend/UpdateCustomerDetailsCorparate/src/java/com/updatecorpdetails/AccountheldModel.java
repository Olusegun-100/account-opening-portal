/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.updatecorpdetails;

/**
 *
 * @author User
 */
public class AccountheldModel {

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
     * @return the nameANDaddress
     */
    public String getNameANDaddress() {
        return nameANDaddress;
    }

    /**
     * @param nameANDaddress the nameANDaddress to set
     */
    public void setNameANDaddress(String nameANDaddress) {
        this.nameANDaddress = nameANDaddress;
    }

    /**
     * @return the accountName
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * @param accountName the accountName to set
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * @return the accountNumber
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * @param accountNumber the accountNumber to set
     */
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
    private String nameANDaddress;
    private String accountName;
    private String accountNumber;
    private String status;
    private String general_id;
}
