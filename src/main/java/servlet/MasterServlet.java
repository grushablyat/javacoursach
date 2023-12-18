package servlet;

import exception.WebException;
import model.Comment;
import model.User;
import service.CommentDBService;
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
                        case 3 -> {
                            int servID = Integer.parseInt(pathList[2]);
                            model.master.Service service = sdbs.getForMasterByID(servID);
                            if (service == null || service.getMaster() != masterID) {
                                throw new WebException(403, "Master is not the owner of this service");
                            }
                            List<Comment> comments = new CommentDBService().getByService(servID);
                            req.setAttribute("service", service);
                            req.setAttribute("comments", comments);
                            req.getRequestDispatcher("/master-page/service.jsp").forward(req, resp);
                        }
                        default -> {
                            throw new WebException(404, "No such path");
                        }
                    }
                } else {
                    throw new WebException(404, "No path without '/services'-prefix");
                }
            } catch (WebException e) {
                System.out.println(e.getCode() + ": " + e.getMessage());
                switch (e.getCode()) {
                    case 403 -> {
                        req.getRequestDispatcher("/master-page/403.jsp").forward(req, resp);
                    }
                    case 404 -> {
                        req.getRequestDispatcher("/master-page/404.jsp").forward(req, resp);
                    }
                    default -> {
                        req.getRequestDispatcher("/master-page/404.jsp").forward(req, resp);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
