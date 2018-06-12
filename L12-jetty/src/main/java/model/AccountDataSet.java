package model;

import javax.persistence.*;

@Entity
@Table(name = "account")
public class AccountDataSet extends DataSet {

    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;

    public AccountDataSet() {
        super();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
