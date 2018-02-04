package orm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService implements AutoCloseable{

    private static final String CREATE_TABLE_USER = "create table if not exists data (" +
            "id bigint(20) NOT NULL auto_increment, " +
            "name varchar(255), " +
            "age int(3), " +
            "primary key (id))";
    private static final String INSERT_USER = "insert into data (name, age) values ('%s', %d)";
    private static final String SELECT_USER_NAME = "select name from data where id=%d";
    private static final String SELECT_USER_AGE = "select age from data where id=%d";
    private static final String DELETE_TABLE_USER = "drop table data";

    private final Connection connection;

    public DBService() {
        connection = ConnectionHelper.getConnection();
    }

    public String getMetaData() {
        try {
            return "Connected to: " + connection.getMetaData().getURL() + "\n" +
                    "DB name: " + connection.getMetaData().getDatabaseProductName() + "\n" +
                    "DB version: " + connection.getMetaData().getDatabaseProductVersion() + "\n" +
                    "Driver: " + connection.getMetaData().getDriverName();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void prepareTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(CREATE_TABLE_USER);
        }
        System.out.println("Table is created.");
    }

    public void addUser(String name, int age) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(String.format(INSERT_USER, name, age));
            System.out.println("User is added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserName(long id) {
        String name = null;
        try (Statement stmt = connection.createStatement()){
            ResultSet result = stmt.executeQuery(String.format(SELECT_USER_NAME, id));
            result.next();
            name = result.getString("name");
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public int getUserAge(long id) {
        int age = 0;
        try (Statement stmt = connection.createStatement()){
            ResultSet result = stmt.executeQuery(String.format(SELECT_USER_AGE, id));
            result.next();
            age = Integer.parseInt(result.getString("age"));
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return age;
    }

    public void deleteTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(DELETE_TABLE_USER);
        }
        System.out.println("Table is dropped");
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection is closed.");
    }
}