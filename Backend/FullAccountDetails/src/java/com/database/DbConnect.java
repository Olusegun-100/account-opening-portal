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
import com.getdetailsModel.AccountModel;
import com.getdetailsModel.AccountheldModel;
import com.getdetailsModel.ContactModel;
import com.getdetailsModel.DocumentModel;
import com.getdetailsModel.MyResponse;
import com.getdetailsModel.SignatoryModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author User
 */
public class DbConnect {
    
    public MyResponse getFullAccountDetails(String id) {
        
//        System.out.println("ref ffr 009");
        Connection con = null;
        PreparedStatement stmt = null;                
        
        String query = "SELECT " +
                        "address.first_name as fname, " +
                        "address.last_name as lname, " +
                        "address.phonenumber as phonenumber, " +
                        "address.email as email, " +
                        "address.title as title, " +
                        "address.middle_name as mname, " +
                        "address.mother_maidenname as Mmaidenname, " +
                        "address.dob as DOB,address.gender as Gender, " +
                        "address.marital_status as MaritalStat, " +
                        "address.religion as Religion, " +
                        "address.place_of_birth as PlaceOfBirth, " +
                        "address.state as StateOfOrigin, " +
                        "address.lga as LGA,address.nationality as Nationality," +
                        "address.residential_address as ResidentialAdd,address.residential_address_lga as ResidentialLga, " +
                        "address.residential_address_state as ResidentialState,address.place_of_permit_issue as PlaceOfPermit, " +
                        "address.resident_permit_number as ResidentPermitNumber,address.permit_issue_date as PermitIssueDate, " +
                        "address.permit_expiry_date as PermitExpiryDate, address.maritalName, address.genderName, address.religionName," +
                        "address.nationalityName as nationalityname, address.stateName as stateName, address.lgaName as lgaName, " +
                        "address.contact_addressone as contactone,address.contact_addresstwo as contacttwo," +
                        " nextofkin.nxk_lastname as NxkLastN, " +
                        "nextofkin.nxk_firstname as NxkFirstN, " +
                        "document.doc_type as doctype, " +
                        "document.file as file_file, " +
                        "document.id as doc_id, " +
                        "document.filename as filename, " +
                        "document.timestamp as timestamp, customer.status as status, customer.staffId as staffId, customer.approverId as approverId, " +
                        "nextofkin.nxk_phonenumber as NxkPhone,address.general_id,nextofkin." +
                        "nxk_email as NxkEmail,nextofkin.nxk_relationship as NxkRelationship,nextofkin. " +
                        "nxk_address as NxkAddress,customer.bvn as BVN, " +
                        "accttypes.account_types as accttypes," +
                        "customer.account_officer as OfficerName,customer.branch_code as branchCode, customer.EmploymentStatName as "+
                        "empStat, customer.IncomeBracketName as incBracket, customer.BusinessTypeName as busType, customer.occCodeName as occCode,"+
                        " customer.sectorcodeName as sectorCode,customer.acct_name as companyName,customer.IncomeBracket as incomecode,customer.EmploymentStat as empcode ," +
                        "customer.BusinessType as businesscode,customer.companyquotedname as companyquotedname, customer.occCode as occupation,customer.sectorcode as seccode "+
                        ", branch.branch_name as branchname, customer.tin as tin, customer.rcno as rcno, customer.dateofIncorparation as dateIncorparate," +
                        "customer.jurisdificationofIncorparation as jurisdification,customer.operatingBusinessaddress as OperatingBusiness," +
                        "customer.website as website,customer.agentname as agentName, customer.crm as crm, customer.specialcontrolunit as specialcontrol, customer.iscompanyquoted as companyquoted," +
                        " accountheld.nameANDaddress as nameAdd, accountheld.accountName as accountname, customer.accountservice as acctService," +
                        "accountheld.accountNumber as accountnumber, accountheld.status as accStatus, accountheld.id as heldId,"+
                        "keycontacts.id as contactsId, keycontacts.fullname as contactfullname, keycontacts.jobtitle as contactjob," +
                        "keycontacts.emails as contactemail, keycontacts.mobilenumber as contactmobile,keycontacts.officeaddress as contactoffice," +
                        "signatories.id as signatoriesId, signatories.email as signatoriesEmail, signatories.dob as signatoriesDOB," +
                        "signatories.first_name as signatoriesFirstname,signatories.gender as signatoriesGender,signatories.last_name as signatoriesLastName," +
                        "signatories.lga as signatoriesLga,signatories.marital_status as signatoriesMaritalStatus,signatories.religion as signatoriesReligion," +
                        "signatories.residential_address as signatoriesaddress,signatories.title as signatoriesTitle,"
                    + "signatories.otherCitizenshipStatus as citizenshipstatus, signatories.mailingaddress as mailingaddress,signatories.countryOfResidency as countryofresident,"
                    + "signatories.ssn as ssn,signatories.occupation as signatoriesOccupation," +
                            "signatories.jobtitle as signatoriesJobtitle, signatories.alt_phonenumber as alternatenumber,"
                    + "signatories.permit_expiry_date as permitexdate, signatories.permit_issue_date as permitissdate,"
                    + "signatories.resident_permit_number as signatoriesresidentpermitnumber, signatories.residential_address_lga as signatoriesresidentiallga,"
                    + "signatories.residential_address_state as residentstate, signatories.state_of_origin as sigstateoforigin,"
                    + "signatories.middle_name as middlename, signatories.mother_maidenname as mothermaidenname,"
                    + "signatories.idIssuerdate as idissuerdate,signatories.idExpirydate as Idexpirydate,"
                    + "signatories.tin as tin, signatories.meansofidentification as meansofidentification,"
                    + "signatories.identificationnumber as identification, signatories.place_of_permit_issue as signatoriesplaceofpermitissue,"
                    + "signatories.phonenumber as phonenumber,signatories.place_of_birth as countryofbirth,"
                    + "signatories.nationality as signatoriesnationality,nexkOfkinName as nextofkinname,signatories.office as signatoriesOffice,signatories.bvn as signatoriesBVN " +
                        "FROM actdb.address " +
                        "INNER JOIN actdb.customer ON address.general_id=customer.id " +
                        "LEFT JOIN actdb.accountheld ON customer.id= accountheld.general_id " +
                        "LEFT JOIN actdb.nextofkin ON customer.id=nextofkin.general_id " +
                        "LEFT JOIN actdb.keycontacts ON customer.id=keycontacts.general_id " +
                        "LEFT JOIN actdb.signatories ON customer.id=signatories.general_id " +
                        "INNER JOIN actdb.document ON customer.id=document.general_id " +
                        "INNER JOIN actdb.account ON customer.id = account.general_id " +
                        "INNER JOIN actdb.branch ON customer.branch_code = branch.branch_code " +
                        "INNER JOIN actdb.accttypes ON accttypes.prodCode = account.acct_type " +
                        "WHERE customer.id = '"+id+"'";
               
        try {
            System.out.println("Query: " + query);
            AccountModel acct = new AccountModel();
            MyResponse myResponse = new MyResponse();
            
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/actdb?user=root&password=mypassword");
            stmt = con.prepareStatement(query);
            
            ResultSet result = null;
            
            result = stmt.executeQuery();
            List<DocumentModel> docs = new ArrayList<>();
            List<String> ids = new ArrayList();
            List<AccountheldModel> accheld = new ArrayList<>();
            List<ContactModel> contacts = new ArrayList<>();
            List<SignatoryModel> signatories = new ArrayList<>();
            while(result.next()){
                
                acct.setEmail(result.getString("email"));
                acct.setFirstname(result.getString("fname"));
                acct.setBvn(result.getString("BVN"));
                acct.setTitle(result.getString("title"));
                acct.setMiddlename(result.getString("mname"));
                acct.setLastname(result.getString("lname"));
                acct.setPhonenumber(result.getString("phonenumber"));
                acct.setMothersmaidenname(result.getString("Mmaidenname"));
                acct.setDob(result.getString("DOB"));
                acct.setGender(result.getString("Gender"));
                acct.setMaritalstatus(result.getString("MaritalStat"));
                acct.setReligion(result.getString("Religion"));
                acct.setPlaceofbirth(result.getString("PlaceOfBirth"));
                acct.setStateoforigin(result.getString("StateOfOrigin"));
                acct.setLga(result.getString("LGA"));
                acct.setNationality(result.getString("Nationality"));
                acct.setResidentialaddress(result.getString("ResidentialAdd"));
                acct.setAddlga(result.getString("ResidentialLga"));
                acct.setStateofresidence(result.getString("ResidentialState"));
                acct.setPlaceOfpermitissue(result.getString("PlaceOfPermit"));
                acct.setResidentpermitnumber(result.getString("ResidentPermitNumber"));
                acct.setPermitIssueDate(result.getString("PermitIssueDate"));
                acct.setPermitExpiryDate(result.getString("PermitExpiryDate"));
                acct.setNextofkin_surname(result.getString("NxkLastN"));
                acct.setNextofkin_firstname(result.getString("NxkFirstN"));
                acct.setNextofkin_phonenumber(result.getString("NxkPhone"));
                acct.setNextofkin_email(result.getString("NxkEmail"));
                acct.setNextofkin_relationship(result.getString("NxkRelationship"));
                acct.setNextofkin_residentialadd(result.getString("NxkAddress"));   
                acct.setAccttypes(result.getString("accttypes"));   
                acct.setStatus(result.getString("status"));   
                acct.setStaffId(result.getString("staffId"));   
                acct.setSupervisorId(result.getString("approverId"));   
                acct.setOfficerName(result.getString("OfficerName"));   
                acct.setBranch_code(result.getString("branchCode"));   
                acct.setBranch_name(result.getString("branchname"));   
                acct.setTin(result.getString("tin"));   
                acct.setRcno(result.getString("rcno")); 
                acct.setEmpStatName(result.getString("empStat"));
                acct.setIncBrackName(result.getString("incBracket"));
                acct.setBusTypeName(result.getString("busType"));
                acct.setOccCodeName(result.getString("occCode"));
                acct.setSectorCodeName(result.getString("sectorCode"));
                acct.setMaritalName(result.getString("maritalName"));
                acct.setReligionName(result.getString("religionName"));
                acct.setGenderName(result.getString("genderName"));
                acct.setNationalityName(result.getString("nationalityname"));
                acct.setLgaName(result.getString("lgaName"));
                acct.setStateName(result.getString("stateName"));
                
                acct.setSectcode(result.getString("seccode"));
                acct.setBusinesscode(result.getString("businesscode"));
                acct.setEmpStatcode(result.getString("empcode"));
                acct.setOccCode(result.getString("occupation"));
                acct.setIncomeBrackcode(result.getString("incomecode"));
                acct.setDateofIncorparation(result.getString("dateIncorparate"));
                acct.setJustisficationofIncorparation(result.getString("jurisdification"));
                acct.setOperatingBusiness(result.getString("OperatingBusiness"));
                acct.setWebsite(result.getString("website"));
                acct.setCrm(result.getString("crm"));
                acct.setSpecialunit(result.getString("specialcontrol"));
                acct.setIscompanyquoted(result.getString("companyquoted"));
                acct.setCompanyname(result.getString("companyName"));
                acct.setAgentName(result.getString("agentName"));
                acct.setContactaddressone(result.getString("contactone"));
                acct.setContactaddressttwo(result.getString("contacttwo"));
                acct.setCompanyquotedname(result.getString("companyquotedname"));
                acct.setAcctService(result.getString("acctService"));
//                acct.setAcctService(services);
                
                
                DocumentModel docModel = new DocumentModel();
                docModel.setGeneral_Id(result.getString("general_id"));
                docModel.setDoc_id(result.getString("doc_id"));
                docModel.setDate_log(result.getString("timestamp"));
                docModel.setFile_name(result.getString("filename"));
                docModel.setFile(result.getString("file_file"));
                docModel.setDoc_type(result.getString("doctype"));
                if(!ids.contains(result.getString("doc_id"))) docs.add(docModel);
                ids.add(result.getString("doc_id"));
                
                AccountheldModel acctheldModel = new AccountheldModel();
                acctheldModel.setStatus(result.getString("accStatus"));
                acctheldModel.setAccountNumber(result.getString("accountnumber"));
                acctheldModel.setAccountName(result.getString("accountname"));
                acctheldModel.setNameANDaddress(result.getString("nameAdd"));
                acctheldModel.setId(result.getString("heldId"));
                if(!ids.contains(result.getString("heldId"))) accheld.add(acctheldModel);
                ids.add(result.getString("heldId"));
                
                ContactModel contactModel = new ContactModel();
                contactModel.setOfficeaddress(result.getString("contactoffice"));
                contactModel.setMobilenumber(result.getString("contactmobile"));
                contactModel.setJobtitle(result.getString("contactjob"));
                contactModel.setEmail(result.getString("contactemail"));
                contactModel.setFullname(result.getString("contactfullname"));
                contactModel.setId(result.getString("contactsId"));
                if(!ids.contains(result.getString("contactsId"))) contacts.add(contactModel);
                ids.add(result.getString("contactsId"));
                
                SignatoryModel signatoryModel = new SignatoryModel();
                signatoryModel.setId(result.getString("signatoriesId"));
                signatoryModel.setEmail(result.getString("signatoriesEmail"));
                signatoryModel.setDob(result.getString("signatoriesDOB"));
                signatoryModel.setFirst_name(result.getString("signatoriesFirstname"));
                signatoryModel.setGender(result.getString("signatoriesGender"));
                signatoryModel.setLast_name(result.getString("signatoriesLastName"));
                signatoryModel.setLga(result.getString("signatoriesLga"));
                signatoryModel.setMarital_status(result.getString("signatoriesMaritalStatus"));
                signatoryModel.setReligion(result.getString("signatoriesReligion"));
                signatoryModel.setResidential_address(result.getString("signatoriesaddress"));
                signatoryModel.setTitle(result.getString("signatoriesTitle"));
                signatoryModel.setOccupation(result.getString("signatoriesOccupation"));
                signatoryModel.setJobtitle(result.getString("signatoriesJobtitle"));
                signatoryModel.setOffice(result.getString("signatoriesOffice"));
                signatoryModel.setBvn(result.getString("signatoriesBVN"));
                
                signatoryModel.setAlt_phonenumber(result.getString("alternatenumber"));                
                signatoryModel.setPermit_expiry_date(result.getString("permitexdate"));                
                signatoryModel.setPermit_issue_date(result.getString("permitissdate"));                
                signatoryModel.setResident_permit_number(result.getString("signatoriesresidentpermitnumber"));                
                signatoryModel.setResidential_address(result.getString("signatoriesaddress"));                
                signatoryModel.setResidential_address_state(result.getString("residentstate"));                
                signatoryModel.setResidential_address_lga(result.getString("signatoriesresidentiallga"));                
                signatoryModel.setState_of_origin(result.getString("sigstateoforigin"));                
                signatoryModel.setMiddle_name(result.getString("middlename"));                
                signatoryModel.setMother_maidenname(result.getString("mothermaidenname"));                
                signatoryModel.setPhonenumber(result.getString("phonenumber"));                
                signatoryModel.setPlace_of_birth(result.getString("countryofbirth"));                
                signatoryModel.setPlace_of_permit_issue(result.getString("signatoriesplaceofpermitissue"));                
                signatoryModel.setNationality(result.getString("signatoriesnationality"));                
                signatoryModel.setNextofkinName(result.getString("nextofkinname"));                
                signatoryModel.setMeansofidentification(result.getString("meansofidentification"));                
                signatoryModel.setIdentificationnumber(result.getString("identification"));                
                signatoryModel.setTin(result.getString("tin"));                
                signatoryModel.setIdissuerdate(result.getString("idissuerdate"));                
                signatoryModel.setIdexpirydate(result.getString("Idexpirydate"));                
                signatoryModel.setCitizenshipstatus(result.getString("citizenshipstatus"));                
                signatoryModel.setMailingaddress(result.getString("mailingaddress"));                
                if(!ids.contains(result.getString("signatoriesId"))) signatories.add(signatoryModel);
                ids.add(result.getString("signatoriesId"));
                
            }
            acct.setDocument(docs);
            acct.setAccountheld(accheld);
            acct.setContact(contacts);
            acct.setSignatories(signatories);
            
            myResponse.setStatuscode("00");
            myResponse.setStatusmessage("Successful");
            myResponse.setData(acct);
            return myResponse;            
        } catch (Exception ex) {
            MyResponse myResponse = new MyResponse();
              ex.printStackTrace();
              myResponse.setStatuscode("96");
              myResponse.setStatusmessage("System malfunction, please contact the administrator");
              return myResponse;
        }

    }
    
}
