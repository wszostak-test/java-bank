package pl.training.bank.service.repository;

import pl.training.bank.entity.Client;

import java.util.HashMap;
import java.util.Map;

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
