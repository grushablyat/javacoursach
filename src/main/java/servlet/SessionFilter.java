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

        if ("/login".equals(path)
                || "/signup".equals(path)
                || "/logout".equals(path)
                || (path.startsWith("/styles/") && path.endsWith(".css"))
                || (path.startsWith("/images/") && (path.endsWith(".png") || path.endsWith(".jpg")))
        ) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null
                || session.getAttribute("id") == null
                || session.getAttribute("role") == null) {
            resp.sendRedirect("/login");
            return;
        }

        if (!path.isEmpty()
                && !"/".equals(path)
                && !"/404".equals(path)) {
            switch ((int) session.getAttribute("role")) {
                case 1 -> {
                    if (!path.startsWith("/users")) {
                        resp.sendRedirect("/404");
                        return;
                    }
                }
                case 2 -> {
                    if (!path.startsWith("/services")) {
                        resp.sendRedirect("/404");
                        return;
                    }
                }
                case 3 -> {
                    if (!path.startsWith("/requests")) {
                        resp.sendRedirect("/404");
                        return;
                    }
                }
                default -> {}
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
