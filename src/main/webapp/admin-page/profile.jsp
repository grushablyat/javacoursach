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
                <li><a href="${pageContext.request.contextPath}/admin">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/users">Users</a></li>
                <li><a href="${pageContext.request.contextPath}/admin/requests">Requests</a></li>
            </ul>
        </nav>
    </div>
    <a href="${pageContext.request.contextPath}/logout">
        <img class="logout" src="${pageContext.request.contextPath}/images/logout.png" alt="Log Out">
    </a>
</header>

<div id="content">
    <h1 class="retroshadow">Welcome, ${admin.getLogin()}!</h1>
    <p>You can create manipulate requests and services here.</p>
    <table>
        <tr>
            <th>Name</th>
            <td>${admin.getName()}</td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td>${admin.getEmail()}</td>
        </tr>
        <tr>
            <th>Login</th>
            <td>${admin.getLogin()}</td>
        </tr>
        <tr>
            <th>Role</th>
            <td>Admin</td>
        </tr>
    </table>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
