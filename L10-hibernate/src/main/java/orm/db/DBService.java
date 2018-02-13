package orm.db;

import orm.models.UserDataSet;

public interface DBService {

    UserDataSet read(long id);
    void save(UserDataSet user);
    void createTable();
    void deleteTable();
    void close();
}