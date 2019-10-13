package pl.mw.servlets;

import pl.mw.dao.DaoFactory;
import pl.mw.dao.DogDao;
import pl.mw.dao.UserDao;
import pl.mw.dao.UserDaoImpl;
import pl.mw.model.Dog;
import pl.mw.model.User;
import pl.mw.model.Visit;
import pl.mw.utils.DogService;
import pl.mw.utils.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "EmployeeOptionsServlet", value="/EmployeeOptionsServlet")
public class EmployeeOptionsServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        if(param.equals("confNote")){

            int id = Integer.parseInt(request.getParameter("id"));
            String newNote = request.getParameter("groomernotes");
            Dog dog = DaoFactory.getDaoFactory().getDogDao().selectByParam("id_dog",id).get(0);
            dog.setGroomer_notes(newNote);
            DogDao dogDao = DaoFactory.getDaoFactory().getDogDao();
            try {
                if(dogDao.update(dog)){
                    List<Dog> list =dogDao.getAll().stream().filter(dog1 -> !dog1.getDogname().equals("default_value")).collect(Collectors.toList());
                    request.getSession(false).setAttribute("doglist",list);
                    request.setAttribute("allDogList",list);
                    request.setAttribute("msg_ok","Nota została zmieniona poprawnie");
                    request.getRequestDispatcher("employee.jsp").forward(request,response);
                }
            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("msg_nok","Błąd!!! Nota nie została zmieniona ");
                request.getRequestDispatcher("employee.jsp").forward(request,response);
            }


        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String) request.getSession(false).getAttribute("username");
        String param = request.getParameter("param");
        VisitService visitService = new VisitService();
        if(param.equals("myVisits")){
            List<Visit> currentVisitList = visitService.getCurrentEmployeeVisits(username);
            if(currentVisitList!=null){
                request.setAttribute("currentVisitList",currentVisitList);
                request.getRequestDispatcher("employee.jsp").forward(request,response);
            }
        }
        if(param.equals("history")){
            List<Visit> historyVisitList = visitService.getEmployeeHistoryVisits(username);
            request.setAttribute("historyVisitList",historyVisitList);
            request.getRequestDispatcher("employee.jsp").forward(request,response);
        }
        if(param.equals("cancel")){
            String id = request.getParameter("id");
            Visit visit = DaoFactory.getDaoFactory().getVisitDao().read(Integer.valueOf(id));
            if(visit.getGroomer().getUsername().equals(username) && visitService.cancelVisit(id)){
                List<Visit> currentVisitList = visitService.getCurrentEmployeeVisits(username);
                request.setAttribute("currentVisitList",currentVisitList);
                request.setAttribute("msg_ok","Wizyta została anulowana");
                request.getRequestDispatcher("employee.jsp").forward(request,response);

            }
            else{
                request.setAttribute("msg_nok","Błąd!!! Wizyta nie została anulowana");
                request.getRequestDispatcher("employee.jsp").forward(request,response);
            }
        }
        if(param.equals("customers")){
            UserDao userDao = DaoFactory.getDaoFactory().getUserDao();
            List<User> list = userDao.selectByRole("customer").stream().filter(user ->!user.getUsername().equals("default_value") ).collect(Collectors.toList());
            request.setAttribute("customersList",list);
            request.getRequestDispatcher("employee.jsp").forward(request,response);
        }
        if(param.equals("dogs")){
            List<Dog> list = (List<Dog>) request.getSession(false).getAttribute("doglist");
            request.setAttribute("allDogList",list);
            request.getRequestDispatcher("employee.jsp").forward(request,response);

        }
        if(param.equals("gnote")){
            int id = Integer.parseInt(request.getParameter("id"));
            Dog dog = DaoFactory.getDaoFactory().getDogDao().selectByParam("id_dog",id).get(0);
            request.setAttribute("dog",dog);
            request.getRequestDispatcher("edit-note.jsp").forward(request,response);
        }

    }
}
