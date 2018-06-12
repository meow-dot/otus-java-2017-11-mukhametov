package account;

import model.AccountDataSet;

public interface AccountDBService {

    long save(AccountDataSet account);

    AccountDataSet readAccount(String login);

    void close();
}
