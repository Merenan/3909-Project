package CourseCards;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

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
        
        String[] results = db.query("SELECT code, name, prof, room, time, days FROM Courses;");
        //!!!!!!!!!!! QUERY TO GET EVERYTHING IN THE TABLE !!!!!!!!!!!!!//
        for (int i=0; i<results.length; i++){
            switch (i%6){
                case 1: courseNos.add(results[i]); break;
                case 2: courseNames.add(results[i]); break;
                case 3: professors.add(results[i]); break;
                case 4: rooms.add(results[i]); break;
                case 5: times.add(results[i]); break;
                case 0: days.add(results[i]); break;
            }// switch
        }// for
        
        // Create a bean with the given information
        CourseBean bean = new CourseBean();
        bean.setCodes((String[])courseNos.toArray());
        bean.setNames((String[])courseNames.toArray());
        bean.setProfessors((String[])professors.toArray());
        bean.setRooms((String[])rooms.toArray());
        bean.setTimes((String[])times.toArray());
        bean.setDays((String[])days.toArray());
        request.setAttribute("courses",bean);
        
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
        // Ready the database
        CoursesDB db = new CoursesDB(); //Set up the database
        db.login();
        
        // Collect information from the form
        //!!!!!!!!!!!!!!! SET THE ATTRIBUTE NAMES !!!!!!!!!!!!!!!!//
        String code = (String)request.getAttribute(null);
        String name = (String)request.getAttribute(null);
        String professor = (String)request.getAttribute(null);
        String room = (String)request.getAttribute(null);
        String time = (String)request.getAttribute(null);
        String days = (String)request.getAttribute(null);
        
        // Set up the SQL update
        db.update("INSERT INTO Courses (code,name,prof,room,time,days)"
                + "VALUES (" + code + "," + name + "," + professor + "," 
                + room + "," + time + "," + days + ");");
        
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Servlet used to handle data collection for course cards.";
    }

}
