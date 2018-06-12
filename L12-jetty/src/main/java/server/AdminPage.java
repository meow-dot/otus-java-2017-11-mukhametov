package server;

import account.AccountDBService;
import db.DBService;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.resource.Resource;

public class AdminPage implements AutoCloseable {

    private final static String PUBLIC_HTML = "/public_html";
    private final DBService dbService;
    private final AccountDBService accountDBService;
    private final Server server;

    public AdminPage(int port, DBService dbService, AccountDBService accountDBService) {
        this.dbService = dbService;
        this.accountDBService = accountDBService;
        server = new Server(port);
    }

    public void start() {

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResource(Resource.newClassPathResource(PUBLIC_HTML));

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new StatServlet(dbService)), "/stat");
        context.addServlet(new ServletHolder(new LoginServlet(accountDBService)), "/login");


        server.setHandler(new HandlerList(resourceHandler, context));

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void close() throws Exception {
        dbService.close();
        server.stop();
    }
}
