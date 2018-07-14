package war_ioc.db;

import war_ioc.model.AddressDataSet;
import war_ioc.model.PhoneDataSet;
import war_ioc.model.UserDataSet;

public interface DBService extends AutoCloseable{
    UserDataSet readUser(long id);
    long save(UserDataSet user);
    AddressDataSet readAddress(long id);
    long save(AddressDataSet address);
    PhoneDataSet readPhone(long id);
    long save(PhoneDataSet phone);
    void close();

    long cacheSize();
    int cacheHits();
    int cacheMisses();
}
