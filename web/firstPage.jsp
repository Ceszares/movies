<%@ page import="edu.movies.model.Page" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 20.04.2018
  Time: 22:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Movies</title>
    <link rel="stylesheet" type="text/css" href="firstPage.css">
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
<h1>

    <p class="outset" style="background-color: dodgerblue; "> How it would be for you to have access to the finest and
        highest rated movies of all time? All the titles and actors who
        won an Oscar here at your disposal with this new application which let you decide fast who or what you want to
        watch!<br>Or if you want to be able to create and personalize your own list of favorite movies go on and sign
        in.</p>
</h1>
<img class="myImage" src="http://www.mymovies.dk/media/28311/maincoverviewc.png">
</body>


</html>
