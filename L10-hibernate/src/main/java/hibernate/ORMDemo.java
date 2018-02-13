package hibernate;

import hibernate.db.DBService;
import hibernate.impl.DBServiceHibernateImpl;
import hibernate.models.UserDataSet;

public class ORMDemo {
    public static void main(String[] args) {
        DBService dbService = new DBServiceHibernateImpl();
        UserDataSet user = new UserDataSet();
        user.setName("Slava");
        user.setAge(30);
        dbService.save(user);
        UserDataSet userClone = dbService.read(1);

        dbService.close();
        System.out.println(userClone);
    }
}
