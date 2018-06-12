import db.DBService;
import db.DBServiceCacheImpl;
import model.UserDataSet;

import java.util.ArrayList;
import java.util.List;

public class DataGenerator implements Runnable {

    private final DBService dbService;

    public DataGenerator(DBService dbService) {
        this.dbService = dbService;
    }

    @Override
    public void run() {
        List<Long> ids = new ArrayList<>();
        while (true) {
            for (long i = 0; i < 100; i ++) {
                UserDataSet user = new UserDataSet();
                ids.add(dbService.save(user));
            }

            for (long id = ids.get(0); id < ids.size(); id += Math.random() * 10) {
                UserDataSet user = dbService.readUser(id);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}