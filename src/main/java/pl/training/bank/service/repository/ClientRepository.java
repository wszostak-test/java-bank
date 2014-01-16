package pl.training.bank.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.training.bank.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
