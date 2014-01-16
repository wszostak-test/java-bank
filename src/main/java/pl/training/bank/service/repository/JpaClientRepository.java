package pl.training.bank.service.repository;

import pl.training.bank.entity.Client;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@DAO(type = DAO.Type.JPA)
public class JpaClientRepository implements ClientRepository
{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Client add(Client client) {
        entityManager.persist(client);
        entityManager.flush();
        entityManager.refresh(client);
        return client;
    }

    @Override
    public Client get(Long id) {
        return entityManager.find(Client.class, id);
    }
}
