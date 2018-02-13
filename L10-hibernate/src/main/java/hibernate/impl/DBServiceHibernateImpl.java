package hibernate.impl;

import hibernate.impl.dao.UserDataSetDAO;
import hibernate.models.UserDataSet;
import hibernate.db.DBService;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.function.Function;

public class DBServiceHibernateImpl implements DBService {

    private SessionFactory sessionFactory;

    public DBServiceHibernateImpl() {
        sessionFactory = createSessionFactory(ConfigurationHelper.getConfiguration());
    }

    @Override
    public void save(UserDataSet user) {
        try (Session session = sessionFactory.openSession()){
            UserDataSetDAO dao = new UserDataSetDAO(session);
            dao.save(user);
        }
    }

    @Override
    public UserDataSet read(long id) {
        return runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.read(id);
        });
    }

    @Override
    public void close() {
        sessionFactory.close();
        System.out.println("Session is closed.");
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }
}
