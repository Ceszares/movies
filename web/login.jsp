<%--
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
</head>
<body>

<h2>Login Form</h2>

<form action="${pageContext.request.contextPath}/doLogin" method="post">
    <div class="imgcontainer">
        <img src="https://www.w3schools.com/howto/img_avatar.png" alt="Avatar" class="avatar">


    </div>

    <div class="container">
        <label for="username"><b>Username</b></label>
        <input type="text" id="username" placeholder="Enter Username" name="username" required>

        <label for="password"><b>Password</b></label>
        <input type="password" id="password" placeholder="Enter Password" name="password" required>

        <button type="submit">Login</button>

    </div>

</form>

</body>
</html>
