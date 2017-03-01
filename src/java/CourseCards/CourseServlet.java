package CourseCards;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Callum Granger 3062987
 */
public class CourseServlet extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();
        
        // Collect data for the course cards
        CoursesDB db = new CoursesDB(); //Set up the database
        ArrayList<String> courseNos = new ArrayList<>();
        ArrayList<String> courseNames = new ArrayList<>();
        ArrayList<String> professors = new ArrayList<>();
        ArrayList<String> rooms = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();
        ArrayList<String> days = new ArrayList<>();
        
        // Log in to the database to get started
        db.login();
        
        // Get the user's name to search the database
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        
        String[] results = db.query("SELECT code,name,prof,room,time,days "
                + "FROM courses.courses WHERE user = '" + user + "';");
        
       for (int i=0; i<results.length; i++){
            switch (i%6){
                case 0: courseNos.add(results[i]); break;
                case 1: courseNames.add(results[i]); break;
                case 2: professors.add(results[i]); break;
                case 3: rooms.add(results[i]); break;
                case 4: times.add(results[i]); break;
                case 5: days.add((results[i])); break;
            }// switch
        }// for
       
        // Create a bean with the given information
        CourseBean bean = new CourseBean();
        bean.setCodes(courseNos.toArray(new String[courseNos.size()]));
        bean.setNames(courseNames.toArray(new String[courseNames.size()]));
        bean.setProfessors(professors.toArray(new String[professors.size()]));
        bean.setRooms(rooms.toArray(new String[rooms.size()]));
        bean.setTimes(times.toArray(new String[times.size()]));
        bean.setDays(days.toArray(new String[days.size()]));
        request.setAttribute("Course", bean);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");        
        dispatcher.forward(request, response);
    }// processRequest()
    
    

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
        
        PrintWriter out = response.getWriter(); // REMOVE AFTER TESTING
        // Ready the database
        CoursesDB db = new CoursesDB(); //Set up the database
        db.login();
        
        // Collect information from the form
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String professor = request.getParameter("prof");
        String room = request.getParameter("room");
        String time = request.getParameter("time");
        String[] days = request.getParameterValues("days");
        
        // Check if any values are null or empty strings
        if (code == null || code.equals(""))
            code = "Not Specified";
        if (name == null || name.equals(""))
            name = "Not Specified";
        if (professor == null || professor.equals(""))
            professor = "Not Specified";
        if (room == null || room.equals(""))
            room = "Not Specified";
        if (time == null || time.equals(""))
            time = "Not Specified";
        if (days == null)
            days = new String[]{"0"};
        
        // Get the user from the session
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("username");
        
        // Set up the SQL update
        for (String day : days){
        db.update("INSERT INTO courses.courses (code,name,prof,room,time,days,user) "
                + "VALUES ('" + code + "','" + name + "','" + professor + "','" 
                + room + "','" + time + "'," + day + ",'" + user + "');");
        }// for
//        // Set new values to the page
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
