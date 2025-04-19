/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author User
 */
public class MyResponse {

    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }
    
    private String statuscode;
    private String statusmessage;
    private Object data;
    
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

}
