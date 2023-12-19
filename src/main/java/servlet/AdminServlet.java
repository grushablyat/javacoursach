package servlet;

import exception.WebException;
import jdk.swing.interop.SwingInterOpUtils;
import model.*;
import service.CommentDBService;
import service.RequestDBService;
import service.ServiceDBService;
import service.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/admin/*")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();

        if (path == null || path.isEmpty() || "/".equals(path)) {
            User admin = new UserDBService().getByID((Integer) req.getSession().getAttribute("id"));
            if (admin == null) {
                resp.sendRedirect(req.getContextPath() + "/logout");
                return;
            }

            req.setAttribute("admin", admin);
            req.getRequestDispatcher("/admin-page/profile.jsp").forward(req, resp);
        } else {
            int adminID = (Integer) req.getSession().getAttribute("id");
            try {
                String[] pathList = path.split("/");
                switch (pathList[1]) {
                    case "requests" -> {
                        switch (pathList.length) {
                            // requests
                            case 2 -> {
                                List<RequestExtended> requests = new RequestDBService().getAllExtended();
                                if (requests != null) {
                                    req.setAttribute("requests", requests);
                                }
                                req.getRequestDispatcher("/admin-page/requests.jsp").forward(req, resp);
                            }
                            // request-services
                            case 3 -> {
                                int reqID = Integer.parseInt(pathList[2]);
                                RequestExtended request = new RequestDBService().getForClientByID(reqID);
                                if (request == null) {
                                    throw new WebException(403, "Problem with getting request from DB");
                                }
                                List<model.client.Service> services = new ServiceDBService().getByRequest(reqID);
                                req.setAttribute("request", request);
                                req.setAttribute("services", services);
                                req.getRequestDispatcher("/admin-page/request.jsp").forward(req, resp);
                            }
                            // request-service-comments
                            case 4 -> {
                                int reqID = Integer.parseInt(pathList[2]);
                                if ("new".equals(pathList[3])) {
                                    List<User> masters = new UserDBService().getMasters();
                                    req.setAttribute("reqID", reqID);
                                    req.setAttribute("masters", masters);
                                    req.getRequestDispatcher("/admin-page/addService.jsp").forward(req, resp);
                                } else if ("edit".equals(pathList[3])) {
                                    Request request = new RequestDBService().getByID(reqID);
                                    req.setAttribute("request", request);
                                    req.getRequestDispatcher("/admin-page/editRequest.jsp").forward(req, resp);
                                } else {
                                    int servID = Integer.parseInt(pathList[3]);
                                    RequestExtended request = new RequestDBService().getForClientByID(reqID);
                                    if (request == null) {
                                        throw new WebException(403, "Problem with getting request from DB");
                                    }
                                    model.client.Service service = new ServiceDBService().getForClientByID(servID);
                                    if (service == null || service.getRequest() != reqID) {
                                        throw new WebException(403, "The service is not for this request");
                                    }
                                    List<Comment> comments = new CommentDBService().getByService(servID);
                                    req.setAttribute("request", request);
                                    req.setAttribute("service", service);
                                    req.setAttribute("comments", comments);
                                    req.getRequestDispatcher("/admin-page/request-service.jsp").forward(req, resp);
                                }
                            }
                            default -> {
                                throw new WebException(404, "No such path: " + path);
                            }
                        }
                    }
                    case "users" -> {
                        if (pathList.length == 2) {
                            List<model.admin.User> users = new UserDBService().getForAdminAll();
                            req.setAttribute("users", users);
                            req.getRequestDispatcher("/admin-page/users.jsp").forward(req, resp);
                        } else {
                            throw new WebException(404, "No such path: " + path);
                        }
                    }
                    default -> {
                        throw new WebException(404, "No such path: " + path);
                    }
                }
            } catch (WebException e) {
                System.out.println(e.getCode() + ": " + e.getMessage());
                req.getRequestDispatcher("/admin-page/" + e.getCode() + ".jsp").forward(req, resp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                req.getRequestDispatcher("/admin-page/404.jsp").forward(req, resp);
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

            if ("requests".equals(pathList[1])
                    && pathList.length == 4
            ) {
                switch (pathList[3]) {
                    case "new" -> {
                        if (req.getParameter("add") != null) {
                            int reqID = Integer.parseInt(pathList[2]);
                            new ServiceDBService().create(new Service(
                                    reqID,
                                    Integer.parseInt(req.getParameter("master"))
                            ));
                            resp.sendRedirect(req.getContextPath() + "/admin/requests/" + reqID);
                            return;
                        } else {
                            throw new WebException(405, "The request method POST is inappropriate for the URL: " + path);
                        }
                    }
                    case "edit" -> {
                        if (req.getParameter("edit") != null) {
                            int reqID = Integer.parseInt(pathList[2]);
                            new RequestDBService().edit(reqID, new Request(
                                    reqID,
                                    Integer.parseInt(req.getParameter("client")),
                                    Date.valueOf(req.getParameter("date")),
                                    req.getParameter("description"),
                                    Integer.parseInt(req.getParameter("status"))
                            ));
                            resp.sendRedirect(req.getContextPath() + "/admin/requests/" + reqID);
                            return;
                        } else {
                            throw new WebException(405, "The request method POST is inappropriate for the URL: " + path);
                        }
                    }
                    default -> {
                        throw new WebException(405, "The request method POST is inappropriate for the URL: " + path);
                    }
                }
            } else {
                throw new WebException(405, "The request method POST is inappropriate for the URL: " + path);
            }
        } catch (WebException e) {
            System.out.println(e.getCode() + ", " + e.getMessage());
            req.getRequestDispatcher("/master-page/" + e.getCode() + ".jsp").forward(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            req.getRequestDispatcher("/admin-page/404.jsp").forward(req, resp);
        }

        super.doPost(req, resp);
    }
}
