package hibernate;

import hibernate.db.DBService;
import hibernate.impl.DBServiceHibernateImpl;
import hibernate.models.AddressDataSet;
import hibernate.models.PhoneDataSet;
import hibernate.models.UserDataSet;

import java.util.HashSet;
import java.util.Set;

public class ORMDemo {
    public static void main(String[] args) {
        DBService dbService = new DBServiceHibernateImpl();
        UserDataSet user = new UserDataSet();
        user.setName("Slava");
        user.setAge(30);
        AddressDataSet address = new AddressDataSet("Nikolskaya");
        address.setUser(user);
        PhoneDataSet phone = new PhoneDataSet("123456789");
        phone.setUser(user);
        dbService.saveUser(user);
        dbService.saveAddress(address);
        dbService.savePhone(phone);
        UserDataSet userClone = dbService.readUser(1);
        System.out.println(userClone);
        System.out.println(userClone.getAddress());
        System.out.println(userClone.getPhones());
    }
}
