/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.acct.opening;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.acct.utils.AccountOpeningRequest;
import org.acct.utils.AccountOpeningResponse;
import org.codehaus.jackson.map.ObjectMapper;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;
import org.acct.models.AccountheldModel;
import org.acct.models.AddressModel;
import org.acct.models.ContactPersonModel;
import org.acct.models.DocumentModel;
import org.acct.models.MyResponse;
import org.acct.models.SignatoryModel;
import org.codehaus.jackson.JsonNode;
import org.glassfish.jersey.media.multipart.BodyPartEntity;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;
import repository.AccountRepo;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/accountopening")
public class AccountOpeningsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AccountOpeningsResource
     */
    public AccountOpeningsResource() {
    }
    
    private String location = "C:\\Users\\User\\Documents\\";

    /**
     * Retrieves representation of an instance of org.acct.opening.AccountOpeningsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        
        
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of AccountOpeningResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST                                        
    @Produces(MediaType.APPLICATION_JSON)
    public AccountOpeningResponse processInfo(String content) throws Exception {//TODO

        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        AccountOpeningResponse accountOpeningResponsee = new AccountOpeningResponse();
      
        try{
            
            System.out.println("content = "+ content);            
            
            AccountOpeningResponse accountOpeningResponse = new AccountOpeningResponse();
            AccountOpeningRequest  data =  new ObjectMapper().readValue(content, AccountOpeningRequest.class);
            JsonNode docs = new ObjectMapper().convertValue(data.getDocuments(), JsonNode.class);
            JsonNode held = new ObjectMapper().convertValue(data.getAccounheld(), JsonNode.class);
            JsonNode contacts = new ObjectMapper().convertValue(data.getContactperson(), JsonNode.class);
            JsonNode signatories = new ObjectMapper().convertValue(data.getSignatories(), JsonNode.class);
            
            System.out.println("documents map = "+docs);
            
            NbgrConnections nbgrconnections = new NbgrConnections();
            con = nbgrconnections.mySqlDBconnection();
            //con.setAutoCommit(false);
            AccountRepo repo = new AccountRepo(data);  
                        System.out.println("Account repooooo: " + repo.isBvnAcctountTypeExist(data.getBvn(),data.getAcct_type()));
            if(repo.isBvnAcctountTypeExist(data.getBvn(), data.getAcct_type())) {
                accountOpeningResponsee.setStatuscode("96");
                accountOpeningResponsee.setStatusmessage("BVN and Account Type already exist!!");
            } else {
                   repo.saveAccount();
                   repo.saveAddress();
                   repo.saveCustomer();
            if(docs != null)
            docs.forEach(
                e->{
                    try {
                        DocumentModel doc = new DocumentModel();
                        doc.setDoc_id(UUID.randomUUID().toString());
                        doc.setDoc_type(e.get("doc_type").asText());
                        doc.setDate_log(LocalDate.now().toString());
                        doc.setFile_name(e.get("file_name").asText());
                        System.out.println(docs);
                        doc.setFile(e.get("file").asText());
                        repo.saveDocument(doc);
                    } catch (Exception ex) {
                        Logger.getLogger(AccountOpeningsResource.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            );

                if(held != null)
                        held.forEach(
                            e->{
                                try {
                                    AccountheldModel acc = new AccountheldModel();
                                    acc.setId(UUID.randomUUID().toString());
                                    acc.setNameANDaddress(e.get("nameANDaddress").asText());
                                    acc.setAccountName(e.get("accountName").asText());
                                    acc.setAccountNumber(e.get("accountNumber").asText());
                                    acc.setStatus(e.get("status").asText());
                                    System.out.println("HELD---"+ held);
                                    repo.saveAccountheld(acc);
                                } catch (Exception ex) {
                                    Logger.getLogger(AccountOpeningsResource.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                    );
                
                if(contacts != null)
                        contacts.forEach(
                            e->{
                                try {
                                    ContactPersonModel contact = new ContactPersonModel();
                                    contact.setId(UUID.randomUUID().toString());
                                    contact.setFullname(e.get("fullname").asText());
                                    contact.setJobtitle(e.get("jobtitle").asText());
                                    contact.setEmail(e.get("email").asText());
                                    contact.setMobilenumber(e.get("mobilenumber").asText());
                                    contact.setOfficeaddress(e.get("officeaddress").asText());
                                    System.out.println("CONTACTS---"+ contacts);
                                    repo.saveContactPerson(contact);
                                } catch (Exception ex) {
                                    Logger.getLogger(AccountOpeningsResource.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                    );
                
                    if(signatories != null)
                            signatories.forEach(
                                e->{
                                    try {
                                        SignatoryModel signatory = new SignatoryModel();
                                        signatory.setId(UUID.randomUUID().toString());
                                        signatory.setEmail(e.get("email").asText());
                                        signatory.setAlt_phonenumber(e.get("alt_phonenumber").asText());
                                        signatory.setDob(e.get("dob").asText());
                                        signatory.setFirst_name(e.get("first_name").asText());
                                        signatory.setGender(e.get("gender").asText());
                                        signatory.setLast_name(e.get("last_name").asText());
                                        signatory.setLga(e.get("lga").asText());
                                        signatory.setMarital_status(e.get("marital_status").asText());
                                        signatory.setPermit_expiry_date(e.get("permit_expiry_date").asText());
                                        signatory.setPermit_issue_date(e.get("permit_issue_date").asText());
                                        signatory.setPhonenumber(e.get("phonenumber").asText());
                                        signatory.setPlace_of_birth(e.get("place_of_birth").asText());
                                        signatory.setPlace_of_permit_issue(e.get("place_of_permit_issue").asText());
                                        signatory.setReligion(e.get("religion").asText());
                                        signatory.setResident_permit_number(e.get("resident_permit_number").asText());
                                        signatory.setResidential_address(e.get("residential_address").asText());
                                        signatory.setResidential_address_lga(e.get("residential_address_lga").asText());
                                        signatory.setResidential_address_state(e.get("residential_address_state").asText());
                                        signatory.setState_of_origin(e.get("state_of_origin").asText());
                                        signatory.setTitle(e.get("title").asText());
                                        signatory.setMiddle_name(e.get("middle_name").asText());
                                        signatory.setMother_maidenname(e.get("mother_maidenname").asText());
                                        signatory.setNationality(e.get("nationality").asText());
                                        signatory.setState(e.get("state").asText());
                                        signatory.setNextofkinName(e.get("nextofkinName").asText());
                                        signatory.setMeansofidentification(e.get("meansofidentification").asText());
                                        signatory.setIdentificationnumber(e.get("identificationnumber").asText());
                                        signatory.setTin(e.get("tin").asText());
                                        signatory.setIdissuerdate(e.get("idissuerdate").asText());
                                        signatory.setIdexpirydate(e.get("idexpirydate").asText());
                                        signatory.setOccupation(e.get("occupation").asText());
                                        signatory.setJobtitle(e.get("jobtitle").asText());
                                        signatory.setOffice(e.get("office").asText());
                                        signatory.setCitizenshipstatus(e.get("citizenshipstatus").asText());
                                        signatory.setSsn(e.get("ssn").asText());
                                        signatory.setMailingaddress(e.get("mailingaddress").asText());
                                        signatory.setCountryofResidency(e.get("countryofResidency").asText());
                                        signatory.setBvn(e.get("bvn").asText());
                                        System.out.println("SIGNATORIES---"+ signatories);
                                        repo.saveSignatory(signatory);
                                    } catch (Exception ex) {
                                        Logger.getLogger(AccountOpeningsResource.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                        );
                    accountOpeningResponse.setStatuscode("00");
                    accountOpeningResponse.setStatusmessage("Submit Successful!!");
                    return accountOpeningResponse;
        }       
    }        
                          
            
        catch(Exception ex){
            
            ex.printStackTrace();
            
            AccountOpeningResponse accountOpeningResponse = new AccountOpeningResponse();
            accountOpeningResponse.setStatuscode("96");
            accountOpeningResponse.setStatusmessage("Error , system malfunction");

            return accountOpeningResponse;
            
        }finally{
            
            if(con != null){
                con.close();
            }
            
            if(psmt != null){
                psmt.close();
            }
            
            if(rs != null){
                rs.close();
            }
            
        }                   
            return accountOpeningResponsee;
    }
    
    private void saveFile(InputStream file, String name) {
            try {
                /* Change directory path */
                java.nio.file.Path path = FileSystems.getDefault().getPath(location + name);
                /* Save InputStream as file */
                
                if (!new File(location + name).exists()) {
                    Files.copy(file, path);
                }
            } catch (IOException ie) {
                System.out.println("error saving "+ name);
                ie.printStackTrace();
            }
    }
    
    private String fileExt(String name){
        return name.split("\\.")[name.split("\\.").length-1];
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public AccountOpeningResource getAccountOpeningResource(@PathParam("id") String id) {
        return AccountOpeningResource.getInstance(id);
    }
}
