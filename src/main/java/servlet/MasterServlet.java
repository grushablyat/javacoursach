package servlet;

import model.User;
import service.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/master/*")
public class MasterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if (path == null || path.isEmpty() || "/".equals(path)) {
            User master = new UserDBService().getByID((Integer) req.getSession().getAttribute("id"));
            if (master == null) {
                resp.sendRedirect("/logout");
                return;
            }

            req.setAttribute("master", master);
            req.getRequestDispatcher("/master-page/profile.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/master-page/404.jsp").forward(req, resp);
        }

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
