package pl.training.bank.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Qualifier("jpa")
public class JpaAccountNumberGenerator implements AccountNumberGenerator
{
    @PersistenceContext
    private EntityManager entityManager;
    private static final long DEFAULT_ACCOUNT_NUMBER = 1;
    private static final String SQL_LAST_ACCOUNT_NUMBER =
            "select max(a.number) from Account a";

    @Override
    public String next() {
        Long lastAccountNumber = DEFAULT_ACCOUNT_NUMBER;

        String result = entityManager
            .createQuery(SQL_LAST_ACCOUNT_NUMBER, String.class)
            .getSingleResult();

        if (result != null) {
            lastAccountNumber = Long.parseLong(result);
            ++lastAccountNumber;
        }

        return String.format("%026d", lastAccountNumber);
    }
}
