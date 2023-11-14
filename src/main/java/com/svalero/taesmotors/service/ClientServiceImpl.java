package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.domain.Employee;
import com.svalero.taesmotors.exception.ClientNotFoundException;
import com.svalero.taesmotors.exception.EmployeeNotFoundException;
import com.svalero.taesmotors.repository.ClientRepository;
import com.svalero.taesmotors.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    private final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(long clientId) {
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client findById(long clientId) throws ClientNotFoundException {
        logger.info("Client id: " + clientId);
        return clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
    }

    @Override
    public Client modifyClient(long clientId, Client newClient) throws ClientNotFoundException {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(ClientNotFoundException::new);
        logger.info("Client to modify: " + existingClient);
        existingClient.setName(newClient.getName());
        existingClient.setLastName(newClient.getLastName());
        existingClient.setPhone(newClient.getPhone());
        existingClient.setAddress(newClient.getAddress());
        existingClient.setPostalCode(newClient.getPostalCode());
        existingClient.setCity(newClient.getCity());


        logger.info("Employee modified: " + newClient);
        return clientRepository.save(existingClient);
    }
}
