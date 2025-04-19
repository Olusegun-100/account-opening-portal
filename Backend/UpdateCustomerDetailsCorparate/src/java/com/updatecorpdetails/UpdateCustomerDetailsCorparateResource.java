/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.updatecorpdetails;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
public class UpdateCustomerDetailsCorparateResource {

    private String id;

    /**
     * Creates a new instance of UpdateCustomerDetailsCorparateResource
     */
    private UpdateCustomerDetailsCorparateResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the UpdateCustomerDetailsCorparateResource
     */
    public static UpdateCustomerDetailsCorparateResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of UpdateCustomerDetailsCorparateResource class.
        return new UpdateCustomerDetailsCorparateResource(id);
    }

    /**
     * Retrieves representation of an instance of com.updatedetails.UpdateCustomerDetailsCorparateResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of UpdateCustomerDetailsCorparateResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource UpdateCustomerDetailsCorparateResource
     */
    @DELETE
    public void delete() {
    }
}
