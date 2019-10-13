package pl.mw.utils;

import pl.mw.dao.DaoFactory;
import pl.mw.dao.VisitDao;
import pl.mw.dao.VisitDaoImpl;
import pl.mw.model.User;
import pl.mw.model.Visit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class VisitService {


    public static boolean setUpVisits(String groomer, String fromDate, String toDate,int numberOfDogs, String startHour, int interval) throws ParseException {
        LocalDate fromLocalDate = LocalDate.parse(fromDate);
        if(fromLocalDate.isBefore(LocalDate.now())){
            return false;
        }
        String baseStartHour = startHour;
        LocalDate toLocalDate = LocalDate.parse(toDate);
        Period period = Period.between(fromLocalDate,toLocalDate);
        int diff = period.getDays();
        VisitDao visitDao = DaoFactory.getDaoFactory().getVisitDao();
        for(int j=0;j<=diff;j++) {


            for (int i = 0; i < numberOfDogs; i++) {
                if(visitDao.createEmptyVisit(groomer,fromLocalDate, startHour)==false){
                    return false;
                }
                startHour = addHours(startHour, interval);

            }
            fromLocalDate=fromLocalDate.plusDays(1);
            startHour =baseStartHour;
        }
        return true;

    }

    private static String addHours(String startHour, int interval) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("kk:mm");
        Date date = simpleDateFormat.parse(startHour);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR,interval);
        return simpleDateFormat.format(calendar.getTime());
    }


    public static List<Visit> getListByDate(String date) {
        List<Visit> oldList = DaoFactory.getDaoFactory().getVisitDao().getAll();
        return oldList.stream().filter(visit -> visit.getVisit_date().toString().equals(date)).collect(Collectors.toList());

    }

    public static boolean deleteById(String id) {
        VisitDao visitDao = DaoFactory.getDaoFactory().getVisitDao();
        if(visitDao.delete(Integer.valueOf(id))){
            return true;
        }
        return false;
    }

    public static List<Visit> getAll() {
        return DaoFactory.getDaoFactory().getVisitDao().getAll();
    }

    public static Visit getById(String id) {
        VisitDao visitDao = DaoFactory.getDaoFactory().getVisitDao();
        return visitDao.read(Integer.valueOf(id));

    }

    public static boolean updateVisit(int id, String groomer, String newDate, String hour) throws ParseException {
        Visit updatedVisit = VisitService.getById(String.valueOf(id));
        updatedVisit.setGroomer(DaoFactory.getDaoFactory().getUserDao().getUserByName(groomer));
        updatedVisit.setVisit_date(LocalDate.parse(newDate));
        updatedVisit.setVisit_time(hour);
        return DaoFactory.getDaoFactory().getVisitDao().update(updatedVisit);

    }

    public static boolean bookVisit(String username, int id_visit, int id_dog) {
        VisitDao visitDao = DaoFactory.getDaoFactory().getVisitDao();
        Visit visit = visitDao.read(id_visit);
        visit.setCustomer(DaoFactory.getDaoFactory().getUserDao().getUserByName(username));
        visit.setDog(DaoFactory.getDaoFactory().getDogDao().selectByParam("id_dog",id_dog).get(0));
        visit.setBooked(true);
        try {
            return visitDao.update(visit);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }

    }

    public List<Visit> getHistory(String username) {
        List<Visit> list = getAllVisitsByCustomer(username).stream().filter(visit -> visit.getVisit_date().isBefore(LocalDate.now())).collect(Collectors.toList());
        return list;
    }

    public List <Visit> getAllVisitsByCustomer(String username){
        List<Visit> list = DaoFactory.getDaoFactory().getVisitDao().getByParam("customer", username);
        return list;
    }
    public List<Visit>getAllCurrentVisits(String username){
        List<Visit> list = getAllVisitsByCustomer(username).stream().filter(visit -> visit.getVisit_date().isAfter(LocalDate.now().minusDays(1))).collect(Collectors.toList());
        return list;
    }

    public boolean cancelVisit(String id) {
        return DaoFactory.getDaoFactory().getVisitDao().cancelVisit(id);
    }

    public List<Visit> getUsersVisitsByRange(String username, String from, String to) {
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        List<Visit> allByDate = DaoFactory.getDaoFactory().getVisitDao().getByRange(fromDate, toDate);
        return allByDate.stream().filter(visit -> visit.getCustomer().getUsername().equals(username)).collect(Collectors.toList());

    }

    public List<Visit> getCurrentEmployeeVisits(String username) {
        LocalDate now = LocalDate.now();
        List<Visit> list = DaoFactory.getDaoFactory().getVisitDao().getByParam("groomer", username);
        List<Visit> newList = list.stream().filter(visit -> visit.getVisit_date().isAfter(now.minusDays(1))).collect(Collectors.toList());
        return newList;


    }

    public List<Visit> getEmployeeHistoryVisits(String username) {
        LocalDate now = LocalDate.now();
        List<Visit> list = DaoFactory.getDaoFactory().getVisitDao().getByParam("groomer", username);
        List<Visit> newList = list.stream().filter(visit -> visit.getVisit_date().isBefore(now)).collect(Collectors.toList());
        return newList;
    }

    public void clearVisits() {
        LocalDate now = LocalDate.now();
        DaoFactory.getDaoFactory().getVisitDao().cleanUpHistory(now);
    }


}
