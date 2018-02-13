package orm.executor;

import java.sql.*;

public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execQuery(String query, ExecuteHandler execute, ResultHandler result){
        try (PreparedStatement statement = getConnection().prepareStatement(query)) {
            execute.accept(statement);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            result.handle(resultSet);
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execSimpleQuery(String query) {
        try (Statement statement = getConnection().createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void execUpdate(String update, ExecuteHandler execute) {
        try (PreparedStatement statement = getConnection().prepareStatement(update)) {
            execute.accept(statement);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() {
        return connection;
    }
}