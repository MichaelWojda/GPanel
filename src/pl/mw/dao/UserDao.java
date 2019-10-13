package pl.mw.dao;

import pl.mw.model.User;

import java.util.List;

public interface UserDao extends BasicInterface<User,Integer>{

     List<User> getAll();
     User getUserByName(String name);
     String checkUserRole(String name);
     List<User> selectByParam (String param,String value);
     List<User> selectByRole (String role);
     boolean setRole(String username,String role);
     boolean deleteRole(String username);
}
