package hibernate;

import hibernate.db.DBService;
import hibernate.impl.DBServiceHibernateImpl;
import hibernate.models.AddressDataSet;
import hibernate.models.PhoneDataSet;
import hibernate.models.UserDataSet;

public class ORMDemo {
    public static void main(String[] args) {
        try (DBService dbService = new DBServiceHibernateImpl()){
            UserDataSet user = new UserDataSet();
            user.setName("Slava");
            user.setAge(30);
            AddressDataSet address = new AddressDataSet("Nikolskaya");
            address.setUser(user);
            PhoneDataSet phone = new PhoneDataSet("123456789");
            phone.setUser(user);
            dbService.saveUser(user);
            System.out.println(dbService.readUser(1));
            System.out.println(dbService.readUser(1).getPhones());
            System.out.println(dbService.readUser(1).getAddress());
        }
    }
}
