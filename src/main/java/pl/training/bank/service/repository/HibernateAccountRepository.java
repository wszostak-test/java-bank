package pl.training.bank.service.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;

@DAO(type = DAO.Type.HIBERNATE)
public class HibernateAccountRepository implements AccountRepository
{
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateAccountRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Account add(Account account) {
        Session session = sessionFactory.getCurrentSession();

        session.save(account);
        session.flush();
        session.refresh(account);

        return account;
    }

    @Override
    public Account get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Account) session.get(Account.class, id);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) throws AccountDoesNotExistException {
        Session session = sessionFactory.getCurrentSession();
        Account account = (Account) session
            .getNamedQuery(Account.QL_SELECT_BY_NUMBER)
            .setParameter("number", accountNumber)
            .uniqueResult();

        if (account != null) {
            return account;
        }

        throw new AccountDoesNotExistException();
    }

    @Override
    public void update(Account account) throws AccountDoesNotExistException {
        getByAccountNumber(account.getNumber());
        Session session = sessionFactory.getCurrentSession();
        session.update(account);
    }
}
