/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corparate;

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

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/corparateAcct")
public class CorparateAcctTypesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CorparateAcctTypesResource
     */
    public CorparateAcctTypesResource() {
    }

    /**
     * Retrieves representation of an instance of com.corparate.CorparateAcctTypesResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse getJson() {
       try {
            
            MyResponse myResponse = new MyResponse();
            DbConnect db = new DbConnect();            
            
            myResponse = db.getaccoType();
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
     * POST method for creating an instance of CorparateAcctTypeResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postJson(String content) {
        //TODO
        return Response.created(context.getAbsolutePath()).build();
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public CorparateAcctTypeResource getCorparateAcctTypeResource(@PathParam("id") String id) {
        return CorparateAcctTypeResource.getInstance(id);
    }
}
