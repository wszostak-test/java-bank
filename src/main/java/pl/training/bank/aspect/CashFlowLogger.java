package pl.training.bank.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.NumberFormat;

@Component
@Aspect
public class CashFlowLogger
{
    @Pointcut("execution(* pl.training.bank.Bank.payInCashToAccount(..)) && args(accountNumber, amount)")
    public void payIn(String accountNumber, BigDecimal amount) {

    }

    @Pointcut("execution(* pl.training.bank.Bank.payOutCashToAccount(..)) && args(accountNumber, amount)")
    public void payOut(String accountNumber, BigDecimal amount) {

    }

    @Pointcut("execution(* pl.training.bank.Bank.transferCash(..)) && args(fromAccountNumber, toAccountNumber, amount)")
    public void transferCash(String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
    }

    private void startLogEntry(String operationName) {
        System.out.println("=========================================");
        System.out.println("Rozpoczęto operację: " + operationName);
    }

    private String formatCurrency(BigDecimal amount) {
        return NumberFormat.getCurrencyInstance().format(amount);
    }

    @Before("payIn(accountNumber, amount)")
    public void beforePayInOperation(JoinPoint jp, String accountNumber, BigDecimal amount) {
        startLogEntry(jp.getSignature().getName());
        System.out.println(formatCurrency(amount) + " -> " + accountNumber);
    }

    @Before("payOut(accountNumber, amount)")
    public void beforePayOutOperation(JoinPoint jp, String accountNumber, BigDecimal amount) {
        startLogEntry(jp.getSignature().getName());
        System.out.println(accountNumber + " -> " + formatCurrency(amount));
    }

    @Before("transferCash(fromAccountNumber, toAccountNumber, amount)")
    public void beforePayOutOperation(JoinPoint jp, String fromAccountNumber, String toAccountNumber, BigDecimal amount) {
        startLogEntry(jp.getSignature().getName());
        System.out.println(fromAccountNumber
                + " -> " + formatCurrency(amount)+ " -> "
                + toAccountNumber);
    }
}
