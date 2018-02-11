package orm;

import orm.models.UserDataSet;
import orm.db.DBService;

public class ORMDemo {
    public static void main(String[] args) {
        DBService dbService = new DBService();
        UserDataSet user = new UserDataSet();
        user.setName("Egor");
        user.setAge(30);
        UserDataSet userClone = null;

        try {
            dbService.prepareTables();
            dbService.save(user);
            userClone = dbService.load(1, UserDataSet.class);
            dbService.deleteTables();
            dbService.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userClone);
    }
}
