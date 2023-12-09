<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <title>Requests</title>
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
        <h1>Requests list</h1>
        <table class="recordTable">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Client</th>
                    <th>Date</th>
                    <th>Description</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${requests}" var="request">
                    <tr class="record" onclick="window.location.href='http://localhost:8080/requests/${request.id}'">
                        <td>${request.id}</td>
                        <td>${request.client}</td>
                        <td>${request.date}</td>
                        <td>${request.description}</td>
                        <td>${request.status}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <input type="button" value="Add request" onclick="window.location.href='http://localhost:8080/requests/new'">
    </div>

    <footer>
        <p>Specially for Java and Web term papers</p>
        <p>Tselousov G.A.</p>
    </footer>

</body>
</html>
