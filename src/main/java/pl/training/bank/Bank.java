package pl.training.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.repository.AccountRepository;
import pl.training.bank.service.repository.ClientRepository;

import java.math.BigDecimal;

@Component
@Transactional(rollbackFor = BankException.class)
public class Bank {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @Autowired
    public Bank(
            ClientRepository clientRepository,
            AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client addClient(Client client) throws BankException {
        return clientRepository.saveAndFlush(client);
    }

    public Account addAccount(Account account) throws BankException {
        Long accountNumber = 1L;

        String lastNumber = accountRepository.findLastNumber();
        if (lastNumber != null) {
            accountNumber = Long.parseLong(lastNumber) + 1;
        }

        account.setNumber(String.format("%026d", accountNumber));
        account.setBalance(BigDecimal.ZERO);
        return accountRepository.saveAndFlush(account);
    }

    public void assignClientToAccount(Long accountId, Long clientId) throws BankException {
        Account account = accountRepository.findOne(accountId);
        Client client = clientRepository.findOne(clientId);
        if (account != null && client != null) {
            account.addClient(client);
        } else {
            throw new BankException();
        }
    }

    public void payInCashToAccount(String accountNumber, BigDecimal amount)  throws BankException {
        Account account = accountRepository.findByNumber(accountNumber);
        account.payIn(amount);
    }

    public void payOutCashFromAccount(String accountNumber, BigDecimal amount)  throws BankException {
        Account account = accountRepository.findByNumber(accountNumber);
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
