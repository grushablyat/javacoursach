<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;500;600&display=swap">
    <link rel="stylesheet" type="text/css" media="screen" href="styles/login-form.css">

    <title>Sign Up</title>
</head>
<body>
<div class="background">
    <div class="shape signup"></div>
    <div class="shape signup"></div>
</div>
<form method="post">
    <h3>Sign up Here</h3>
    <label>${signUpResult}</label>

    <label for="name">Name</label>
    <input type="text" placeholder="Name" id="name" name="name"
           pattern="^[A-Z]{1}[a-z]{0,} [A-Z]{1}[a-z]{0,}$"
           title="Name contains two words beginning with uppercase letters"
           required>

    <label for="email">E-mail</label>
    <input type="email" placeholder="E-mail" id="email" name="email"
           pattern="^[a-z]{1,}[a-z0-9.]{0,}[a-z0-9]{1,}@[a-z]{1,}[a-z0-9]{0,}\.[a-z]{1,}$"
           title="E-mail is a sequence of letters, digits and points separated by @"
           required>

    <label for="username">Username</label>
    <input type="text" placeholder="Username" id="username" name="login"
           pattern="^[a-zA-Z0-9_]{1,}$"
           title="Username is a sequence of letters, digits and underscore symbols"
           required>

    <label for="password">Password</label>
    <input type="password" placeholder="Password" id="password" name="password"
           pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&-_])[A-Za-z\d@$!%*#?&-_]{8,}$"
           title="Password must contain minimum eight characters, at least one letter, one number and one special character"
           required>

    <label for="role">Role</label>
    <select name="role" id="role" required>
        <option value="0" disabled selected>--Choose a role--
        <option value="3">Client
        <option value="2">Master
        <option value="1">Admin
    </select>

    <label for="keyword">Keyword</label>
    <input type="password" placeholder="Keyword" id="keyword" name="keyword">

    <input type="submit" name="signupButton" value="Sign Up">

    <span class="logged-or-not">Already have an</span>
    <span class="logged-or-not" style="margin-top: 3px;">account? <a href="/login">Log In</a>!</span>
</form>
</body>
</html>
