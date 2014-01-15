package pl.training.service;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14.01.14
 * Time: 15:24
 * To change this template use File | Settings | File Templates.
 */
public class BankException extends Exception {
    public BankException() {
    }

    public BankException(String message) {
        super(message);
    }

    public BankException(Throwable cause) {
        super(cause);
    }
}
