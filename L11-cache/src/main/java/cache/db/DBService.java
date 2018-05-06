package cache.db;

import cache.model.AddressDataSet;
import cache.model.PhoneDataSet;
import cache.model.UserDataSet;

public interface DBService extends AutoCloseable{
    UserDataSet readUser(long id);
    long save(UserDataSet user);
    AddressDataSet readAddress(long id);
    long save(AddressDataSet address);
    PhoneDataSet readPhone(long id);
    long save(PhoneDataSet phone);
    void close();
}
