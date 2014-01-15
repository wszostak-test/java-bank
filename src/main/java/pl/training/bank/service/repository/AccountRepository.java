package pl.training.bank.service.repository;

import pl.training.bank.entity.Account;

public interface AccountRepository {

    Account add(Account account);

    Account get(Long id);

}
