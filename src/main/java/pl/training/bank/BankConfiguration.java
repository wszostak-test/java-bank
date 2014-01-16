package pl.training.bank;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import pl.training.bank.service.repository.AccountRepository;
import pl.training.bank.service.repository.ClientRepository;

@Configuration
public class BankConfiguration
{
    @Bean
    @Scope("singleton")
    public Bank bank(ClientRepository clientRepository, AccountRepository accountRepository) {
        return new Bank(clientRepository, accountRepository);
    }
}
