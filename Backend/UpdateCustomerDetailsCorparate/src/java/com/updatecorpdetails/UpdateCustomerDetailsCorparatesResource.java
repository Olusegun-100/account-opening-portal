/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.updatecorpdetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/updatedetailscorp")
public class UpdateCustomerDetailsCorparatesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UpdateCustomerDetailsCorparatesResource
     */
    public UpdateCustomerDetailsCorparatesResource() {
    }

    /**
     * Retrieves representation of an instance of com.updatedetails.UpdateCustomerDetailsCorparatesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of UpdateCustomerDetailsCorparateResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse postJson(String content) {
        Connection con = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        
        MyResponse myResponsee = new MyResponse();
        
        try{
            CustomerModel  customer =  new ObjectMapper().readValue(content, CustomerModel.class);
            DbConnect repo = new DbConnect(customer);  
            
            MyResponse myResponse = new MyResponse();
            
            if(!repo.accountPending(customer.getId())){
                
                JsonNode docs = new ObjectMapper().convertValue(customer.getDocuments(), JsonNode.class); 
                JsonNode held = new ObjectMapper().convertValue(customer.getAccountheld(), JsonNode.class);
                JsonNode contacts = new ObjectMapper().convertValue(customer.getContactperson(), JsonNode.class);
                JsonNode signatories = new ObjectMapper().convertValue(customer.getSignatories(), JsonNode.class);
            
                
                                
                repo.updateAccount(customer.getId());
                System.out.println("01");
                repo.updateAddress(customer.getId());
                System.out.println("02");
                repo.updateCustomer(customer.getId());
                System.out.println("03");
                
//                System.out.println("docs = "+docs);
                repo.clearAllDocs(customer.getId());
                if(docs != null)
                docs.forEach(
                    e->{
                        try {
                            DocumentModel doc = new DocumentModel();
                            doc.setDoc_id(UUID.randomUUID().toString());
                            doc.setDoc_type(e.get("doc_type").asText());
                            doc.setDate_log(LocalDate.now().toString());
                            doc.setFile_name(e.get("file_name").asText());
                            System.out.println("each docs = "+doc);
                            doc.setFile(e.get("file").asText());
                            repo.saveDocument(doc);
                        } catch (Exception ex) {
                            Logger.getLogger(UpdateCustomerDetailsCorparateResource.class.getName()).log(Level.SEVERE, null, ex);
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
                                    repo.updateAccountheld(acc);
                                } catch (Exception ex) {
                                    Logger.getLogger(UpdateCustomerDetailsCorparateResource.class.getName()).log(Level.SEVERE, null, ex);
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
                                    repo.updateContactPerson(contact);
                                } catch (Exception ex) {
                                    Logger.getLogger(UpdateCustomerDetailsCorparateResource.class.getName()).log(Level.SEVERE, null, ex);
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
                                        repo.updateSignatories(signatory);
                                    } catch (Exception ex) {
                                        Logger.getLogger(UpdateCustomerDetailsCorparateResource.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                        );
                

                myResponse.setStatuscode("00");
                myResponse.setStatusmessage("Submit Successful!!");
            }else{
                myResponse.setStatuscode("96");
                myResponse.setStatusmessage("Account no pending upload/registration");
            }
            
            return myResponse;
        }              
            
        catch(Exception ex){
            
            ex.printStackTrace();
            
            MyResponse accountOpeningResponse = new MyResponse();
            accountOpeningResponse.setStatuscode("96");
            accountOpeningResponse.setStatusmessage("Error , system malfunction");
//            he don come
            return myResponsee;
            
        }
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public UpdateCustomerDetailsCorparateResource getUpdateCustomerDetailsCorparateResource(@PathParam("id") String id) {
        return UpdateCustomerDetailsCorparateResource.getInstance(id);
    }
}
