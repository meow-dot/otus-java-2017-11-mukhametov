package cache.model;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet{

    @Column(name = "street")
    private String street;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDataSet user;

    public AddressDataSet() {
        super();
    }

    public AddressDataSet(String street) {
        super(-1);
        this.street = street;
    }

    public AddressDataSet(long id, String street) {
        super(id);
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public UserDataSet getUser() {
        return user;
    }

    public void setUser(UserDataSet user) {
        this.user = user;
        user.setAddress(this);
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + street + '\'' +
                ", id=" + getId() +
                '}';
    }
}
