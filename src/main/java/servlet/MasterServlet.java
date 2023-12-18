package servlet;

import model.User;
import service.ServiceDBService;
import service.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/master/*")
public class MasterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        ServiceDBService sdbs = new ServiceDBService();

        if (path == null || path.isEmpty() || "/".equals(path)) {
            User master = new UserDBService().getByID((Integer) req.getSession().getAttribute("id"));
            if (master == null) {
                resp.sendRedirect("/logout");
                return;
            }

            req.setAttribute("master", master);
            req.getRequestDispatcher("/master-page/profile.jsp").forward(req, resp);
        } else {
            Integer masterID = (int) req.getSession().getAttribute("id");
            try{
                String[] pathList = path.split("/");
                if ("services".equals(pathList[1])) {
                    switch (pathList.length) {
                        case 2 -> {
                            List<model.master.Service> services = sdbs.getByMaster(masterID);
                            if (services != null) {
                                req.setAttribute("services", services);
                            }
                            req.getRequestDispatcher("/master-page/services.jsp").forward(req, resp);
                        }
                        case 3 -> {}
                        default -> {
                            throw new Exception("404: No such path");
                        }
                    }
                } else {
                    throw new Exception("404: No path without '/services'-prefix");
                }
            } catch (Exception e) {
                req.getRequestDispatcher("/master-page/404.jsp").forward(req, resp);
            }
        }

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
