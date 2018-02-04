package orm.executor;

import orm.models.DataSet;
import orm.db.DBService;

import java.lang.reflect.Field;

public class Executor {

    private final DBService dbService;
    public Executor(DBService dbService) {
        this.dbService = dbService;
    }

    public <T extends DataSet> void save(T user) throws IllegalAccessException{
        String name = null;
        int age = 0;
        for (Field field : user.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().equals("name")) {
                name = (String) field.get(user);
            }
            if (field.getName().equals("age")) {
                age = (int) field.get(user);
            }
        }
        dbService.addUser(name, age);
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws Exception  {
        T user = clazz.getConstructor().newInstance();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.getName().equals("name")) {
                field.set(user, dbService.getUserName(id));
            }
            if (field.getName().equals("age")) {
                field.set(user, dbService.getUserAge(id));
            }
        }
        return user;
    }
}