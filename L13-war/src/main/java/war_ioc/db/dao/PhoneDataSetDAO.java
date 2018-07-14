package war_ioc.db.dao;

import org.hibernate.Session;
import war_ioc.model.PhoneDataSet;

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
