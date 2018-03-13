package orm.impl;

import org.h2.tools.Server;
import orm.db.DBService;
import orm.impl.dao.UserDataSetDAO;
import orm.models.UserDataSet;

import java.sql.Connection;
import java.sql.SQLException;

public class DBServiceImpl implements DBService {

    private Server server;
    private Connection connection;

    public DBServiceImpl() {
        try {
            server = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        server.stop();
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection is closed.");
    }
}
