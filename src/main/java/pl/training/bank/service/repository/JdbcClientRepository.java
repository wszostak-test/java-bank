package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Client;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Qualifier("jdbc")
@Repository
public class JdbcClientRepository implements ClientRepository
{
    private static final String SQL_INSERT_CLIENT =
        "insert into client (id, firstName, lastName) values (null, :firstName, :lastName)";
    private static final String SQL_SELECT_CLIENT =
        "select * from client c where c.id = :id";
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcClientRepository(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public Client add(Client client) {
        KeyHolder key = new GeneratedKeyHolder();

        jdbcTemplate.update(
            SQL_INSERT_CLIENT,
            new BeanPropertySqlParameterSource(client),
            key
        );

        client.setId(key.getKey().longValue());

        return client;
    }

    @Override
    public Client get(Long id) {
        return jdbcTemplate.query(
            SQL_SELECT_CLIENT,
            new MapSqlParameterSource("id", id),
            new ClientMapper()
        );
    }

    private class ClientMapper implements ResultSetExtractor<Client> {
        @Override
        public Client extractData(ResultSet resultSet) throws SQLException, DataAccessException {
            Client client = null;

            if (resultSet.next()) {
                client = new Client();
                client.setId(resultSet.getLong("id"));
                client.setFirstName(resultSet.getString("firstName"));
                client.setLastName(resultSet.getString("lastName"));
            }

            return client;
        }
    }
}