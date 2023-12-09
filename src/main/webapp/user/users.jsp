<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <title>Пользователи</title>
</head>
<body>
    <header>
        <img src="../images/whitelogo.jpg">
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/users">Users</a></li>
                <li><a href="/requests">Requests</a></li>
                <li><a href="/services">Services</a></li>
            </ul>
        </nav>
    </header>

    <div class="recordList">
        <h1>Users list</h1>
        <table class="recordTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>E-mail</th>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Role</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr class="record" onclick="window.location.href='http://localhost:8080/users/${user.id}'">
                        <td>${user.id}</td>
                        <td>${user.name}</td>
                        <td>${user.email}</td>
                        <td>${user.login}</td>
                        <td>${user.password}</td>
                        <td>${user.role}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="button" value="Add user" onclick="window.location.href='http://localhost:8080/users/new'">
    </div>

    <footer>
        <p>Specially for Java and Web term papers</p>
        <p>Tselousov G.A.</p>
    </footer>

</body>
</html>
