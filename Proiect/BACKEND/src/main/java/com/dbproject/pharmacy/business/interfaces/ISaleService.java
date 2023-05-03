package com.dbproject.pharmacy.business.interfaces;

import com.dbproject.pharmacy.model.Sale;

import java.sql.SQLException;
import java.util.List;

public interface ISaleService {
    void create(Sale sale);

    List<Sale> getAll() throws SQLException;
    Sale getById(Integer id_s);

    void update(Sale sale);

    void deleteById(Integer id_s);
}
