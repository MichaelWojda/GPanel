package pl.mw.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static pl.mw.utils.UserService.checkRole;

@WebFilter(filterName = "CustomerFilter",
urlPatterns = { "/customer.jsp","/edit-dog.jsp","/visit-history.jsp","/book-visit.jsp" }

)
public class CustomerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);
        //UserService userService = new UserService();
        String username = (String) session.getAttribute("username");
        if(checkRole(username).equals("customer")){
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
