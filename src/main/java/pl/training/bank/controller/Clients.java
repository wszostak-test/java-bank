package pl.training.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pl.training.bank.Bank;
import pl.training.bank.BankException;
import pl.training.bank.entity.Address;
import pl.training.bank.entity.Client;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class Clients {

    private Bank bank;

    @Autowired
    public Clients(Bank bank) {
        this.bank = bank;
    }

    @RequestMapping(value = "addClientForm", method = RequestMethod.GET)
    public ModelAndView showAddForm() {
        Client client = new Client();
        List<Address> addresses = new ArrayList<>();
        addresses.add(new Address());
        client.setAddresses(addresses);
        client.setFirstName("Bronek");
        ModelAndView modelAndView = new ModelAndView("addClientForm");
        modelAndView.addObject(client);
        return modelAndView;
    }

    @RequestMapping(value = "addClientForm", method = RequestMethod.POST)
    public String saveClient(@Valid Client client, BindingResult bindingResult) throws BankException {
        if (bindingResult.hasErrors()) {
            return "addClientForm";
        }
        bank.addClient(client);
        return "home";
    }

    @RequestMapping(value = "showAllClients", method = RequestMethod.GET)
    public ModelAndView showAllClients(){
        List<Client> clients = bank.getClients();
        ModelAndView modelAndView = new ModelAndView("allClients");
        modelAndView.addObject(clients);
        return modelAndView;
    }

}
