<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/styles/style.css">
    <title>Пользователи</title>
</head>
<body>
<header>
    <div class="logolist">
        <img class="logo" src="/images/whitelogo.jpg">
        <nav>
            <ul>
                <li><a href="/admin">Home</a></li>
                <li><a href="/admin/users">Users</a></li>
                <li><a href="/admin/requests">Requests</a></li>
            </ul>
        </nav>
    </div>
    <a href="/logout"><img class="logout" src="/images/logout.png" alt="Log Out"></a>
</header>

<div class="recordList">
    <h1>All users</h1>
    <table class="recordTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>E-mail</th>
            <th>Login</th>
<%--            <th>Password Hash</th>--%>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
<%--            <tr class="record" onclick="window.location.href='http://localhost:8080/users/${user.id}'">--%>
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.email}</td>
                <td>${user.login}</td>
<%--                <td>${user.password}</td>--%>
                <td>${user.roleName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
<%--    <input type="button" value="Add user" onclick="window.location.href='http://localhost:8080/users/new'">--%>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
