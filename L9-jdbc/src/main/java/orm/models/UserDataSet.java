package orm.models;

public class UserDataSet extends DataSet {

    protected final String name;
    protected final int age;

    public UserDataSet(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserDataSet(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "{'id':" + id + ",'name':" + name + ",'age':" + age + "}";
    }
}
