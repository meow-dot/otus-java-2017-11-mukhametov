package orm09.db;

import orm09.models.UserDataSet;

public interface DBService {

    UserDataSet read(long id);
    void save(UserDataSet user);
    void createTable();
    void deleteTable();
    void close();
}