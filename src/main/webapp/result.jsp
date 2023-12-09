<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="/styles/style.css">
    <title>Result</title>
</head>
<body>
    <header>
        <img src="/images/whitelogo.jpg">
        <nav>
            <ul>
                <li><a href="/">Home</a></li>
                <li><a href="/users">Users</a></li>
                <li><a href="/requests">Requests</a></li>
                <li><a href="/services">Services</a></li>
            </ul>
        </nav>
    </header>

    <p>${result}</p>
    <input type="submit" value="Back to the list" onclick="window.location.href='http://localhost:8080/${table}'">

    <footer>
        <p>Specially for Java and Web term papers</p>
        <p>Tselousov G.A.</p>
    </footer>

</body>
</html>
