import account.AccountDBService;
import account.AccountDBServiceImpl;
import db.DBService;
import db.DBServiceCacheImpl;
import model.AccountDataSet;
import server.AdminPage;

public class AdminPageDemo {

    public static void main(String[] args) {

        DBService dbService = new DBServiceCacheImpl();

        Thread thread = new Thread(new DataGenerator(dbService));
        thread.start();

        AccountDBService accountDBService = new AccountDBServiceImpl();
        AccountDataSet account = new AccountDataSet();
        account.setLogin("admin");
        account.setPassword("password");
        accountDBService.save(account);

        AdminPage adminPage = new AdminPage(8080, dbService, accountDBService);
        adminPage.start();
    }
}
