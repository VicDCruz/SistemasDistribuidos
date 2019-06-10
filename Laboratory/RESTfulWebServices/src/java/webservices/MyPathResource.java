/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("MyPath/{username}")
public class MyPathResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MyPathResource
     */
    public MyPathResource() {
    }
    
    @GET
    @Produces("text/plain")
    public String getText(@PathParam("username")String username){
        return "UserName: "+username;
    }

    /**
     * Retrieves representation of an instance of webservices.MyPathResource
     * @return an instance of java.lang.String
     */
    /*@GET
    @Produces("text/html")
    public String getHtml(@QueryParam("name")String name, 
                @QueryParam("age")String age) {
        // return "Mi primer servicio RESTful";
        return "Hola, mi nombre es " + name + " con " + age + " de edad.";
    }*/
    
    /**
     * Retrieves representation of an instance of webservices.MyPathResource
     * @return an instance of java.lang.String
     */
    @POST
    @Produces("text/html")
    public String postHtml(@FormParam("name")String name, 
                @FormParam("age")String age) {
        // return "Mi primer servicio RESTful";
        return "Hola, mi nombre es " + name + " con " + age + " de edad.";
    }

    /**
     * PUT method for updating or creating an instance of MyPathResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
        System.out.println(content);
    }
}
