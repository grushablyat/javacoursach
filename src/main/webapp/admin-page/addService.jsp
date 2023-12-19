<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
    <title>Add service</title>
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

<div style="margin: 0 auto 0 auto; width: 270px">
    <form method="post">
        <p>You can add service here</p>
        <table>
            <tr>
                <td>Description</td>
                <td><select name="master" id="master">
                    <option value="0" disabled selected>--Choose a master--
                    <c:forEach items="${masters}" var="master">
                        <option value="${master.id}">${master.name}
                    </c:forEach>
                </select></td>
            </tr>
        </table>
        <input type="submit" name="add" value="Add">
        <input type="button" onclick="window.location='${pageContext.request.contextPath}/admin/requests/${reqID}'" name="cancel" value="Cancel">
    </form>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
