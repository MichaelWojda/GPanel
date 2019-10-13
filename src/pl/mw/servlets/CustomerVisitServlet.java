package pl.mw.servlets;

import pl.mw.model.Visit;
import pl.mw.utils.UserService;
import pl.mw.utils.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerVisitServlet", value="/CustomerVisitServlet")
public class CustomerVisitServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        String username = String.valueOf(request.getSession(false).getAttribute("username"));
        VisitService visitService = new VisitService();
        if(param.equals("bookVisit")){
            String from = request.getParameter("fromDate");
            String to = request.getParameter("toDate");
            int id_dog= Integer.parseInt(request.getParameter("dog"));
            List<Visit> list = visitService.getUsersVisitsByRange("default_value",from,to);
            if(!list.isEmpty()) {
                request.setAttribute("availableVisits", list);
                request.setAttribute("id_dog", id_dog);
                request.getRequestDispatcher("book-visit.jsp").forward(request, response);
            }
            else{
                request.setAttribute("msg_nok","Brak dostępnych wizyt w tym terminie");
                request.getRequestDispatcher("customer.jsp").forward(request,response);
            }

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String param = request.getParameter("param");
        String id = request.getParameter("id");
        String username = String.valueOf(request.getSession(false).getAttribute("username"));
        VisitService visitService = new VisitService();

        if(param.equals("cancel")){
           if(visitService.cancelVisit(id)){
               request.getSession(false).setAttribute("currentVisits",visitService.getAllCurrentVisits(username));
               request.setAttribute("msg_ok","Wizyta anulowana");
               request.getRequestDispatcher("customer.jsp").forward(request,response);

           }
           else{
               request.setAttribute("msg_nok","Wizyta nie została anulowana");
               request.getRequestDispatcher("customer.jsp").forward(request,response);

           }

        }
        if(param.equals("book")){
            int id_visit= Integer.parseInt(request.getParameter("id"));
            int id_dog = Integer.parseInt(request.getParameter("id_dog"));
            if(VisitService.bookVisit(username,id_visit,id_dog)){
                request.getSession(false).setAttribute("currentVisits",visitService.getAllCurrentVisits(username));
                request.setAttribute("msg_ok","Wizyta została zabookowana");
                request.getRequestDispatcher("customer.jsp").forward(request,response);


            }
            else{
                request.setAttribute("msg_nok","Wizyta nie została zabookowana");
                request.getRequestDispatcher("customer.jsp").forward(request,response);

            }

        }
        if(param.equals("visitHistory")){

            List<Visit> historyVisits = visitService.getHistory(username);
            if(historyVisits!=null) {
                request.setAttribute("historyVisits", historyVisits);
                request.getRequestDispatcher("visit-history.jsp").forward(request,response);

            }
            else{
                request.setAttribute("msg_nok","Brak wizyt historycznych");
                request.getRequestDispatcher("customer.jsp").forward(request,response);
            }
        }

    }
}
