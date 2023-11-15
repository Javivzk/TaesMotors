package com.svalero.taesmotors.service;

import com.svalero.taesmotors.domain.Client;
import com.svalero.taesmotors.exception.ClientNotFoundException;


import java.util.List;
import java.util.Map;

public interface ClientService {

    List<Client> findAll();

    Client addClient(Client client);
    void deleteClient(long clientId);
    Client findById(long clientId) throws ClientNotFoundException;

    Client actualizarClienteParcial (Long clientId, Map<String, Object> camposActualizados) throws ClientNotFoundException;
    Client modifyClient(long clientId, Client newClient) throws ClientNotFoundException;
}
