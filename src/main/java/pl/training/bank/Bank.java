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
import java.util.List;

@Transactional(rollbackFor = BankException.class)
@Component
public class Bank {

    private ClientRepository clientRepository;
    private AccountRepository accountRepository;

    @Autowired
    public Bank(ClientRepository clientRepository, AccountRepository accountRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
    }

    public Client addClient(Client client) throws BankException {
        return clientRepository.saveAndFlush(client);
    }

    public Account addAccount(Account account) throws BankException {
        String lastAccountNumber = accountRepository.findLastNumber();
        long lastId = Long.parseLong(lastAccountNumber);
        account.setNumber(String.format("%026d", ++lastId));
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
        accountRepository.findByNumber(accountNumber).payIn(amount);
    }

    public void payOutCashFromAccount(String accountNumber, BigDecimal amount)  throws BankException {
        accountRepository.findByNumber(accountNumber).payOut(amount);
    }

    public void transferCash(String fromAccountNumber, String toAccountNumber, BigDecimal amount)  throws BankException {
        payOutCashFromAccount(fromAccountNumber, amount);
        payInCashToAccount(toAccountNumber, amount);
    }

    public Client getClientById(Long id) {
        return clientRepository.findOne(id);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

}
