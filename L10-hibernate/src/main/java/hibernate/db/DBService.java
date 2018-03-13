package hibernate.db;

import hibernate.models.AddressDataSet;
import hibernate.models.PhoneDataSet;
import hibernate.models.UserDataSet;

public interface DBService {

    UserDataSet readUser(long id);
    void saveUser(UserDataSet user);
    AddressDataSet readAddress(long id);
    void saveAddress(AddressDataSet address);
    PhoneDataSet readPhone(long id);
    void savePhone(PhoneDataSet phone);
    void close();
}