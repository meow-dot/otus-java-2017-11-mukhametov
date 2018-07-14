package cache.db.dao;

import cache.model.PhoneDataSet;
import org.hibernate.Session;

public class PhoneDataSetDAO {

    private final Session session;

    public PhoneDataSetDAO(Session session) {
        this.session = session;
    }

    public PhoneDataSet read(long id) {
        return session.load(PhoneDataSet.class, id);
    }

    public long save(PhoneDataSet phone) {
        session.saveOrUpdate(phone);
        return phone.getId();
    }
}
