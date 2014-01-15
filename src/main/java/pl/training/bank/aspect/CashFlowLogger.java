package pl.training.bank.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import pl.training.bank.BankException;

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

    @Pointcut("execution (* pl.training.bank.Bank.*Cash*(..))")
    public void cashFlowOperation() {
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

    @AfterReturning("cashFlowOperation()")
    public void afterSuccess() {
        System.out.println("Operacja zakończona pomyślnie");
        System.out.println("=========================================");
    }

    @AfterThrowing(value="cashFlowOperation()", throwing = "bankException")
    public void afterFailure(BankException bankException) {
        System.out.println("Wyjątek: " + bankException.getClass().getSimpleName());
        System.out.println("=========================================");
    }
}
