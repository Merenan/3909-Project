<%-- 
    Document   : index
    Created on : Feb 20, 2017, 2:07:32 PM
    Author     : harveycabaguio
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.Date.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="CourseCards.*"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="styles.css"/>
    </head>
    <body>
        <jsp:useBean id="Course" class="CourseCards.CourseBean"/>
        <jsp:setProperty name="Course" property="*"/>
        <%! String[] dayCheck; %>
        <section id="main">
            <%-- if new session or no classes, show welcome. else generate table --%>
            <% if (session.isNew()) { %>
                <div class="welcome">
                    <h1>UW Weekly Planner</h1>
                    <p>Organize your student life by adding courses to your timetable and tasks to your todo list</p>
                </div>
            <% } else { %>
                <div class="timetable">
                    <div class="row tall" id="headers">
                        <h2 class="col">Sunday</h2>
                        <h2 class="col">Monday</h2>
                        <h2 class="col">Tuesday</h2>
                        <h2 class="col">Wednesday</h2>
                        <h2 class="col">Thursday</h2>
                        <h2 class="col">Friday</h2>
                        <h2 class="col">Saturday</h2>
                    </div>
                    <div class="row var">
                        <%
                            dayCheck = Course.getDays();
                            for (int i=0;i<7;i++) {
                        %>
                        <div class="col">
                        <%
                            for (int j=0;j<dayCheck.length;j++) {
                                int k = Integer.parseInt(dayCheck[j]);
                                if ( k == i) {
                        %>
                                    <div class="card">
                                        <div class="row">
                                            <h3><jsp:getProperty name="Course" property="codes" /></h3>
                                        </div>
                                        <h2><jsp:getProperty name="Course" property="names" /></h2>
                                        <div class="row">
                                            <i class="fa fa-user" aria-hidden="true"></i><span><jsp:getProperty name="Course" property="professors" /></span>
                                        </div>
                                        <div class="row">
                                            <i class="fa fa-map-marker" aria-hidden="true"></i><span><jsp:getProperty name="Course" property="rooms" /></span>
                                        </div>
                                        <div class="row">
                                            <i class="fa fa-clock-o" aria-hidden="true"></i><span><jsp:getProperty name="Course" property="times" /></span>
                                        </div>
                                    </div>
                        <%
                                }
                            }
                        %>
                        </div>
                        <%
                            }
                        %>
                    </div>
                </div>
            <% } %>
            <input type="checkbox" id="toggleForm">
            <label id="fab" for="toggleForm"><i class="fa fa-plus" aria-hidden="true"></i></label>
            <div id="formBg">
                <div id="formWindow" class="card">
                    <form method="post" action="index.jsp" name="addForm">
                        <div class="row">
                            <label>Course Number:</label>
                            <input type="text" name="codes" required>
                        </div>
                        <div class="row">
                            <label>Course Name:</label>
                            <input type="text" name="names" required>
                        </div>
                        <div class="row">
                            <label>Professor:</label>
                            <input type="text" name="professors" required>
                        </div>
                        <div class="row">
                            <label>Room:</label>
                            <input type="text" name="rooms" required>
                        </div>
                        <div class="row">
                            <label>Time:</label>
                            <input type="text" name="times" required>
                        </div>
                        <div id="days" class="row">
                            <label>Days:</label>
                            <div>
                                <div class="row">
                                    <input type="checkbox" name="days" value="0" id="Sunday"><label for="Sunday">Sunday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" value="1" id="Monday"><label for="Monday">Monday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" value="2" id="Tuesday"><label for="Tuesday">Tuesday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" value="3" id="Wednesday"><label for="Wednesday">Wednesday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" value="4" id="Thursday"><label for="Thursday">Thursday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" value="5" id="Friday"><label for="Friday">Friday</label>
                                </div>
                                <div class="row">
                                    <input type="checkbox" name="days" value="6" id="Saturday"><label for="Saturday">Saturday</label>
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
