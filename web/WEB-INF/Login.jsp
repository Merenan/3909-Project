<%-- 
    Document   : Login
    Created on : 26-Feb-2017, 10:29:25 PM
    Author     : Callum Granger
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <form id="login" action="LoginServlet" method="GET" enctype="text/plain">

            <label for="username">Username:</label>
            <input type="text" name="username" id="username" size="20" /><br />
            <label for="password">Password:</label>
            <input type="password" name="password" id="password" size="20" /><br />

            <input type="submit" name="action" value="login" />
            <input type="submit" name="action" value="create"/>
        </form>
    </body>
</html>
