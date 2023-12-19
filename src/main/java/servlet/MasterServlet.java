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
                resp.sendRedirect(req.getContextPath() + "/logout");
                return;
            }

            req.setAttribute("master", master);
            req.getRequestDispatcher("/master-page/profile.jsp").forward(req, resp);
        } else {
            int masterID = (int) req.getSession().getAttribute("id");
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
                        case 4 -> {
                            if ("new".equals(pathList[3])) {
                                req.setAttribute("servID", Integer.parseInt(pathList[2]));
                                req.getRequestDispatcher("/master-page/addComment.jsp").forward(req, resp);
                            } else {
                                throw new WebException(404, "No such path: " + path);
                            }
                        }
                        default -> {
                            throw new WebException(404, "No such path + " + path);
                        }
                    }
                } else {
                    throw new WebException(404, "No path without '/services'-prefix, current: " + path);
                }
            } catch (WebException e) {
                System.out.println(e.getCode() + ": " + e.getMessage());
                req.getRequestDispatcher("/master-page/" + e.getCode() + ".jsp").forward(req, resp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                req.getRequestDispatcher("/master-page/404.jsp").forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo();

        try {
            if (path == null || path.isEmpty() || "/".equals(path)) {
                throw new WebException(405, "The request method POST is inappropriate for the URL: " + path);
            }

            String[] pathList = path.split("/");

            if ("services".equals(pathList[1])
                    && pathList.length == 4
                    && "new".equals(pathList[3])
            ) {
                if (req.getParameter("add") != null) {
                    int servID = Integer.parseInt(pathList[2]);
                    new CommentDBService().create(new Comment(
                            servID,
                            req.getParameter("text")
                    ));
                    resp.sendRedirect(req.getContextPath() + "/master/services/" + servID);
                    return;
                } else {
                    throw new WebException(405, "The request method POST is inappropriate for the URL: " + path);
                }
            } else {
                throw new WebException(405, "The request method POST is inappropriate for the URL: " + path);
            }
        } catch (WebException e) {
            System.out.println(e.getCode() + ": " + e.getMessage());
            req.getRequestDispatcher("/master-page/" + e.getCode() + ".jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.getRequestDispatcher("/admin-page/404.jsp").forward(req, resp);
        }

        super.doPost(req, resp);
    }
}
