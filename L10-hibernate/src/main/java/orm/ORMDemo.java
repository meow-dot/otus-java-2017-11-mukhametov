package orm;

import orm.impl.DBServiceImpl;
import orm.models.UserDataSet;
import orm.db.DBService;

public class ORMDemo {
    public static void main(String[] args) {
        DBService dbService = new DBServiceImpl();
        UserDataSet user = new UserDataSet();
        user.setName("Egor");
        user.setAge(30);
        UserDataSet userClone = null;

        try {
            dbService.createTable();
            dbService.save(user);
            userClone = dbService.read(1);
            dbService.deleteTable();
            dbService.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userClone);
    }
}
