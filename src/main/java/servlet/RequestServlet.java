package servlet;

import model.Request;
import service.RequestDBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

public class RequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDBService requestDB = new RequestDBService();
        String path = req.getPathInfo();

        if (path == null || path.isEmpty() || "/".equals(path)) {
            List<Request> requests = requestDB.getAll();
            if (requests != null) {
                req.setAttribute("requests", requests);
                req.getRequestDispatcher("/request/requests.jsp").forward(req, resp);
            }
        } else {
            String [] pathList = path.split("/");
            String requestID = pathList[1];
            switch (requestID) {
                case "new" -> {
                    req.getRequestDispatcher("/request/addRequest.jsp").forward(req, resp);
                }
                default -> {
                    Request request = requestDB.getByID(Integer.parseInt(requestID));
                    if (request != null) {
                        req.setAttribute("request", request);
                        req.getRequestDispatcher("/request/editRequest.jsp").forward(req, resp);
                    } else {
                        resp.sendRedirect("/404");
                        return;
                    }
                }
            }
        }

        resp.setContentType("text/html");
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String path = req.getPathInfo();

        if (path == null || path.isEmpty()) {
            return;
        }

        String result = "Nothing happened.";
        String requestID = path.split("/")[1];
        switch (requestID) {
            case "new" -> {
                if (req.getParameter("add") != null && req.getParameter("cancel") == null) {
                    result = (new RequestDBService().create(new Request(
                            0,
                            Integer.parseInt(req.getParameter("client")),
                            Date.valueOf(req.getParameter("date")),
                            req.getParameter("description"),
                            Integer.parseInt(req.getParameter("status"))
                    )))
                            ? "Record was successfully added!"
                            : "Record was not added, error occurred.";
                }
            }
            default -> {
                if (req.getParameter("edit") != null) {
                    result = (new RequestDBService().edit(Integer.parseInt(requestID), new Request(
                            0,
                            Integer.parseInt(req.getParameter("client")),
                            Date.valueOf(req.getParameter("date")),
                            req.getParameter("description"),
                            Integer.parseInt(req.getParameter("status"))
                    )))
                            ? "Record was successfully edited!"
                            : "Record was not edited, error occurred.";
                }
                if (req.getParameter("delete") != null) {
                    result = (new RequestDBService().delete(Integer.parseInt(requestID)))
                            ? "Record was successfully deleted!"
                            : "Record was not deleted, error occurred.";
                }
            }
        }

        req.setAttribute("result", result);
        req.setAttribute("table", "requests");
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
    }
}
