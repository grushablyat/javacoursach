package servlet;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class SessionFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String path = req.getRequestURI();

        if ((req.getContextPath() + "/login").equals(path)
                || (req.getContextPath() + "/signup").equals(path)
                || (req.getContextPath() + "/logout").equals(path)
                || (path.startsWith(req.getContextPath() + "/styles/") && path.endsWith(".css"))
                || (path.startsWith(req.getContextPath() + "/images/") && (path.endsWith(".png") || path.endsWith(".jpg")))
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null
                || session.getAttribute("id") == null
                || session.getAttribute("role") == null
        ) {
            var text = req.getContextPath();
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        switch ((int) session.getAttribute("role")) {
            case 1 -> {
                if (!path.startsWith(req.getContextPath() + "/admin")) {
                    req.getRequestDispatcher(req.getContextPath() + "/admin-page/404.jsp").forward(req, resp);
                    return;
                }
            }
            case 2 -> {
                if (!path.startsWith(req.getContextPath() + "/master")) {
                    req.getRequestDispatcher(req.getContextPath() + "/master-page/404.jsp").forward(req, resp);
                    return;
                }
            }
            case 3 -> {
                if (!path.startsWith(req.getContextPath() + "/client")) {
                    req.getRequestDispatcher(req.getContextPath() + "/client-page/404.jsp").forward(req, resp);
                    return;
                }
            }
            default -> {
                // Is it an available option?
                resp.sendRedirect(req.getContextPath() + "/logout");
                return;
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
