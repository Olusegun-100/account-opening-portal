/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.updatedetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.updatedetails.NbgrConnections;

/**
 *
 * @author User
 */
public class DbConnect {
    
    Connection con = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    NbgrConnections nbgrconnections = new NbgrConnections();
    
    MyResponse myResponse = new MyResponse();
    CustomerModel  data =  null;
    DocumentModel docc = new DocumentModel();
    
    public DbConnect(CustomerModel model) throws Exception{
       
            data = model;
            
            con = nbgrconnections.mySqlDBconnection();
//            con.setAutoCommit(false);
    }
    
    public void updateAddress(String id) throws Exception{
        
                    
            String insert_addresstable = "update address SET address_description=?,country=?, email=?, postal_code=?, status=?, alt_phonenumber=?, "
                    + "contact_addressone=?, contact_addresstwo=?, dob=?, first_name=?, gender=?, last_name=?, lga=?,marital_status=?,"
                    + " permit_expiry_date=?, permit_issue_date=?, phonenumber=?, place_of_birth=?, place_of_permit_issue=?, religion=?, resident_permit_number=?,"
                    + " residential_address=?, residential_address_lga=?,residential_address_state=?, state_of_origin=?,"
                    + " title=?, middle_name=?, mother_maidenname=?, nationality=?, state=?,"
                    + "maritalName =?, genderName = ?,religionName =?, nationalityName=?,lgaName=?,"
                    + "stateName=?, general_id=? where general_id = '"+id+"'";
            
            psmt = con.prepareStatement(insert_addresstable);
            
//            psmt.setString(1, data.getAddress_description());
            psmt.setString(1, data.getAddress_description());
            psmt.setString(2, data.getCountry());
            psmt.setString(3, data.getEmail());
            psmt.setString(4, data.getPostal_code());
            psmt.setString(5, data.getStatus());
            psmt.setString(6, data.getAlt_phonenumber());
            psmt.setString(7, data.getContact_addressone());
            psmt.setString(8, data.getContact_addresstwo());
            psmt.setString(9, data.getDob());
            psmt.setString(10, data.getFirst_name());
            psmt.setString(11, data.getGender());
            psmt.setString(12, data.getLast_name());
            psmt.setString(13, data.getLga());
            psmt.setString(14, data.getMarital_status());
            psmt.setString(15, data.getPermit_expiry_date());
            psmt.setString(16, data.getPermit_issue_date());
            psmt.setString(17, data.getPhonenumber());
            psmt.setString(18, data.getPlace_of_birth());
            psmt.setString(19, data.getPlace_of_permit_issue());
            psmt.setString(20, data.getReligion());
            psmt.setString(21, data.getResident_permit_number());
            psmt.setString(22, data.getResidential_address());
            psmt.setString(23, data.getResidential_address_lga());
            psmt.setString(24, data.getResidential_address_state());
            psmt.setString(25, data.getState_of_origin());
            psmt.setString(26, data.getTitle());
            psmt.setString(27, data.getMiddle_name());
            psmt.setString(28, data.getMother_maidenname());
            psmt.setString(29, data.getNationality());
            psmt.setString(30, data.getState());
            psmt.setString(31, data.getMaritalstatusName());
            psmt.setString(32, data.getGenderName());
            psmt.setString(33, data.getReligionName());
            psmt.setString(34, data.getNationalityName());
            psmt.setString(35, data.getLgaName());
            psmt.setString(36, data.getStateName());
            psmt.setString(37, id);
            psmt.executeUpdate();
          }
    
    public void updateAccount(String id) throws Exception{
        
        String insert_accounttable = "update account set time_stamp= ?,user_id=?,acct_description=?,acct_no=?,acct_type=?"
                + ",currency_code=?,date=?,date_open=?,general_id=? where general_id='"+id+"'";
            
            psmt = con.prepareStatement(insert_accounttable);
            
            psmt.setString(1, LocalDateTime.now().toString());
            psmt.setString(2,data.getUser_id());
            psmt.setString(3,data.getAcct_description());
            psmt.setString(4,data.getAcct_no());
            psmt.setString(5,data.getAcct_type());
            psmt.setString(6,data.getCurrency_code());
            psmt.setString(7,data.getDate());
            psmt.setString(8,data.getDate_open());
            psmt.setString(9,id);

            
            psmt.executeUpdate();
       
    }
    
    
    public void saveDocument(DocumentModel model)throws Exception{
        
         String insert_accounttable = "insert into document (id,datelog,filename,doc_location,doc_type,timestamp"
                 + ",general_id,file) values(?,?,?,?,?,?,?,?)";
            
            psmt = con.prepareStatement(insert_accounttable);
            
            psmt.setString(1, model.getDoc_id());
            psmt.setString(2, LocalDateTime.now().toString());
            psmt.setString(3, model.getFile_name());
            psmt.setString(4, model.getDoc_location());
            psmt.setString(5, model.getDoc_type());
            psmt.setString(6, LocalDateTime.now().toString());
            psmt.setString(7, data.getId());
            psmt.setString(8, model.getFile());
            
            System.out.println("insert = "+insert_accounttable);
            
            psmt.executeUpdate();
            
            System.out.println("name = "+model.getFile_name());
            System.out.println("gen_id = "+model.getGeneral_Id());
    }
    
