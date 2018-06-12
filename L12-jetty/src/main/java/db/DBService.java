package db;

import model.AddressDataSet;
import model.PhoneDataSet;
import model.UserDataSet;

public interface DBService extends AutoCloseable{

    long save(UserDataSet user);
    long save(AddressDataSet address);
    long save(PhoneDataSet phone);

    UserDataSet readUser(long id);
    AddressDataSet readAddress(long id);
    PhoneDataSet readPhone(long id);

    long cacheSize();
    int cacheHits();
    int cacheMisses();

    void close();
}
