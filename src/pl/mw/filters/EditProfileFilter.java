package pl.mw.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static pl.mw.utils.UserService.checkRole;

@WebFilter(filterName = "EditProfileFilter",
urlPatterns = { "/edit-profile.jsp" }

)
public class EditProfileFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        String username = (String) session.getAttribute("username");
        String role = checkRole(username);
        if(role.equals("employee")|| role.equals("customer")){
            chain.doFilter(req, resp);
        }
        else{
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendError(403);
        }



    }

    public void init(FilterConfig config) throws ServletException {

    }

}
