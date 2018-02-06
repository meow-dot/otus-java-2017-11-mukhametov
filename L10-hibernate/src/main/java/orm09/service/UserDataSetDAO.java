package orm09.service;

import orm09.db.UserDataSet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDataSetDAO {

    private static final String CREATE_TABLE_USER = "create table if not exists user (" +
            "id bigint(20) NOT NULL auto_increment, " +
            "name varchar(255), " +
            "age int(3), " +
            "primary key (id))";
    private static final String INSERT_USER = "insert into user (name, age) values ('%s', %d)";
    private static final String SELECT_USER_NAME = "select name from user where id=%d";
    private static final String SELECT_USER_AGE = "select age from user where id=%d";
    private static final String UPDATE_USER_NAME = "update user set name='%s' where id=%d";
    private static final String UPDATE_USER_AGE = "update user set age=%d where id=%d";
    private static final String DELETE_TABLE_USER = "drop table user";

    private Connection connection;

    public UserDataSetDAO(Connection connection) {
        this.connection = connection;
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(CREATE_TABLE_USER);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserDataSet read(long id) {
        UserDataSet user = new UserDataSet();
        user.setName(getUserName(id));
        user.setAge(getUserAge(id));
        return user;
    }

    public void save(UserDataSet user) {
        try (Statement stmt = connection.createStatement()) {
            if (user.getId() == -1) {
                stmt.execute(String.format(INSERT_USER, user.getName(), user.getAge()));
            } else {
                stmt.execute(String.format(UPDATE_USER_NAME, user.getName(), user.getId()));
                stmt.execute(String.format(UPDATE_USER_AGE, user.getAge(), user.getId()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getUserName(long id) {
        String name = null;
        try (Statement stmt = connection.createStatement()){
            ResultSet result = stmt.executeQuery(String.format(SELECT_USER_NAME, id));
            while (result.next()) {
                name = result.getString("name");
            }
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
            while (result.next()) {
                age = Integer.parseInt(result.getString("age"));
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return age;
    }
}
