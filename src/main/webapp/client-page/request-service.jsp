<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/styles/style.css">
    <title>Request-Service</title>
</head>
<body>
<header>
    <div class="logolist">
        <img class="logo" src="/images/whitelogo.jpg">
        <nav>
            <ul>
                <li><a href="/client">Home</a></li>
                <li><a href="/client/requests">Your requests</a></li>
            </ul>
        </nav>
    </div>
    <a href="/logout"><img class="logout" src="/images/logout.png" alt="Log Out"></a>
</header>

<div class="recordList">
    <h1>Your request</h1>
    <table class="recordTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Description</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${request.id}</td>
            <td>${request.date}</td>
            <td>${request.description}</td>
            <td>${request.statusName}</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="recordList">
    <h1>Service</h1>
    <table class="recordTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Master</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>${service.id}</td>
            <td>${service.masterName}</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="recordList">
    <h1>Existing comments for current service</h1>
    <table class="recordTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Text</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comments}" var="comment">
            <tr>
                <td>${comment.id}</td>
                <td>${comment.text}</td>
                <td>${comment.date}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
