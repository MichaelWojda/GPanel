package pl.mw.servlets;

import pl.mw.dao.DaoFactory;
import pl.mw.model.User;
import pl.mw.model.Visit;
import pl.mw.utils.UserService;
import pl.mw.utils.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static pl.mw.utils.UserService.checkRole;
import static pl.mw.utils.UserService.updateUser;

@WebServlet(name = "OptionsServlet", value = "/OptionsServlet")
public class OptionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        if (param.equals("update")) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                String groomer = request.getParameter("groomer");
                String newDate = request.getParameter("newDate");
                String hour = request.getParameter("hour");
                if (VisitService.updateVisit(id, groomer, newDate, hour)) {
                    request.setAttribute("msg_ok", "Zmiana w rekordzie została dokonana pomyślnie");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg_nok", "Wystąpił błąd - zmiana nie udała się, sprawdż czy w podanym terminie nie ma już innych wizyt");
                    request.getRequestDispatcher("admin.jsp").forward(request, response);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        if (param.equals("change_user")) {
            String userNameNew = (request.getParameter("userNameNew"));
            String userFirstNameNew = request.getParameter("userFirstNameNew");
            String userLastNameNew = request.getParameter("userLastNameNew");
            int newPhoneNumber = Integer.parseInt(request.getParameter("newPhoneNumber"));
            String newEmail = request.getParameter("newEmail");
            int id = Integer.parseInt(request.getParameter("id"));
            User user = UserService.getUserById(id);
            String oldName = user.getUsername();
            UserService.deleteRole(oldName);
            String role = request.getParameter("role");
            user.setUsername(userNameNew);
            user.setFirstname(userFirstNameNew);
            user.setLastname(userLastNameNew);
            user.setPhonenumber(newPhoneNumber);
            user.setEmail(newEmail);
            if (UserService.updateUser(user)) {
                UserService.setRole(userNameNew, role);
                createUserList(request);
                request.setAttribute("msg_ok", "Użytkownik został zmieniony pomyślnie");
                request.getSession(false).setAttribute("employees", UserService.getEmployees());
                request.getRequestDispatcher("admin.jsp").forward(request, response);

            } else {
                createUserList(request);
                request.setAttribute("msg_nok", "Błąd! Użytkownik nie został zmieniony ");
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }


        }
        if (param.equals("change_user_profile")) {
            String userFirstNameNew = request.getParameter("userFirstNameNew");
            String userLastNameNew = request.getParameter("userLastNameNew");
            int newPhoneNumber = Integer.parseInt(request.getParameter("newPhoneNumber"));
            String newEmail = request.getParameter("newEmail");
            User user = UserService.getUserbyName((String) request.getSession(false).getAttribute("username"));
            String role = checkRole(user.getUsername());
            user.setFirstname(userFirstNameNew);
            user.setLastname(userLastNameNew);
            user.setPhonenumber(newPhoneNumber);
            user.setEmail(newEmail);
            if (UserService.updateUser(user)) {
                request.setAttribute("msg_ok", "Profil został zmieniony pomyślnie");
                request.getSession(false).setAttribute("employees", UserService.getEmployees());
                if(role.equals("customer")) {
                    request.getRequestDispatcher("customer.jsp").forward(request, response);
                }
                else{
                    request.getRequestDispatcher("employee.jsp").forward(request, response);
                }

            } else {
                request.setAttribute("msg_nok", "Błąd! Profil nie został zmieniony ");
                if(role.equals("customer")) {
                    request.getRequestDispatcher("customer.jsp").forward(request, response);
                }
                else{
                    request.getRequestDispatcher("employee.jsp").forward(request, response);
                }
            }


        }
        if(param.equals("confirm_pass")){
            String oldpass = request.getParameter("oldPass");
            String newPass = request.getParameter("newPass");
            UserService userService =new UserService();
            User user = DaoFactory.getDaoFactory().getUserDao().getUserByName(String.valueOf(request.getSession(false).getAttribute("username")));
            if(user.getPassword().equals(oldpass) || userService.encryptPass(oldpass).equals(user.getPassword())){
                user.setPassword(userService.encryptPass(newPass));
                boolean updated =updateUser(user);
                if(updated==true){
                    String role = checkRole(user.getUsername());
                    request.setAttribute("msg_ok", "Hasło zostało zmienione pomyślnie");
                    if(role.equals("customer")) {
                        request.getRequestDispatcher("customer.jsp").forward(request, response);
                    }
                    else{
                        request.getRequestDispatcher("employee.jsp").forward(request, response);
                    }
                }
                else {
                    request.setAttribute("msg_nok", "Błąd,hasło nie zostało zmienione!!!");
                    request.getRequestDispatcher("edit-pass.jsp").forward(request, response);

                }

            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        if (param.equals("delete")) {
            if (VisitService.deleteById(request.getParameter("id"))) {
                List<Visit> visitList;
                String from = String.valueOf(request.getSession(false).getAttribute("fDate"));
                String to = String.valueOf(request.getSession(false).getAttribute("tDate"));
                if (!from.isEmpty() && !to.isEmpty()) {
                    visitList = DaoFactory.getDaoFactory().getVisitDao().getByRange(LocalDate.parse(from),LocalDate.parse(to));
                } else {
                    visitList = VisitService.getAll();
                }
                request.setAttribute("msg_ok", "Rekord został usuniety pomyślnie");
                request.setAttribute("visitList", visitList);
                request.getRequestDispatcher("admin.jsp").forward(request, response);

            } else {
                List<Visit> visitList = VisitService.getAll();
                request.setAttribute("visitList", visitList);
                request.setAttribute("msg_nok", "Wystąpił błąd w trakcie usuwania rekordu");
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        }
        if (param.equals("edit")) {
            Visit visit = VisitService.getById(request.getParameter("id"));
            request.setAttribute("singleVisit", visit);
            request.getRequestDispatcher("edit-visit.jsp").forward(request, response);

        }
        if (param.equals("showAll")) {
            List<Visit> visitList = VisitService.getAll();
            if (!visitList.isEmpty()) {
                request.setAttribute("visitList", visitList);
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else {
                request.setAttribute("msg_nok", "Nie ma utworzonych żadnych wizyt");
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        }
        if (param.equals("showUsers")) {
            if (createUserList(request)) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            } else {
                request.setAttribute("msg_nok", "W bazie danych nie ma zapisanych żadnych uzytkowników");
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }
        }
        if (param.equals("delete_user")) {
            boolean check = UserService.deleteByID(Integer.valueOf(request.getParameter("id")));
            if (check) {
                createUserList(request);
                request.setAttribute("msg_ok", "Użytkownik został usunięty pomyślnie");
                request.getSession(false).setAttribute("employees", UserService.getEmployees());
                request.getRequestDispatcher("admin.jsp").forward(request, response);

            } else {
                createUserList(request);
                request.setAttribute("msg_nok", "Błąd! Użytkownik nie został usunięty ");
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }

        }
        if (param.equals("edit_user")) {
            User userOld = UserService.getUserById(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("userOld", userOld);
            request.getRequestDispatcher("edit-user.jsp").forward(request, response);
        }
        if (param.equals("edit_profile")) {
            User userOld = UserService.getUserbyName((String) request.getSession(false).getAttribute("username"));
            request.setAttribute("userOld", userOld);
            request.getRequestDispatcher("edit-profile.jsp").forward(request, response);
        }
        if(param.equals("change_pass")){
            User user = UserService.getUserbyName((String) request.getSession(false).getAttribute("username"));
            request.setAttribute("user", user);
            request.getRequestDispatcher("edit-pass.jsp").forward(request, response);

        }
        if(param.equals("back")){
            String uname = (String) request.getSession(false).getAttribute("username");
            String role = checkRole(uname);
            if(role.equals("customer")){
                response.sendRedirect("customer.jsp");
            }
            else{
                response.sendRedirect("employee.jsp");
            }
        }



    }

    private boolean createUserList(HttpServletRequest request) {
        List<User> userList = UserService.getAll().stream().filter(user -> !user.getUsername().equals("default_value")).collect(Collectors.toList());
        if (!userList.isEmpty()) {
            userList.forEach(user -> user.setRole(checkRole(user.getUsername())));
            Collections.sort(userList, (user1, user2) -> user1.getId_user() - user2.getId_user());
            request.setAttribute("userList", userList);
            return true;
        }
        return false;

    }
}
