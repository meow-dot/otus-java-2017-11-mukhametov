package orm09.db;

public interface DBService {

    void save(UserDataSet user);
    UserDataSet read(long id);
}