/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.login;

import com.database.DbConnect;
import com.encryption.Decrypt;
import com.encryption.Encrypt;
import com.model.ApiResponse;
import com.model.LoginModel;
import com.model.MyResponse;
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
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/adminportallogin")
public class AdminLoginsResource {
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdminLoginsResource
     */
    public AdminLoginsResource() {
    }

    /**
     * Retrieves representation of an instance of com.login.AdminLoginsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
   
    /**
     * POST method for creating an instance of AdminLoginResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse postJson(String req) throws Exception{
        
        MyResponse myResponse = new MyResponse();
        try {
            
        
         Map<String ,String> request = new ObjectMapper().readValue(req, Map.class);
            System.out.println("rrrequest = "+request);
            if(request.containsKey("data")){
                    String data = Decrypt.decryptAES(request.get("data"), "ILoveJesusPlenty");
                    System.out.println("data = "+data);

                    Map<String ,String> userDetails = new ObjectMapper().readValue(data, Map.class);

                       if(userDetails.containsKey("username") && userDetails.containsKey("password")){

                           LoginModel loginmodel = new DbConnect().OnLogin(userDetails);
                           if(loginmodel != null) {
                                myResponse.setStatuscode("00");
                                myResponse.setStatusmessage("Successful!");
                                myResponse.setData(loginmodel.toString()); 
//                              myResponse.setData(Encrypt.encryptAES(loginmodel.toString()));                                  

                           }
                           else {
                               myResponse.setStatuscode("98");
                               myResponse.setStatusmessage("invalid credentials");
                               
                           }

                       }else{
                           myResponse.setStatuscode("96");
                           myResponse.setStatusmessage("All fields are required! [Username, password]"); 
                           
                   }             

            }else{
                myResponse.setStatuscode("96");
                myResponse.setStatusmessage("Invalid Request");
                
            }
//            return myResponse;
//             
        } catch (Exception ex){
            ex.printStackTrace();
            myResponse.setStatuscode("96");
            myResponse.setStatusmessage("Account does not exist!");
//            return myResponse;
        }
        
        ApiResponse apiResponse = new ApiResponse();
        
        apiResponse.setData(Base64.getEncoder().encodeToString(Encrypt.encryptAES(myResponse.toString())));
        return apiResponse;
        
        

//return null;
    }  

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public AdminLoginResource getAdminLoginResource(@PathParam("id") String id) {
        return AdminLoginResource.getInstance(id);
    }
}
