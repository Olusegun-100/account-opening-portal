/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.account;

/**
 *
 * @author User
 */
public class AccountModel {

    /**
     * @return the cudId
     */
    public String getCudId() {
        return cudId;
    }

    /**
     * @param cudId the cudId to set
     */
    public void setCudId(String cudId) {
        this.cudId = cudId;
    }

    /**
     * @return the prodCode
     */
    public String getProdCode() {
        return prodCode;
    }

    /**
     * @param prodCode the prodCode to set
     */
    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    /**
     * @return the branchCode
     */
    public String getBranchCode() {
        return branchCode;
    }

    /**
     * @param branchCode the branchCode to set
     */
    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    /**
     * @return the marketedBy
     */
    public String getMarketedBy() {
        return marketedBy;
    }

    /**
     * @param marketedBy the marketedBy to set
     */
    public void setMarketedBy(String marketedBy) {
        this.marketedBy = marketedBy;
    }

    /**
     * @return the smsNo
     */
    public String getSmsNo() {
        return smsNo;
    }

    /**
     * @param smsNo the smsNo to set
     */
    public void setSmsNo(String smsNo) {
        this.smsNo = smsNo;
    }

    /**
     * @return the emailNo
     */
    public String getEmailNo() {
        return emailNo;
    }

    /**
     * @param emailNo the emailNo to set
     */
    public void setEmailNo(String emailNo) {
        this.emailNo = emailNo;
    }

    /**
     * @return the addedBy
     */
    public String getAddedBy() {
        return addedBy;
    }

    /**
     * @param addedBy the addedBy to set
     */
    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    /**
     * @return the accountNo
     */
    public String getAccountNo() {
        return accountNo;
    }

    /**
     * @param accountNo the accountNo to set
     */
    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }
    
    private String cudId;
    private String prodCode;
    private String branchCode;
    private String marketedBy;
    private String smsNo;
    private String emailNo;
    private String addedBy;
    private String accountNo;
    
    
    public String account_toString (){
        return "{"
                + "\"cudId\":\"" + getCudId()+ "\",\n"
                + "\"prodCode\":\"" + getProdCode()+ "\",\n"
                + "\"branchCode\":\"" + getBranchCode()+ "\",\n"
                + "\"marketedBy\":\"" + getMarketedBy()+ "\"\n"
                + "\"smsNo\":\"" + getSmsNo()+ "\",\n"
                + "\"emailNo\":\"" + getEmailNo()+ "\",\n"
                + "\"addedBy\":\"" + getAddedBy()+ "\",\n"
                + "\"accountNo\":\"" + getAccountNo()+ "\"\n"
            + "}";
    }
}
