package pl.training;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.training.service.BankException;

import java.math.BigDecimal;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14.01.14
 * Time: 15:07
 * To change this template use File | Settings | File Templates.
 */
public class App {
    public static void main(String[] args) throws BankException {
        ApplicationContext context = new ClassPathXmlApplicationContext("bank.xml");

        Bank bank = context.getBean(Bank.class);

        bank.payInCache("sdfdsfdsf", new BigDecimal("12.23"));
    }
}
