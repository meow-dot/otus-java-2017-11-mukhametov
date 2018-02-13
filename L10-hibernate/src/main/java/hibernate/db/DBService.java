package hibernate.db;

import hibernate.models.UserDataSet;

public interface DBService {

    UserDataSet read(long id);
    void save(UserDataSet user);
    void close();
}