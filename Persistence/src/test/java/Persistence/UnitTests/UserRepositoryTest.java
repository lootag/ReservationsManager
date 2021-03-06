package Persistence.UnitTests;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;
import static org.junit.Assert.*;
import Persistence.UserRepository.UserRepository;
import Persistence.Entities.User;
import java.util.Arrays;
import java.util.Random;


public class UserRepositoryTest {
    @Test public void ShouldEntityBeSavedCorrectly() {
        //Arrange
        Random random = new Random();
        byte[] Hash = new byte[20];
        byte[] Salt = new byte[20];
        random.nextBytes(Hash);
        random.nextBytes(Salt);
        User toSave = new User( -1,"email", Hash, Salt);

        //Act
        UserRepository sut = new UserRepository();
        User result = sut.SaveUser(toSave);

        SessionFactory factory = this.CreateSessionFactory();
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        long id = result.getId();
        Persistence.HibernateEntities.User retrievedUser = (Persistence.HibernateEntities.User) session.get(Persistence.HibernateEntities.User.class, id);
        session.getTransaction().commit();
        factory.close();
        //Assert
        assertNotNull(retrievedUser);
        assertTrue(retrievedUser.getId() > 0);
        assertTrue(toSave.getEmail().equals(retrievedUser.getEmail()));
        assertTrue(Arrays.equals(toSave.getHash(),retrievedUser.getHash()));
        assertTrue(Arrays.equals(toSave.getSalt(),retrievedUser.getSalt()));

    }

    private SessionFactory CreateSessionFactory(){
        Configuration configuration = new Configuration();
        configuration.configure();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties())
                .build();
        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        return factory;
    }
}
