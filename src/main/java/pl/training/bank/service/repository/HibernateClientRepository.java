package pl.training.bank.service.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.training.bank.entity.Client;

@DAO(type = DAO.Type.HIBERNATE)
public class HibernateClientRepository implements ClientRepository
{
    private SessionFactory sessionFactory;

    @Autowired
    public HibernateClientRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Client add(Client client) {
        Session session = sessionFactory.getCurrentSession();

        session.save(client);
        session.flush();
        session.refresh(client);

        return client;
    }

    @Override
    public Client get(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (Client) session.get(Client.class, id);
    }
}
