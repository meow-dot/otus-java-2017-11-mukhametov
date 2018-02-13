package orm.models;

public class DataSet {

    protected long id;

    public DataSet() {
        this.id = -1;
    }

    public DataSet(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
