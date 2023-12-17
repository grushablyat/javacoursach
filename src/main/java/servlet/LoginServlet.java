package servlet;

import model.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/login", "/logout"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();

        if ("/logout".equals(uri)) {
            HttpSession session = req.getSession();
            if (session != null && session.getAttribute("id") != null) {
                session.removeAttribute("id");
            }
            if (session != null && session.getAttribute("role") != null) {
                session.removeAttribute("role");
            }
        }

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

        User user = new LoginService().logIn(login, password);
        if (user != null && user.getId() != null) {
            HttpSession session = req.getSession();
            session.setAttribute("id", user.getId());
            session.setAttribute("role", user.getRole());
            resp.sendRedirect("/");
        } else {
            req.setAttribute("logInResult", "Incorrect username or password");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            resp.setContentType("text/html");
            super.doPost(req, resp);
        }
    }
}