    public void updateCustomer(String id) throws Exception{
           String insert_customertable = 
                   "update customer set "
                   + "acct_name = ?,dateopen= ?, "
                   + "status = ?,staffId = ?,approverId =?,initiated_date = ?,approved_date =?, firstname = ?, full_name =?,"
                   + " lastname = ?, middlename =?, "
                   + "rcno = ?, timestamp = ?, customer_id=?, tin = ?, BVN=?, branch_code =?,"
                   + "account_officer=?, EmploymentStat= ?, IncomeBracket= ?, BusinessType = ?,"
                   + "occCode = ?, sectorcode = ?, sectorcodeName = ?, occCodeName =?, IncomeBracketName =?,"
                   + "BusinessTypeName = ?, EmploymentStatName = ? where id ='"+id+"'";

           psmt = con.prepareStatement(insert_customertable);

            psmt.setString(1, data.getAcct_name());
            psmt.setString(2, data.getDateopen());
            psmt.setString(3, data.getStatus());
            psmt.setString(4, data.getStaffId());
            psmt.setString(5, data.getSupervisorId());
            psmt.setString(6, null);
            psmt.setString(7, null);
            psmt.setString(8, data.getFirstname());
            psmt.setString(9, data.getFirstname() + " " + data.getLast_name());
            psmt.setString(10, data.getLastname());
            psmt.setString(11, data.getMiddlename());
            psmt.setString(12, data.getRcno());
            psmt.setString(13, LocalDateTime.now().toString());
            psmt.setString(14, "");
            psmt.setString(15, data.getTin());
            psmt.setString(16, data.getBvn());
            psmt.setString(17, data.getBranch_code());
            psmt.setString(18, data.getAccount_officer());
            psmt.setString(19, data.getEmpStat());
            psmt.setString(20, data.getIncBrack());
            psmt.setString(21, data.getBusType());
            psmt.setString(22, data.getOccCode());
            psmt.setString(23, data.getSectorCode());
            psmt.setString(24, data.getSectCodename());
            psmt.setString(25, data.getOccCodename());
            psmt.setString(26, data.getIncCBracketname());
            psmt.setString(27, data.getBusTypename());
            psmt.setString(28, data.getEmploystatname());
            System.out.println("query = "+psmt);
            psmt.executeUpdate();
    }
    
    
    public void updateNextOfKin(String id) throws Exception{
            
            String insert_personaldetailstable = "update nextofkin set nxk_address=?, nxk_email=?, nxk_firstname=?, nxk_lastname=?, "
                    + "nxk_phonenumber=?, nxk_relationship=?, general_id=? where general_id='"+id+"'";
            
            psmt = con.prepareStatement(insert_personaldetailstable);
            
            psmt.setString(1, data.getNxk_address());
            psmt.setString(2, data.getNxk_email());
            psmt.setString(3, data.getNxk_firstname());
            psmt.setString(4, data.getNxk_lastname());
            psmt.setString(5, data.getNxk_phonenumber());
            psmt.setString(6, data.getNxk_relationship());
            psmt.setString(7, data.getId());
            
            
//            System.out.println("query = "+psmt);
            psmt.executeUpdate();
            
    }
    
    
    public boolean accountPending(String id){
        boolean pending=false;
        String query = "SELECT * FROM actdb.customer WHERE id='"+id+"' AND status=''";
        
        try{
            psmt = con.prepareStatement(query);
            rs = psmt.executeQuery();
            pending = rs.next();
            
        }catch(Exception e){
            e.printStackTrace();
        }            
        return pending;
    }
    
   
    
    public void clearAllDocs(String id){
        String query = "DELETE FROM actdb.document WHERE general_id='"+id+"'";
        try{
            psmt = con.prepareStatement(query);
            psmt.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
