/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.acctofficer;

import java.util.HashMap;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
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
@Path("/accountOfficer")
public class AccountOfficersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AccountOfficersResource
     */
    public AccountOfficersResource() {
    }

    /**
     * Retrieves representation of an instance of com.acctofficer.AccountOfficersResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of AccountOfficerResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse postJson(String content) {
       try {
            
            MyResponse myResponse = new MyResponse();
            DbConnect db = new DbConnect();            
            HashMap user = new ObjectMapper().readValue(content, HashMap.class);
            
            
            
            myResponse = db.getaccountOfficer(user.get("branch_code").toString());
//            myResponse = db.getaccountDetails("pending");
            return myResponse;

        } catch (Exception ex) {
            
            MyResponse myResponse = new MyResponse();
            myResponse.setStatuscode("96");
            myResponse.setStatusmessage("System malfunction....");
            ex.printStackTrace();
            return myResponse;
            
        }
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public AccountOfficerResource getAccountOfficerResource(@PathParam("id") String id) {
        return AccountOfficerResource.getInstance(id);
    }
}
