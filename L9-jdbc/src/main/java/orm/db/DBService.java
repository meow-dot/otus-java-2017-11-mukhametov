package orm.db;

import orm.executor.Executor;
import orm.models.DataSet;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBService implements AutoCloseable{

    private static final String CREATE_TABLE_USER = "create table if not exists user (" +
            "id bigint(20) NOT NULL auto_increment, " +
            "name varchar(255), " +
            "age int(3), " +
            "primary key (id))";
    private static final String INSERT_USER = "insert into user (name, age) values (?,?)";
    private static final String SELECT_USER = "select * from user where id=?";
    private static final String UPDATE_USER = "update user set name=?, age=? where id=?";
    private static final String DELETE_TABLE_USER = "drop table user";

    private final Connection connection;

    public DBService() {
        connection = ConnectionHelper.getConnection();
    }

    public void prepareTables() throws SQLException {
        Executor executor = new Executor(getConnection());
        executor.execSimpleQuery(CREATE_TABLE_USER);
        System.out.println("Table is created.");
    }

    public void deleteTables() throws SQLException {
        Executor executor = new Executor(getConnection());
        executor.execSimpleQuery(DELETE_TABLE_USER);
        System.out.println("Table is dropped");
    }

    public <T extends DataSet> void save(T user) throws SQLException {
        Executor executor = new Executor(getConnection());
        if (user.getId() == -1){
            executor.execUpdate(INSERT_USER, statement -> {
                statement.setString(1, getFieldValue(user, "name"));
                statement.setInt(2, getFieldValue(user, "age"));
            });
        } else {
            executor.execUpdate(UPDATE_USER, statement -> {
                statement.setString(1, getFieldValue(user, "name"));
                statement.setInt(2, getFieldValue(user, "age"));
                statement.setLong(3, getFieldValue(user, "id"));
            });
        }
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws Exception {
        T user = clazz.getConstructor().newInstance();
        user.setId(id);
        Executor executor = new Executor(getConnection());
        executor.execQuery(SELECT_USER,
                statement -> statement.setLong(1, id),
                result -> {
                    setFieldValue(user, "name", result.getString("name"));
                    setFieldValue(user, "age", result.getInt("age"));
                }
            );
        return user;
    }

    @SuppressWarnings("unchecked")
    private <V,T> V getFieldValue(T object, String fieldName) {
        V value = null;
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                if (field.getName().equals(fieldName)) {
                    value = (V) field.get(object);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    private <V,T> void setFieldValue(T object, String fieldName, V fieldValue) {
        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.getName().equals(fieldName)) {
                field.setAccessible(true);
                try {
                    field.set(object, fieldValue);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Connection getConnection() {
        return connection;
    }

    @Override
    public void close() throws Exception {
        connection.close();
        System.out.println("Connection is closed.");
    }
}