<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/styles/style.css">
    <title>Add request</title>
</head>
<body>
<header>
    <div class="logolist">
        <img class="logo" src="${pageContext.request.contextPath}/images/whitelogo.jpg">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/client">Home</a></li>
                <li><a href="${pageContext.request.contextPath}/client/requests">Your requests</a></li>
            </ul>
        </nav>
    </div>
    <a href="${pageContext.request.contextPath}/logout">
        <img class="logout" src="${pageContext.request.contextPath}/images/logout.png" alt="Log Out">
    </a>
</header>

<div style="margin: 0 auto 0 auto; width: 270px">
    <form method="post">
        <p>You can add request here</p>
        <table>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" required></td>
            </tr>
        </table>
        <input type="submit" name="add" value="Add">
        <input type="button" onclick="window.location='${pageContext.request.contextPath}/client/requests'" name="cancel" value="Cancel">
    </form>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
