<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
    <title>Request</title>
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

<div class="recordList">
    <h1>Request</h1>
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
        <tr class="record" onclick="window.location.href='${pageContext.request.contextPath}/admin/requests/${request.id}/edit'">
            <td>${request.id}</td>
            <td>${request.clientName}</td>
            <td>${request.date}</td>
            <td>${request.description}</td>
            <td>${request.statusName}</td>
        </tr>
        </tbody>
    </table>
</div>

<div class="recordList">
    <h1>Existing services for this request</h1>
    <table class="recordTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Master</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${services}" var="service">
            <tr class="record" onclick="window.location.href='${pageContext.request.contextPath}/admin/requests/${request.id}/${service.id}'">
                <td>${service.id}</td>
                <td>${service.masterName}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="button" value="Add service" onclick="window.location.href='${pageContext.request.contextPath}/admin/requests/${request.id}/new'">
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
