package war_ioc.db.dao;

import org.hibernate.Session;
import war_ioc.model.UserDataSet;

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
