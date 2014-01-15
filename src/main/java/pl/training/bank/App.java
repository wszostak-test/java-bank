package pl.training.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.JdbcAccountNumberGenerator;

import javax.sql.DataSource;
import java.math.BigDecimal;

public class App
{
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "bank.xml",
                "bank-repository.xml");

        Bank bank = context.getBean(Bank.class);

        try {
            Client client = new Client();
            client.setFirstName("Jan");
            client.setLastName("Kolwalski");

            bank.addClient(client);
        } catch (BankException e) {
            // e.printStackTrace();
        }
    }

}
