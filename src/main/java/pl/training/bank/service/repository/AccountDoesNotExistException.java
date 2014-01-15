package pl.training.bank.service.repository;

import pl.training.bank.BankException;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 15.01.14
 * Time: 10:58
 * To change this template use File | Settings | File Templates.
 */
public class AccountDoesNotExistException extends BankException {
    public AccountDoesNotExistException() {
    }

    public AccountDoesNotExistException(String message) {
        super(message);
    }

    public AccountDoesNotExistException(Throwable cause) {
        super(cause);
    }
}
