/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acctofficer;

/**
 *
 * @author User
 */
public class AccountOfficerModel {

    /**
     * @return the Id
     */
    public String getId() {
        return Id;
    }

    /**
     * @param Id the Id to set
     */
    public void setId(String Id) {
        this.Id = Id;
    }

    /**
     * @return the accountOfficerName
     */
    public String getAccountOfficerName() {
        return accountOfficerName;
    }

    /**
     * @param accountOfficerName the accountOfficerName to set
     */
    public void setAccountOfficerName(String accountOfficerName) {
        this.accountOfficerName = accountOfficerName;
    }

    /**
     * @return the branch_code
     */
    public String getBranch_code() {
        return branch_code;
    }

    /**
     * @param branch_code the branch_code to set
     */
    public void setBranch_code(String branch_code) {
        this.branch_code = branch_code;
    }
    
    private String Id;
    private String accountOfficerName;
    private String branch_code;
    
}
