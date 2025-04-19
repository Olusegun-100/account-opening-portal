/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import org.acct.models.AccountheldModel;
import org.acct.models.ContactPersonModel;
import org.acct.models.DocumentModel;
import org.acct.models.SignatoryModel;
import org.acct.opening.NbgrConnections;
import org.acct.utils.AccountOpeningRequest;
import org.acct.utils.AccountOpeningResponse;
//import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author User
 */
public class AccountRepo {
    Connection con = null;
    PreparedStatement psmt = null;
    ResultSet rs = null;
    NbgrConnections nbgrconnections = new NbgrConnections();
    
    AccountOpeningResponse accountOpeningResponse = new AccountOpeningResponse();
    AccountOpeningRequest  data =  null;
    
    String custtable_id = java.util.UUID.randomUUID().toString();
    String acct_id = java.util.UUID.randomUUID().toString();
    String add_id = java.util.UUID.randomUUID().toString();
    String nxk_id = java.util.UUID.randomUUID().toString();

    public AccountRepo(AccountOpeningRequest request) throws Exception{
        data = request;
        
         con = nbgrconnections.mySqlDBconnection();
            //con.setAutoCommit(false);
        
    }
    
    
    
    public void saveAddress() throws Exception{
        
                    
            String insert_addresstable = "insert into address (id,address_description,country, email, postal_code, status, alt_phonenumber,"
                    + " contact_addressone, contact_addresstwo, dob, first_name, gender, last_name, lga,marital_status, permit_expiry_date,"
                    + " permit_issue_date, phonenumber, place_of_birth, place_of_permit_issue, religion, resident_permit_number, residential_address,"
                    + " residential_address_lga,residential_address_state, state_of_origin, title, middle_name, mother_maidenname, nationality, state,"
                    + " maritalName, genderName, religionName,"
                    + "nationalityName, lgaName, stateName, general_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            psmt = con.prepareStatement(insert_addresstable);
            
            psmt.setString(1, add_id);
            psmt.setString(2, data.getAddress_description());
            psmt.setString(3, data.getCountry());
            psmt.setString(4, data.getEmail());
            psmt.setString(5, data.getPostal_code());
            psmt.setString(6, data.getStatus());
            psmt.setString(7, data.getAlt_phonenumber());
            psmt.setString(8, data.getContact_addressone());
            psmt.setString(9, data.getContact_addresstwo());
            psmt.setString(10, data.getDob());
            psmt.setString(11, data.getFirst_name());
            psmt.setString(12, data.getGender());
            psmt.setString(13, data.getLast_name());
            psmt.setString(14, data.getLga());
            psmt.setString(15, data.getMarital_status());
            psmt.setString(16, data.getPermit_expiry_date());
            psmt.setString(17, data.getPermit_issue_date());
            psmt.setString(18, data.getPhonenumber());
            psmt.setString(19, data.getPlace_of_birth());
            psmt.setString(20, data.getPlace_of_permit_issue());
            psmt.setString(21, data.getReligion());
            psmt.setString(22, data.getResident_permit_number());
            psmt.setString(23, data.getResidential_address());
            psmt.setString(24, data.getResidential_address_lga());
            psmt.setString(25, data.getResidential_address_state());
            psmt.setString(26, data.getState_of_origin());
            psmt.setString(27, data.getTitle());
            psmt.setString(28, data.getMiddle_name());
            psmt.setString(29, data.getMother_maidenname());
            psmt.setString(30, data.getNationality());
            psmt.setString(31, data.getState());
            psmt.setString(32, data.getMaritalstatusName());
            psmt.setString(33, data.getGenderName());
            psmt.setString(34, data.getReligionName());
            psmt.setString(35, data.getNationalityName());
            psmt.setString(36, data.getLgaName());
            psmt.setString(37, data.getStateName());
            psmt.setString(38, custtable_id);
            psmt.executeUpdate();
          }
    
    public void saveAccount() throws Exception{
        
        String insert_accounttable = "insert into account (id,time_stamp,user_id,acct_description,acct_no,acct_type,currency_code,date,date_open,general_id) values(?,?,?,?,?,?,?,?,?,?)";
            
            psmt = con.prepareStatement(insert_accounttable);
            
            psmt.setString(1, acct_id);
            psmt.setString(2, LocalDateTime.now().toString());
            psmt.setString(3,data.getUser_id());
            psmt.setString(4,data.getAcct_description());
            psmt.setString(5,data.getAcct_no());
            psmt.setString(6,data.getAcct_type());
            psmt.setString(7,data.getCurrency_code());
            psmt.setString(8,data.getDate());
            psmt.setString(9,data.getDate_open());
            psmt.setString(10,custtable_id);

            
            psmt.executeUpdate();
       
    }
    
