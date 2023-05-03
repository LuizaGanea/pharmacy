package com.dbproject.pharmacy.business.interfaces;

import com.dbproject.pharmacy.model.Client;

import java.sql.SQLException;
import java.util.List;

public interface IClientService {
    void create(Client client);

    List<Client> getAll() throws SQLException;
    Client getById(Integer id);

    void update(Client client);

    void deleteById(Integer id);
}
