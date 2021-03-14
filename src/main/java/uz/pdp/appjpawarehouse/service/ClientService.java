package uz.pdp.appjpawarehouse.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import uz.pdp.appjpawarehouse.entity.Client;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.repositort.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {

    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Result addClient(@RequestBody Client client) {

        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result(client.getPhoneNumber() + " phone number already exist, enter other", false);

        Client newClient = new Client();

        newClient.setName(client.getName());
        newClient.setPhoneNumber(client.getPhoneNumber());

        Client save = clientRepository.save(newClient);
        return new Result("New Client successfully saved", true, save.getId());
    }

    public Page<Client> getClients(Integer page) {

        Pageable pageable = PageRequest.of(page, 15);
        Page<Client> clientPage = clientRepository.findAll(pageable);
        return clientPage;
    }

    public Result editClient(Integer clientId, Client client) {

        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent())
            return new Result("Invalid Client Id", false);

        boolean existsByPhoneNumber = clientRepository.existsByPhoneNumber(client.getPhoneNumber());
        if (existsByPhoneNumber)
            return new Result(client.getPhoneNumber() + " phone number already exist, enter other", false);

        Client editedClient = optionalClient.get();

        editedClient.setName(client.getName());
        editedClient.setPhoneNumber(client.getPhoneNumber());
        clientRepository.save(editedClient);

        return new Result("Client successfully edited", true);
    }

    public Result deleteClient(Integer clientId) {

        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (!optionalClient.isPresent())
            return new Result("Client not found, Invalid Client ID", false);

        clientRepository.deleteById(clientId);
        return new Result("Client deleted", true);
    }
}

