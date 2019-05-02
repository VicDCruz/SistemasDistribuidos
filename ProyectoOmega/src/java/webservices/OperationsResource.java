/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import org.json.simple.JSONObject;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("Operations")
public class OperationsResource {

    @Context
    private UriInfo context;
    private JSONObject json;

    /**
     * Creates a new instance of OperationsResource
     */
    public OperationsResource() {
        this.json = new JSONObject();
    }

    /**
     * Retrieves representation of an instance of webservices.OperationsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        json.put("hello", "world!");
        return json.toString();
    }

    /**
     * PUT method for updating or creating an instance of OperationsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
