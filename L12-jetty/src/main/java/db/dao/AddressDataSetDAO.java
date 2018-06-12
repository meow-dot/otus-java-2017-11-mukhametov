package db.dao;

import model.AddressDataSet;
import org.hibernate.Session;

public class AddressDataSetDAO {

    private final Session session;

    public AddressDataSetDAO(Session session) {
        this.session = session;
    }

    public AddressDataSet read(long id) {
        return session.load(AddressDataSet.class, id);
    }

    public long save(AddressDataSet address) {
        if (address.getId() == -1) {
            return (long) session.save(address);
        } else {
            session.update(address);
            return address.getId();
        }
    }
}
