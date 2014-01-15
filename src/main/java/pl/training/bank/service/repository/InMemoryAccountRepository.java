package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Account;

import java.util.HashMap;
import java.util.Map;

@Qualifier("inMemory")
@Repository
public class InMemoryAccountRepository implements AccountRepository
{
    private Map<Long, Account> accounts = new HashMap<>();
    private long lastId;

    @Override
    public synchronized Account add(Account account) {
        account.setId(++lastId);
        accounts.put(lastId, account);
        return account;
    }

    @Override
    public Account get(Long id) {
        return accounts.get(id);
    }

    @Override
    public Account getByAccountNumber(String accountNumber) throws AccountDoesNotExistException {
        for (Account account: accounts.values()) {
            if (account.getNumber().equals(accountNumber)) {
                return account;
            }
        }

        throw new AccountDoesNotExistException();
    }

    @Override
    public void update(Account account) throws AccountDoesNotExistException {
        Long accountId = account.getId();

        if (accounts.containsKey(accountId)) {
            accounts.put(accountId, account);
        } else {
            throw new AccountDoesNotExistException();
        }
    }
}
