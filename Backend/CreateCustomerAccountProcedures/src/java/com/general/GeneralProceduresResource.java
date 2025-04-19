/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.general;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
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
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/procedure")
public class GeneralProceduresResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GeneralProceduresResource
     */
    public GeneralProceduresResource() {
    }

    /**
     * Retrieves representation of an instance of com.general.GeneralProceduresResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of GeneralProcedureResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse postJson(String content) {
        MyResponse myResponse = new MyResponse();
         Optional.ofNullable(content).ifPresent(
                data -> {
                   
            try {
               GeneralModel generalmodel = new ObjectMapper().readValue(content,GeneralModel.class);           
               URL customer_url= new URL("http://105.112.2.19:8080/CUSProcedure/webresources/cusprocedure");
               HttpURLConnection customer_conn = (HttpURLConnection) customer_url.openConnection();

               customer_conn.setRequestMethod("POST");
               customer_conn.setRequestProperty("Accept", "application/json");
               customer_conn.setRequestProperty("Content-Type", "application/json; charset=utf8");
               //con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                   
                customer_conn.setDoOutput(true);                
               
                try(OutputStream customer_os = customer_conn.getOutputStream()) {
                    byte[] customer_input = generalmodel.customer_toString().getBytes("utf-8");
                    customer_os.write(customer_input, 0, customer_input.length);			
                }               
//                System.out.println("code = "+customer_conn.getResponseCode());
//                System.out.println("connection = "+customer_conn.getResponseCode());
//                System.out.println("..... request = "+generalmodel.customer_toString());
                
                if (customer_conn.getResponseCode() == 200) {
                    InputStreamReader in = new InputStreamReader(customer_conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;                    
                    while ((output = br.readLine()) != null) {
                        HashMap<String,String> resp = new ObjectMapper().readValue(output, HashMap.class);
                        if(resp.get("cusId") != null) {     
                            System.out.println("...cusId = "+resp.get("cusId").equals(null));
                            generalmodel.setCusId(resp.get("cusId").toString());
                            HashMap<String,Object> respy = Procedure.accountProcedure(generalmodel.account_toString());
                            if (respy != null) {
                                myResponse.setStatuscode("00");
                                myResponse.setAcctNo(respy.get("acctNo").toString());
                                myResponse.setCusId(resp.get("cusId").toString());
                                myResponse.setStatusmessage("Success");
                            } else {
                                myResponse.setStatuscode("96");
                                myResponse.setStatusmessage("ERROR");
                            }
                        } else {
                                myResponse.setStatuscode("96");
                                myResponse.setStatusmessage("ERROR");
                        }
                    }
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
    public GeneralProcedureResource getGeneralProcedureResource(@PathParam("id") String id) {
        return GeneralProcedureResource.getInstance(id);
    }
}
