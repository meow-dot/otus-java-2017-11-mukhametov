package cache.db.dao;

import cache.model.UserDataSet;
import org.hibernate.Session;

public class UserDataSetDAO {

    private final Session session;

    public UserDataSetDAO(Session session) {
        this.session = session;
    }

    public UserDataSet read(long id) {
        return session.load(UserDataSet.class, id);
    }

    public long save(UserDataSet user) {
        session.saveOrUpdate(user);
        return user.getId();
    }
}
