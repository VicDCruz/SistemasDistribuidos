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
    private Connection con = null;
    private Statement query;

    public Conexion(String database) {
        this.database = database;
        if (!this.connectDatabase())
            this.createDatabase();
    }
    
    public boolean hasConnection() {
        return this.con != null;
    }
    
    private boolean connectDatabase() {
        boolean output = false;
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            this.con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + this.database + ";", "root", "root");
            this.query = con.createStatement();
            output = true;
        } catch (SQLException | ClassNotFoundException ex) {
            // Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No existe BD");
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
        String queryString = "create table " + table + " (";
        for (int i = 0; i < attributes.length; i++)
            queryString += attributes[i] + " " + types[i] + ",";
        // As an example
        // + " (id int not null, name " +
        //    "varchar(25),gender varchar(20),address varchar(50),phone varchar(20), primary key(id))"
        queryString = queryString.substring(0, queryString.length() - 1) + ")";
        return this.execQueryTable(queryString);
    }
    
    public boolean deleteTable(String table) {
        String queryString = "DROP TABLE " + table;
        return this.execQueryTable(queryString);
    }
    
    public boolean createTuple(String table, String[] values) {
        String queryString = "INSERT INTO " + table + " VALUES (";
        for (String value : values)
            queryString += value + ",";
        queryString = queryString.substring(0, queryString.length() - 1) + ")";
        return this.execQueryTable(queryString);
    }
    
    public boolean deleteTuple(String table, String[] conditions) {
        String queryString = "DELETE FROM " + table + " WHERE ";
        for(String condition: conditions)
            queryString += condition + " AND ";
        queryString = queryString.substring(0, queryString.length() - 5);
        return this.execQueryTable(queryString);
    }
    
    public boolean updateTuple(String table, String[] values, String[] conditions) {
        String queryString = "UPDATE " + table + " SET ";
        for (String value : values)
            queryString += value + ",";
        queryString = queryString.substring(0, queryString.length() - 1) + " WHERE ";
        for(String condition: conditions)
            queryString += condition + " AND ";
        queryString = queryString.substring(0, queryString.length() - 5);
        return this.execQueryTable(queryString);
    }
    
    /**
     * 
     * @param table Name of table
     * @param start Index zero-based, where to start
     * @param length Total of rows to get, it can be more than total of rows
     * @return 
     */
    public String[][] scrollTable(String table, int start, int length) {
        String[][] output = null;
        String queryString = "SELECT * FROM " + table + " OFFSET " + start +
                " ROWS FETCH NEXT " + length + " ROWS ONLY";
        try {
            ResultSet rs = this.query.executeQuery(queryString);
            int i = 0, columns = rs.getMetaData().getColumnCount(), row = rs.getRow();
            output = new String[row + 1][columns];
            while (rs.next()) {
                for (int j = 0; j < columns; j++) {
                    output[i][j] = rs.getObject(j + 1).toString();
                }
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
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
    
    public String printScroll(String table, int start, int length) {
        String output = "";
        for (String[] row : this.scrollTable(table, start, length)) {
            String line = "";
            for (String cell : row) {
                line += cell + " | ";
            }
            output += line;
        }
        return output;
    }
    
    public static void main(String[] args) {
        Conexion c = new Conexion("mySecondDatabase");
        // Test
        for (String name: c.getTables())
            System.out.println(name);
        c.createTable("prueba", new String[] {"id", "name", "gender", "address", "phone", "primary"}
                , new String[] {"int not null", "varchar(25)", "varchar(20)", "varchar(50)", "varchar(20)", "key(id)"});
        for (String name: c.getTables())
            System.out.println(name);
        c.createTuple("prueba", new String[] {"1", "'VIctor'", "'M'", "'a'", "'b'"});
        System.out.println(c.printScroll("prueba", 0, 10) + "\n");
        c.updateTuple("prueba", new String[] {"name = 'Victor'"}, new String[] {"id = 1"});
        System.out.println(c.printScroll("prueba", 0, 10) + "\n");
        c.updateTuple("prueba", new String[] {"id = 2"}, new String[] {"id = 1"});
        System.out.println(c.printScroll("prueba", 0, 10) + "\n");
        c.deleteTuple("prueba", new String[] {"id = 1"});
        c.deleteTable("prueba");
        for (String name: c.getTables())
            System.out.println(name);
    }
}
