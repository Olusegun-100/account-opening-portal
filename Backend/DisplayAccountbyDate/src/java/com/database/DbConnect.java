/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.model.AccountModel;
import com.model.MyResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DbConnect {

    public MyResponse filterByDate(String staffId, String status,String startdate, String enddate, int page) {

        Connection con = null;
        ResultSet result = null;
        PreparedStatement stmt = null;
//        page *= size;

        String query = "SELECT " +
                "address.first_name as fname, " +
                "address.last_name as lname, " +
                "address.phonenumber as phonenumber, " +
                "address.general_id as customerId, " +
                "address.email as email " +
                "FROM actdb.address " +
                "INNER JOIN actdb.customer ON address.general_id = customer.id " +
                "WHERE customer.status ='"+status+"'" +
                "and customer.staffId='"+staffId+"'" +
                "and customer.dateopen BETWEEN '"+startdate+"' AND  '"+enddate+"'";
//                " ORDER BY dateopen DESC LIMIT = '"+page+"'";
                
        System.out.println("date range query = "+query);
        try {

            List<AccountModel> lst = new ArrayList<>();
            MyResponse data = new MyResponse();

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            stmt = con.prepareStatement(query);
            result = stmt.executeQuery();

            while (result.next()) {

                AccountModel acct = new AccountModel();
                acct.setFirstname(result.getString("fname"));
                acct.setLastname(result.getString("lname"));
                acct.setPhonenumber(result.getString("phonenumber"));
                acct.setEmail(result.getString("email"));
                acct.setCust_id(result.getString("customerId"));

                lst.add(acct);
            }
            
            data.setData(lst);
            data.setStatuscode("00");
            data.setStatusmessage("Ok");
            return data;

        } catch (Exception ex) {

            MyResponse data = new MyResponse();
            //Logger.getLogger(DbConnect.class.getName()).log(Level.SEVERE, "Error connecting to MySql DB", ex);
            ex.printStackTrace();
            data.setStatuscode("96");
            data.setStatusmessage("System malfunction, please contact the adminstrator");
            return data;
        }

    }

}
