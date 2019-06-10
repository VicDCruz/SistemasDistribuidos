/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel
 */
public class SignZodiac extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            int day = Integer.parseInt(request.getParameter("day"));
            int month = Integer.parseInt(request.getParameter("month"));
            int year = Integer.parseInt(request.getParameter("year"));
            String zodiac = this.getZodiac(day, month, year);
            if ("".equals(zodiac)) {
                response.sendRedirect("/?error=1");
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ServletInicial</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Zodiaco</h1>");
            out.println("<p> Tu signo es: " + zodiac + "</p>");
            out.println("<form  action = 'Signs'>");
            out.println("<input type='hidden' name='zodiac' value='" + zodiac + "'/>");
            out.println("<input type='submit' value='Ver signos compatibles'/>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    private String getZodiac(int day, int month, int year) {
        String output = "";
        GregorianCalendar cal;
        try {
            cal = new GregorianCalendar(year, month, day);
        } catch (Exception e) {
            return "";
        }
        if(cal.after(new GregorianCalendar(year, 2, 21)) && cal.before(new GregorianCalendar(year, 3, 20))) {
            output = "Aries";
        }
        if(cal.after(new GregorianCalendar(year, 3, 21)) && cal.before(new GregorianCalendar(year, 4, 20))) {
            output = "Tauro";
        }
        if(cal.after(new GregorianCalendar(year, 4, 21)) && cal.before(new GregorianCalendar(year, 5, 20))) {
            output = "Géminis";
        }
        if(cal.after(new GregorianCalendar(year, 5, 21)) && cal.before(new GregorianCalendar(year, 6, 20))) {
            output = "Cáncer";
        }
        if(cal.after(new GregorianCalendar(year, 6, 21)) && cal.before(new GregorianCalendar(year, 7, 20))) {
            output = "Leo";
        }
        return output;
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

}
