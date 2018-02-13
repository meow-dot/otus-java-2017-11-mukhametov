package orm09.models;

public class UserDataSet extends DataSet {

    private String name;
    private int age;

    public UserDataSet(){}

    public UserDataSet(String name, int age) {
        super(-1);
        this.name = name;
        this.age = age;
    }

    public UserDataSet(long id, String name, int age) {
        super(id);
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

    @Override
    public String toString() {
        return "UserDataSet{" +
                "name='" + getName() + '\'' +
                ", age=" + getAge() +
                ", id=" + getId() +
                '}';
    }
}
