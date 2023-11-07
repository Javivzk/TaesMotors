package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.exception.ClientNotFoundException;


import java.util.List;

public interface ClientService {

    List<Client> findAll();

    Client addClient(Client client);
    void deleteClient(String clientId);
    Client findById(String clientId) throws ClientNotFoundException;

    Client modifyClient(String clientId, Client newClient) throws ClientNotFoundException;
}
