package pl.mw.servlets;

import pl.mw.dao.DaoFactory;
import pl.mw.model.Dog;
import pl.mw.utils.UserService;
import pl.mw.utils.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        String username = request.getParameter("login");
        String userpass = request.getParameter("pass");
        UserService userService = new UserService();
        VisitService visitService = new VisitService();
        if (userService.validateUser(username, userpass)) {
            if (userService.checkRole(username).equals("admin")) {
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("employees", userService.getEmployees());
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
            if (userService.checkRole(username).equals("customer")) {

                request.getSession().setAttribute("doglist", userService.getDogList(username));
                request.getSession().setAttribute("username", username);
                request.getSession(false).setAttribute("currentVisits",visitService.getAllCurrentVisits(username));
                request.getRequestDispatcher("customer.jsp").forward(request, response);

            }
            if (userService.checkRole(username).equals("employee")) {
                request.getSession().setAttribute("username", username);
                List<Dog> list = DaoFactory.getDaoFactory().getDogDao().getAll().stream().filter(dog -> !dog.getDogname().equals("default_value")).collect(Collectors.toList());
                request.getSession().setAttribute("doglist",list );
                request.getRequestDispatcher("employee.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Błąd uwierzytelniania - uprawnienia");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            request.setAttribute("error", "Błąd uwierzytelniania - błąd login/hasło");
            request.getRequestDispatcher("index.jsp").forward(request, response);

        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }
}
