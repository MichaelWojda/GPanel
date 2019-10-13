package pl.mw.utils;

import pl.mw.dao.DaoFactory;
import pl.mw.dao.UserDao;
import pl.mw.model.Dog;
import pl.mw.model.User;
import pl.mw.model.Visit;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;

public class UserService {


    public boolean validateUser(String username, String userpass){
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        User user = daoFactory.getUserDao().getUserByName(username);
        if(user!=null && user.getUsername().equals(username) && user.getPassword().equals(userpass)){
            return true;
        }
        else if(user!=null && user.getUsername().equals(username) && user.getPassword().equals(encryptPass(userpass))){
            return true;
        }

        return false;
    }

    public static String checkRole(String username){
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        return daoFactory.getUserDao().checkUserRole(username);

    }
    public boolean addUserToDB(HttpServletRequest request){
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        if(userExists(request.getParameter("login"))){
            request.setAttribute("error","Błąd użytkownik o podanym loginie już istnieje");
            return false;
        }
        else if (daoFactory.getUserDao().selectByParam("email",request.getParameter("email"))!=null){
            request.setAttribute("error","Błąd podany adres email jest już zarejestrowany");
            return false;
        }
        else {
            User user = new User();
            user.setUsername(request.getParameter("login"));
            user.setPassword(encryptPass(request.getParameter("pass")));
            user.setFirstname(request.getParameter("name"));
            user.setLastname(request.getParameter("surname"));
            user.setEmail(request.getParameter("email"));
            user.setPhonenumber(Integer.parseInt(request.getParameter("phonenumber")));
            return daoFactory.getUserDao().create(user);
        }
    }

    public static boolean userExists(String username){
        DaoFactory daoFactory = DaoFactory.getDaoFactory();
        User result = daoFactory.getUserDao().getUserByName(username);
        if(result!=null){
            return true;
        }
        return false;

    }
    public static boolean setRole(String username , String role){

        return DaoFactory.getDaoFactory().getUserDao().setRole(username,role);
    }
    public static boolean deleteRole(String username){

        return DaoFactory.getDaoFactory().getUserDao().deleteRole(username);
    }

    public List<Dog> getDogList(String username) {
        DogService dogService = new DogService();
        return dogService.getDogByUsername(username);
    }

    public static List<User> getEmployees(){
        return DaoFactory.getDaoFactory().getUserDao().selectByRole("employee");
    }
    public List<User> getCustomers(){
        return DaoFactory.getDaoFactory().getUserDao().selectByRole("customer");
    }
    public List<User> getUnsigned(){
        return DaoFactory.getDaoFactory().getUserDao().selectByRole("basic");
    }
    public static List<User> getAll(){
        return DaoFactory.getDaoFactory().getUserDao().getAll();
    }

    public static boolean deleteByID(int id){
        return DaoFactory.getDaoFactory().getUserDao().delete(id);
    }
    public static User getUserById(int id){
        return DaoFactory.getDaoFactory().getUserDao().read(id);
    }

    public static boolean updateUser(User user) {
        UserDao userDao = DaoFactory.getDaoFactory().getUserDao();
        if(userExists(user.getUsername())&& user.getId_user()!=getUserbyName(user.getUsername()).getId_user()){
            System.out.println("Błąd username");
            return false;


        }
        else if (userDao.selectByParam("email",user.getEmail())!=null && userDao.selectByParam("email",user.getEmail()).get(0).getId_user()!=user.getId_user() ){
            System.out.println("Błąd email");
            return false;
        }
        else{
            try {
                return DaoFactory.getDaoFactory().getUserDao().update(user);
            } catch (ParseException e) {
                System.out.println("Inny błąd");
                e.printStackTrace();
                return false;
            }
        }


    }
    public static User getUserbyName(String username){
        return DaoFactory.getDaoFactory().getUserDao().getUserByName(username);

    }

    public String encryptPass(String string){
        MessageDigest messageDigest = null;
        try {
            messageDigest =MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(string.getBytes());
        String newPass = new BigInteger(1, messageDigest.digest()).toString(16);
        return newPass;

    }



}

