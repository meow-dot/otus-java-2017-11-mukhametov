package orm09.executor;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface ExecuteHandler {
    void accept(PreparedStatement statement) throws SQLException;
}
