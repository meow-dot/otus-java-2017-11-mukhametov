package war_ioc.account;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.function.Function;

public class AccountDBServiceImpl implements AccountDBService {

    private final SessionFactory sessionFactory;

    public AccountDBServiceImpl() {
        sessionFactory = createSessionFactory();
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public long save(AccountDataSet account) {
        return runInSession(session -> {
            AccountDataSetDAO dao = new AccountDataSetDAO(session);
            return dao.save(account);
        });
    }

    @Override
    public AccountDataSet readAccount(String login) {
        return runInSession(session -> {
            AccountDataSetDAO dao = new AccountDataSetDAO(session);
            return dao.read(login);
        });

    }


    @Override
    public void close() {
        sessionFactory.close();
    }

    private SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration
                .addAnnotatedClass(AccountDataSet.class);
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
