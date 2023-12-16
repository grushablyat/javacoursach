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
        if (path.equals("/login") || path.equals("/signup")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("session") == null) {
            resp.sendRedirect("/login");
            return;
        }

//        if (session == null || session.getAttribute("session") == null ||
//                (new UserDBService().getByID(Integer.parseInt(
//                        String.valueOf(session.getAttribute("session"))))) == null) {
//            resp.sendRedirect("/login");
//            return;
//        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
