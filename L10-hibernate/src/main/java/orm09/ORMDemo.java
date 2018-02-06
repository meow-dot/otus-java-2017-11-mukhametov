package orm09;

import orm09.db.UserDataSet;
import orm09.db.DBService;
import orm09.service.DBServiceImpl;

public class ORMDemo {
    public static void main(String[] args) {

        DBService dbService = new DBServiceImpl();
        UserDataSet user = new UserDataSet();
        user.setName("Vasya");
        user.setAge(30);
        UserDataSet userClone = null;

        try {
            dbService.save(user);
            userClone = dbService.read(3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(userClone);
    }
}
