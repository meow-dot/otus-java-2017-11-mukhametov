package war_ioc.account;

public interface AccountDBService {

    long save(AccountDataSet account);

    AccountDataSet readAccount(String login);

    void close();
}
