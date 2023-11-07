package com.svalero.taesmotors.controller;

import com.svalero.taesmotors.domain.Car;
import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.exception.CarNotFoundException;
import com.svalero.taesmotors.exception.ClientNotFoundException;
import com.svalero.taesmotors.service.CarService;
import com.svalero.taesmotors.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {

    private final Logger logger = LoggerFactory.getLogger(ClientController.class);

    @Autowired
    private ClientService clientService;

    @GetMapping("/clients")
    public ResponseEntity<List<Client>> getClients() {
        List<Client> clients = clientService.findAll();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Client> getClient(@PathVariable String clientId) throws ClientNotFoundException, NumberFormatException{
        logger.info("GET CLIENT");
        Client client = clientService.findById(clientId);
        logger.info("END GET CLIENT");
        return ResponseEntity.ok(client);
    }

    @PostMapping("/clients")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = clientService.addClient(client);
        return new ResponseEntity<>(newClient,HttpStatus.CREATED);
    }

    @PutMapping("/client/{clientId}")
    public ResponseEntity<Client> modifyClient(@PathVariable String clientId, @Valid @RequestBody Client client) throws ClientNotFoundException {
        logger.error("PUT CLIENT");
        Client newClient = clientService.modifyClient(clientId,client);
        logger.error("END PUT CLIENT");
        return ResponseEntity.status(HttpStatus.OK).body(newClient);
    }

    @DeleteMapping(value = "/client/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable String clientId) {
        clientService.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }
}
