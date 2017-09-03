package dbService;

import dbService.dao.impls.PetsDAO;
import dbService.dao.impls.UsersDAO;
import dbService.datasets.Pet;
import dbService.datasets.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "update";

    private static SessionFactory sessionFactory = null;

    public DBService(){
        //Configuration configuration = getMySQLConnection();
        //sessionFactory = createSessionFactory(configuration);
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(ssrb.build());
    }

    public User getUser(String email) throws DBException{
        try{
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            User users = dao.get(email);
            session.close();
            return users;
        }catch (HibernateException e){e.printStackTrace();}
        return null;
    }

    public long addUser(String name, String email, String pass, String country, String location) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.insert(name, email, pass, country, location);
            transaction.commit();
            session.close();
            return id;
        }catch (HibernateException e){e.printStackTrace();}
        return -1;
    }

    public List<Pet> getUserPets(User user) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PetsDAO dao = new PetsDAO(session);
            List<Pet> pets = dao.getUserPets(user);
            transaction.commit();
            session.close();
            return pets;
        }catch (HibernateException e){e.printStackTrace();}
        return null;
    }

    public List<Pet> getAllPets() throws DBException{
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PetsDAO dao = new PetsDAO(session);
            List<Pet> pets = dao.getAllPets();
            transaction.commit();
            session.close();
            return pets;
        }catch (HibernateException e){e.printStackTrace();}
        return null;
    }

    public Pet getPet(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PetsDAO dao = new PetsDAO(session);
            Pet pet = dao.get(id);
            transaction.commit();
            session.close();
            return pet;
        }catch (HibernateException e){e.printStackTrace();}
        return null;
    }

    public long addPet(User user, String name, String animalClass, int age) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PetsDAO dao = new PetsDAO(session);
            Date curDate = new Date();
            SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
            String date = format.format(curDate);
            long id = dao.insert(user, name, animalClass, age, date);
            transaction.commit();
            session.close();
            return id;
        }catch (HibernateException e){e.printStackTrace();}
        return -1;
    }

    public long getPetId(Pet pet) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            PetsDAO dao = new PetsDAO(session);
            long id = dao.getId(pet);
            transaction.commit();
            session.close();
            return id;
        }catch (HibernateException e){e.printStackTrace();}
        return -1;
    }

    /*@SuppressWarnings("UnusedDeclaration")
    public static Configuration getMySQLConnection(){
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Pet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db_example");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    private static SessionFactory createSessionFactory(Configuration configuration){
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }*/

    public void printConnectInfo() {
        try {
            SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) sessionFactory;
            Connection connection = sessionFactoryImpl.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeSessionFactory(){
        sessionFactory.close();
    }

}
