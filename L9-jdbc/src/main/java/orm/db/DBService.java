package orm.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService implements AutoCloseable{

    private static final String CREATE_TABLE_USER = "create table if not exists user (" +
            "id bigint(20) NOT NULL auto_increment, " +
            "name varchar(255), " +
            "age int(3), " +
            "primary key (id))";
    private static final String INSERT_USER = "insert into user (id) values (%d)";
    private static final String SELECT_USER = "select name from user where id=%s";
    private static final String DELETE_USER = "drop table user";

    private final Connection connection;

    public DBService() {
        connection = ConnectionHelper.getConnection();
    }

    public String getMetaData() {
        try {
            return "Connected to: " + getConnection().getMetaData().getURL() + "\n" +
                    "DB name: " + getConnection().getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + getConnection().getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + getConnection().getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void prepareTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(CREATE_TABLE_USER);
        }
        System.out.println("Table created");
    }

    public void addUser(long id) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(String.format(INSERT_USER, id));
        } catch (SQLException ex) {
            System.out.println("This id is occupied.");
        }
        System.out.println("User added.");
    }

    public void deleteTables() throws SQLException {

        System.out.println("Table dropped");
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed.");
    }

    protected Connection getConnection() {
        return connection;
    }


}