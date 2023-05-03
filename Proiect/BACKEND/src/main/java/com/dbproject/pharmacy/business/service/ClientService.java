package com.dbproject.pharmacy.business.service;

import com.dbproject.pharmacy.business.interfaces.IClientService;
import com.dbproject.pharmacy.model.Client;
import com.dbproject.pharmacy.persistence.interfaces.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClientService implements IClientService {
    @Autowired
    private IClientRepository clientRepository;

    @Override
    public void create(Client client) {
        clientRepository.create(client);
    }

    @Override
    public List<Client> getAll() throws SQLException {
        return clientRepository.getAll();
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.getById(id);
    }

    @Override
    public void update(Client client) {
        clientRepository.update(client);
    }

    @Override
    public void deleteById(Integer id) {
        clientRepository.deleteById(id);
    }
}
