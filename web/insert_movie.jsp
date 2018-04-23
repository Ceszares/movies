<%@ page import="edu.movies.model.Page" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>New Movie</title>
    <%--<link rel="stylesheet" type="text/css" href="movies.css">--%>
    <link rel="stylesheet" type="text/css" href="nav.css">
    <link rel="stylesheet" type="text/css" href="insert_movie.css">
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
<h1>New Movie</h1>
<form action="${pageContext.request.contextPath}/insertMovie" method="post">
    <table>
        <tr>
            <td>Title</td>
            <td><input type="text" name="title"></td>
        </tr>
        <tr>
            <td>Year</td>
            <td><input type="text" name="year"></td>
        </tr>
        <tr>
            <td>Actor</td>
            <td><input type="text" name="actor"></td>
        </tr>
        <tr>
            <td>Genre</td>
            <td><input type="text" name="genre"></td>
        </tr>
        <tr>
            <td>Image URL</td>
            <td><input type="text" name="imageUrl"></td>
        </tr>
    </table>
    <br>
    <button type="submit">Submit</button>
</form>

</body>
</html>
