package orm09.impl.dao;

import orm09.executor.Executor;
import orm09.models.UserDataSet;

import java.sql.Connection;
import java.sql.SQLException;

public class UserDataSetDAO {

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

    public UserDataSetDAO(Connection connection) {
        this.connection = connection;
    }

    public UserDataSet read(long id) {
        UserDataSet user = new UserDataSet();
        user.setId(id);
        Executor executor = new Executor(getConnection());
        executor.execQuery(SELECT_USER,
                statement -> statement.setLong(1, id),
                result -> {
                    user.setName(result.getString("name"));
                    user.setAge(result.getInt("age"));
                }
        );
        return user;
    }
    public void save(UserDataSet user) {
        Executor executor = new Executor(getConnection());
        if (user.getId() == -1){
            executor.execUpdate(INSERT_USER, statement -> {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
            });
        } else {
            executor.execUpdate(UPDATE_USER, statement -> {
                statement.setString(1, user.getName());
                statement.setInt(2, user.getAge());
                statement.setLong(3, user.getId());
            });
        }
    }

    public void createTable() {
        Executor executor = new Executor(getConnection());
        executor.execSimpleQuery(CREATE_TABLE_USER);
        System.out.println("Table is created.");
    }

    public void deleteTable() {
        Executor executor = new Executor(getConnection());
        executor.execSimpleQuery(DELETE_TABLE_USER);
        System.out.println("Table is deleted.");
    }

    private Connection getConnection() {
        return connection;
    }
}
