<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap">
    <link rel="stylesheet" type="text/css" media="screen" href="styles/login-form.css">

    <title>Log In</title>
</head>
<body>
<div class="background">
    <div class="shape"></div>
    <div class="shape"></div>
</div>
<form method="post">
    <h3>Login Here</h3>
    <label>${logInResult}</label>

    <label for="username">Username</label>
    <input type="text" placeholder="Username" id="username" name="login">

    <label for="password">Password</label>
    <input type="password" placeholder="Password" id="password" name="password">

    <input type="submit" name="loginButton" value="Log In">

    <span class="logged-or-not">Do not have an</span>
    <span class="logged-or-not" style="margin-top: 3px;">account yet? <a href="/signup">Sign Up</a>!</span>
</form>
</body>
</html>
