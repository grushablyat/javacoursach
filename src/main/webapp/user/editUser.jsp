<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <script type="text/javascript" src="../scripts/userValidation.js"></script>
    <title>Edit user</title>
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
<%--        <form name="userValidation" action="/users" method="post" onSubmit="return userValidation(this);">--%>
        <form method="post">
            <p>You can change something and save record or delete it</p>
            <table>
                    <tr>
                        <td>Name</td>
                        <td><input type="text" name="name" value="${user.name}"
                                   pattern="^[A-Z]{1}[a-z]{0,} [A-Z]{1}[a-z]{0,}$"
                                   title="Name contains two words beginning with uppercase letters"
                                   required></td>
                    </tr>
                    <tr>
                        <td>E-mail</td>
                        <td><input type="email" name="email" value="${user.email}"
                                   pattern="^[a-z]{1,}[a-z0-9.]{0,}[a-z0-9]{1,}@[a-z]{1,}[a-z0-9]{0,}\.[a-z]{1,}$"
                                   title="E-mail is a sequence of letters, digits and points separated by @"
                                   required></td>
                    </tr>
                    <tr>
                        <td>Login</td>
                        <td><input type="text" name="login" value="${user.login}"
                                   pattern="^[a-zA-Z0-9_]{1,}$"
                                   title="Login is a sequence of letters, digits and underscore symbols"
                                   required></td>
                    </tr>
                    <tr>
                        <td>Role</td>
                        <td><input type="number" name="role" value="${user.role}"
                                   min="1"
                                   title="Role is a positive number (ID of a role)"
                                   required></td>
                    </tr>
            </table>
            <input type="submit" name="edit" value="Save changes">
<%--            <input type="button" onclick="window.location='http://localhost:8080/users/delete/${user.id}'" name="delete" value="Delete">--%>
<%--            <input type="submit" name="delete" value="Delete">--%>
            <input type="button" onclick="window.location='http://localhost:8080/users'" name="cancel" value="Cancel">
        </form>
        <form method="post">
            <input type="submit" name="delete" value="Delete">
<%--            <input type="button" onclick="window.location='http://localhost:8080/users'" name="cancel" value="Cancel">--%>
        </form>
    </div>

    <script>
    </script>

    <footer>
        <p>Specially for Java and Web term papers</p>
        <p>Tselousov G.A.</p>
    </footer>

</body>
</html>
