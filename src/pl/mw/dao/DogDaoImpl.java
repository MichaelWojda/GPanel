package pl.mw.dao;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import pl.mw.model.Dog;
import pl.mw.model.User;
import pl.mw.utils.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DogDaoImpl implements DogDao {

    private NamedParameterJdbcTemplate template;

    public DogDaoImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());

    }

    @Override
    public boolean create(Dog dog) {
        String ADD = "insert into dogs (dogname,race,age,health_notes,groomer_notes,dogowner) values (:dogname,:race,:age,:health_notes,:groomer_notes,:dogowner)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("dogname", dog.getDogname());
        parameterSource.addValue("race", dog.getRace());
        parameterSource.addValue("age", dog.getAge());
        parameterSource.addValue("health_notes", dog.getHealth_notes());
        parameterSource.addValue("groomer_notes", dog.getGroomer_notes());
        parameterSource.addValue("dogowner", dog.getDogowner().getId_user());
        int update = template.update(ADD, parameterSource);
        if (update > 0) {
            return true;
        }

        return false;


    }


    @Override
    public Dog read(Integer primaryKey) {
        return null;
    }

    @Override
    public boolean update(Dog dog) {
        String UPDATE="update dogs set dogname=:dogname,race=:race,age=:age,health_notes=:health_notes,groomer_notes=:groomer_notes,dogowner=:dogowner where id_dog=:id_dog";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("dogname", dog.getDogname());
        parameterSource.addValue("id_dog", dog.getId_dog());
        parameterSource.addValue("race", dog.getRace());
        parameterSource.addValue("age", dog.getAge());
        parameterSource.addValue("health_notes", dog.getHealth_notes());
        parameterSource.addValue("groomer_notes", dog.getGroomer_notes());
        parameterSource.addValue("dogowner", dog.getDogowner().getId_user());
        int update = template.update(UPDATE, parameterSource);
        if (update > 0) {
            return true;
        }

        return false;


    }



    @Override
    public boolean delete(Integer primaryKey) {
        String DELETE = "delete from dogs where id_dog = :id_dog";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource("id_dog", primaryKey);
        int update = template.update(DELETE, parameterSource);
        if (update > 0) {
            return true;
        }
        return false;

    }

    @Override
    public List<Dog> getAll() {
        return template.query("select id_dog,dogname,race,age,health_notes,groomer_notes,username,firstname,lastname,phonenumber,email,password,id_user from dogs join users on dogowner=id_user",new DogRowMapper());
    }

    @Override
    public List<Dog> selectByParam(String param, String value) {
        String BYPARAM = "select id_dog,dogname,race,age,health_notes,groomer_notes,username,firstname,lastname,phonenumber,email,password,id_user from dogs join users on dogowner=id_user and " + param + " = :value";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("value", value);
        List<Dog> result = template.query(BYPARAM, parameterSource, new DogRowMapper());
        if (result.isEmpty()) {
            return null;
        }
        return result;


    }

    public List<Dog> selectByParam(String param, int value) {
        String BYPARAM = "select id_dog,dogname,race,age,health_notes,groomer_notes,username,firstname,lastname,phonenumber,email,password,id_user from dogs join users on dogowner=id_user and " + param + " = :value";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("value", value);
        List<Dog> result = template.query(BYPARAM, parameterSource, new DogRowMapper());
        if (result.isEmpty()) {
            return null;
        }
        return result;


    }


    private class DogRowMapper implements RowMapper<Dog> {
        @Override
        public Dog mapRow(ResultSet resultSet, int i) throws SQLException {
            Dog dog = new Dog();
            dog.setId_dog(resultSet.getInt("id_dog"));
            dog.setDogname(resultSet.getString("dogname"));
            dog.setRace(resultSet.getString("race"));
            dog.setAge(resultSet.getInt("age"));
            dog.setHealth_notes(resultSet.getString("health_notes"));
            dog.setGroomer_notes(resultSet.getString("groomer_notes"));
            User user = DaoFactory.getDaoFactory().getUserDao().getUserByName(resultSet.getString("username"));
            dog.setDogowner(user);
            return dog;

        }
    }
}
