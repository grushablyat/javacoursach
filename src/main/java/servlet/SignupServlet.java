package servlet;

import model.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("signUpResult", "Enter information about you");
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        if (login == null || name == null || email == null || password == null) {
            req.setAttribute("signUpResult", "Don't leave input fields empty");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            resp.setContentType("text/html");
            super.doPost(req, resp);
            return;
        }

        Integer id = new LoginService().signUp(name, email, login, password);
        if (id != null) {
            if (req.getSession().getAttribute("session") != null) {
                req.getSession().removeAttribute("session");
            }
            req.getSession().setAttribute("session", id);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("signUpResult", "Username is already taken");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            resp.setContentType("text/html");
            super.doPost(req, resp);
        }
    }
}
