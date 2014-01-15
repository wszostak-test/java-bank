package pl.training.bank;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.Bank;
import pl.training.BankException;
import pl.training.bank.entity.Client;

public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bank.xml");
        Bank bank = context.getBean(Bank.class);
        try {
            bank.addClient(new Client());
        } catch (BankException e) {
            e.printStackTrace();
        }
    }

}
