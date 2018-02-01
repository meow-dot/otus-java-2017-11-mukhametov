package orm.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBService implements AutoCloseable{

    private static final String CREATE_TABLE_DATA = "create table if not exists data (" +
            "id bigint(20) NOT NULL auto_increment, " +
            "name varchar(255), " +
            "age int(3), " +
            "primary key (id))";
    private static final String INSERT_DATA = "insert into data (name, age) values ('%s', %d)";
    private static final String SELECT_DATA = "select * from data where id=%d";
    private static final String DELETE_DATA = "drop table data";

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
            stmt.execute(CREATE_TABLE_DATA);
        }
        System.out.println("Table created.");
    }

    public void addData(String name, int age) {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(String.format(INSERT_DATA, name, age));
            System.out.println("Data added.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Data getData(long id) {
        Data data = new Data();
        try (Statement stmt = connection.createStatement()){
            ResultSet result = stmt.executeQuery(String.format(SELECT_DATA, id));
            result.next();
            data.setId(Long.parseLong(result.getString("id")));
            data.setName(result.getString("name"));
            data.setAge(Integer.parseInt(result.getString("age")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User selected.");
        return data;
    }

    public void deleteTables() throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(DELETE_DATA);
        }
        System.out.println("Table dropped");
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection closed.");
    }
}