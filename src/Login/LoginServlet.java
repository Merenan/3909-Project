/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cally
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
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
        PrintWriter out = response.getWriter();
       // Get the session
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(600); //Set inactive time to ten minutes
        
        //Set up the database
        LoginDB DB = new LoginDB();
        DB.login();
        
        // Determine desired action
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        
        if (action.equals("login")){ // If the request is to log in
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            String set = DB.query();
            
            // If no username or password was given
            if (user == null || pass == null || user.equals("") || pass.equals("")){
                out.println("<p> Please enter a username and password! </p>");
            } else if (set.contains(user + " " + pass)) {
                //Create a session on successful log in                
                session.setAttribute("username", user);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");        
                dispatcher.forward(request, response);
            } else {
                out.println("<p>Username/password not found. </p>");
                // TEST CODE // 
                out.println(DB.query());
            }// else
        }// if
        else if (action.equals("create")){ // If the request is to create
            // Get entered username and password
            String user = request.getParameter("username");
            String pass = request.getParameter("password");
            
            // If no username or password was given
            if (user == null || pass == null || user.equals("") || pass.equals("")){
                out.println("<p> Please enter a username and password!</p>");
            } else {
            // If a table exists, add to it
                DB.update(user, pass);
                
                // UNCOMMENT THIS PLEASE //
                //Create a session on successful profile creation               
//                session.setAttribute("username", user);
//                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");        
//                dispatcher.forward(request, response);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");        
            dispatcher.forward(request, response);
            }// else (Username and password were entered)
        } else{// Not login or create
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");        
            dispatcher.forward(request, response);
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
