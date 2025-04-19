/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.account;

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
public class SpAccountResource {

    private String id;

    /**
     * Creates a new instance of SpAccountResource
     */
    private SpAccountResource(String id) {
        this.id = id;
    }

    /**
     * Get instance of the SpAccountResource
     */
    public static SpAccountResource getInstance(String id) {
        // The user may use some kind of persistence mechanism
        // to store and restore instances of SpAccountResource class.
        return new SpAccountResource(id);
    }

    /**
     * Retrieves representation of an instance of com.account.SpAccountResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of SpAccountResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    /**
     * DELETE method for resource SpAccountResource
     */
    @DELETE
    public void delete() {
    }
}
