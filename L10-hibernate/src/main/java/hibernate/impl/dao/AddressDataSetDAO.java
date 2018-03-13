package hibernate.impl.dao;

import hibernate.models.AddressDataSet;
import org.hibernate.Session;


public class AddressDataSetDAO {

    private final Session session;

    public AddressDataSetDAO(Session session) {
        this.session = session;
    }

    public AddressDataSet read(long id) {
        return session.load(AddressDataSet.class, id);
    }
    public void save(AddressDataSet phone) {
        session.save(phone);
    }
}
