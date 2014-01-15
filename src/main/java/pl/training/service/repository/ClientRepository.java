package pl.training.service.repository;

import pl.training.entity.Client;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 14.01.14
 * Time: 15:35
 * To change this template use File | Settings | File Templates.
 */
public interface ClientRepository  {
    Long addClient(Client client);
}
