/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author daniel
 */
public class DataExtractor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            /* TODO output your page here. You may use following sample code. */
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            Connection con = DriverManager.getConnection(
                    "jdbc:derby://localhost:1527/Enterprise",
                    "root",
                    "root");
            Statement query = con.createStatement();
            String id = request.getParameter("id");
            ResultSet rs;
            boolean obtainAll = id != null;
            if (id != null)
                rs = query.executeQuery("SELECT * FROM CUSTOMERS WHERE ID = " + id);
            else
                rs = query.executeQuery("SELECT ID, NAME FROM CUSTOMERS");
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("names", this.resultSetToArray(rs, obtainAll));
            out.write(jsonResponse.toString());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private JSONArray resultSetToArray(ResultSet rs, boolean obtainAll) {
        JSONArray output = new JSONArray();
        try {
            System.out.println("hola");
            int numRows = rs.getRow();
            System.out.println(numRows);
            ResultSetMetaData rsmd = rs.getMetaData();
            int numCols = rsmd.getColumnCount();
            int j = 0;
            while (rs.next()) {
                JSONObject address = new JSONObject();
                if (!obtainAll) {
                    address.put("id" , rs.getObject(1));
                    address.put("name" , rs.getObject(2));
                } else {
                    address.put("id" , rs.getObject(1));
                    address.put("name" , rs.getObject(2));
                    address.put("address" , rs.getObject(3));
                    address.put("balance" , rs.getObject(4));
                    address.put("premium" , rs.getObject(5));
                }
                output.add(address);
                j++;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return output;
    }
}
