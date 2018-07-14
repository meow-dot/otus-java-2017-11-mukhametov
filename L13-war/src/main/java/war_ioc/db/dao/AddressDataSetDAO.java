package war_ioc.db.dao;

import org.hibernate.Session;
import war_ioc.model.AddressDataSet;

public class AddressDataSetDAO {

    private final Session session;

    public AddressDataSetDAO(Session session) {
        this.session = session;
    }

    public AddressDataSet read(long id) {
        return session.load(AddressDataSet.class, id);
    }

    public long save(AddressDataSet address) {
        session.saveOrUpdate(address);
        return address.getId();
    }
}
