package pl.training.bank.service.repository;

import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Client;

@Repository
public interface ClientRepository {

    Client add(Client client);

    Client get(Long id);

}
