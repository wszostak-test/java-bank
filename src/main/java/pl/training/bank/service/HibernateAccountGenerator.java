package pl.training.bank.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Qualifier("hibernate")
@Service
public class HibernateAccountGenerator implements AccountNumberGenerator {

    private SessionFactory sessionFactory;
    private static final long DEFAULT_ACCOUNT_NUMBER = 1;
    private static final String SQL_LAST_ACCOUNT_NUMBER =
            "select max(a.number) from Account a";

    @Autowired
    public HibernateAccountGenerator(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public String next() {
        Long lastAccountNumber = DEFAULT_ACCOUNT_NUMBER;

        Session session = sessionFactory.getCurrentSession();
        String result = (String) session
            .createQuery(SQL_LAST_ACCOUNT_NUMBER)
            .uniqueResult();

        if (result != null) {
            lastAccountNumber = Long.parseLong(result);
            ++lastAccountNumber;
        }

        return String.format("%026d", lastAccountNumber);
    }
}
