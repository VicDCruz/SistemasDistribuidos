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
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author daniel
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
            this.query = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            output = true;
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
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
            System.out.println("BD creada");
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
        System.out.println(queryString);
        boolean output = this.execQueryTable(queryString);
        try {
            this.con.commit();
            output = output && output;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            output = false;
        }
        return output;
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
            rs.last();
            int i = 0, columns = rs.getMetaData().getColumnCount(), rows = rs.getRow();
            rs.beforeFirst();
            output = new String[rows][columns];
            while (rs.next()) {
                for (int j = 0; j < columns; j++) {
                    String value = rs.getObject(j + 1).toString();
                    if (!value.equals("null"))
                        output[i][j] = value;
                }
                i++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    private boolean execQueryTable(String queryString) {
        System.out.println(queryString);
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
            // Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + this.database, "root", "root");
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
                line += cell + "|";
            }
            if (!line.equals(""))
                output += line.substring(0, line.length() - 1) + "\n";
        }
        return ((!output.equals("")) ? output.substring(0, output.length() - 1) : "");
    }
    
    public String getTablesName(String user) {
        String[] names = this.getTables();
        String output = "";
        for (String name : names) 
            if (name.contains(user))
                output += name + ",";
        return (output.equals("")) ? "" : output.substring(0, output.length() - 1);
    }
    
    public String getTableColumns(String table) {
        String output = "";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + this.database, "root", "root");
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getColumns(null, null, table, null);
            while (res.next()) {
                output += res.getString("COLUMN_NAME") + ",";
            }
            res.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output.substring(0, output.length() - 1);
    }
    
    public String getTableColumnsTypes(String table) {
        String output = "";
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + this.database, "root", "root");
            DatabaseMetaData meta = con.getMetaData();
            ResultSet res = meta.getColumns(null, null, table, null);
            while (res.next()) {
                output += res.getString("TYPE_NAME") + ",";
            }
            res.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output.substring(0, output.length() - 1);
    }
    
    public String getLength(String table) {
        String output = "";
        try {
            String queryString = "SELECT COUNT(*) AS rowcount FROM " + table;
            ResultSet rs = this.query.executeQuery(queryString);
            rs.next();
            output += rs.getInt("rowcount");
            rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return output;
    }
    
    public boolean close() {
        boolean output = true;
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            output = false;
        }
        return output;
    }
    
    public String checkUser(String table, String[] conditions) throws SQLException {
        String queryString = "SELECT * FROM " + table + " WHERE ";
        // String queryString2 = "SELECT name FROM " + table + " WHERE name ='Paola' ";
        for (String condition : conditions)
            queryString += condition + " AND ";
        queryString = queryString.substring(0, queryString.length() - 5);
        ResultSet rs = this.execQuerySelect(queryString);
        JSONObject jsonResponse = new JSONObject();
        if (rs.next()) {
            // Object data[][] = ResultSetToArray(rs);
            jsonResponse.put("name", rs.getString("name"));
        } else {
            jsonResponse.put("name", "error");
        }
        return jsonResponse.toString();
    }
    
    public String checkPassword(String table, String[] conditions) throws SQLException {
        String queryString = "SELECT * FROM " + table + " WHERE ";
        // String queryString2 = "SELECT name FROM " + table + " WHERE name ='Paola' ";
        for (String condition : conditions)
            queryString += condition + " AND ";
        queryString = queryString.substring(0, queryString.length() - 5);
        ResultSet rs = this.execQuerySelect(queryString);
        JSONObject jsonResponse = new JSONObject();
        if (rs.next()) {
            // Object data[][] = ResultSetToArray(rs);
            jsonResponse.put("name", rs.getString("name"));
            jsonResponse.put("password", rs.getString("password"));
        } else {
            jsonResponse.put("name", "error");
            jsonResponse.put("password", "error");
        }

        return jsonResponse.toString();
    }

    private ResultSet execQuerySelect(String queryString) {
        boolean output = false;
        ResultSet rs = null;
        try {
            rs = query.executeQuery(queryString);
            output = true;
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    private Object[][] ResultSetToArray(ResultSet rs) {
        Object data[][] = null;
        try {
            rs.last();
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            int numRows = rs.getRow();
            data = new Object[numRows][numCols];
            int j = 0;
            rs.beforeFirst();
            while (rs.next()) {
                for (int i = 0; i < numCols; i++) {
                    data[j][i] = rs.getObject(i + 1);
                }
                j++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return data;
    }
    
    public static void main(String[] args) {
        Conexion c = new Conexion("myThirdDatabase");
        // Test
        System.out.println(c.connectDatabase());
        try {
            // String values = "name ='Paola',password ='123'";
            // String divider = ",";
            // String[] res =values.split(divider);
            // System.out.println(res[1].toString());
            // System.out.println(c.checkTuple("users", new String[]{"name
            // ='Paola'","password ='123'"}));
            System.out.println(c.createTable("users", new String[] {"name","password"},
            new String[] {"varchar(20)","varchar(20)"}));
            // System.out.println(c.createTuple("users", new String[]{"'Paola'","'123'"}));
            System.out.println(c.checkUser("users", new String[] { "name ='Paola'" }));
            // System.out.println(c.checkPassword("users", new String[]{"name
            // ='Paola'","password ='123'"}));

            /*
             * for (String name: c.getTables()) System.out.println(name);
             * c.createTable("prueba", new String[] {"id", "name", "gender", "address",
             * "phone", "primary"} , new String[] {"int not null", "varchar(25)",
             * "varchar(20)", "varchar(50)", "varchar(20)", "key(id)"});
             * System.out.println(c.getTableColumns("PRUEBA")); for (String name:
             * c.getTables()) System.out.println(name); c.createTuple("prueba", new String[]
             * {"1", "'VIctor'", "'M'", "'a'", "'b'"});
             * System.out.println(c.printScroll("prueba", 0, 10) + "\n");
             * c.updateTuple("prueba", new String[] {"name = 'Victor'"}, new String[]
             * {"id = 1"}); System.out.println(c.printScroll("prueba", 0, 10) + "\n");
             * c.updateTuple("prueba", new String[] {"id = 2"}, new String[] {"id = 1"});
             * System.out.println(c.printScroll("prueba", 0, 10) + "\n");
             * c.deleteTuple("prueba", new String[] {"id = 1"}); c.deleteTable("prueba");
             * for (String name: c.getTables()) System.out.println(name);
             */
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
