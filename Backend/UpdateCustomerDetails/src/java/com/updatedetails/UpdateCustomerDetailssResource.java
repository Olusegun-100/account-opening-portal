/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.updatedetails;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
@Path("/updatedetails")
public class UpdateCustomerDetailssResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UpdateCustomerDetailssResource
     */
    public UpdateCustomerDetailssResource() {
    }

    /**
     * Retrieves representation of an instance of com.updatedetails.UpdateCustomerDetailssResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of UpdateCustomerDetailsResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse postJson(String content) throws SQLException {
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
                
                                
                repo.updateAccount(customer.getId());
                System.out.println("01");
                repo.updateAddress(customer.getId());
                System.out.println("02");
                repo.updateCustomer(customer.getId());
                System.out.println("03");
                repo.updateNextOfKin(customer.getId());
                
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
                            Logger.getLogger(UpdateCustomerDetailsResource.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                );    
                

                myResponse.setStatuscode("00");
                myResponse.setStatusmessage("Submit Successful!!");
            }else{
                myResponse.setStatuscode("96");
                myResponse.setStatusmessage("Account no pending upload/registration");
            }
            
//            System.out.println("documents map = "+docs);
//            
//            org.acct.opening.NbgrConnections nbgrconnections = new org.acct.opening.NbgrConnections();
//            con = nbgrconnections.mySqlDBconnection();
//            con.setAutoCommit(false);
//            

            return myResponse;
        }              
            
        catch(Exception ex){
            
            ex.printStackTrace();
            
            MyResponse accountOpeningResponse = new MyResponse();
            accountOpeningResponse.setStatuscode("96");
            accountOpeningResponse.setStatusmessage("Error , system malfunction");

            return myResponsee;
            
        }
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public UpdateCustomerDetailsResource getUpdateCustomerDetailsResource(@PathParam("id") String id) {
        return UpdateCustomerDetailsResource.getInstance(id);
    }
}
