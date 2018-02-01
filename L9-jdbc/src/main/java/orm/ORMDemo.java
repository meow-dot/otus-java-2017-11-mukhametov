package orm;

import orm.models.UserDataSet;
import orm.db.DBService;
import orm.executor.Executor;

public class ORMDemo {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        Executor executor = new Executor(dbService);
        UserDataSet user = new UserDataSet("Vasya", 30);
        UserDataSet userClone = null;

        dbService.getMetaData();
        try {
            dbService.prepareTables();
            executor.save(user);
            userClone = executor.load(1, UserDataSet.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(userClone);
    }
}
