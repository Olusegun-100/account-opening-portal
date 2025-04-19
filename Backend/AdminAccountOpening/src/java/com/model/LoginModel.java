/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import com.encryption.Encrypt;

/**
 *
 * @author User
 */
public class LoginModel {

    /**
     * @return the sessionId
     */
    public String getSessionId() {
        return sessionId;
    }

    /**
     * @param sessionId the sessionId to set
     */
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
    private String staffId;
    private String rolename;
    private String supervisorId;
    private String roleId;
    private String sessionId;

    
    @Override
    public String toString (){
        return "{"
                + "\"staffId\":\"" + staffId + "\",\n"
                + "\"rolename\":\"" + rolename + "\",\n"
                + "\"supervisorId\":\""+ supervisorId + "\",\n"
                + "\"sessionId\":\""+ sessionId + "\",\n"
                + "\"roleId\":\"" + roleId + "\"\n"
            + "}";
    }
    
    /**
     * @return the staffId
     */
    public String getStaffId() {
        return staffId;
    }

    /**
     * @param staffId the staffId to set
     */
    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    /**
     * @return the rolename
     */
    public String getRolename() {
        return rolename;
    }

    /**
     * @param rolename the rolename to set
     */
    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    /**
     * @return the supervisorId
     */
    public String getSupervisorId() {
        return supervisorId;
    }

    /**
     * @param supervisorId the supervisorId to set
     */
    public void setSupervisorId(String supervisorId) {
        this.supervisorId = supervisorId;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
    
}
