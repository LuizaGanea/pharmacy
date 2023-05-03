package com.dbproject.pharmacy.persistence.interfaces;

import com.dbproject.pharmacy.model.Supply;

import java.sql.SQLException;
import java.util.List;

public interface ISupplyRepository {
    void createTable() throws SQLException;

    void create(Supply supply);

    List<Supply> getAll() throws SQLException;
    Supply getById(Integer id);

    Supply getByMedicineId(Integer id);
    void update(Supply supply);

    void deleteById(Integer id);
}
