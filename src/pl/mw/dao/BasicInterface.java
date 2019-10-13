package pl.mw.dao;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

public interface BasicInterface <T , PK extends Serializable> {

    //CRUD
    boolean create(T newObject);
    T read (PK primaryKey);
    boolean update(T updatedObject) throws ParseException;
    boolean delete(PK primaryKey);
    List<T> getAll();


}