    public void saveDocument(DocumentModel model)throws Exception{
        
         String insert_accounttable = "insert into document (id,datelog,filename,doc_location,doc_type,timestamp,general_id,file) values(?,?,?,?,?,?,?,?)";
            
            psmt = con.prepareStatement(insert_accounttable);
            
            psmt.setString(1, model.getDoc_id());
            psmt.setString(2, model.getDate_log());
            psmt.setString(3, model.getFile_name());
            psmt.setString(4, model.getDoc_location());
            psmt.setString(5, model.getDoc_type(   ));
            psmt.setString(6, LocalDateTime.now().toString());
            psmt.setString(7, custtable_id);
            psmt.setString(8, model.getFile());
            
            psmt.executeUpdate();
    }
    
    public void saveAccountheld(AccountheldModel accountheld)throws Exception{
        
         String insert_accountheldtable = "insert into accountheld (id,nameANDaddress,accountName,accountNumber,status,general_id) values(?,?,?,?,?,?)";
            
            psmt = con.prepareStatement(insert_accountheldtable);
            
            psmt.setString(1, accountheld.getId());
            psmt.setString(2, accountheld.getNameANDaddress());
            psmt.setString(3, accountheld.getAccountName());
            psmt.setString(4, accountheld.getAccountNumber());
            psmt.setString(5, accountheld.getStatus());
            psmt.setString(6, custtable_id);
            
            psmt.executeUpdate();
    }
    
    public void saveContactPerson(ContactPersonModel contact)throws Exception{
        
         String insert_contacttable = "insert into keycontacts (id,fullname,jobtitle,emails,mobilenumber,officeaddress,general_id) values(?,?,?,?,?,?,?)";
            
            psmt = con.prepareStatement(insert_contacttable);
            
            psmt.setString(1, contact.getId());
            psmt.setString(2, contact.getFullname());
            psmt.setString(3, contact.getJobtitle());
            psmt.setString(4, contact.getEmail());
            psmt.setString(5, contact.getMobilenumber());
            psmt.setString(6, contact.getOfficeaddress());
            psmt.setString(7, custtable_id);
            
            psmt.executeUpdate();
    }
    
