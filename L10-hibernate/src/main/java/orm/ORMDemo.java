package orm;

import orm.impl.DBServiceImpl;
import orm.models.UserDataSet;
import orm.db.DBService;

public class ORMDemo {
    public static void main(String[] args) {
        try (DBService dbService = new DBServiceImpl()){
            UserDataSet user = new UserDataSet();
            user.setName("Egor");
            user.setAge(30);
            dbService.createTable();
            dbService.save(user);
            System.out.println(dbService.read(1));
            dbService.deleteTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
