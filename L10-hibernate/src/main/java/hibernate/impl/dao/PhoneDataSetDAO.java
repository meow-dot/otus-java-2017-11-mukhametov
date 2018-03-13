package hibernate.impl.dao;

import hibernate.models.PhoneDataSet;
import org.hibernate.Session;


public class PhoneDataSetDAO {

    private final Session session;

    public PhoneDataSetDAO(Session session) {
        this.session = session;
    }

    public PhoneDataSet read(long id) {
        return session.load(PhoneDataSet.class, id);
    }
    public void save(PhoneDataSet phone) {
        session.save(phone);
    }
}
