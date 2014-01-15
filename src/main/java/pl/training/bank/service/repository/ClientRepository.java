package pl.training.bank.service.repository;

import pl.training.bank.entity.Client;

public interface ClientRepository {

    Client add(Client client);

    Client get(Long id);

}
