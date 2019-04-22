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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paola y Victor
 */
public class Conexion {

    public Conexion() {
    }
    
    public String[] getTables(String dataBase) {
        ArrayList<String> output = new ArrayList<String>();
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection("jdbc:derby://localhost:1527/" + dataBase, "root", "root");
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
        Conexion c = new Conexion();
        // Test
        for (String name: c.getTables("myFirstDatabase"))
            System.out.println(name);
    }
}
