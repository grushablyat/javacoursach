<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <title>Add user</title>
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
            <p>You can add user here</p>
            <table>
                <tr>
                    <td>Name</td>
                    <td><input type="text" name="name" pattern="^[A-Z]{1}[a-z]{0,} [A-Z]{1}[a-z]{0,}$"
                    title="Name contains two words beginning with uppercase letters" required></td>
                </tr>
                <tr>
                    <td>E-mail</td>
                    <td><input type="email" name="email" pattern="^[a-z]{1,}[a-z0-9.]{0,}[a-z0-9]{1,}@[a-z]{1,}[a-z0-9]{0,}\.[a-z]{1,}$"
                    title="E-mail is a sequence of letters, digits and points separated by @" required></td>
                </tr>
                <tr>
                    <td>Login</td>
                    <td><input type="text" name="login" pattern="^[a-zA-Z0-9_]{1,}$"
                    title="Login is a sequence of letters, digits and underscore symbols" required></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td><input type="password" name="password" pattern="^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&-_])[A-Za-z\d@$!%*#?&-_]{8,}$"
                    title="Password must contain minimum eight characters, at least one letter, one number and one special character" required></td>
                </tr>
                <tr>
                    <td>Role</td>
                    <td><input type="number" name="role" value="1" min="1"
                    title="Role is a positive number..." required></td>
                </tr>
            </table>
            <input type="submit" name="add" value="Add">
            <input type="button" onclick="window.location='http://localhost:8080/users'" name="cancel" value="Cancel">
        </form>
    </div>

    <footer>
        <p>Specially for Java and Web term papers</p>
        <p>Tselousov G.A.</p>
    </footer>

</body>
</html>
