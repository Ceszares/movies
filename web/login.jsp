<%@ page import="edu.movies.model.Page" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 20.04.2018
  Time: 23:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="login.css">
    <link rel="stylesheet" type="text/css" href="nav.css">
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


<h2>Login Form</h2>
<form action="${pageContext.request.contextPath}/doLogin" method="post">

    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
    %>
    <p>Message: <%=message%>
    </p>
    <%
        }
    %>

    <div class="container">
        <div class="imgcontainer">
            <img src="https://www.w3schools.com/howto/img_avatar.png" alt="Avatar" class="avatar">
        </div>
        <label for="username"><b>Username</b></label>
        <input type="text" id="username" placeholder="Enter Username" name="username" required>

        <label for="password"><b>Password</b></label>
        <input type="password" id="password" placeholder="Enter Password" name="password" required>

        <button type="submit">Login</button>

    </div>

</form>

</body>
</html>
