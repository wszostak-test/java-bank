package pl.training.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterBatchUpdateUtils;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class JdbcAccountNumberGenerator implements AccountNumberGenerator
{
    private static final String SQL_LAST_ACCOUNT_NUMBER =
        "select max(number) from account";

    private static final long DEFAULT_ACCOUNT_NUMBER = 1;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAccountNumberGenerator(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public String next() {
        Long lastAccountNumber = jdbcTemplate.queryForObject(
            SQL_LAST_ACCOUNT_NUMBER,
            new MapSqlParameterSource(),
            Long.class);

        if (lastAccountNumber != null) {
            ++lastAccountNumber;
        } else {
            lastAccountNumber = DEFAULT_ACCOUNT_NUMBER;
        }

        return String.format("%026d", lastAccountNumber);
    }
}
