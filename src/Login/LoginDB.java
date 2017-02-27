/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Login;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Cally
 */
public class LoginDB {
    private Connection connection;
    private Statement statement;
    public boolean isConnected = false;
    private String error;
    private String content;
    
    public void login() throws IOException {
        
        if (isConnected){
            return;
        }// if
        
        String url = "jdbc:mysql://localhost:3306/login";
        String driver = "com.mysql.jdbc.Driver";
        String username = "root";
        String password = "ApgOf3";
        
        try { // Try connection to database
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
            
        }
        catch(ClassNotFoundException e){
            error = "Error loading driver: " + e;
        }// catch
        catch (SQLException e){
            error = "Error with connection: " + e;
        }
    }// login
    
    public void logoff() throws IOException {

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
    
    public void update(String user, String pass) throws IOException {
        if (!isConnected) {
            return;
        }


        try {
            statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO login.credentials VALUES ('MYACS','HAPPYTIMES');");
            content = null;
        } catch (SQLException e) {
            error = "Error with update: " + e;
        }
    }
    
    public String query(){
        try {
        statement = connection.createStatement();
            
        ResultSet resultSet = statement.executeQuery("SELECT * FROM login.credentials");
        ResultSetMetaData result = resultSet.getMetaData();
        int cn = result.getColumnCount();
        StringBuilder strb = new StringBuilder();
        
        while (resultSet.next()) {
            for (int i = 1; i <= cn; i++) {
            strb.append(resultSet.getString(i) + " ");
            }
        }// while
        
        return strb.toString();
        } catch (SQLException e) {
            error = "Error with query: " + e;
        }// catch
        
        return "";
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
}
