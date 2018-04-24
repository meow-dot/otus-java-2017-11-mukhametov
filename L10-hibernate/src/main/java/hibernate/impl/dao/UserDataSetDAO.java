package hibernate.impl.dao;

import hibernate.models.UserDataSet;
import org.hibernate.Session;


public class UserDataSetDAO {

    private final Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }
    public void save(UserDataSet user) {
        if (user.getId() < 0) {
            session.save(user);
        } else {
            session.update(user);
        }
    }
}