    public void saveSignatory(SignatoryModel signatories) throws Exception{
        
                    
            String insert_signatoriestable = "insert into signatories (id, email, alt_phonenumber,"
                    + " dob, first_name,gender, last_name, lga,marital_status, permit_expiry_date,"
                    + " permit_issue_date, phonenumber, place_of_birth, place_of_permit_issue, religion, resident_permit_number, residential_address,"
                    + " residential_address_lga,residential_address_state, state_of_origin, title, middle_name, mother_maidenname, nationality, state,"
                    + " nexkOfkinName, meansofIdentification,identificationnumber, tin,"
                    + "idIssuerdate, idExpirydate, occupation,jobtitle, office,"
                    + "otherCitizenshipStatus,bvn, mailingaddress, countryOfResidency, ssn, general_id) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            
            psmt = con.prepareStatement(insert_signatoriestable);
            
            psmt.setString(1, signatories.getId());
            psmt.setString(2, signatories.getEmail());
            psmt.setString(3, signatories.getAlt_phonenumber());
            psmt.setString(4, signatories.getDob());
            psmt.setString(5, signatories.getFirst_name());
            psmt.setString(6, signatories.getGender());
            psmt.setString(7, signatories.getLast_name());
            psmt.setString(8, signatories.getLga());
            psmt.setString(9, signatories.getMarital_status());
            psmt.setString(10, signatories.getPermit_expiry_date());
            psmt.setString(11, signatories.getPermit_issue_date());
            psmt.setString(12, signatories.getPhonenumber());
            psmt.setString(13, signatories.getPlace_of_birth());
            psmt.setString(14, signatories.getPlace_of_permit_issue());
            psmt.setString(15, signatories.getReligion());
            psmt.setString(16, signatories.getResident_permit_number());
            psmt.setString(17, signatories.getResidential_address());
            psmt.setString(18, signatories.getResidential_address_lga());
            psmt.setString(19, signatories.getResidential_address_state());
            psmt.setString(20, signatories.getState_of_origin());
            psmt.setString(21, signatories.getTitle());
            psmt.setString(22, signatories.getMiddle_name());
            psmt.setString(23, signatories.getMother_maidenname());
            psmt.setString(24, signatories.getNationality());
            psmt.setString(25, signatories.getState());
            psmt.setString(26, signatories.getNextofkinName());
            psmt.setString(27, signatories.getMeansofidentification());
            psmt.setString(28, signatories.getIdentificationnumber());
            psmt.setString(29, signatories.getTin());
            psmt.setString(30, signatories.getIdissuerdate());
            psmt.setString(31, signatories.getIdexpirydate());
            psmt.setString(32, signatories.getOccupation());
            psmt.setString(33, signatories.getJobtitle());
            psmt.setString(34, signatories.getOffice());
            psmt.setString(35, signatories.getCitizenshipstatus());
            psmt.setString(36, signatories.getBvn());            
            psmt.setString(37, signatories.getMailingaddress());
            psmt.setString(38, signatories.getCountryofResidency());
            psmt.setString(39, signatories.getSsn());
            
            psmt.setString(40, custtable_id);
            psmt.executeUpdate();
          }
    
    
    public void saveCustomer() throws Exception{        
           String insert_customertable = 
                   "insert into customer "
                   + "(id, acct_name,dateopen, "
                   + "status,staffId,approverId,initiated_date,approved_date, firstname, full_name, lastname, middlename, "
                   + "rcno, timestamp, customer_id, tin, BVN, branch_code,account_officer, EmploymentStat, IncomeBracket, BusinessType,"
                   + "occCode,sectorcode,sectorcodeName,occCodeName,IncomeBracketName, BusinessTypeName, EmploymentStatName,dateofIncorparation,"
                   + "jurisdificationofIncorparation, operatingBusinessaddress, website, crm, specialcontrolunit, iscompanyquoted,companyquotedname, agentname, accountservice) "
                   + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            psmt = con.prepareStatement(insert_customertable);

            psmt.setString(1, custtable_id);
            psmt.setString(2, data.getAcct_name());
            psmt.setString(3, LocalDateTime.now().toString());
            psmt.setString(4, "");
            psmt.setString(5, "");
            psmt.setString(6, "");
            psmt.setString(7, null);
            psmt.setString(8, null);
            psmt.setString(9, data.getFirst_name());
            psmt.setString(10, data.getFirst_name()+ " " + data.getMiddle_name()+ " " + data.getLast_name());
            psmt.setString(11, data.getLast_name());
            psmt.setString(12, data.getMiddle_name());
            psmt.setString(13, data.getRcno());
            psmt.setString(14, LocalDateTime.now().toString());
            psmt.setString(15, "");
            psmt.setString(16, data.getTin());
            psmt.setString(17, data.getBvn());
            psmt.setString(18, data.getBranch_code());
            psmt.setString(19, data.getAccount_officer());
            psmt.setString(20, data.getEmploystat());
            psmt.setString(21, data.getIncCBracket());
            psmt.setString(22, data.getBusType());
            psmt.setString(23, data.getOccCode());
            psmt.setString(24, data.getSectCode());
            psmt.setString(25, data.getSectCodename());
            psmt.setString(26, data.getOccCodename());
            psmt.setString(27, data.getIncCBracketname());
            psmt.setString(28, data.getBusTypename());
            psmt.setString(29, data.getEmploystatname());
            psmt.setString(30, data.getDateofincorparation());
            psmt.setString(31, data.getJurisdificationofincorparation());
            psmt.setString(32, data.getOperatingbusinessaddress());
            psmt.setString(33, data.getWebsite());
            psmt.setString(34, data.getCrm());
            psmt.setString(35, data.getSpecialcontrolunit());
            psmt.setString(36, data.getIscompanyquoted());
            psmt.setString(37, data.getCompanyquotedname());
            psmt.setString(38, data.getAgentname());
            psmt.setString(39, data.getAccountservices());
            psmt.executeUpdate();
    }

    public String accountOfficer(String brach_code){
        String query = "SELECT branch_officer from actdb.branch_officer where id='"+brach_code+"'";

        try {
           psmt = con.prepareStatement(query);

           ResultSet rst = psmt.executeQuery();
           if(rst.next()){
               return rst.getString("branch_officername");
           }
           return null;
        }catch(Exception e){
            e.printStackTrace();
        } 
        return null;
    }
    
    public boolean isBvnAcctountTypeExist(String bvn, String accttype) throws SQLException{ 
       
       String query = "SELECT account.acct_type as accttype, " +
                        "customer.bvn as BVN " +
                        "FROM actdb.customer " +
                        "INNER JOIN actdb.account ON customer.id=account.general_id " +
                        "WHERE customer.bvn = '"+bvn+"' AND account.acct_type ='"+accttype+"'";
        
       
                    try {
                        psmt = con.prepareStatement(query);

                        ResultSet rst = psmt.executeQuery();
                            return rst.next();
                     }catch(Exception e){
                         e.printStackTrace();
                     } 
                    return false;
                 }
}
