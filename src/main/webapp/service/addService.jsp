<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <title>Add service</title>
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

<div>
    <form method="post">
        <p>You can add service here</p>
        <table>
            <tr>
                <td>Request</td>
                <td><input type="number" name="request" min="1" value="1"
                           title="Request is a positive number (id of a request)" required></td>
            </tr>
            <tr>
                <td>Master</td>
                <td><input type="number" name="master" min="1" value="1"
                           title="Master is a positive number (id of a master)" required></td>
            </tr>
        </table>
        <input type="submit" name="add" value="Add">
        <input type="button" onclick="window.location='http://localhost:8080/services'" name="cancel" value="Cancel">
    </form>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
