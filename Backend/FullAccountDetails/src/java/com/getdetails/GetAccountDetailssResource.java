/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.getdetails;


import com.database.DbConnect;
import com.getdetailsModel.AccountModel;
import com.getdetailsModel.MyResponse;
//import com.model.ApiResponse;
import com.security.Decrypt;
import com.security.Encrypt;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
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
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/getdetails")
public class GetAccountDetailssResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetAccountDetailssResource
     */
    public GetAccountDetailssResource() {
    }

    /**
     * Retrieves representation of an instance of com.getdetails.GetAccountDetailssResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse myResponse() { 
      return null;
    }

    /**
     * POST method for creating an instance of GetAccountDetailsResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
   @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse postJson(String id) throws Exception {
        ApiResponse apiResponse = new ApiResponse();
        MyResponse myResponse = new MyResponse();          
        try {
                   DbConnect db=new DbConnect();       
                   AccountModel accountmodel = new AccountModel();
                   HashMap user = new ObjectMapper().readValue(id, HashMap.class);
                   System.out.println("request = "+user);
            if(user.containsKey("data")){
                String data = Decrypt.decryptAES(user.get("data").toString(), "ILoveJesusPlenty");

                    Map<String ,String> userDetails = new ObjectMapper().readValue(data, Map.class);
                    System.out.println("data = "+userDetails.get("id"));

                    System.out.println("first");
                   myResponse = db.getFullAccountDetails(userDetails.get("id"));
                    System.out.println("SECOND");

                   
            } else {
                    myResponse.setStatuscode("96");
                    myResponse.setStatusmessage("invalid credentials");
            }
                   
        } catch (Exception ex) {
            
            myResponse.setStatuscode("96");
            myResponse.setStatusmessage("System malfunction....");
            ex.printStackTrace();         
        }
        apiResponse.setData(Base64.getEncoder().encodeToString(Encrypt.encryptAES(myResponse.toString())));
        System.out.println("Final" + Decrypt.decryptAES(apiResponse.getData(), "ILoveJesusPlenty"));
        return apiResponse;
        
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public GetAccountDetailsResource getGetAccountDetailsResource(@PathParam("id") String id) {
        return GetAccountDetailsResource.getInstance(id);
    }
}
