package Persistence.UserRepository;

import Persistence.Entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class UserRepository implements IUserRepository{

    @Override
    public User SaveUser(User user) {

        Persistence.HibernateEntities.User hUser = new Persistence.HibernateEntities.User(user.getEmail(), user.getHash(), user.getSalt());
        SessionFactory factory = this.CreateSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        try{
            session.save(hUser);
            session.getTransaction().commit();
            User toReturn = new User(hUser.getId(), hUser.getEmail(), hUser.getHash(), hUser.getSalt());
            System.out.println("The Id of the user you've just created is " + hUser.getId());
            return toReturn;
        }
        catch (Exception ex){
            ex.printStackTrace();
            session.getTransaction().rollback();
            throw ex;
        }
        finally {
            factory.close();
        }

    }

    private SessionFactory CreateSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        return  factory;
    }
}