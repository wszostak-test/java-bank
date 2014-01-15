package pl.training;

import pl.training.entity.Account;
import pl.training.entity.Client;
import pl.training.service.AccountNumberGenerator;
import pl.training.service.BankException;
import pl.training.service.repository.AccountRepository;
import pl.training.service.repository.ClientRepository;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14.01.14
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class Bank
{
    private AccountRepository accountRepository;
    private ClientRepository clientRepository;
    private AccountNumberGenerator accountNumberGenerator;

    public Bank(AccountRepository accountRepository, ClientRepository clientRepository, AccountNumberGenerator accountNumberGenerator) {
        this.accountRepository = accountRepository;
        this.clientRepository = clientRepository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public void payInCache(String accountNumber, BigDecimal amount) throws BankException {
        System.out.println("payInCache");
    }

    public void payOutCache(String accountNumber, BigDecimal amount) throws BankException {
        System.out.println("payOutCache");
    }

    public void transferCache(String fromAccountNumber, String toAccountNumber, BigDecimal amount) throws BankException {
        System.out.println("transferCache");
    }

    public Long addClient(Client client) throws BankException {
        return clientRepository.addClient(client);
    }

    public Long addAccount(Account account) throws BankException {
        account.setNumber(accountNumberGenerator.next());
        return accountRepository.addAccount(account);
    }
}
