<%-- 
    Document   : index
    Created on : Feb 20, 2017, 2:07:32 PM
    Author     : harveycabaguio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="styles.css"/>
    </head>
    <body>
        <section id="main">
            <%-- if there are classes, list them, else show intro text --%>
            <% if (session.getLastAccessedTime() == session.getLastAccessedTime()) { %>
                <%-- don't forget to move to the else --%>
                <div class="welcome">
                    <h1>UW Weekly Planner</h1>
                    <p>Organize your student life by adding courses to your timetable and tasks to your todo list</p>
                </div>
            <% } else { %>
                
            <% } %>
            <input type="checkbox" id="toggleForm">
            <label id="fab" for="toggleForm"><i class="fa fa-plus" aria-hidden="true"></i></label>
            <div id="formBg">
                <div id="formWindow" class="card">
                    <form method="" action="" name="">
                        <div class="row">
                            <label>Course Number:</label>
                            <input type="text" name="code">
                        </div>
                        <div class="row">
                            <label>Course Name:</label>
                            <input type="text" name="name">
                        </div>
                        <div class="row">
                            <label>Professor:</label>
                            <input type="text" name="prof">
                        </div>
                        <div class="row">
                            <label>Room:</label>
                            <input type="text" name="room">
                        </div>
                        <div class="row">
                            <label>Time:</label>
                            <input type="text" name="time">
                        </div>
                        <div id="days" class="row">
                            <label>Days:</label>
                            <div>
                                <div class="row">
                                    <input type="checkbox" name="days" id="Sunday"><label for="Sunday">Sunday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" id="Monday"><label for="Monday">Monday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" id="Tuesday"><label for="Tuesday">Tuesday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" id="Wednesday"><label for="Wednesday">Wednesday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" id="Thursday"><label for="Thursday">Thursday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" id="Friday"><label for="Friday">Friday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" id="Saturday"><label for="Saturday">Saturday</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <input type="submit">
                        </div>
                    </form>
                </div>
            </div>
        </section>
        <section id="side">
            <% Date currDate = new Date(); %>
            <h1><%
                SimpleDateFormat dayNo = new SimpleDateFormat("d");
                out.println(dayNo.format(currDate));
            %></h1>
            <h1><%
                SimpleDateFormat month = new SimpleDateFormat("MMMM");
                out.println(month.format(currDate));
            %></h1>
            <h2><%
                SimpleDateFormat day = new SimpleDateFormat("EEEE");
                out.println(day.format(currDate));
            %></h2>
            <%-- get # of added classes --%>
            <p><%  %> Classes today</p>
            <div id="todo" class="card">
                <h3>Todo</h3>
                <div class="row">
                    <i class="fa fa-plus" aria-hidden="true"></i>
                    <input type="text" placeholder="Add another task..."/>
                </div>
            </div>
        </section>
    </body>
</html>
