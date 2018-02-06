package orm09.service;

import orm09.db.DBService;
import orm09.db.UserDataSet;

import java.sql.Connection;

public class DBServiceImpl implements DBService {

    private Connection connection;

    public DBServiceImpl() {
        connection = ConnectionHelper.getConnection();
    }

    @Override
    public void save(UserDataSet user) {
        UserDataSetDAO userDAO = new UserDataSetDAO(connection);
        userDAO.save(user);
    }

    @Override
    public UserDataSet read(long id) {
        UserDataSetDAO userDAO = new UserDataSetDAO(connection);
        return userDAO.read(id);
    }
}
