/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.corparate;

import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
public class CorparateAcctTypeResource {

    private String id;

    /**
     * Creates a new instance of CorparateAcctTypeResource
     */
    private CorparateAcctTypeResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the CorparateAcctTypeResource
     */
    public static CorparateAcctTypeResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of CorparateAcctTypeResource class.
        return new CorparateAcctTypeResource(id);
    }

    /**
     * Retrieves representation of an instance of com.corparate.CorparateAcctTypeResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of CorparateAcctTypeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource CorparateAcctTypeResource
     */
    @DELETE
    public void delete() {
    }
}
