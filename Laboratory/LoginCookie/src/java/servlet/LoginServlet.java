/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author daniel
 */
public class LoginServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            boolean isUser = false;
            boolean samePassword = false;
            String name = request.getParameter("name");
            String password = request.getParameter("password");
            Cookie cName = new Cookie("name", name);
            Cookie cPwd = new Cookie("password", password);
            Cookie[] cookies = request.getCookies();
            Cookie cookie = null;
            int i = 0;
            while (!isUser && i < cookies.length) {                
                if (cookies[i].getName().equals("name") && cookies[i].getValue().equals(name)) {
                    isUser = true;
                }
                if (isUser && cookies[i].getName().equals("password") && cookies[i].getValue().equals(password)) {
                    samePassword = true;
                } else if (isUser) {
                    samePassword = false;
                }
                i++;
            }
            boolean isNewUser = !isUser && !samePassword;
            if (isNewUser) {
                response.addCookie(cName);
                response.addCookie(cPwd);
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<jsp:include page='link.html'/>");
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/link.html");
            dispatcher.include(request, response);
            if ((isUser && samePassword) || isNewUser) {
                out.println("<p>Log in correcto</p><p>Bienvenido, " + name + "</p>");
            } else if (!samePassword) {
                out.println("<p>Lo sentimos, usuario o password incorrecto</p>");
                dispatcher = getServletContext().getRequestDispatcher("/login.html");
                dispatcher.include(request, response);
            }
            out.println("</body>");
            out.println("</html>");
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

}
