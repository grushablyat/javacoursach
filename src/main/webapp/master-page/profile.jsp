<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
    <title>Home</title>
</head>
<body>
<header>
    <div class="logolist">
        <img class="logo" src="${pageContext.request.contextPath}/images/whitelogo.jpg">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/master">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/master/services">Your services</a></li>
            </ul>
        </nav>
    </div>
    <a href="${pageContext.request.contextPath}/logout">
        <img class="logout" src="${pageContext.request.contextPath}/images/logout.png" alt="Log Out">
    </a>
</header>

<div id="content">
    <h1 class="retroshadow">Welcome, ${master.getLogin()}!</h1>
    <p>You can view your services and add comments here.</p>
    <table class="profile">
        <tr>
            <td>Name</td>
            <td>${master.getName()}</td>
        </tr>
        <tr>
            <td>E-mail</td>
            <td>${master.getEmail()}</td>
        </tr>
        <tr>
            <td>Login</td>
            <td>${master.getLogin()}</td>
        </tr>
        <tr>
            <td>Role</td>
            <td>Master</td>
        </tr>
    </table>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
