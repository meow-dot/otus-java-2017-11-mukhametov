package orm.models;

public class UserDataSet extends DataSet {

    private String name;
    private int age;

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
        return "{'id':" + id + ",'name':" + name + ",'age':" + age + "}";
    }
}
