/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corparate;

import java.util.List;


/**
 *
 * @author User
 */
public class MyResponse {

    /**
     * @return the statusmessage
     */
    public String getStatusmessage() {
        return statusmessage;
    }

    /**
     * @param statusmessage the statusmessage to set
     */
    public void setStatusmessage(String statusmessage) {
        this.statusmessage = statusmessage;
    }

    /**
     * @return the statuscode
     */
    public String getStatuscode() {
        return statuscode;
    }

    /**
     * @param statuscode the statuscode to set
     */
    public void setStatuscode(String statuscode) {
        this.statuscode = statuscode;
    }

    /**
     * @return the data
     */
    public List<TypeModel> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<TypeModel> data) {
        this.data = data;
    }
    
    private String statusmessage;
    private String statuscode;
    private List<TypeModel> data;
    
}
