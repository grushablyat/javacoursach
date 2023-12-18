package servlet;

import model.Request;
import model.RequestExtended;
import model.Service;
import model.User;
import service.RequestDBService;
import service.RequestExtendedDBService;
import service.ServiceDBService;
import service.UserDBService;

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
                                req.getRequestDispatcher("/client-page/requests.jsp").forward(req, resp);
                            }
                        }
                        case 3 -> {
                            String requestID = pathList[2];
                            if ("new".equals(requestID)) {
                                req.getRequestDispatcher("/client-page/addRequest.jsp").forward(req, resp);
                            } else {
                                int reqID = Integer.parseInt(requestID);
                                RequestExtended request = redbs.getByID(reqID);
                                if (request.getClient() != clientID) {
                                    throw new Exception("404: Client is not the owner of this request");
                                }
                                List<model.client.Service> services = new ServiceDBService().getByRequest(reqID);
                                req.setAttribute("request", request);
                                req.setAttribute("services", services);
                                req.getRequestDispatcher("/client-page/request.jsp").forward(req, resp);
                            }
                        }
                        default -> {
                            throw new Exception("404: There is not such path");
                        }
                    }
                }
            } catch (Exception e) {
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
