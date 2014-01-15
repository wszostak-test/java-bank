package pl.training.bank.service.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import pl.training.bank.entity.Client;

import java.util.HashMap;
import java.util.Map;

@Qualifier("inMemory")
@Repository
public class InMemoryClientRepository implements ClientRepository{

    private Map<Long, Client> clients = new HashMap<>();
    private long lastId;

    @Override
    public synchronized Client add(Client client) {
        client.setId(++lastId);
        clients.put(lastId, client);
        return client;
    }

    @Override
    public Client get(Long id) {
        return clients.get(id);
    }

}
