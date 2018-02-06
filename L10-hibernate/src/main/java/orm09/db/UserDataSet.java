package orm09.db;

public class UserDataSet extends DataSet {

    private String name;

    private int age;

    public UserDataSet() {}

    public UserDataSet(String name, int age) {
        this.setId(-1);
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String toString() {
        return "{" +
                "'id':" + getId() +
                ",'name':" + getName() +
                ",'age':" + getAge() +
                "}";
    }
}
