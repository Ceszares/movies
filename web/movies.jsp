<%@ page import="edu.movies.model.Movie" %>
<%@ page import="edu.movies.model.Page" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: Alex
  Date: 20.04.2018
  Time: 22:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="movies.css">
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
<h1>Movies</h1>
<form action="${pageContext.request.contextPath}/movies" method="post">

    <label for="filterBy">Filter By Title</label>
    <input type="text" id="filterBy" name="filterBy">
    <button type="submit">Filter</button>

    <br>
</form>

<%
    ArrayList<Movie> movies = (ArrayList<Movie>) request.getAttribute("movies");
    boolean authenticated = (boolean) request.getAttribute("Authenticated");
%>


<table>
    <tr>
        <th>Title</th>
        <th>Actor</th>
        <th>Created Date</th>
        <th>Genre</th>
        <th>Image</th>
    </tr>
    <%
        index = 0;
        while (index < movies.size()) {
    %>
    <tr>
        <td><%=movies.get(index).getTitle()%>
        </td>
        <td><%=movies.get(index).getActor()%>
        </td>
        <td><%=movies.get(index).getCreatedDate()%>
        </td>
        <td><%=movies.get(index).getGenre()%>
        </td>
        <td>
            <img class="icon-img" src="<%=movies.get(index).getImageUrl()%>">
        </td>
        <% if (authenticated) {%>
        <td>
            <button><a href="/delete?id=<%=movies.get(index).getId()%>">Delete</a></button>
        </td>
        <% }%>
    </tr>
    <%
            index++;
        }
    %>
</table>
</body>
</html>
