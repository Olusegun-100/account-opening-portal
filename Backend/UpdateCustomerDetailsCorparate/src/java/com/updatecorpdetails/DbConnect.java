/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.updatecorpdetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.updatecorpdetails.NbgrConnections;

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
    
    public void updateAccountheld(AccountheldModel accountheld) throws Exception{
        
        String insert_accountheldtable = "update accountheld set nameANDaddress= ?,accountName=?,accountNumber=?,status=?"
                + ",general_id=? ";
            
            psmt = con.prepareStatement(insert_accountheldtable);
            
            psmt.setString(1, accountheld.getNameANDaddress());
            psmt.setString(2, accountheld.getAccountName());
            psmt.setString(3, accountheld.getAccountNumber());
            psmt.setString(4, accountheld.getStatus());
            psmt.setString(5, data.getId());
            
            psmt.executeUpdate();
       
    }
    
    public void updateContactPerson(ContactPersonModel contactperson) throws Exception{
        
        String insert_contactpersontable = "update keycontacts set fullname = ?,jobtitle =? ,emails = ?,mobilenumber = ?,"
                + "officeaddress = ?,general_id = ?";
               
            
            psmt = con.prepareStatement(insert_contactpersontable);
            
            psmt.setString(1, contactperson.getFullname());
            psmt.setString(2, contactperson.getJobtitle());
            psmt.setString(3, contactperson.getEmail());
            psmt.setString(4, contactperson.getMobilenumber());
            psmt.setString(5, contactperson.getOfficeaddress());
            psmt.setString(6, data.getId());
            
            psmt.executeUpdate();
       
    }
    
    public void updateSignatories(SignatoryModel signatory) throws Exception{
        
        String insert_contactpersontable = "update signatories set email = ?, alt_phonenumber = ?,"
                    + " dob = ?, first_name = ?,gender = ?, last_name = ?, lga = ?,marital_status = ?, permit_expiry_date = ?,"
                    + " permit_issue_date = ?, phonenumber = ?, place_of_birth = ?, place_of_permit_issue = ?, religion = ?, resident_permit_number = ?, residential_address = ?,"
                    + " residential_address_lga = ?,residential_address_state = ?, state_of_origin = ?, title = ?, middle_name = ?, mother_maidenname = ?, nationality = ?, state = ?,"
                    + " nexkOfkinName = ?, meansofIdentification = ?,identificationnumber = ?, tin = ?,"
                    + "idIssuerdate = ?, idExpirydate = ?, occupation = ?,jobtitle = ?, office = ?,"
                    + "otherCitizenshipStatus = ?,bvn = ?, mailingaddress = ?, countryOfResidency = ?, ssn = ?, general_id = ?";
               
            
            psmt = con.prepareStatement(insert_contactpersontable);
            
            psmt.setString(1, signatory.getEmail());
            psmt.setString(2, signatory.getAlt_phonenumber());
            psmt.setString(3, signatory.getDob());
            psmt.setString(4, signatory.getFirst_name());
            psmt.setString(5, signatory.getGender());
            psmt.setString(6, signatory.getLast_name());
            psmt.setString(7, signatory.getLga());
            psmt.setString(8, signatory.getMarital_status());
            psmt.setString(9, signatory.getPermit_expiry_date());
            psmt.setString(10, signatory.getPermit_issue_date());
            psmt.setString(11, signatory.getPhonenumber());
            psmt.setString(12, signatory.getPlace_of_birth());
            psmt.setString(13, signatory.getPlace_of_permit_issue());
            psmt.setString(14, signatory.getReligion());
            psmt.setString(15, signatory.getResident_permit_number());
            psmt.setString(16, signatory.getResidential_address());
            psmt.setString(17, signatory.getResidential_address_lga());
            psmt.setString(18, signatory.getResidential_address_state());
            psmt.setString(19, signatory.getState_of_origin());
            psmt.setString(20, signatory.getTitle());
            psmt.setString(21, signatory.getMiddle_name());
            psmt.setString(22, signatory.getMother_maidenname());
            psmt.setString(23, signatory.getNationality());
            psmt.setString(24, signatory.getState());
            psmt.setString(25, signatory.getNextofkinName());
            psmt.setString(26, signatory.getMeansofidentification());
            psmt.setString(27, signatory.getIdentificationnumber());
            psmt.setString(28, signatory.getTin());
            psmt.setString(29, signatory.getIdissuerdate());
            psmt.setString(30, signatory.getIdexpirydate());
            psmt.setString(31, signatory.getOccupation());
            psmt.setString(32, signatory.getJobtitle());
            psmt.setString(33, signatory.getOffice());
            psmt.setString(34, signatory.getCitizenshipstatus());
            psmt.setString(35, signatory.getBvn());
            psmt.setString(36, signatory.getMailingaddress());
            psmt.setString(37, signatory.getCountryofResidency());
            psmt.setString(38, signatory.getSsn());
            psmt.setString(39, data.getId());
            
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
            
            psmt.executeUpdate();
            
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
                   + "BusinessTypeName = ?, EmploymentStatName = ?, dateofIncorparation = ?,"
                   + "jurisdificationofIncorparation = ?, operatingBusinessaddress = ?, website = ?,"
                   + "crm= ?, specialcontrolunit = ?, iscompanyquoted = ?, companyquotedname=?, agentname = ?,"
                   + "accountservice = ? where id ='"+id+"'";

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
            psmt.setString(29, data.getDateofincorparation());
            psmt.setString(30, data.getJuridificationofincorparation());
            psmt.setString(31, data.getOperatingaddress());
            psmt.setString(32, data.getWebsite());
            psmt.setString(33, data.getCrm());
            psmt.setString(34, data.getSpecialcontrolunit());
            psmt.setString(35, data.getIscompanyquoted());
            psmt.setString(36, data.getCompanyquotedname());
            psmt.setString(37, data.getAgentname());
            psmt.setString(38, data.getAccountservices());
            System.out.println("query = "+psmt);
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
