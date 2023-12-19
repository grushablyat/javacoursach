<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/styles/style.css">
    <title>Edit request</title>
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

<div style="margin: 0 auto 0 auto; width: 270px">
    <form method="post">
        <p>You can change something and save record</p>
        <table>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" value="${request.description}" required></td>
            </tr>
            <tr>
                <td>Status</td>
<%--                <td><input type="number" name="status" value="${request.status}" min="1" required></td>--%>
                <td><select name="status" id="status">
                    <c:choose>
                        <c:when test="${request.status == 1}">
                            <option value="1" selected>Opened
                            <option value="2">In process
                            <option value="3">Closed
                        </c:when>
                        <c:when test="${request.status == 2}">
                            <option value="1">Opened
                            <option value="2" selected>In process
                            <option value="3">Closed
                        </c:when>
                        <c:when test="${request.status == 3}">
                            <option value="1">Opened
                            <option value="2">In process
                            <option value="3" selected>Closed
                        </c:when>
                        <c:otherwise>
                            <option value="0" disabled selected>--Choose a status--
                            <option value="1">Opened
                            <option value="2">In process
                            <option value="3">Closed
                        </c:otherwise>
                    </c:choose>
                </select></td>
            </tr>
        </table>
        <input type="hidden" name="client" value="${request.client}">
        <input type="hidden" name="date" value="${request.date}">
        <input type="submit" name="edit" value="Save changes">
        <input type="button" onclick="window.location='http://localhost:8080/admin/requests/${request.id}'" name="cancel" value="Cancel">
    </form>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
