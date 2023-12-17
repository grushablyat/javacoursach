package servlet;

import model.User;
import service.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/client/*")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if (path == null || path.isEmpty() || "/".equals(path)) {
            Object id = req.getSession().getAttribute("id");
            if (id == null) {
                resp.sendRedirect("/logout");
                return;
            }

            User client = new UserDBService().getByID((Integer) id);
            if (client == null) {
                resp.sendRedirect("/logout");
                return;
            }

            req.setAttribute("client", client);
            req.getRequestDispatcher("/client-page/profile.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/client-page/404.jsp").forward(req, resp);
        }

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
