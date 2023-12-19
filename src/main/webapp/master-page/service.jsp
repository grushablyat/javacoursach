<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
    <title>Service</title>
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

<div class="recordList">
    <h1>Your service</h1>
    <table class="recordTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Request</th>
            <th>Client</th>
            <th>Date</th>
            <th>Description</th>
            <th>Status</th>
        </tr>
        </thead>
        <tbody>
            <tr>
                <td>${service.id}</td>
                <td>${service.request}</td>
                <td>${service.clientName}</td>
                <td>${service.date}</td>
                <td>${service.description}</td>
                <td>${service.statusName}</td>
            </tr>
        </tbody>
    </table>
</div>

<div class="recordList">
    <h1>Your comments for this service</h1>
    <table class="recordTable">
        <thead>
        <tr>
            <th>ID</th>
            <th>Date</th>
            <th>Text</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${comments}" var="comment">
<%--            <tr class="record" onclick="window.location.href='http://localhost:8080/master/services/${service.id}/${comment.id}'">--%>
            <tr>
                <td>${comment.id}</td>
                <td>${comment.date}</td>
                <td>${comment.text}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="button" value="Add comment" onclick="window.location.href='${pageContext.request.contextPath}/master/services/${service.id}/new'">
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
