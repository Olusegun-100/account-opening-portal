/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.account;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.HashMap;
import java.util.Optional;
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
import org.codehaus.jackson.map.ObjectMapper;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("/createAccount")
public class SpAccountsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SpAccountsResource
     */
    public SpAccountsResource() {
    }

    /**
     * Retrieves representation of an instance of com.account.SpAccountsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * POST method for creating an instance of SpAccountResource
     * @param content representation for the new resource
     * @return an HTTP response with content of the created resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MyResponse postJson(AccountModel accountModel) {
             
        MyResponse myResponse = new MyResponse();
        Optional.ofNullable(accountModel).ifPresent(
                data -> {
                   
            try {
               URL account_url= new URL("http://105.112.2.19:8080/ACTProcedure/webresources/ActProcedure");
               HttpURLConnection account_conn = (HttpURLConnection) account_url.openConnection();

               account_conn.setRequestMethod("POST");
               account_conn.setRequestProperty("Accept", "application/json");
               account_conn.setRequestProperty("Content-Type", "application/json; charset=utf8");
               //con.setRequestProperty("Content-Type", "application/json; charset=utf8");
                   
                account_conn.setDoOutput(true);                
               
                try(OutputStream account_os = account_conn.getOutputStream()) {
                    byte[] account_input = accountModel.account_toString().getBytes("utf-8");
                    account_os.write(account_input, 0, account_input.length);			
                }               
                System.out.println("code = "+account_conn.getResponseCode());
                System.out.println("connection = "+account_conn.getResponseCode());
                System.out.println("request = "+accountModel.account_toString());
                
                if (account_conn.getResponseCode() == 200) {
                    
                    InputStreamReader in = new InputStreamReader(account_conn.getInputStream());
                    BufferedReader br = new BufferedReader(in);
                    String output;
                    
                    while ((output = br.readLine()) != null) {
                        myResponse.setStatuscode("00");
                        myResponse.setStatusmessage("Successful");                       
                        HashMap<String,Object> resp = new ObjectMapper().readValue(output, HashMap.class);
                        HashMap<String,Object> acctNoresp = new ObjectMapper().convertValue(resp.get("acctNo"),HashMap.class);
                        myResponse.setAcctNo("ACCTNO");
                        System.out.println("output 111: " + output);
                        System.out.println("0000");
                        System.out.println("ACCT" + acctNoresp.get("acctNo"));
                    }
                    System.out.println("out: "+output);
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
    public SpAccountResource getSpAccountResource(@PathParam("id") String id) {
        return SpAccountResource.getInstance(id);
    }
}
