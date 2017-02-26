/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CourseCards;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
/**
 * CoursesDB - This class allows for database functionality by handling the database 
 connection and communication.
 * 
 * @author Cally
 */
public class CoursesDB {
    private Connection connection;
    private Statement statement;
    public boolean isConnected = false;
    private String error;
    private String content;
    
    private void login() throws IOException {
        
        if (isConnected){
            return;
        }// if
        
        String url = "jdbc:mysql://localhost:3306/courses";
        String driver = "com.mysql.jdbc.Driver";
        String username = "ACS3062987";
        String password = "ApgOf3";
        
        try { // Try connection to database
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement(); // Create query statement
        }
        catch(ClassNotFoundException e){
            error = "Error loading driver: " + e;
        }// catch
        catch (SQLException e){
            error = "Error with connection: " + e;
        }
    }// login
    
    private void logoff() throws IOException {

        if (!isConnected) {
            return;
        }

        try {
            connection.close();
            
        } catch (SQLException sqle) {
            error = "Error with connection: " + sqle;
        }
        content = null;
        isConnected = false;
    }// logoff
    
    public void action(String action, String command) throws IOException {
        if (action == null) {
            return;
        }// if

        if (action.equals("Login")) {
            login();
        }// if

        if (action.equals("Logoff")) {
            logoff();
        }// if

        if (action.equals("Update")) {
            update(command);
        }// if

        if (action.equals("Query")) {
            query(command);
        }// if
    }// action
    
    public void update(String command) throws IOException {
        if (!isConnected) {
            return;
        }

        if (command == null || command.equals("")) {
            content = "Please specify an update for the database!<br />";
            return;
        }

        try {
            statement.executeUpdate(command);
            content = null;
        } catch (SQLException e) {
            error = "Error with update: " + e;
        }
    }

    public String[] query(String command) throws IOException {
        if (!isConnected) {
            return null;
        }// if

        if (command == null || command.equals("")) {
            content = "Please specify a query to query the database!<br />";
            return null;
        }// if

        try {// Get the query results
            ResultSet resultSet = statement.executeQuery(command);
            ArrayList<String> list = new ArrayList<>();
            ResultSetMetaData result = resultSet.getMetaData();
            int cn = result.getColumnCount();
            while (resultSet.next()) {
                // Iterate the entire set, and populate teh Array List
                for (int i = 1; i <= cn; i++) {
                    list.add(resultSet.getString(i));
                }// for
            }// while
            
            // Convert ArrayList to String[] and return it
            return (String[]) list.toArray();
        } catch (SQLException e) {
            error = "Error with query: " + e;
        }// catch
        
        // Return null by default, if something went wrong
        return null;
    }// query
    
    public boolean hasErrors() {
        return (error != null);
    }
    
    public String getErrors() {
        return error;
    }
    
    public String getContent() {
        return content;
    }
}// CoursesDB
