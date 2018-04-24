<%@ page import="edu.movies.model.Page" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 21.04.2018
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="nav.css">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="signUp.css">

</head>
<body>
<ul>
    <%
        ArrayList<Page> pages = (ArrayList<Page>) request.getAttribute("pages");
    %>
    <%
        int index = 0;
        while (index < pages.size()) {
    %>
    <li>
        <a
                <%
                    if (pages.get(index).isActive()) {
                %>
                class="active"
                <%
                    }
                %>
                href="${pageContext.request.contextPath}/<%=pages.get(index).getPath()%>"><%=pages.get(index).getName()%>
        </a>
    </li>
    <%
            index++;
        }
    %>
</ul>
<h2>Sign Up Form</h2>
<form action="${pageContext.request.contextPath}/signUp" method="post">
    <div class="imgcontainer">
        <img class="imgcontainer"
             src="https://p14.zdassets.com/hc/settings_assets/451051/200005349/a5d8Fe78iyibcdSPGl3IPQ-logo-gathr-green-text__4_.png"
        >
    </div>

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <p>Message: <%=message%>
    </p>
    Error
    <%
        }
    %>

    <div class="container">
        <label for="username"><b>Username</b></label>
        <input type="text" id="username" placeholder="Enter Username" name="username" required>

        <label for="password"><b>Password</b></label>
        <input type="password" id="password" placeholder="Enter Password" name="password" required>

        <button type="submit">Submit</button>

    </div>
</form>

</body>
</html>
