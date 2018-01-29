package orm.executor;

import orm.base.DataSet;
import orm.db.DBService;

public class Executor {

    private final DBService dbService;

    public Executor(DBService dbService) {
        this.dbService = dbService;
    }

    public <T extends DataSet> void save(T user) {

    }
    public <T extends DataSet> T load(long id, Class<T> clazz) {
        return null;
    }
}