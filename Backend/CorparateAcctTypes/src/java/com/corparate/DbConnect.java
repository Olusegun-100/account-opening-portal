/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corparate;

import com.corparate.MyResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.corparate.TypeModel;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DbConnect {

    public MyResponse getaccoType() {

        Connection con = null;
        ResultSet result = null;
        PreparedStatement stmt = null;

        String query = "SELECT id as id, account_types as accttype, prodCode as prodcode FROM actdb.accttypes where category= 'corparate'";

        try {

            List<TypeModel> lst = new ArrayList<>();
            MyResponse data = new MyResponse();

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            stmt = con.prepareStatement(query);
//            stmt.setString(1, status);

            result = stmt.executeQuery();

            while (result.next()) {

                TypeModel type = new TypeModel();
                type.setId(result.getString("id"));
                type.setAcct_type(result.getString("accttype"));
                type.setProdCode(result.getString("prodCode"));
                lst.add(type);

            }

            data.setData(lst);
            data.setStatuscode("00");
            data.setStatusmessage("Succesful");
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
