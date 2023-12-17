<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/styles/style.css">
    <title>Home</title>
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

<div id="content">
    <h1 class="retroshadow">Welcome, ${client.getLogin()}!</h1>
    <p>You can create a request for tech-support or check the status of existing one.</p>
    <table>
        <tr>
            <th>Name</th>
            <td>${client.getName()}</td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td>${client.getEmail()}</td>
        </tr>
        <tr>
            <th>Login</th>
            <td>${client.getLogin()}</td>
        </tr>
        <tr>
            <th>Role</th>
            <td>Client</td>
        </tr>
    </table>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
