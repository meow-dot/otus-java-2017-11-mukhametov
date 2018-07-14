package war_ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import war_ioc.account.AccountDBService;
import war_ioc.db.DBService;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ApplicationContext appContext = new AnnotationConfigApplicationContext(AppConfig.class);

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("dBService", appContext.getBean(DBService.class));
        servletContext.setAttribute("accountDbService", appContext.getBean(AccountDBService.class));

        DataGenerator dataGenerator = appContext.getBean(DataGenerator.class);
        new Thread(dataGenerator).start();
    }
}