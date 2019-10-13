package pl.mw.servlets;

import pl.mw.model.Dog;
import pl.mw.utils.DogService;
import pl.mw.utils.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(name = "CustomerPanelServlet", value = "/CustomerPanelServlet")
public class CustomerPanelServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DogService dogService = new DogService();
        UserService userService = new UserService();
        String username = (String) request.getSession(false).getAttribute("username");
        String param = request.getParameter("param");
        if (param.equals("change")) {
            try {
                if(dogService.updateDog(request)){
                    request.getSession(false).setAttribute("doglist", userService.getDogList(username));
                    request.setAttribute("msg_ok", "Pies został zmieniony pomyślnie ");
                    request.getRequestDispatcher("customer.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("msg_nok", "Błąd! Pies nie został zmieniony ");
                    request.getRequestDispatcher("customer.jsp").forward(request, response);
                }
            } catch (ParseException e) {
                request.setAttribute("msg_nok", "Błąd! Pies nie został zmieniony ");
                request.getRequestDispatcher("customer.jsp").forward(request, response);
            }

        } else if(param.equals("addDog")) {
            if (dogService.addDogToDB(request)) {
                request.getSession(false).setAttribute("doglist", userService.getDogList(username));
                request.getRequestDispatcher("customer.jsp").forward(request, response);
            } else {
                response.getWriter().println("Coś poszło nie tak");
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        int id = Integer.parseInt(request.getParameter("id"));
        DogService dogService = new DogService();
        UserService userService = new UserService();
        String username = (String) request.getSession(false).getAttribute("username");
        Dog dog = dogService.getDogById(id).get(0);
        String dogsOwnerName = dog.getDogowner().getUsername();
        if (param.equals("delete") && username.equals(dogsOwnerName)) {
            if (dogService.deleteDog(id)) {
                request.getSession(false).setAttribute("doglist", userService.getDogList(username));
                request.setAttribute("msg_ok", "Pies został usunięty pomyślnie ");
                request.getRequestDispatcher("customer.jsp").forward(request, response);
            } else {
                request.setAttribute("msg_nok", "Błąd! Pies nie został usunięty ");
                request.getRequestDispatcher("customer.jsp").forward(request, response);
            }

        }
        if (param.equals("edit") && username.equals(dogsOwnerName)) {
            request.setAttribute("oldDog", dog);
            request.getRequestDispatcher("edit-dog.jsp").forward(request, response);
        } else {
            response.sendError(403);
        }

    }
}
