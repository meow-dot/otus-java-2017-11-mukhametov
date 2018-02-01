package orm.executor;

import orm.db.Data;
import orm.models.DataSet;
import orm.db.DBService;

import java.lang.reflect.Field;

public class Executor {

    protected final DBService dbService;
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
                System.out.println(name);
            }
            if (field.getName().equals("age")) {
                age = (int) field.get(user);
                System.out.println(age);
            }
        }
        dbService.addData(name, age);
    }

    public <T extends DataSet> T load(long id, Class<T> clazz) throws Exception  {
        Data data = dbService.getData(id);
        return clazz.getConstructor(Long.class, String.class, Integer.class)
                .newInstance(data.getId(), data.getName(), data.getAge());
    }
}