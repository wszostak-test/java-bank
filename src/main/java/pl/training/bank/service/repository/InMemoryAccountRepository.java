package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

import java.util.HashMap;
import java.util.Map;

public class InMemoryAccountRepository implements AccountRepository {

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

}
