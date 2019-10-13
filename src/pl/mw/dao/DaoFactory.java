package pl.mw.dao;

public abstract class DaoFactory {

    private static final int SQL = 1;
    private static final int NOSQL = 2;
    public abstract UserDao getUserDao();
    public abstract DogDao getDogDao();
    public abstract VisitDao getVisitDao();

    public static DaoFactory getDaoFactory(int type) {
        DaoFactory daoFactory = null;
        if(type==SQL){
            daoFactory = new SqlDaoFactory();
        }
        //space for NONSQL Database Implementation
        return daoFactory;

    }
    public static DaoFactory getDaoFactory(){
        DaoFactory daoFactory = getDaoFactory(SQL);
        return daoFactory;


    }



}
