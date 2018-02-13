package orm09;

import orm09.impl.DBServiceImpl;
import orm09.models.UserDataSet;
import orm09.db.DBService;

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
