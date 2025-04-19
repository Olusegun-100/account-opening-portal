/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acctofficer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.acctofficer.AccountOfficerModel;
import com.acctofficer.MyResponse;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class DbConnect {

    public MyResponse getaccountOfficer(String branch_code) {

        Connection con = null;
        ResultSet result = null;
        PreparedStatement stmt = null;

        String query = "SELECT id as Id,branch_officername as branch_officer, branch_code as branch_code from actdb.branch_officer where branch_code ='" +branch_code+"'";
        try {

            List<AccountOfficerModel> lst = new ArrayList<>();
            MyResponse data = new MyResponse();

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=gims");
            stmt = con.prepareStatement(query);
//            stmt.setString(1, status);

            result = stmt.executeQuery();

            while (result.next()) {

                AccountOfficerModel officer = new AccountOfficerModel();
                officer.setId(result.getString("Id"));
                officer.setAccountOfficerName(result.getString("branch_officer"));
                officer.setBranch_code(result.getString("branch_code"));

                lst.add(officer);
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
