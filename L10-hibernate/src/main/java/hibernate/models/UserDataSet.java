package hibernate.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;
    @Column(name = "age")
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
