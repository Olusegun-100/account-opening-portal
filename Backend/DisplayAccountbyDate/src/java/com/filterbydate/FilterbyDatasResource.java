/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filterbydate;

import com.database.DbConnect;
import java.util.HashMap;
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
import com.model.MyResponse;
import com.model.AccountModel;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/filterbydate")
public class FilterbyDatasResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of FilterbyDatasResource
     */
    public FilterbyDatasResource() {
    }

    /**
     * Retrieves representation of an instance of com.filterbydate.FilterbyDatasResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of FilterbyDataResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse postJson(String dateRange) {
        try {
                   MyResponse myResponse =new MyResponse();
                   DbConnect db=new DbConnect();                          
                   HashMap<String, String> date = new ObjectMapper().readValue(dateRange, HashMap.class);
                   int page = 0;
//                   int size = 10;
                   
                   
                   if(date.containsKey("page")) page = Integer.parseInt(date.get("page"));
//                   if(date.containsKey("size")) size = Integer.parseInt(date.get("size"));
                   
                   myResponse = db.filterByDate(
                            date.get("staffId"),date.get("status"),date.get("startDate"), date.get("endDate"),
                           page
                   );
//                   1def6b47-a7d5-49e9-8a36-446638305d46
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
    public FilterbyDataResource getFilterbyDataResource(@PathParam("id") String id) {
        return FilterbyDataResource.getInstance(id);
    }
}
