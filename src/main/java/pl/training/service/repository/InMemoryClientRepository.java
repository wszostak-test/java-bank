package pl.training.service.repository;

import pl.training.entity.Client;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14.01.14
 * Time: 15:34
 * To change this template use File | Settings | File Templates.
 */
public class InMemoryClientRepository implements ClientRepository {
    private Map<Long, Client> clients = new HashMap<>();
    private long lastId;

    @Override
    public synchronized Long addClient(Client client) {
        client.setId(++lastId);
        clients.put(lastId, client);
        return lastId;
    }
}
