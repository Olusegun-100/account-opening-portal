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
import com.gettable.AccountModel;
import com.gettable.MyResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DbConnect {

    public MyResponse getaccountDetails(String status, String staffId) {

        Connection con = null;
        ResultSet result = null;
        PreparedStatement stmt = null;

        String query = "SELECT " +
                        "address.first_name as fname, " +
                        "address.last_name as lname, " +
                        "address.phonenumber as phonenumber, " +
                        "address.general_id as customerId, " +
                        "address.email as email, " +
                        "accttypes.account_types as accounttype," +
                        "customer.acct_name as  companyName, customer.rcno as Rcno " +
                        "FROM actdb.address " +
                        "INNER JOIN actdb.customer ON address.general_id=customer.id " +
                        "INNER JOIN actdb.account ON customer.id=account.general_id " +
                        "INNER JOIN actdb.accttypes ON account.acct_type=accttypes.prodCode " +
                        "WHERE customer.status = '"+status+"' AND (staffId='"+staffId+"' OR approverId='"+staffId+"')";
//        String query = "SELECT "
//                + "address.first_name as fname, "
//                + "address.last_name as lname, "
//                + "address.phonenumber as phonenumber, "
//                + "address.general_id as customerId, "
//                + "address.email as email "
//                + "FROM actdb.address "
//                + "INNER JOIN actdb.customer ON address.general_id=customer.id "
//                + "WHERE customer.status = '"+status+"' AND (staffId='"+staffId+"' OR approverId='"+staffId+"')";

        System.out.println(query);
        try {

            List<AccountModel> lst = new ArrayList<>();
            MyResponse data = new MyResponse();

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            stmt = con.prepareStatement(query);
//            stmt.setString(1, status);

            result = stmt.executeQuery();

            while (result.next()) {
                
                System.out.println("nexted");;

                AccountModel acct = new AccountModel();
                acct.setFirstname(result.getString("fname"));
                acct.setLastname(result.getString("lname"));
                acct.setPhonenumber(result.getString("phonenumber"));
                acct.setEmail(result.getString("email"));
                acct.setCust_id(result.getString("customerId"));
                acct.setAccttypes(result.getString("accounttype"));
                acct.setCompanyname(result.getString("companyName"));
                acct.setRcno(result.getString("Rcno"));
//                acct.setCorparateType(result.getString("corparateaccounttype"));

                lst.add(acct);

            }
            
            System.out.println("List = "+lst);

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
