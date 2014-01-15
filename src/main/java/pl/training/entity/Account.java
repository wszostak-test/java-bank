package pl.training.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 13.01.14
 * Time: 13:52
 * To change this template use File | Settings | File Templates.
 */
public class Account {
    private Long id;

    private String number;
    private BigDecimal balance;

    private List<Client> clients;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (balance != null ? !balance.equals(account.balance) : account.balance != null) return false;
        if (clients != null ? !clients.equals(account.clients) : account.clients != null) return false;
        if (number != null ? !number.equals(account.number) : account.number != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = number != null ? number.hashCode() : 0;
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (clients != null ? clients.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "number='" + number + '\'' +
                ", balance=" + balance +
                ", clients=" + clients +
                '}';
    }

    public boolean addClient(Client client) {
        boolean result = false;

        if (!clients.contains(client)) {
            result = clients.add(client);
            client.getAccounts().add(this);
        }

        return result;
    }

    public boolean payIn(BigDecimal amount) {
        balance = balance.add(amount);
        return true;
    }

    public boolean payOut(BigDecimal amount) {
        boolean result = false;

        if (amount.compareTo(balance) <= 0) {
            balance = balance.subtract(amount);
            result = true;
        }

        return result;
    }
}
