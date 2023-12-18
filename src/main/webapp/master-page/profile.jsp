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
                <li><a href="/master">Home</a></li>
                <li><a href="/master/services">Your services</a></li>
            </ul>
        </nav>
    </div>
    <a href="/logout"><img class="logout" src="/images/logout.png" alt="Log Out"></a>
</header>

<div id="content">
    <h1 class="retroshadow">Welcome, ${master.getLogin()}!</h1>
    <p>You can view your services and add comments here.</p>
    <table>
        <tr>
            <th>Name</th>
            <td>${master.getName()}</td>
        </tr>
        <tr>
            <th>E-mail</th>
            <td>${master.getEmail()}</td>
        </tr>
        <tr>
            <th>Login</th>
            <td>${master.getLogin()}</td>
        </tr>
        <tr>
            <th>Role</th>
            <td>Master</td>
        </tr>
    </table>
</div>

<footer>
    <p>Specially for Java and Web term papers</p>
    <p>Tselousov G.A.</p>
</footer>

</body>
</html>
