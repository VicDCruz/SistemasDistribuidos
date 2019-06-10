/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import org.json.simple.JSONArray;
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

    private static final String DIVIDER = ",";
    private static final String TABLE = "table";
    private static final String TUPLE = "tuple";
    private static final String NAME = "name";
    private static final String SCROLL = "scroll";
    private static final String COLUMNS = "columns";
    private static final String LENGTH = "length";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String DATABASE = "myThirdDataBase";

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
     * @param type
     * @param user
     * @param table
     * @param start
     * @param length
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String scroll(
            @QueryParam("user") String user, 
            @QueryParam("password") String password, 
            @QueryParam("table") String table,
            @QueryParam("start") String start,
            @QueryParam("length") String length,
            @QueryParam("type") String type) {
        this.json = new JSONObject();
        if (type != null && type.equals(SCROLL)) {
            int startNum = -1, lengthNum = -1;
            if (start != null && start.matches("-?\\d+(\\.\\d+)?"))
                startNum = Integer.parseInt(start);
            if (length != null && length.matches("-?\\d+(\\.\\d+)?"))
                lengthNum = Integer.parseInt(length);
            if (user != null && !user.equals("") && 
                table != null && !table.equals("") &&
                startNum >= 0 && lengthNum >= 0) {
                String result = OperationsResource.scrollTable(table.toUpperCase(), startNum, lengthNum);
                JSONArray ja = new JSONArray();
                System.out.println("SCROLL: " + result);
                if (!result.equals(""))
                    ja.addAll(Arrays.asList(result.split("\n")));
                this.json.put("result", ja);
            }
            else
                this.json.put("error", "Some parameters where not correct");
        } else if (type != null && type.equals(NAME) && user != null & !user.equals("")) {
            String names = OperationsResource.getTablesName(user.toUpperCase());
            JSONArray ja = new JSONArray();
            ja.addAll(Arrays.asList(names.split(DIVIDER)));
            this.json.put("result", ja);
        } else if (type != null && type.equals(COLUMNS) && 
                table != null && !table.equals("")) {
            String columns = OperationsResource.getTableColumns(table.toUpperCase());
            JSONArray ja = new JSONArray();
            ja.addAll(Arrays.asList(columns.split(DIVIDER)));
            this.json.put("result", ja);
            String columnsTypes = OperationsResource.getTableColumnsTypes(table.toUpperCase());
            ja = new JSONArray();
            ja.addAll(Arrays.asList(columnsTypes.split(DIVIDER)));
            this.json.put("columnsTypes", ja);
        } else if (type != null && type.equals(LENGTH) && 
                table != null && !table.equals("")) {
            String result = OperationsResource.getLength(table.toUpperCase());
            this.json.put("result", result);
        } else if (type != null && type.equals(USER) && 
                user != null && !user.equals("")) {
            String result = OperationsResource.checkUser(table, "name='" + user + "'", DIVIDER);
            this.json.put("name", result);
        } else if (type != null && type.equals(PASSWORD) && 
                user != null && !user.equals("") && 
                password != null && !password.equals("")) {
            String conditions = "name='" + user + "',password='" + password + "'";
            String result = OperationsResource.checkPassword(table, conditions, DIVIDER);
            String[] resArray = result.split("|");
            this.json.put("name", resArray[0]);
            this.json.put("password", resArray[1]);
        } else
            this.json.put("error", "Some parameters where not correct");
        return this.json.toString();
    }

    /**
     * PUT method for updating or creating an instance of OperationsResource
     * @param content representation for the resource: user, table, values, conditions
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    @Produces("application/xml")
    public String putJson(String content) {
        String output = "";
        JSONObject input = null;
        try {
            input = (JSONObject) (new JSONParser()).parse(content);
        } catch (ParseException ex) {
            Logger.getLogger(OperationsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.isNaE(input, new String[] {
            "user", "table", "values", "conditions"
        })) {
            String table = (String) input.get("table"), 
                    values = (String) input.get("values"), user = (String) input.get("user"),
                    conditions = (String) input.get("conditions");
            boolean result = OperationsResource.updateTuple(table, values, conditions, DIVIDER);
            output = "<result>" + result + "</result>";
        } else
            output = "<error>Some parameters where not correct</error>";
        return output;
    }
    
    /**
     * POST method for updating or creating an instance of OperationsResource
     * @param content representation for the resource: type, user, table, attributes,
     * types, values
     * @return an HTTP response with content of the updated or created resource.
     */
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public String postJson(String content) {
        this.json = new JSONObject();
        JSONObject input = null;
        try {
            input = (JSONObject) (new JSONParser()).parse(content);
        } catch (ParseException ex) {
            Logger.getLogger(OperationsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.isNaE(input, new String[] {
            "type", "user", "table", "attributes", "types", "values"
        })) {
            String tableName = (String) input.get("table");
            String output = "";
            String type = (String) input.get("type"), 
                    attributes = (String) input.get("attributes"), types = (String) input.get("types"),
                    values = (String) input.get("values"), user = (String) input.get("user");
            if (type.equals(OperationsResource.TABLE))
                output += OperationsResource.createTable((tableName + "_" + user).toUpperCase(), attributes, types, DIVIDER);
            else if (type.equals(OperationsResource.TUPLE))
                output += OperationsResource.createTuple(tableName, values, DIVIDER);
            this.json.put("result", output);
            this.json.put("method", "post");
        } else
            this.json.put("error", "Some parameters where not correct");
        return this.json.toString();
    }
    
    /**
     * DELETE method for updating or creating an instance of OperationsResource
     * @param content representation for the resource: type, user, table, conditions
     * @return an HTTP response with content of the updated or created resource.
     */
    @DELETE
    @Consumes("application/json")
    @Produces("application/xml")
    @Path("/{json}")
    public String deleteJson(@PathParam("json") String content) {
        String output = "";
        System.out.println(content);
        JSONObject input = null;
        try {
            input = (JSONObject) (new JSONParser()).parse(content);
        } catch (ParseException ex) {
            Logger.getLogger(OperationsResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (this.isNaE(input, new String[] {
            "type", "user", "table", "conditions"
        })) {
            String result = "", tableName = (String) input.get("table");
            String type = (String) input.get("type"), conditions = (String) input.get("conditions");
            if (type.equals(OperationsResource.TABLE))
                result += OperationsResource.deleteTable(tableName);
            else if (type.equals(OperationsResource.TUPLE))
                result += OperationsResource.deleteTuple(tableName, conditions, DIVIDER);
            output = "<result>" + result + "</result>";
            output += "<method>delete</method>";
            
        } else
            output = "<error>Some parameters where not correct</error>";
        return output;
    }
    
    private boolean isNaE(JSONObject input, String[] values) {
        boolean output = true;
        for (String value : values){
            System.out.println(value + ": " + input.containsKey(value)+ ", " + input.get(value));
            output = output && input.containsKey(value) && !((String) input.get(value)).equals("");
        }
        return output;
    }

    private static boolean createTable(java.lang.String table, java.lang.String attributes, java.lang.String types, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.createTable(DATABASE, table, attributes, types, divider);
    }

    private static boolean createTuple(java.lang.String table, java.lang.String values, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.createTuple(DATABASE, table, values, divider);
    }

    private static boolean deleteTable(java.lang.String table) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.deleteTable(DATABASE, table);
    }

    private static boolean deleteTuple(java.lang.String table, java.lang.String conditions, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.deleteTuple(DATABASE, table, conditions, divider);
    }

    private static String scrollTable(java.lang.String table, int start, int length) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.scrollTable(DATABASE, table, start, length);
    }

    private static boolean updateTuple(java.lang.String table, java.lang.String values, java.lang.String conditions, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.updateTuple(DATABASE, table, values, conditions, divider);
    }

    private static String getTablesName(java.lang.String user) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.getTablesName(DATABASE, user);
    }

    private static String getTableColumns(java.lang.String table) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.getTableColumns(DATABASE, table);
    }

    private static String getTableColumnsTypes(java.lang.String table) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.getTableColumnsTypes(DATABASE, table);
    }

    private static String getLength(java.lang.String table) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.getLength(DATABASE, table);
    }

    private static String checkPassword(java.lang.String table, java.lang.String conditions, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.checkPassword(DATABASE, table, conditions, divider);
    }

    private static String checkUser(java.lang.String table, java.lang.String conditions, java.lang.String divider) {
        webservicesclients.DataBaseService_Service service = new webservicesclients.DataBaseService_Service();
        webservicesclients.DataBaseService port = service.getDataBaseServicePort();
        return port.checkUser(DATABASE, table, conditions, divider);
    }
}
