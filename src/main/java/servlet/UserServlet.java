package servlet;

import model.User;
import service.UserDBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDBService userDB = new UserDBService();
        String path = req.getPathInfo();

        if (path == null || path.isEmpty()) {
            List<User> users = userDB.getAll();
            if (users != null) {
                req.setAttribute("users", users);
                req.getRequestDispatcher("/user/users.jsp").forward(req, resp);
            }
        } else {
            String [] pathList = path.split("/");
            String userID = pathList[1];
            switch (userID) {
                case "new" -> {
                    req.getRequestDispatcher("/user/addUser.jsp").forward(req, resp);
                }
//                case "delete" -> {
//                    req.setAttribute( "result", new UserDBService().delete(Integer.parseInt(pathList[2]))
//                        ? "Record was successfully deleted!"
//                        : "Record was not deleted, error occurred."
//                    );
//                    req.getRequestDispatcher("/result.jsp").forward(req, resp);
//                    return;
//                }
                default -> {
                    User user = userDB.getByID(Integer.parseInt(userID));
                    if (user != null) {
                        req.setAttribute("user", user);
                        req.getRequestDispatcher("/user/editUser.jsp").forward(req, resp);
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

//        User user = new User(
//                0,
//                req.getParameter("name"),
//                req.getParameter("email"),
//                req.getParameter("login"),
//                req.getParameter("password"),
//                Integer.parseInt(req.getParameter("role"))
//        );

        String result = "Nothing happened.";
        String userID = path.split("/")[1];
        switch (userID) {
            case "new" -> {
                if (req.getParameter("add") != null && req.getParameter("cancel") == null) {
//                    new UserDBService().create(user);
                    result = (new UserDBService().create(new User(
                            0,
                            req.getParameter("name"),
                            req.getParameter("email"),
                            req.getParameter("login"),
                            req.getParameter("password"),
                            Integer.parseInt(req.getParameter("role"))
                    )))
                            ? "Record was successfully added!"
                            : "Record was not added, error occurred.";
                }
            }
            default -> {
                if (req.getParameter("edit") != null) {
//                    new UserDBService().edit(Integer.parseInt(userID), user);
                    result = (new UserDBService().edit(Integer.parseInt(userID), new User(
                            0,
                            req.getParameter("name"),
                            req.getParameter("email"),
                            req.getParameter("login"),
                            req.getParameter("password"),
                            Integer.parseInt(req.getParameter("role"))
                    )))
                            ? "Record was successfully edited!"
                            : "Record was not edited, error occurred.";
                }
                if (req.getParameter("delete") != null) {
//                    new UserDBService().delete(Integer.parseInt(userID));
                    result = (new UserDBService().delete(Integer.parseInt(userID)))
                            ? "Record was successfully deleted!"
                            : "Record was not deleted, error occurred.";
                }
            }
        }

//        resp.sendRedirect("/users");
        req.setAttribute("result", result);
        req.setAttribute("table", "users");
        req.getRequestDispatcher("/result.jsp").forward(req, resp);
//        super.doPost(req, resp);
    }
}
