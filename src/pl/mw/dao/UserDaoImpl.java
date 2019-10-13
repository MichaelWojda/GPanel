package pl.mw.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import pl.mw.model.User;
import pl.mw.utils.ConnectionProvider;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {


    private NamedParameterJdbcTemplate template;
    private static final String READ_BY_NAME = "select * from users where username = :username";
    private static final String READ_BY_ID = "select * from users where id_user = :id_user";
    private static final String DELETE = "DELETE from users where id_user = :id_user";
    private static final String CREATE = "insert into users (username,firstname,lastname,phonenumber,email,password) values (:username,:firstname,:lastname,:phonenumber,:email,:password)";
    private static final String UPDATE = "update users set username=:username,firstname=:firstname,lastname=:lastname,phonenumber=:phonenumber,email=:email,password=:password where id_user =:id_user";
    private static final String READ_BY_ROLE = "select id_user, username,firstname,lastname,phonenumber,email,password, rolename from users join user_roles using (username) where rolename = :role";

    public UserDaoImpl() {
        template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
    }

    @Override
    public boolean create(User newObject) {
        SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(newObject);

        int update = template.update(CREATE, parameterSource);
        if (update > 0) {
            return true;
        }
        return false;

    }

    @Override
    public User read(Integer primaryKey) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id_user", primaryKey);
        List<User> user = template.query(READ_BY_ID, sqlParameterSource, new UserRowMapper());
        if (user.isEmpty()) {
            return null;
        }
        return user.get(0);
    }

    @Override
    public boolean update(User updatedObject) {
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(updatedObject);
        int update = template.update(UPDATE, parameterSource);
        if (update > 0) {
            return true;
        }
        return false;


    }


    @Override
    public boolean delete(Integer primaryKey) {
        SqlParameterSource sqlParameterSource = new MapSqlParameterSource("id_user", primaryKey);
        int update = template.update(DELETE, sqlParameterSource);
        if (update > 0) {
            return true;
        }
        return false;

    }

    @Override
    public List<User> getAll() {
        return template.query("select * from users", new UserRowMapper());
    }

    @Override
    public User getUserByName(String name) {
        SqlParameterSource parameter = new MapSqlParameterSource("username", name);
        List<User> user = template.query(READ_BY_NAME, parameter, new UserRowMapper());
        if (user.isEmpty()) {
            return null;
        }
        return user.get(0);


    }


    @Override
    public String checkUserRole(String uname) {
        String CHECK = "SELECT rolename from user_roles where username = :username";
        SqlParameterSource parameterSource = new MapSqlParameterSource("username", uname);
        List<String> role = template.query(CHECK, parameterSource, (resultSet, i) -> resultSet.getString("rolename"));
        if (!role.isEmpty()) {
            return role.get(0);

        }
        return "none";


    }


    @Override
    public List<User> selectByParam(String param, String value) {
        String BYPARAM = "select * from users where " + param + " = :value";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("value", value);
        List<User> result = template.query(BYPARAM, parameterSource, new UserRowMapper());
        if (result.isEmpty()) {
            return null;
        }
        return result;

    }

    @Override
    public List<User> selectByRole(String role) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("role", role);
        return template.query(READ_BY_ROLE, parameterSource, new UserRowMapper());
    }

    @Override
    public boolean setRole(String username, String role) {
        String SET_ROLE = "insert into user_roles (username , rolename) values (:username, :role)";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        parameterSource.addValue("role", role);
        int update = template.update(SET_ROLE, parameterSource);
        if (update > 0) {
            return true;
        } else {
            return false;
        }


    }

    @Override
    public boolean deleteRole(String username) {
        String DELETE_ROLE = "delete from user_roles where username=:username";
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("username", username);
        int update = template.update(DELETE_ROLE, parameterSource);
        if (update > 0) {
            return true;
        } else {
            return false;
        }

    }

    private class UserRowMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId_user(resultSet.getInt("id_user"));
            user.setUsername(resultSet.getString("username"));
            user.setFirstname(resultSet.getString("firstname"));
            user.setLastname(resultSet.getString("lastname"));
            user.setPhonenumber(resultSet.getInt("phonenumber"));
            user.setEmail(resultSet.getString("email"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
    }
}
