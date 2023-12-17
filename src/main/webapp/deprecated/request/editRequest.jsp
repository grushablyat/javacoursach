<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/styles/style.css">
    <title>Add request</title>
</head>
<body>
<header>
    <div class="logolist">
        <img class="logo" src="/images/whitelogo.jpg">
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/users">Users</a></li>
                <li><a href="/requests">Requests</a></li>
                <li><a href="/services">Services</a></li>
            </ul>
        </nav>
    </div>
    <a href="/logout"><img class="logout" src="/images/logout.png" alt="Log Out"></a>
</header>

<div>
    <form method="post">
        <p>You can change something and save record or delete it</p>
        <table>
            <tr>
                <td>Client</td>
                <td><input type="number" name="client" min="1" value="${request.client}"
                           title="Client is a positive number (id of a user)" required></td>
            </tr>
            <tr>
                <td>Date</td>
                <td><input type="date" name="date" value="${request.date}" required></td>
            </tr>
            <tr>
                <td>Description</td>
                <td><input type="text" name="description" value="${request.description}" required></td>
            </tr>
            <tr>
                <td>Status</td>
                <td><input type="number" name="status" value="${request.status}" min="1" required></td>
            </tr>
        </table>
        <input type="submit" name="edit" value="Save changes">
        <input type="button" onclick="window.location='http://localhost:8080/requests'" name="cancel" value="Cancel">
    </form>
    <form method="post">
        <input type="submit" name="delete" value="Delete">
    </form>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>