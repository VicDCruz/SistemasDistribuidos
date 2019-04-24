/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paola y Victor
 */
public class Conexion {
    private String database;
    private Connection con;
    private Statement query;

    public Conexion(String database) {
        this.database = database;
        if (!this.connectDatabase())
            this.createDatabase();
    }
    
    private boolean connectDatabase() {
        boolean output = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + this.database + ";", "root", "root");
            this.query = con.createStatement();
            output = true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    private boolean createDatabase() {
        boolean output = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + this.database + ";create=true;", "root", "root");
            this.query = con.createStatement();
            output = true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public boolean createTable(String table, String[] attributes, String[] types) {
        // falta poner los atributos y tipos en el query
        String queryString = "create table " + table + " (id int not null, name " +
                "varchar(25),gender varchar(20),address varchar(50),phone varchar(20), primary key(id))";
        return this.execQueryTable(queryString);
    }
    
    public boolean deleteTable(String table) {
        String queryString = "DROP TABLE " + table;
        return this.execQueryTable(queryString);
    }
    
    public boolean insertTuple(String table, String[] values) {
        String queryString = "INSERT INTO " + table + " VALUES (";
        for (String value : values)
            queryString += value + ",";
        queryString = queryString.substring(0, queryString.length() - 2) + ")";
        return this.execQueryTable(queryString);
    }
    
    public boolean deleteTuple(String table, String[] conditions) {
        String queryString = "DELETE FROM " + table + " WHERE ";
        for(String condition: conditions)
            queryString += condition + " AND ";
        queryString = queryString.substring(0, queryString.length() - 2);
        return this.execQueryTable(queryString);
    }
    
    public boolean updateTuple(String table, String[] values, String[] conditions) {
        String queryString = "UPDATE " + table + " SET ";
        for (String value : values)
            queryString += value + ",";
        queryString = queryString.substring(0, queryString.length() - 2) + " WHERE ";
        for(String condition: conditions)
            queryString += condition + " AND ";
        queryString = queryString.substring(0, queryString.length() - 2);
        return this.execQueryTable(queryString);
    }
    
    public void scrollTable(String table) {
    }
    
    private boolean execQueryTable(String queryString)
    {
        boolean output = false;
        try {
            query.executeUpdate(queryString);
            output = true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public String[] getTables() {
        ArrayList<String> output = new ArrayList<String>();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + this.database, "root", "root");
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getTables(null, null, null, new String[] {"TABLE"});
            while (res.next()) {
                output.add(res.getString("TABLE_NAME"));
            }
            res.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output.toArray(new String[0]);
    }
    
    public static void main(String[] args) {
        Conexion c = new Conexion("myFirstDatabase");
        // Test
        for (String name: c.getTables())
            System.out.println(name);
    }
}
