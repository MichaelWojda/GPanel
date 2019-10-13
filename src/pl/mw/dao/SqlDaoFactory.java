package pl.mw.dao;

public class SqlDaoFactory extends DaoFactory {


    @Override
    public UserDao getUserDao() {
        return new UserDaoImpl();
    }

    @Override
    public DogDao getDogDao() {
        return new DogDaoImpl();
    }

    @Override
    public VisitDao getVisitDao() {
        return new VisitDaoImpl();
    }
}
