/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import com.encryption.Encrypt;
import com.model.LoginModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class DbConnect {
    Connection con = null;
    ResultSet result;
    PreparedStatement stmt = null;
    public DbConnect(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
        } catch (Exception ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public LoginModel getSessionDetails(String sessionId){
        try {
            String query = "SELECT staff.roleId as ID,"
                + " staff.staffId as staffID,"
                + " staff.rolename as roleName,"
                + " staff.supervisorId as supervisorId "
                + "from actdb.staff where sessionId='"+sessionId+"'";
            
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            LoginModel log = new LoginModel();
            if(result.next()){
              
                log.setRoleId(result.getString("ID"));
                log.setRolename(result.getString("roleName"));
                log.setStaffId(result.getString("staffID"));
                log.setSupervisorId(result.getString("supervisorId"));
                return log;
            }

        } catch (Exception ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    public LoginModel OnLogin(Map<String, String> userdetail){
        
        

        try {
            
            String query = "SELECT staff.roleId as ID,"
                + " staff.staffId as staffID,"
                + " staff.rolename as roleName,"
                + " staff.sessionId as sessionId,"
                + " staff.supervisorId as supervisorId "
                + "from actdb.staff where username='"+userdetail.get("username")+"' AND password='"+userdetail.get("password")+"'";
            
            
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            LoginModel log = new LoginModel();
            if(result.next()){
//                String session = Base64.getEncoder().encodeToString(Encrypt.encryptAES(UUID.randomUUID().toString()));
//                String session =UUID.randomUUID();
                String session = java.util.UUID.randomUUID().toString();

                query = "UPDATE staff SET staff.sessionId='"+session+"' WHERE staff.staffId ='"+result.getString("staffID")+"'";
                stmt = con.prepareStatement(query);
                stmt.executeUpdate();
                
                log.setRoleId(result.getString("ID"));
                log.setRolename(result.getString("roleName"));
                log.setStaffId(result.getString("staffID"));
                log.setSupervisorId(result.getString("supervisorId"));
                log.setSessionId(session);
                return log;
            }

        } catch (Exception ex) {
            Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, "Error connecting to Oracle DB", ex);
            ex.printStackTrace();
        }
        return null;
    }
}