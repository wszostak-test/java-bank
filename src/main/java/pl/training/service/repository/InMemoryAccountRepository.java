package pl.training.service.repository;

import pl.training.entity.Account;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14.01.14
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class InMemoryAccountRepository implements AccountRepository {
    private Map<Long, Account> accounts = new HashMap<>();
    private long lastId;

    @Override
    public synchronized Long addAccount(Account account) {
        account.setId(++lastId);
        accounts.put(lastId, account);
        return lastId;
    }
}
