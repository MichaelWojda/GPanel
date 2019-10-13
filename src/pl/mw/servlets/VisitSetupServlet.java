package pl.mw.servlets;

import pl.mw.dao.DaoFactory;
import pl.mw.model.Visit;
import pl.mw.utils.VisitService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "VisitSetupServlet", value = "/VisitSetupServlet")
public class VisitSetupServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String groomer = request.getParameter("groomer");
        String fromDate = request.getParameter("fromDate");
        String toDate = request.getParameter("toDate");
        int numberOfDogs = Integer.parseInt(request.getParameter("dogNumber"));
        String startHour = request.getParameter("startHour");
        int interval = Integer.parseInt(request.getParameter("interval"));
        try {
            boolean check = VisitService.setUpVisits(groomer, fromDate, toDate, numberOfDogs, startHour, interval);
            if (check) {
                request.setAttribute("error", "Terminy utworzone pomyślnie");
            } else {
                request.setAttribute("error", "Wystąpił błąd, sprawdź czy terminy nie pokrywają się");
            }
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fdate = request.getParameter("fDate");
        String tdate = request.getParameter("tDate");
        List<Visit> visitList = DaoFactory.getDaoFactory().getVisitDao().getByRange(LocalDate.parse(fdate),LocalDate.parse(tdate));
        if (!visitList.isEmpty()) {
            request.setAttribute("visitList", visitList);
            request.getSession(false).setAttribute("fDate",fdate);
            request.getSession(false).setAttribute("tDate",tdate);
        } else {
            request.setAttribute("error", "W podanym terminie nie ma żadnych wizyt");
        }
        request.getRequestDispatcher("admin.jsp").forward(request, response);

    }
}
