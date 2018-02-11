package orm.executor;

import java.sql.*;

public class Executor {

    private final Connection connection;

    public Executor(Connection connection) {
        this.connection = connection;
    }

    public void execUpdate(String update, ExecuteHandler execute) throws SQLException {
        PreparedStatement statement = getConnection().prepareStatement(update);
        execute.accept(statement);
        statement.execute();
        statement.close();
    }

    public void execQuery(String query, ExecuteHandler execute, ResultHandler result) throws SQLException{
        PreparedStatement statement = getConnection().prepareStatement(query);
        execute.accept(statement);
        statement.execute();
        ResultSet resultSet = statement.getResultSet();
        resultSet.next();
        result.handle(resultSet);
        resultSet.close();
        statement.close();
    }

    public void execSimpleQuery(String query) throws SQLException {
        Statement statement = getConnection().createStatement();
        statement.execute(query);
        statement.close();
    }

    private Connection getConnection() {
        return connection;
    }
}