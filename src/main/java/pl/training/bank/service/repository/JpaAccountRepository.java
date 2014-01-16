package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DAO(type = DAO.Type.JPA)
public class JpaAccountRepository implements AccountRepository
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Account add(Account account) {
        entityManager.persist(account);
        entityManager.flush();
        entityManager.refresh(account);
        return account;
    }

    @Override
    public Account get(Long id) {
        return entityManager.find(Account.class, id);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) throws AccountDoesNotExistException {
        Account account = entityManager
            .createNamedQuery(
                Account.QL_SELECT_BY_NUMBER,
                Account.class)
            .setParameter("number", accountNumber)
            .getSingleResult();

        if (account != null) {
            return account;
        }

        throw new AccountDoesNotExistException();
    }

    @Override
    public void update(Account account) throws AccountDoesNotExistException {
        getByAccountNumber(account.getNumber());
        entityManager.merge(account);
    }
}