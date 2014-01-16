package pl.training.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.bank.entity.Account;
import pl.training.bank.entity.Client;
import pl.training.bank.service.JdbcAccountNumberGenerator;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Date;

public class App
{
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "bank.xml",
                "bank-repository.xml");

        Bank bank = context.getBean(Bank.class);
        Date date = new Date();

        try {
            Client c1 = new Client();
            c1.setFirstName("Jan");
            c1.setLastName("Kolwalski (" + date.getTime() + ")");

            Client c2 = new Client();
            c2.setFirstName("Jacek");
            c2.setLastName("Nowak (" + date.getTime() + ")");

            Account a1 = new Account();
            Account a2 = new Account();

            bank.addClient(c1);
            bank.addClient(c2);
            bank.addAccount(a1);
            bank.addAccount(a2);
            bank.assignClientToAccount(a1.getId(), c1.getId());
            bank.assignClientToAccount(a2.getId(), c2.getId());
            bank.payInCashToAccount(a1.getNumber(), new BigDecimal(1000));
            bank.payInCashToAccount(a2.getNumber(), new BigDecimal(2000));
            bank.transferCash(a1.getNumber(), a2.getNumber(), new BigDecimal(123));
        } catch (BankException e) {
            // e.printStackTrace();
        }
    }

}
