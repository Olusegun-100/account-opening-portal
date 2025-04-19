/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gettable;

import java.util.List;

/**
 *
 * @author User
 */
public class MyResponse {
    
    private String statuscode;
    private String statusmessage;
    private List<AccountModel> data;

    
    @Override
    public String toString (){
        return "{"
                + "\"statuscode\":\"" + statuscode + "\",\n"
                + "\"statusmessage\":\"" + statusmessage + "\",\n"
                + "\"data\":"+ data + "\n"
            + "}";
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
     * @return the data
     */
    public List<AccountModel> getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(List<AccountModel> data) {
        this.data = data;
    }

    /**
     * @return the data
     */
  
        
}
