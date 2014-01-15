package pl.training.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.AccountNumberGenerator;
import pl.training.bank.service.repository.AccountDoesNotExistException;
import pl.training.bank.service.repository.AccountRepository;
import pl.training.bank.service.repository.ClientRepository;

import java.math.BigDecimal;

@Component
public class Bank {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;
    private AccountNumberGenerator accountNumberGenerator;

    @Autowired
    public Bank(ClientRepository clientRepository, AccountRepository accountRepository, AccountNumberGenerator accountNumberGenerator) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.accountNumberGenerator = accountNumberGenerator;
    }

    public Client addClient(Client client) throws BankException {
        return clientRepository.add(client);
    }

    public Account addAccount(Account account) throws BankException {
        account.setNumber(accountNumberGenerator.next());
        return accountRepository.add(account);
    }

    public void assignClientToAccount(Long accountId, Long clientId) throws BankException {
        Account account = accountRepository.get(accountId);
        Client client = clientRepository.get(clientId);
        if (account != null && client != null) {
            account.addClient(client);
        } else {
            throw new BankException();
        }
    }

    public void payInCashToAccount(String accountNumber, BigDecimal amount)  throws BankException {
        Account account = accountRepository.getByAccountNumber(accountNumber);
        account.payIn(amount);
    }

    public void payOutCashFromAccount(String accountNumber, BigDecimal amount)  throws BankException {
        Account account = accountRepository.getByAccountNumber(accountNumber);
        account.payOut(amount);

    }

    public void transferCash(String fromAccountNumber, String toAccountNumber, BigDecimal amount)  throws BankException {
        payOutCashFromAccount(fromAccountNumber, amount);
        try {
            payInCashToAccount(toAccountNumber, amount);
        } catch (BankException e) {
            payInCashToAccount(fromAccountNumber, amount);
            throw e;
        }
    }
}
