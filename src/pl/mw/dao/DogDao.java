package pl.mw.dao;

import pl.mw.model.Dog;

import java.util.List;

public interface DogDao extends BasicInterface<Dog,Integer> {

    List<Dog> getAll();
    List<Dog> selectByParam (String param,String value);
    List<Dog> selectByParam (String param,int value);
}
