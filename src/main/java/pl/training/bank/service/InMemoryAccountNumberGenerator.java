package pl.training.bank.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

@Qualifier("inMemory")
@Service
public class InMemoryAccountNumberGenerator implements AccountNumberGenerator {

    private AtomicLong lastAccountNumber = new AtomicLong();

    @Override
    public String next() {
        return String.format("%026d", lastAccountNumber.incrementAndGet());
    }

}
