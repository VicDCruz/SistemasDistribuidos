/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author daniel
 */
@Path("Operations")
public class OperationsResource {
    
    private static final String TABLE = "table";
    private static final String TUPLE = "tuple";

    @Context
    private UriInfo context;
    private JSONObject json;

    /**
     * Creates a new instance of OperationsResource
     */
    public OperationsResource() {
    }

    /**
     * Retrieves representation of an instance of webservices.OperationsResource
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        this.json = new JSONObject();
        this.json.put("hello", "world!");
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
        this.json = new JSONObject();
    }
    
    /**
     * POST method for updating or creating an instance of OperationsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/json")
    public String postJson(String content) {
        this.json = new JSONObject();
        try {
            JSONObject input = (JSONObject) (new JSONParser()).parse(content);
            if (input.get("type").equals(OperationsResource.TABLE))
                OperationsResource.createTable(content, content, content, content);
            else if (input.get("type").equals(OperationsResource.TUPLE))
                OperationsResource.createTuple(content, content, content);
        } catch (ParseException ex) {
            Logger.getLogger(OperationsResource.class.getName()).log(Level.SEVERE, null, ex);
            this.json.put("Error", ex);
        }
        return this.json.toString();
    }
    
    /**
     * DELETE method for updating or creating an instance of OperationsResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @DELETE
    @Consumes("application/json")
    public void deleteJson(String content) {
        this.json = new JSONObject();
    }

    private static boolean createTable(java.lang.String table, java.lang.String attributes, java.lang.String types, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.createTable(table, attributes, types, divider);
    }

    private static boolean createTuple(java.lang.String table, java.lang.String values, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.createTuple(table, values, divider);
    }

    private static boolean deleteTable(java.lang.String table) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.deleteTable(table);
    }

    private static boolean deleteTuple(java.lang.String table, java.lang.String conditions, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.deleteTuple(table, conditions, divider);
    }

    private static boolean getDataBaseConnection(java.lang.String databaseName) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.getDataBaseConnection(databaseName);
    }

    private static java.util.List<webservicesclients.StringArray> scrollTable(java.lang.String table, int start, int length) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.scrollTable(table, start, length);
    }
}
