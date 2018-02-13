package orm.impl;

import orm.db.DBService;
import orm.impl.dao.UserDataSetDAO;
import orm.models.UserDataSet;

import java.sql.Connection;
import java.sql.SQLException;

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

    @Override
    public void createTable() {
        UserDataSetDAO userDAO = new UserDataSetDAO(connection);
        userDAO.createTable();
    }

    @Override
    public void deleteTable() {
        UserDataSetDAO userDAO = new UserDataSetDAO(connection);
        userDAO.deleteTable();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection is closed.");
    }
}
