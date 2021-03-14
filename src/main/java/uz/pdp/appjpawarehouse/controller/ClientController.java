package uz.pdp.appjpawarehouse.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjpawarehouse.entity.Client;
import uz.pdp.appjpawarehouse.payload.Result;
import uz.pdp.appjpawarehouse.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {

    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/upload")
    public Result addClient(@RequestBody Client client) {

        Result result = clientService.addClient(client);
        return result;
    }

    @GetMapping("/get")
    public Page<Client> getClients(@RequestParam Integer page) {

        Page<Client> clientPage = clientService.getClients(page);
        return clientPage;
    }

    @PutMapping(value = "/edit/{clientId}")
    public Result editClient(@PathVariable Integer clientId, @RequestBody Client client) {

        Result result = clientService.editClient(clientId, client);
        return result;
    }

    @DeleteMapping(value = "/{clientId}")
    public Result deleteClient(@PathVariable Integer clientId){

        Result result = clientService.deleteClient(clientId);
        return result;
    }
}
