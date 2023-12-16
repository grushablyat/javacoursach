package servlet;

import service.HashService;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("logInResult", "Enter username and password");
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || password == null) {
            req.setAttribute("logInResult", "Don't leave input fields empty");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            resp.setContentType("text/html");
            super.doPost(req, resp);
            return;
        }

        Integer id = new LoginService().logIn(login, password);
        if (id != null) {
            req.getSession().setAttribute("session", id);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("logInResult", "Incorrect username or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            resp.setContentType("text/html");
            super.doPost(req, resp);
        }
    }
}
