package pl.training.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;

import java.math.BigDecimal;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bank.xml", "bank-repository.xml");
        ((AbstractApplicationContext) context).registerShutdownHook();
        Bank bank = context.getBean(Bank.class);
        try {
            Account account1 = bank.addAccount(new Account());
            bank.payInCashToAccount(account1.getNumber(), new BigDecimal(1000));
            Client client = new Client();
            client.setFirstName("Jan");
            client.setLastName("Kowalski");
            bank.addClient(client);
            bank.assignClientToAccount(account1.getId(), client.getId());
        } catch (BankException e) {
            e.printStackTrace();
        }
    }

}
