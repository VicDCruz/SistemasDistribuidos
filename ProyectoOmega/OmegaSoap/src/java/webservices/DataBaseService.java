/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import dataBase.Conexion;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author daniel
 * @see http://localhost:8080/DataBaseService/DataBaseService?WSDL
 */
@WebService(serviceName = "DataBaseService")
@Stateless()
public class DataBaseService {

    
    /**
     * Web service operation
     * @param table
     * @param attributes
     * @param types
     * @param divider
     * @return 
     */
    @WebMethod(operationName = "createTable")
    public boolean createTable(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table, 
            @WebParam(name = "attributes") String attributes, 
            @WebParam(name = "types") String types,
            @WebParam(name = "divider") String divider) {
        Conexion conex = new Conexion(database);
        boolean output = conex.createTable(table, attributes.split(divider), types.split(divider));
        conex.close();
        return output;
    }
    
    /**
     * Web service operation
     * @param table
     * @return 
     */
    @WebMethod(operationName = "deleteTable")
    public boolean deleteTable(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table) {
        Conexion conex = new Conexion(database);
        boolean output = conex.deleteTable(table);
        conex.close();
        return output;
    }
    
    /**
     * Web service operation
     * @param table
     * @param conditions
     * @param divider
     * @return 
     */
    @WebMethod(operationName = "deleteTuple")
    public boolean deleteTuple(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table,
            @WebParam(name = "conditions") String conditions,
            @WebParam(name = "divider") String divider) {
        Conexion conex = new Conexion(database);
        boolean output = conex.deleteTuple(table, conditions.split(divider));
        conex.close();
        return output;
    }
    
    @WebMethod(operationName = "createTuple")
    public boolean createTuple(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table,
            @WebParam(name = "values") String values,
            @WebParam(name = "divider") String divider) {
        Conexion conex = new Conexion(database);
        boolean output = conex.createTuple(table, values.split(divider));
        conex.close();
        return output;
    }
    
    @WebMethod(operationName = "scrollTable")
    public String scrollTable(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table,
            @WebParam(name = "start") int start,
            @WebParam(name = "length") int length) {
        Conexion conex = new Conexion(database);
        String output = conex.printScroll(table, start, length);
        conex.close();
        return output;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "updateTuple")
    public boolean updateTuple(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table, 
            @WebParam(name = "values") String values, 
            @WebParam(name = "conditions") String conditions,
            @WebParam(name = "divider") String divider) {
        Conexion conex = new Conexion(database);
        boolean output = conex.updateTuple(table, values.split(divider), conditions.split(divider));
        conex.close();
        return output;
    }

    /**
     * Web service operation
     * @param user
     * @return 
     */
    @WebMethod(operationName = "getTablesName")
    public String getTablesName(@WebParam(name = "database") String database, 
            @WebParam(name = "user") String user) {
        Conexion conex = new Conexion(database);
        String output = conex.getTablesName(user);
        conex.close();
        return output;
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTableColumns")
    public String getTableColumns(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table) {
        Conexion conex = new Conexion(database);
        String output = conex.getTableColumns(table);
        conex.close();
        return output;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getTableColumnsTypes")
    public String getTableColumnsTypes(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table) {
        Conexion conex = new Conexion(database);
        String output = conex.getTableColumnsTypes(table);
        conex.close();
        return output;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLength")
    public String getLength(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table) {
        Conexion conex = new Conexion(database);
        String output = conex.getLength(table);
        conex.close();
        return output;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkUser")
    public String checkUser(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table, 
            @WebParam(name = "conditions") String conditions,
            @WebParam(name = "divider") String divider) {
        String output = "error at checking user";
        try {
            Conexion conex = new Conexion(database);
            output = conex.checkUser(table, conditions.split(divider));
            conex.close();
            return output;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    /**
     * Web service operation
     */
    @WebMethod(operationName = "checkPassword")
    public String checkPassword(@WebParam(name = "database") String database, 
            @WebParam(name = "table") String table, 
            @WebParam(name = "conditions") String conditions, 
            @WebParam(name = "divider") String divider) {
        String output = "error at checking password";
        try {
            Conexion conex = new Conexion(database);
            output = conex.checkPassword(table, conditions.split(divider));
            conex.close();
            return output;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
}
