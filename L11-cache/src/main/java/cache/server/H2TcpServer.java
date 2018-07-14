package cache.server;

import org.h2.tools.Server;

import java.sql.SQLException;

public class H2TcpServer {

    public H2TcpServer() {
        try {
            Server server = Server.createTcpServer().start();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
