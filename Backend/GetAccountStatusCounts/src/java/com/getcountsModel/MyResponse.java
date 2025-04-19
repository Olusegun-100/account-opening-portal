/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.getcountsModel;

/**
 *
 * @author User
 */
public class MyResponse {

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * @return the rependingcounts
     */
    public String getRependingcounts() {
        return rependingcounts;
    }

    /**
     * @param rependingcounts the rependingcounts to set
     */
    public void setRependingcounts(String rependingcounts) {
        this.rependingcounts = rependingcounts;
    }
    
    private String statuscode;
    private String statusmessage;
    private String pendingcounts;
    private String rejectedcounts;
    private String approvedcounts;
    private String rependingcounts;
    private String data;
    
    
    @Override
    public String toString (){
        return "{"
                + "\"statuscode\":\"" + statuscode + "\",\n"
                + "\"statusmessage\":\"" + statusmessage + "\",\n"
                + "\"pendingcounts\":\""+ pendingcounts + "\",\n"
                + "\"rejectedcounts\":\""+ rejectedcounts + "\",\n"
                + "\"approvedcounts\":\""+ approvedcounts + "\",\n"
                + "\"rependingcounts\":\"" + rependingcounts + "\"\n"
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
     * @return the pendingcounts
     */
    public String getPendingcounts() {
        return pendingcounts;
    }

    /**
     * @param pendingcounts the pendingcounts to set
     */
    public void setPendingcounts(String pendingcounts) {
        this.pendingcounts = pendingcounts;
    }

    /**
     * @return the rejectedcounts
     */
    public String getRejectedcounts() {
        return rejectedcounts;
    }

    /**
     * @param rejectedcounts the rejectedcounts to set
     */
    public void setRejectedcounts(String rejectedcounts) {
        this.rejectedcounts = rejectedcounts;
    }

    /**
     * @return the approvedcounts
     */
    public String getApprovedcounts() {
        return approvedcounts;
    }

    /**
     * @param approvedcounts the approvedcounts to set
     */
    public void setApprovedcounts(String approvedcounts) {
        this.approvedcounts = approvedcounts;
    }

}