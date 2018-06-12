package db.dao;

import model.AccountDataSet;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class AccountDataSetDAO {

    private final Session session;

    public AccountDataSetDAO(Session session) {
        this.session = session;
    }

    public AccountDataSet read(String login) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<AccountDataSet> criteria = builder.createQuery(AccountDataSet.class);
        Root<AccountDataSet> from = criteria.from(AccountDataSet.class);
        criteria.where(builder.equal(from.get("login"), login));
        Query<AccountDataSet> query = session.createQuery(criteria);
        return query.uniqueResult();
    }

    public long save(AccountDataSet account) {
        if (account.getId() == -1) {
            return (long) session.save(account);
        } else {
            session.update(account);
            return account.getId();
        }
    }
}
