package cache;

import cache.db.DBService;
import cache.db.DBServiceCacheImpl;
import cache.model.AddressDataSet;
import cache.model.PhoneDataSet;
import cache.model.UserDataSet;

public class Main {
    public static void main(String[] args) {
        try (DBService dbService = new DBServiceCacheImpl()){
            UserDataSet user = new UserDataSet();
            user.setName("Slava");
            user.setAge(30);
            AddressDataSet address = new AddressDataSet("Nikolskaya");
            address.setUser(user);
            PhoneDataSet phone = new PhoneDataSet("123456789");
            phone.setUser(user);
            long userId = dbService.save(user);
            UserDataSet copy = dbService.readUser(userId);
            System.out.println(copy);
        }
    }
}
