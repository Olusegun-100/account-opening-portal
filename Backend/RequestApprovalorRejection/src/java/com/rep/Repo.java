/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rep;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author User
 */
public class Repo {
    Connection con = null; 
    ResultSet rs = null;
    PreparedStatement stmt = null;
    
    public Repo() throws Exception{
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=gims");
    }
    
    public HashMap<String, String> staffDetails(String staffId){
        
        String query = "SELECT * FROM staff WHERE staffId ='"+staffId+"' ";
        try {
           stmt = con.prepareStatement(query);
           HashMap<String , String> data = new HashMap();
           ResultSet rst = stmt.executeQuery();
           
            if(rst.next()){
               data.put("roleId", String.valueOf(rst.getInt("roleId")));
               data.put("staffId", rst.getString("staffId"));
               data.put("supervisorId", rst.getString("supervisorId"));
               data.put("roleName", rst.getString("rolename"));
               data.put("roleId", rst.getString("roleId"));
            }
            
            return data;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public HashMap<String, String> customerStatus(String custId){
        String query = "SELECT customer.status as status,"
                + " customer.customer_id as customer_Id," +
                  " address.first_name, address.last_name, "
                + "address.email from actdb.address "
                + "INNER JOIN actdb.customer ON general_id=customer.id where customer.id='"+custId+"'";
//        String query = "SELECT customer.status as status, customer.customer_id as customer_Id, addresss.first_name, addresss.last_name, addresss.email INNER JOIN actdb.customer ON general_id=customer.id where id='"+custId+"'";

        HashMap<String, String> data = new HashMap<>();
        try {
           stmt = con.prepareStatement(query);

           ResultSet rst = stmt.executeQuery();
           if(rst.next()){
               data.put("full_name", rst.getString("first_name")+" "+rst.getString("last_name"));
               data.put("status", rst.getString("status"));
               data.put("email", rst.getString("email"));
           }
           
        }catch(Exception e){
            e.printStackTrace();
        } 
        return data;
    }
    
    public String StaffId(String custId){
        String query = "SELECT staffId from actdb.customer where id='"+custId+"'";

        try {
           stmt = con.prepareStatement(query);

           ResultSet rst = stmt.executeQuery();
           if(rst.next()){
               return rst.getString("staffId");
           }
           return null;
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    public String ApproverId(String custId){
        String query = "SELECT approverId from actdb.customer WHERE id='"+custId+"'";

        try {
           stmt = con.prepareStatement(query);

           ResultSet rst = stmt.executeQuery();
           if(rst.next()){
               return rst.getString("approverId");
           }
           return null;
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    
    public void initiateRequest(String approverId,String staffId, String id, String decision) throws Exception{
        String date= LocalDateTime.now().toString();
        String updateStatus = "UPDATE customer SET status= ?, staffId= ?, initiated_date= ?, approverId= ? WHERE id = ?";
        System.out.println("query = "+updateStatus);
        stmt = con.prepareStatement(updateStatus);
        stmt.setString(1, (decision.equalsIgnoreCase("approve")) ? "pending" :decision);
        stmt.setString(2, staffId);
        stmt.setString(3, date);
        stmt.setString(4, approverId);
        stmt.setString(5, id);
//        stmt.setString(6, null );
        
        stmt.executeUpdate();
    }
    
    public void approveRequest(String approverId,String staffId, String id, String status, String cusId) throws Exception{
        String date= LocalDateTime.now().toString();
        String updateStatus = "UPDATE customer SET status = ?, approved_date= ?, staffId =?, customer_id =? WHERE id = '"+id+"' and approverId = '"+approverId+"' ";
        stmt = con.prepareStatement(updateStatus);
        stmt.setString(1, status);
        stmt.setString(2, date);
        stmt.setString(3, approverId);
        stmt.setString(4, cusId);
        stmt.executeUpdate();
    }
    
    public void updateAccountNumber(String acctNum, String id) throws Exception{
        String updateAcc= "UPDATE account SET acct_no = ? WHERE general_id = '"+id+"'";
        stmt = con.prepareStatement(updateAcc);
        stmt.setString(1, acctNum);
       
        stmt.executeUpdate();
    }
    
    public void fullrejectionOfRequest(String approverId,String staffId, String Id, String decision) throws Exception{
        String date= LocalDateTime.now().toString();
        String updateStatus = "UPDATE customer SET status = ?, approved_date= ? WHERE status = 'pending' AND approverId = staffId ";
                
        stmt = con.prepareStatement(updateStatus);
        stmt.setString(1, decision);
        stmt.setString(2, date);
        stmt.executeUpdate();
    }
    
    public String genAccNum(){
//        prevList<String> prevData = new ArrayL
//        AtomicBoolean gen = new AtomicBoolean(true);
        String accNum= "";

//        while(gen.get()){
//            String id = "";
//            gen.set(false);
            for (int i = 0; i < 10; i++) {
                int k = (new Random().nextInt(9));
                accNum+=k;
            }
//            for (String prevId: prevData) {
//                if (prevId.equalsIgnoreCase(alpha)){
//                    gen.set(true);
//                    accNum= "";
//                }
//            }
//        }
        return "Thank you for creating an ITMB account,your ITMB account number is: " + accNum;
    }
//    
    public void halfrejectionOfRequest(String approverId,String staffId, String Id, String decision) throws Exception{
        String date= LocalDateTime.now().toString();
        String updateStatus = "UPDATE customer SET status = ?, approved_date= ? WHERE status = 'pending' and approverId = staffId ";
                
        stmt = con.prepareStatement(updateStatus);
        stmt.setString(1, decision);
        stmt.setString(2, date);
        stmt.executeUpdate();
    }
    
    public String ApproverEmail(String supervisorId){
        String query = "SELECT email from actdb.staff WHERE staffId='"+supervisorId+"'";

        try {
           stmt = con.prepareStatement(query);

           ResultSet rst = stmt.executeQuery();
           if(rst.next()){
               return rst.getString("email");
           }
           return null;
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    public void updateOfficer(String officer_name,String id) throws Exception{
        
                    
        RequestModel req = new RequestModel();
            String upd = "update customer SET account_officer=? where id =?";
            
            stmt = con.prepareStatement(upd);
            
//            psmt.setString(1, data.getAddress_description());
//            stmt.setString(1, req.getAcct_officer());            
            stmt.setString(1, officer_name);            
            stmt.setString(2, id);            
            stmt.executeUpdate();
          }
    
    
    public HashMap<String, Object> AccountNumber(String content) throws IOException{
        
            try {
//               ProcedureModel proceduremodel = new ObjectMapper().readValue(content,ProcedureModel.class);            
//               URL procedure_url= new URL(url);
               URL procedure_url= new URL("http://app.infinitytrustmortgagebank.com:8080/CreateCustomerAccountProcedures/webresources/procedure");
               HttpURLConnection procedure_conn = (HttpURLConnection) procedure_url.openConnection();
               
               procedure_conn.setRequestMethod("POST");
               procedure_conn.setRequestProperty("Accept", "application/json");
               procedure_conn.setRequestProperty("Content-Type", "application/json; charset=utf8");
               //con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                   
                procedure_conn.setDoOutput(true);                
               
                try(OutputStream procedure_os = procedure_conn.getOutputStream()) {
                    byte[] procedure_input = content.getBytes("utf-8");
                    procedure_os.write(procedure_input, 0, procedure_input.length);			
                }  
                System.out.println("CONTTTTTT: "+content);
                System.out.println("RESSSS " +procedure_conn.getResponseCode());
                if (procedure_conn.getResponseCode() == 200) {                    
                    InputStreamReader in = new InputStreamReader(procedure_conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    
                    String output ;
                    
                    
                    while ((output = br.readLine()) != null) {
                        HashMap<String,Object> resp = new ObjectMapper().readValue(output, HashMap.class);                        
//                        resp.get("AcctNo");
                            System.out.println("kkk"+resp);
                        return resp;
                    }
                     System.out.println("out "+output);
                }
                    
                }   
                catch(Exception ex) {
                    ex.printStackTrace();
                }        
                
        return null;
}  
    
    public ProcedureModel procedureDetails(String Id){
        System.out.println("proc 10");
        String query = "SELECT " +
                        "address.last_name as lname, " +
                        "address.first_name as fname, " +
                        "address.middle_name as mname, " +
                        "customer.branch_code as branchcode, " +                       
                        "address.title as title, " +
                        "address.gender as gender, " +
                        "address.marital_status as maritalstatus, " +
                        "address.religion as religion, address.state as state, address.lga as lga, " +
                        "address.dob as dob, address.email as email, address.phonenumber as phonenumber, " +
                        "address.place_of_birth as placeofbirth, " +
                        "address.mother_maidenname as mothermaidenname, account.acct_type as acctType, " +
                        "customer.tin as tin, " +
                        "customer.bvn as bvn,"
                        + "customer.sectorcode as sectorCode, customer.IncomeBracket as incomeBrack,"
                        + "customer.EmploymentStat as empStat, customer.BusinessType as businessType, customer.occCode as occCode"
                        +" from actdb.address, actdb.account " +                   
                        "INNER JOIN actdb.customer ON general_id=customer.id where customer.id = '"+Id+"'";
            System.out.println("proc 11");
        try {
            ProcedureModel proceduremodel = new ProcedureModel();
           stmt = con.prepareStatement(query);

           ResultSet rs = stmt.executeQuery();
           
            System.out.println("proc 12");
            while(rs.next()){                
                System.out.println("proc 13");
                proceduremodel.setLastName(rs.getString("lname"));
                proceduremodel.setFirstName(rs.getString("fname"));
                proceduremodel.setMiddleName(rs.getString("mname"));
//                proceduremodel.setBranchCode(rs.getString("branchcode"));
//                proceduremodel.setTitle(rs.getString("title"));
                proceduremodel.setGender(rs.getString("gender"));
                proceduremodel.setMaritalStatus(rs.getString("maritalstatus"));
                proceduremodel.setReligion(rs.getString("religion"));
                proceduremodel.setBirthdate(rs.getString("dob"));
                proceduremodel.setBirthplace(rs.getString("placeofbirth"));
                proceduremodel.setMothermaidenName(rs.getString("mothermaidenname"));
                proceduremodel.setTinNo(rs.getString("tin"));
                proceduremodel.setBvnNo(rs.getString("bvn"));
                proceduremodel.setEmailNo(rs.getString("email"));
                proceduremodel.setSmsNo(rs.getString("phonenumber"));
                proceduremodel.setProdCode(rs.getString("acctType"));
                proceduremodel.setSectorCode(rs.getString("sectorCode"));
                proceduremodel.setBusinessType(rs.getString("businessType"));
                proceduremodel.setOccCode(rs.getString("occCode"));
                proceduremodel.setEmployStatus(rs.getString("empStat"));
                proceduremodel.setIncomebracketCode(rs.getString("incomeBrack"));
                proceduremodel.setStateCode(rs.getString("state"));
                proceduremodel.setLocalgovtCode(rs.getString("lga"));
                
                proceduremodel.setCertificatDate("");
                proceduremodel.setCertificateIssuer("");
                proceduremodel.setAuthCapital("0");
                proceduremodel.setPaidupCapital("0");
                proceduremodel.setReserves("0");
                proceduremodel.setUnappProfitLoss("0");
                proceduremodel.setFinyearEnd("");
                proceduremodel.setLastauditDate("");
                proceduremodel.setBoardSize("0");
//                proceduremodel.setProdCode("101");
                proceduremodel.setCusType("61400");
                proceduremodel.setTitle("Mr.");
                proceduremodel.setCountryCode("NGR");
                proceduremodel.setLastapprovedBy("System");
                proceduremodel.setLastmodifiedBy("System");
                proceduremodel.setOwnershipCode("01");
                proceduremodel.setBranchCode("100");
                proceduremodel.setAddedBy("System");
                proceduremodel.setMarketedBy("ZZZ");
                proceduremodel.setOccCode("011");
//                proceduremodel.setBusinessType("3052");
//                proceduremodel.setSectorCode("40555");
                proceduremodel.setCerticateNo("");
                proceduremodel.setBranchadded("100");
                proceduremodel.setCusStatus("1");
            }
            
              
                
           return proceduremodel;
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
}
    
