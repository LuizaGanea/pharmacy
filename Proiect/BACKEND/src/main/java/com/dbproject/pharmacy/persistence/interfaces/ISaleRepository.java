package com.dbproject.pharmacy.persistence.interfaces;

import com.dbproject.pharmacy.model.Sale;

import java.sql.SQLException;
import java.util.List;

public interface ISaleRepository {
    void createTable() throws SQLException;

    void create(Sale sale);

    List<Sale> getAll() throws SQLException;
    Sale getById(Integer id);

    void update(Sale sale);

    void deleteById(Integer id);
}
