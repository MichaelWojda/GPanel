package pl.mw.dao;

import pl.mw.model.Visit;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public interface VisitDao extends BasicInterface<Visit, Integer> {
    List<Visit> getAll();
    boolean createEmptyVisit(String groomer, LocalDate date, String startHour) throws ParseException;
    List<Visit> getByParam(String param,String value);
    List<Visit> getByRange(LocalDate from,LocalDate to);
    boolean cancelVisit(String id);
    void cleanUpHistory(LocalDate now);
}
