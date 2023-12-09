package servlet;

import model.Service;
import service.ServiceDBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ServiceServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServiceDBService serviceDB = new ServiceDBService();
        String path = req.getPathInfo();

        if (path == null || path.isEmpty()) {
            List<Service> services = serviceDB.getAll();
            if (services != null) {
                req.setAttribute("services", services);
                req.getRequestDispatcher("/service/services.jsp").forward(req, resp);
            }
        } else {
            String [] pathList = path.split("/");
            String serviceID = pathList[1];
            switch (serviceID) {
                case "new" -> {
                    req.getRequestDispatcher("/service/addService.jsp").forward(req, resp);
                }
                default -> {
                    Service service = serviceDB.getByID(Integer.parseInt(serviceID));
                    if (service != null) {
                        req.setAttribute("service", service);
                        req.getRequestDispatcher("/service/editService.jsp").forward(req, resp);
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
        String serviceID = path.split("/")[1];
        switch (serviceID) {
            case "new" -> {
                if (req.getParameter("add") != null && req.getParameter("cancel") == null) {
                    result = (new ServiceDBService().create(new Service(
                            0,
                            Integer.parseInt(req.getParameter("request")),
                            Integer.parseInt(req.getParameter("master"))
                    )))
                            ? "Record was successfully added!"
                            : "Record was not added, error occurred.";
                }
            }
            default -> {
                if (req.getParameter("edit") != null) {
                    result = (new ServiceDBService().edit(Integer.parseInt(serviceID), new Service(
                            0,
                            Integer.parseInt(req.getParameter("request")),
                            Integer.parseInt(req.getParameter("master"))
                    )))
                            ? "Record was successfully edited!"
                            : "Record was not edited, error occurred.";
                }
                if (req.getParameter("delete") != null) {
//                    new UserDBService().delete(Integer.parseInt(userID));
                    result = (new ServiceDBService().delete(Integer.parseInt(serviceID)))
                            ? "Record was successfully deleted!"
                            : "Record was not deleted, error occurred.";
                }
            }
        }

//        resp.sendRedirect("/users");
        req.setAttribute("result", result);
        req.setAttribute("table", "services");
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
//        super.doPost(req, resp);
    }
}
