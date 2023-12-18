package servlet;

import exception.WebException;
import model.*;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/client/*")
public class ClientServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getPathInfo();
        RequestExtendedDBService redbs = new RequestExtendedDBService();

        if (path == null || path.isEmpty() || "/".equals(path)) {
            User client = new UserDBService().getByID((Integer) req.getSession().getAttribute("id"));
            if (client == null) {
                resp.sendRedirect("/logout");
                return;
            }

            req.setAttribute("client", client);
            req.getRequestDispatcher("/client-page/profile.jsp").forward(req, resp);
        } else {
            Integer clientID = (Integer) req.getSession().getAttribute("id");
            try {
                String[] pathList = path.split("/");
                if ("requests".equals(pathList[1])) {
                    switch (pathList.length) {
                        case 2 -> {
                            List<RequestExtended> requests = redbs.getByClient(clientID);
                            if (requests != null) {
                                req.setAttribute("requests", requests);
                            }
                            req.getRequestDispatcher("/client-page/requests.jsp").forward(req, resp);
                        }
                        case 3 -> {
                            String requestID = pathList[2];
                            if ("new".equals(requestID)) {
                                req.getRequestDispatcher("/client-page/addRequest.jsp").forward(req, resp);
                            } else {
                                int reqID = Integer.parseInt(requestID);
                                RequestExtended request = redbs.getByID(reqID);
                                if (request == null || request.getClient() != clientID) {
                                    throw new WebException(403, "Client is not the owner of this request");
                                }
                                List<model.client.Service> services = new ServiceDBService().getByRequest(reqID);
                                req.setAttribute("request", request);
                                req.setAttribute("services", services);
                                req.getRequestDispatcher("/client-page/request.jsp").forward(req, resp);
                            }
                        }
                        case 4 -> {
                            int reqID = Integer.parseInt(pathList[2]);
                            int servID = Integer.parseInt(pathList[3]);
                            RequestExtended request = redbs.getByID(reqID);
                            if (request == null || request.getClient() != clientID) {
                                throw new WebException(403, "Client is not the owner of this request");
                            }
                            model.client.Service service = new ServiceDBService().getForClientByID(servID);
                            if (service == null || service.getRequest() != reqID) {
                                throw new WebException(403, "The service is not for this request");
                            }
                            List<Comment> comments = new CommentDBService().getByService(servID);
                            req.setAttribute("request", request);
                            req.setAttribute("service", service);
                            req.setAttribute("comments", comments);
                            req.getRequestDispatcher("/client-page/request-service.jsp").forward(req, resp);
                        }
                        default -> {
                            throw new WebException(404, "No such path");
                        }
                    }
                } else {
                    throw new WebException(404, "No path without '/requests'-prefix");
                }
            } catch (WebException e) {
                System.out.println(e.getCode() + ": " + e.getMessage());
                switch (e.getCode()) {
                    case 403 -> {
                        req.getRequestDispatcher("/client-page/403.jsp").forward(req, resp);
                    }
                    case 404 -> {
                        req.getRequestDispatcher("/client-page/404.jsp").forward(req, resp);
                    }
                    default -> {
                        req.getRequestDispatcher("/client-page/404.jsp").forward(req, resp);
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                req.getRequestDispatcher("/client-page/404.jsp").forward(req, resp);
            }
        }

        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo();

        if (path == null || path.isEmpty() || "/".equals(path)) {
            return;
        }

        String result = "Nothing happened";
        String[] pathList = path.split("/");

        if ("requests".equals(pathList[1])
                && pathList.length > 2
                && "new".equals(pathList[2])) {
            if (req.getParameter("add") != null) {
                result = (new RequestDBService().create(new Request(
                        (int) req.getSession().getAttribute("id"),
                        req.getParameter("description")
                )))
                    ? "Request was successfully created!"
                    : "Request was not created, error occurred";
            } else {

            }
        }

//        req.setAttribute("addRequestResult", result);
//        req.getRequestDispatcher()
        resp.sendRedirect("/client/requests");
    }
}
