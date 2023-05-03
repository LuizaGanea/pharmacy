package com.dbproject.pharmacy.business.interfaces;

import com.dbproject.pharmacy.model.Supply;

import java.sql.SQLException;
import java.util.List;

public interface ISupplyService {
    void create(Supply supply);

    List<Supply> getAll() throws SQLException;
    Supply getById(Integer id_s);

    void update(Supply supply) throws SQLException;

    void deleteById(Integer id_s) throws SQLException;
}
