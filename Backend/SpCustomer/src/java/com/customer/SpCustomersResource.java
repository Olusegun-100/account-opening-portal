/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.customer;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;
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
@Path("/createCustomer")
public class SpCustomersResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SpCustomersResource
     */
    public SpCustomersResource() {
    }

    /**
     * Retrieves representation of an instance of com.customer.SpCustomersResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of SpCustomerResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse postJson(CustomerModel customerModel) {
        MyResponse myResponse = new MyResponse();
        Optional.ofNullable(customerModel).ifPresent(
                data -> {
                   
            try {
               URL customer_url= new URL("http://105.112.2.19:8080/CUSProcedure/webresources/cusprocedure");
               
               HttpURLConnection customer_conn = (HttpURLConnection) customer_url.openConnection();

                customer_conn.setRequestMethod("POST");
                customer_conn.setRequestProperty("Accept", "application/json");
                customer_conn.setRequestProperty("Content-Type", "application/json; charset=utf8");
                customer_conn.setDoOutput(true);                
               
                try(OutputStream customer_os = customer_conn.getOutputStream()) {
                    byte[] customer_input = customerModel.customer_toString().getBytes("utf-8");
                    customer_os.write(customer_input, 0, customer_input.length);			
                }               
                System.out.println("Sms code = "+customer_conn.getResponseCode());
                System.out.println("Sms connection = "+customer_conn.getResponseCode());
                System.out.println("Sms request = "+customerModel.customer_toString());
                
               
                if (customer_conn.getResponseCode() == 200) {
                        myResponse.setStatuscode("00");
                        myResponse.setStatusmessage("Successful");
                        myResponse.setCusId("CussssNO");
                }else{
                    myResponse.setStatuscode("96");
                    myResponse.setStatusmessage("Faild 001");
                }         
                    
                }   
                catch(Exception ex) {
                    ex.printStackTrace();
                }        
                }                
               );
        return myResponse;
    }

    /**
     * Sub-resource locator method for {id}
     */
    @Path("{id}")
    public SpCustomerResource getSpCustomerResource(@PathParam("id") String id) {
        return SpCustomerResource.getInstance(id);
    }
}
