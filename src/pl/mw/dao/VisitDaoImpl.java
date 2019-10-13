package pl.mw.dao;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl.mw.model.Dog;
import pl.mw.model.User;
import pl.mw.model.Visit;
import pl.mw.utils.ConnectionProvider;
import pl.mw.utils.VisitService;

import java.security.spec.NamedParameterSpec;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class VisitDaoImpl implements VisitDao {

    private NamedParameterJdbcTemplate template;

    public VisitDaoImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    private static final String CREATE_EMPTY_VISIT = "insert into visits (groomer,visit_date,visit_time,booked) values (:groomer,:visit_date,:visit_time,false)";
    private static final String READALL = "select * from visits";
    private static final String DELETE = "delete from visits where id_visit=:id";
    private static final String READBYID = "select * from visits where id_visit=:id";
    private static final String UPDATE = "update visits set customer=:customer, id_dog=:id_dog,groomer=:groomer,visit_date=:visit_date,visit_time=:visit_time,booked=:booked where id_visit=:id_visit";
    private static final String READBYDATERANGE = "select * from visits where visit_date >= :fromdate and visit_date <= :todate";

    @Override
    public boolean create(Visit newObject) {
        return false;
    }

    @Override
    public Visit read(Integer primaryKey) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", primaryKey);
        List<Visit> list = template.query(READBYID, parameterSource, new VisitRowMapper());
        return list.get(0);
    }

    @Override
    public boolean update(Visit updatedObject) throws ParseException {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("customer", updatedObject.getCustomer().getUsername());
        parameterSource.addValue("id_dog", updatedObject.getDog().getId_dog());
        parameterSource.addValue("groomer", updatedObject.getGroomer().getUsername());
        parameterSource.addValue("visit_date", updatedObject.getVisit_date());
        parameterSource.addValue("visit_time", this.convertStringToTime(updatedObject.getVisit_time()));
        parameterSource.addValue("booked", updatedObject.isBooked());
        parameterSource.addValue("id_visit", updatedObject.getId_visit());
        int update  = template.update(UPDATE,parameterSource);
        if(update > 0){
            return true;
        }
        return false;



    }

    @Override
    public boolean delete(Integer primaryKey) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id", primaryKey);
        int update = template.update(DELETE, parameterSource);
        if (update > 0) {
            return true;
        }
        return false;

    }

    @Override
    public List<Visit> getAll() {
        List<Visit> list = template.query(READALL, new VisitRowMapper());
        sortVisits(list);
        return list;

    }

    @Override
    public boolean createEmptyVisit(String groomer, LocalDate date, String startHour) throws ParseException {
        Time time = convertStringToTime(startHour);
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("visit_date", date);
        parameterSource.addValue("visit_time", time);
        parameterSource.addValue("groomer", groomer);
        List<Visit> result = template.query("select * from visits where groomer = :groomer and visit_date = :visit_date and visit_time = :visit_time ", parameterSource, new VisitRowMapper());
        if (result.isEmpty()) {
            int update = template.update(CREATE_EMPTY_VISIT, parameterSource);
            if (update > 0) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public List<Visit> getByParam(String param, String value) {
        String READBYPARAM = "select * from visits where "+ param +" =:value";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("param",param);
        parameterSource.addValue("value",value);
        List<Visit> list = template.query(READBYPARAM, parameterSource, new VisitRowMapper());
        sortVisits(list);
        return list;

    }

    @Override
    public List<Visit> getByRange(LocalDate from, LocalDate to) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("fromdate",from);
        parameterSource.addValue("todate", to);
        List <Visit> list = template.query(READBYDATERANGE,parameterSource, new VisitRowMapper());
        sortVisits(list);

        return list;

    }

    private void sortVisits(List<Visit> list) {
        Collections.sort(list, new Comparator<Visit>() {
            @Override
            public int compare(Visit o1, Visit o2) {
                if(o1.getVisit_date().isBefore(o2.getVisit_date())){
                    return -1;
                }
                if(o1.getVisit_date().isAfter(o2.getVisit_date())){
                    return 1;
                }
                    return 0;
                }

        });
    }

    @Override
    public boolean cancelVisit(String id) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("id_visit",Integer.valueOf(id));
        int update = template.update("update visits set customer='default_value', id_dog=9999,booked=false where id_visit=:id_visit", parameterSource);
        if(update>0){
            return true;
        }
        return false;
    }

    @Override
    public void cleanUpHistory(LocalDate now) {
        String CLEAR ="delete from visits where visit_date < :now and booked=false";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("now",now);
        template.update(CLEAR,parameterSource);

    }

    private Time convertStringToTime(String startHour) throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        Date timedate = format.parse(startHour);
        return new Time(timedate.getTime());
    }


    private class VisitRowMapper implements RowMapper<Visit> {
        @Override
        public Visit mapRow(ResultSet resultSet, int i) throws SQLException {
            Visit visit = new Visit();
            visit.setId_visit(resultSet.getInt("id_visit"));
            User customer = DaoFactory.getDaoFactory().getUserDao().getUserByName(resultSet.getString("customer"));
            visit.setCustomer(customer);
            User groomer = DaoFactory.getDaoFactory().getUserDao().getUserByName(resultSet.getString("groomer"));
            visit.setGroomer(groomer);
            Dog dog = DaoFactory.getDaoFactory().getDogDao().selectByParam("id_dog", resultSet.getInt("id_dog")).get(0);
            visit.setDog(dog);
            visit.setVisit_date(resultSet.getDate("visit_date").toLocalDate());
            visit.setVisit_time(String.valueOf(resultSet.getTime("visit_time")).substring(0,5));
            visit.setBooked(resultSet.getBoolean("booked"));
            return visit;
        }
    }


}
