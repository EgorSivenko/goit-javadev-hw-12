package org.example.service;

import org.example.dao.impl.ClientDAO;
import org.example.entity.Client;

import java.util.List;
import java.util.Objects;

public class ClientService {

    private final ClientDAO clientDAO = new ClientDAO();

    public Client getById(Long id) {
        return clientDAO.findById(id);
    }

    public List<Client> getAll() {
        return clientDAO.findAll();
    }

    public void add(Client client) {
        Client clientByName = clientDAO.findByName(client.getName());

        if (clientByName != null) {
            throw new IllegalStateException("Client with name '" + client.getName() + "' already exists");
        }
        clientDAO.save(client);
    }

    public void update(Client client) {
        Client clientById = clientDAO.findById(client.getId());

        if (clientById != null && !Objects.equals(clientById.getName(), client.getName())) {
            Client clientByName = clientDAO.findByName(client.getName());

            if (clientByName != null) {
                throw new IllegalStateException("Client with name '" + client.getName() + "' already exists");
            }
            clientDAO.update(client);
        }
    }

    public void delete(Client client) {
        clientDAO.delete(client);
    }

    public Client deleteById(Long id) {
        return clientDAO.deleteById(id);
    }
}
