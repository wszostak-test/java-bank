package pl.training.bank.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pl.training.bank.Bank;
import pl.training.bank.entity.Client;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("bank")
@RestService
public class BankRest {

    private Bank bank;

    @Autowired
    public BankRest(Bank bank) {
        this.bank = bank;
    }

    @GET
    @Path("clients")
    public List<Client> getClients() {
        return bank.getClients();
    }

    @Consumes(MediaType.APPLICATION_XML)
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @GET
    @Path("clients/{id:\\d+}")
    public Client getClientById(@PathParam("id") Long id) {
        Client client = bank.getClientById(id);
        if (client == null) {
            //throw new WebApplicationException(Response.Status.NOT_FOUND);
            throw new IllegalArgumentException();
        }
        return client;
    }

}
