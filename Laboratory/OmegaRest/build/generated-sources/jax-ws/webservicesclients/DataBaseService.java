
package webservicesclients;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "DataBaseService", targetNamespace = "http://webservices/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface DataBaseService {


    /**
     * 
     * @param database
     * @param table
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getLength", targetNamespace = "http://webservices/", className = "webservicesclients.GetLength")
    @ResponseWrapper(localName = "getLengthResponse", targetNamespace = "http://webservices/", className = "webservicesclients.GetLengthResponse")
    @Action(input = "http://webservices/DataBaseService/getLengthRequest", output = "http://webservices/DataBaseService/getLengthResponse")
    public String getLength(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table);

    /**
     * 
     * @param database
     * @param types
     * @param divider
     * @param attributes
     * @param table
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createTable", targetNamespace = "http://webservices/", className = "webservicesclients.CreateTable")
    @ResponseWrapper(localName = "createTableResponse", targetNamespace = "http://webservices/", className = "webservicesclients.CreateTableResponse")
    @Action(input = "http://webservices/DataBaseService/createTableRequest", output = "http://webservices/DataBaseService/createTableResponse")
    public boolean createTable(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table,
        @WebParam(name = "attributes", targetNamespace = "")
        String attributes,
        @WebParam(name = "types", targetNamespace = "")
        String types,
        @WebParam(name = "divider", targetNamespace = "")
        String divider);

    /**
     * 
     * @param database
     * @param divider
     * @param conditions
     * @param table
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkUser", targetNamespace = "http://webservices/", className = "webservicesclients.CheckUser")
    @ResponseWrapper(localName = "checkUserResponse", targetNamespace = "http://webservices/", className = "webservicesclients.CheckUserResponse")
    @Action(input = "http://webservices/DataBaseService/checkUserRequest", output = "http://webservices/DataBaseService/checkUserResponse")
    public String checkUser(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table,
        @WebParam(name = "conditions", targetNamespace = "")
        String conditions,
        @WebParam(name = "divider", targetNamespace = "")
        String divider);

    /**
     * 
     * @param database
     * @param divider
     * @param conditions
     * @param table
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "checkPassword", targetNamespace = "http://webservices/", className = "webservicesclients.CheckPassword")
    @ResponseWrapper(localName = "checkPasswordResponse", targetNamespace = "http://webservices/", className = "webservicesclients.CheckPasswordResponse")
    @Action(input = "http://webservices/DataBaseService/checkPasswordRequest", output = "http://webservices/DataBaseService/checkPasswordResponse")
    public String checkPassword(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table,
        @WebParam(name = "conditions", targetNamespace = "")
        String conditions,
        @WebParam(name = "divider", targetNamespace = "")
        String divider);

    /**
     * 
     * @param database
     * @param table
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteTable", targetNamespace = "http://webservices/", className = "webservicesclients.DeleteTable")
    @ResponseWrapper(localName = "deleteTableResponse", targetNamespace = "http://webservices/", className = "webservicesclients.DeleteTableResponse")
    @Action(input = "http://webservices/DataBaseService/deleteTableRequest", output = "http://webservices/DataBaseService/deleteTableResponse")
    public boolean deleteTable(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table);

    /**
     * 
     * @param database
     * @param divider
     * @param conditions
     * @param table
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "deleteTuple", targetNamespace = "http://webservices/", className = "webservicesclients.DeleteTuple")
    @ResponseWrapper(localName = "deleteTupleResponse", targetNamespace = "http://webservices/", className = "webservicesclients.DeleteTupleResponse")
    @Action(input = "http://webservices/DataBaseService/deleteTupleRequest", output = "http://webservices/DataBaseService/deleteTupleResponse")
    public boolean deleteTuple(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table,
        @WebParam(name = "conditions", targetNamespace = "")
        String conditions,
        @WebParam(name = "divider", targetNamespace = "")
        String divider);

    /**
     * 
     * @param database
     * @param divider
     * @param values
     * @param table
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "createTuple", targetNamespace = "http://webservices/", className = "webservicesclients.CreateTuple")
    @ResponseWrapper(localName = "createTupleResponse", targetNamespace = "http://webservices/", className = "webservicesclients.CreateTupleResponse")
    @Action(input = "http://webservices/DataBaseService/createTupleRequest", output = "http://webservices/DataBaseService/createTupleResponse")
    public boolean createTuple(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table,
        @WebParam(name = "values", targetNamespace = "")
        String values,
        @WebParam(name = "divider", targetNamespace = "")
        String divider);

    /**
     * 
     * @param database
     * @param start
     * @param length
     * @param table
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "scrollTable", targetNamespace = "http://webservices/", className = "webservicesclients.ScrollTable")
    @ResponseWrapper(localName = "scrollTableResponse", targetNamespace = "http://webservices/", className = "webservicesclients.ScrollTableResponse")
    @Action(input = "http://webservices/DataBaseService/scrollTableRequest", output = "http://webservices/DataBaseService/scrollTableResponse")
    public String scrollTable(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table,
        @WebParam(name = "start", targetNamespace = "")
        int start,
        @WebParam(name = "length", targetNamespace = "")
        int length);

    /**
     * 
     * @param database
     * @param divider
     * @param values
     * @param conditions
     * @param table
     * @return
     *     returns boolean
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "updateTuple", targetNamespace = "http://webservices/", className = "webservicesclients.UpdateTuple")
    @ResponseWrapper(localName = "updateTupleResponse", targetNamespace = "http://webservices/", className = "webservicesclients.UpdateTupleResponse")
    @Action(input = "http://webservices/DataBaseService/updateTupleRequest", output = "http://webservices/DataBaseService/updateTupleResponse")
    public boolean updateTuple(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table,
        @WebParam(name = "values", targetNamespace = "")
        String values,
        @WebParam(name = "conditions", targetNamespace = "")
        String conditions,
        @WebParam(name = "divider", targetNamespace = "")
        String divider);

    /**
     * 
     * @param database
     * @param user
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTablesName", targetNamespace = "http://webservices/", className = "webservicesclients.GetTablesName")
    @ResponseWrapper(localName = "getTablesNameResponse", targetNamespace = "http://webservices/", className = "webservicesclients.GetTablesNameResponse")
    @Action(input = "http://webservices/DataBaseService/getTablesNameRequest", output = "http://webservices/DataBaseService/getTablesNameResponse")
    public String getTablesName(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "user", targetNamespace = "")
        String user);

    /**
     * 
     * @param database
     * @param table
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTableColumns", targetNamespace = "http://webservices/", className = "webservicesclients.GetTableColumns")
    @ResponseWrapper(localName = "getTableColumnsResponse", targetNamespace = "http://webservices/", className = "webservicesclients.GetTableColumnsResponse")
    @Action(input = "http://webservices/DataBaseService/getTableColumnsRequest", output = "http://webservices/DataBaseService/getTableColumnsResponse")
    public String getTableColumns(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table);

    /**
     * 
     * @param database
     * @param table
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getTableColumnsTypes", targetNamespace = "http://webservices/", className = "webservicesclients.GetTableColumnsTypes")
    @ResponseWrapper(localName = "getTableColumnsTypesResponse", targetNamespace = "http://webservices/", className = "webservicesclients.GetTableColumnsTypesResponse")
    @Action(input = "http://webservices/DataBaseService/getTableColumnsTypesRequest", output = "http://webservices/DataBaseService/getTableColumnsTypesResponse")
    public String getTableColumnsTypes(
        @WebParam(name = "database", targetNamespace = "")
        String database,
        @WebParam(name = "table", targetNamespace = "")
        String table);

}
