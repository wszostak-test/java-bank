package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Account;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class JdbcAccountRepository implements AccountRepository
{
    private static final String SQL_ADD_ACCOUNT =
        "insert into account (number, balance) values ()";
    private static final String SQL_GET_ACCOUNT =
         "select id, number, balance from account where id = ";
    private static final String SQL_GET_ACCOUNT_BY_NUMBER =
         "select id, number, balance from account where number = ";

    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Account add(Account account) {
        KeyHolder key = new GeneratedKeyHolder();
        jdbcTemplate.update(
            SQL_ADD_ACCOUNT,
            new BeanPropertySqlParameterSource(account),
            key
        );

        account.setId(key.getKey().longValue());

        return account;
    }

    @Override
    public Account get(Long id) {
        return null;
    }

    @Override
    public Account getByAccountNumber(String accountNumber) throws AccountDoesNotExistException {
        return null;
    }

    @Override
    public void update(Account account) throws AccountDoesNotExistException {

    }
}
