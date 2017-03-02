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
        <title>Login - UW Weekly Planner</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="styles.css"/>
        <% Cookie[] cookies = request.getCookies();%>
    </head>
    <body>
        <form id="login" action="LoginServlet" method="GET" enctype="text/plain">
            <h1>Login</h1>
            <div class="row tall">
                <span class="icon"><i class="fa fa-user" aria-hidden="true"></i></span>
                <% if (cookies == null){
                out.print("<input type=\"text\" name=\"username\" id=\"username\" placeholder=\"Username\"/>");
                } else { 
                    String cValue = null;
                    for (Cookie cookie : cookies){
                        if (cookie.getName().equals("defUser")){
                            cValue = cookie.getValue();
                        }
                    }// for
                    if (cValue != null){
                        out.print("<input type=\"text\" name=\"username\" id=\"username\" value=\"" + cValue + "\" placeholder=\"Username\"/>");
                    }
                    else{
                        out.print("<input type=\"text\" name=\"username\" id=\"username\" placeholder=\"Username\"/>");
                    }
                 }%>
            </div>
            <div class="row tall">
                <span class="icon"><i class="fa fa-key" aria-hidden="true"></i></span>
                <input type="password" name="password" id="password" placeholder="Password"/>
            </div>
            <div class="row tall">
                <input type="submit" name="action" value="login" />
                <input type="submit" name="action" value="create"/>
            </div>
        </form>
    </body>
</html>
