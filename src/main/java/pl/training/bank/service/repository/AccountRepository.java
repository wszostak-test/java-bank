package pl.training.bank.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.training.bank.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByNumber(String number);

    @Query("select max(a.number) from Account a")
    String findLastNumber();

}
