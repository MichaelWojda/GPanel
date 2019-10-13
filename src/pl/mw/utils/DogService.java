package pl.mw.utils;

import pl.mw.dao.DaoFactory;
import pl.mw.model.Dog;
import pl.mw.model.User;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

public class DogService {

    public List<Dog> getDogByUsername(String username) {
        return DaoFactory.getDaoFactory().getDogDao().selectByParam("username", username);

    }

    public List<Dog> getDogById(int id) {
        return DaoFactory.getDaoFactory().getDogDao().selectByParam("id_dog", id);

    }

    public boolean addDogToDB(HttpServletRequest request) {
        Dog dog = new Dog();
        dog.setDogname(request.getParameter("dogname"));
        dog.setRace(request.getParameter("race"));
        dog.setHealth_notes(request.getParameter("healthnotes"));
        dog.setAge(Integer.parseInt(request.getParameter("age")));
        dog.setGroomer_notes("");
        String username = (String) request.getSession(false).getAttribute("username");
        User user = DaoFactory.getDaoFactory().getUserDao().getUserByName(username);
        dog.setDogowner(user);
        return DaoFactory.getDaoFactory().getDogDao().create(dog);


    }

    public boolean deleteDog(int id) {

        return DaoFactory.getDaoFactory().getDogDao().delete(id);

    }

    public boolean updateDog(HttpServletRequest request) throws ParseException {
        int id = Integer.parseInt(request.getParameter("id"));
        Dog dog = new Dog();
        dog.setDogname(request.getParameter("dogname"));
        dog.setRace(request.getParameter("race"));
        dog.setHealth_notes(request.getParameter("healthnotes"));
        dog.setAge(Integer.parseInt(request.getParameter("age")));
        if (request.getParameter("groomer_notes") != null) {
            dog.setGroomer_notes(request.getParameter("groomer_notes"));
        }
        String username = (String) request.getSession(false).getAttribute("username");
        User user = DaoFactory.getDaoFactory().getUserDao().getUserByName(username);
        dog.setDogowner(user);
        dog.setId_dog(id);
        return DaoFactory.getDaoFactory().getDogDao().update(dog);


    }
}
