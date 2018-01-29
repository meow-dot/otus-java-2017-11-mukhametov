package orm.base;

public abstract class DataSet {

    protected final long id;

    public DataSet(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
