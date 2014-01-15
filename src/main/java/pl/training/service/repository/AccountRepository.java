package pl.training.service.repository;

import pl.training.entity.Account;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14.01.14
 * Time: 15:40
 * To change this template use File | Settings | File Templates.
 */
public interface AccountRepository {
    Long addAccount(Account account);
}
