package db;

import db.cache.*;
import db.dao.*;
import model.*;
import org.h2.tools.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.SQLException;
import java.util.function.Function;

public class DBServiceCacheImpl implements DBService{

    private static int MAX_CACHED_ELEMENTS = 100;
    private static long MAX_IDLE_TIME_MS = 60 * 1000;

    private Server server;
    private final SessionFactory sessionFactory;
    private final CacheEngine<Long, UserDataSet> cacheEngine;

    public DBServiceCacheImpl() {
        try {
            server = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sessionFactory = createSessionFactory();
        cacheEngine = new CacheEngineImpl<>(MAX_CACHED_ELEMENTS, MAX_IDLE_TIME_MS);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public long save(UserDataSet user) {
        long id = runInSession(session -> {
            UserDataSetDAO dao = new UserDataSetDAO(session);
            return dao.save(user);
        });
        cacheEngine.put(new Element<>(id, user));
        return id;
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public long save(AddressDataSet address) {
        cacheEngine.remove(address.getUser().getId());
        return runInSession(session -> {
            AddressDataSetDAO dao = new AddressDataSetDAO(session);
            return dao.save(address);
        });
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public long save(PhoneDataSet phone) {
        cacheEngine.remove(phone.getUser().getId());
        return runInSession(session -> {
            PhoneDataSetDAO dao = new PhoneDataSetDAO(session);
            return dao.save(phone);
        });
    }

    @Override
    public UserDataSet readUser(long id) {
        UserDataSet user;
        if (cacheEngine.get(id) != null) {
            user = cacheEngine.get(id).getValue();
        } else {
            user = runInSession(session -> {
                UserDataSetDAO dao = new UserDataSetDAO(session);
                return dao.read(id);
            });
            cacheEngine.put(new Element<>(id, user));
        }
        return user;
    }

    @Override
    public AddressDataSet readAddress(long id) {
        return runInSession(session -> {
            AddressDataSetDAO dao = new AddressDataSetDAO(session);
            return dao.read(id);
        });
    }

    @Override
    public PhoneDataSet readPhone(long id) {
        return runInSession(session -> {
            PhoneDataSetDAO dao = new PhoneDataSetDAO(session);
            return dao.read(id);
        });
    }

    @Override
    public void close() {
        cacheEngine.dispose();
        sessionFactory.close();
        server.stop();
    }

    @Override
    public long cacheSize() {
        return cacheEngine.size();
    }

    @Override
    public int cacheHits() {
        return cacheEngine.getHitCount();
    }

    @Override
    public int cacheMisses() {
        return cacheEngine.getMissCount();
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration
                .addAnnotatedClass(UserDataSet.class)
                .addAnnotatedClass(AddressDataSet.class)
                .addAnnotatedClass(PhoneDataSet.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    private <R> R runInSession(Function<Session, R> function) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e){
            if (transaction != null) transaction.rollback();
        }
        return null;
    }
}
