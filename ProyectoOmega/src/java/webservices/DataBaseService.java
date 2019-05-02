/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webservices;

import dataBase.Conexion;
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
    private Conexion conex;

    /**
     * Web service operation
     * @param databaseName
     * @return 
     */
    @WebMethod(operationName = "getDataBaseConnection")
    public boolean getDataBaseConnection(@WebParam(name = "databaseName") String databaseName) {
        conex = new Conexion(databaseName);
        return this.conex.hasConnection();
    }

    /**
     * Web service operation
     * @param table
     * @param attributes
     * @param types
     * @param divider
     * @return 
     */
    @WebMethod(operationName = "createTable")
    public boolean createTable(@WebParam(name = "table") String table, 
            @WebParam(name = "attributes") String attributes, 
            @WebParam(name = "types") String types,
            @WebParam(name = "divider") String divider) {
        return this.conex.createTable(table, attributes.split(divider), types.split(divider));
    }
    
    /**
     * Web service operation
     * @param table
     * @return 
     */
    @WebMethod(operationName = "deleteTable")
    public boolean deleteTable(@WebParam(name = "table") String table) {
        return this.conex.deleteTable(table);
    }
    
    /**
     * Web service operation
     * @param table
     * @param conditions
     * @param divider
     * @return 
     */
    @WebMethod(operationName = "deleteTuple")
    public boolean deleteTuple(@WebParam(name = "table") String table,
            @WebParam(name = "conditions") String conditions,
            @WebParam(name = "divider") String divider) {
        return this.conex.deleteTuple(table, conditions.split(divider));
    }
    
    @WebMethod(operationName = "createTuple")
    public boolean createTuple(@WebParam(name = "table") String table,
            @WebParam(name = "values") String values,
            @WebParam(name = "divider") String divider) {
        return this.conex.createTuple(table, values.split(divider));
    }
    
    @WebMethod(operationName = "scrollTable")
    public String[][] scrollTable(@WebParam(name = "table") String table,
            @WebParam(name = "start") int start,
            @WebParam(name = "length") int length) {
        return this.conex.scrollTable(table, start, length);
    }
}
