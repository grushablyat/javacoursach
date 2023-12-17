package servlet;

import model.Role;
import model.User;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        String roleStr = req.getParameter("role");
        String keyword = req.getParameter("keyword");

        if (login == null || name == null || email == null || password == null || roleStr == null) {
            req.setAttribute("signUpResult", "Don't leave input fields empty");
            req.getRequestDispatcher("/signup.jsp").forward(req, resp);
            resp.setContentType("text/html");
            super.doPost(req, resp);
            return;
        }

        int role = Integer.parseInt(roleStr);
        LoginService ls = new LoginService();

        boolean correctKeyword = false;
        switch (role) {
            case 3 ->  correctKeyword = true;
            case 2 -> {
                if ("4547acb2932caa78af94d3ea6fe2d0a196667ba44d0a9b2798c49bb7debed27d".equals(ls.hash(keyword))) {
                    correctKeyword = true;
                }
            }
            case 1 -> {
                if ("ac22ed03a20a97863b4539cead042632e235bff30d5db2403c51dc5587dffbd7".equals(ls.hash(keyword))) {
                    correctKeyword = true;
                }
            }
            default -> {}
        }

        if (correctKeyword) {
            Integer id = ls.signUp(name, email, login, password, role);

            if (id != null) {
                if (req.getSession().getAttribute("session") != null) {
                    req.getSession().removeAttribute("session");
                }
                req.getSession().setAttribute("session", id);
                resp.sendRedirect("/");
                return;
            } else {
                req.setAttribute("signUpResult", "Username is already taken");
                req.getRequestDispatcher("/signup.jsp").forward(req, resp);
                resp.setContentType("text/html");
                super.doPost(req, resp);
                return;
            }
        }

        req.setAttribute("signUpResult", "Incorrect keyword");
        req.getRequestDispatcher("/signup.jsp").forward(req, resp);
        resp.setContentType("text/html");
        super.doPost(req, resp);
    }
}
