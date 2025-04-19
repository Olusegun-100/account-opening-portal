/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corparate;

/**
 *
 * @author User
 */
public class TypeModel {

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
    
    private String id;
    private String acct_type;
    private String prodCode;
    
}
