/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.getaccttable;

import com.database.DbConnect;
import com.gettable.AccountModel;
import com.gettable.ApiResponse;
import com.gettable.MyResponse;
import com.security.Decrypt;
import com.security.Encrypt;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
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
import javax.ws.rs.core.Response.ResponseBuilder;
//import javax.ws.rs.core.Response;
import org.codehaus.jackson.map.ObjectMapper;
/**
 * REST Web Service
 *
 * @author User
 */
@Path("/GetAccountTable")
//@CrossOrigin(origins="*");
public class GetAccountTableByIDsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GetAccountTableByIDsResource
     */
    public GetAccountTableByIDsResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.getaccttable.GetAccountTableByIDsResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse MyResponse() {
      return null;       
    }

    /**
     * POST method for creating an instance of GetAccountTableByIDResource
     *
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse postJson(String content) throws Exception {
        
        ApiResponse apiResponse = new ApiResponse();    
        MyResponse myResponse = new MyResponse();

        try {
            
            DbConnect db = new DbConnect();            
            HashMap user = new ObjectMapper().readValue(content, HashMap.class);  
            if(user.containsKey("data")) {
                String data = Decrypt.decryptAES(user.get("data").toString(), "ILoveJesusPlenty");
                    Map<String ,String> userDetails = new ObjectMapper().readValue(data, Map.class);

            myResponse = db.getaccountDetails(userDetails.get("status"), userDetails.get("staffId"));
            } else {
                myResponse.setStatuscode("96");
                myResponse.setStatusmessage("Invalid Request");
            }
        } catch (Exception ex) {
            
            myResponse.setStatuscode("96");
            myResponse.setStatusmessage("System malfunction....");
            ex.printStackTrace();
            
        }
        
        
        apiResponse.setData(Base64.getEncoder().encodeToString(Encrypt.encryptAES(myResponse.toString())));
        return apiResponse;
//        return myResponse;
        
        
//        ResponseBuilder responseBuilder = Response.status(Status.OK).entity(data);
//        responseBuilder.header("Access-Control-Allow-Origin", "*")
//                       .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                       .header("Access-Control-Allow-Credentials", "true")
//                       .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD");
//        
//        return responseBuilder.build();
        
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public GetAccountTableByIDResource getGetAccountTableByIDResource(@PathParam("id") String id) {
        return GetAccountTableByIDResource.getInstance(id);
    }
}
