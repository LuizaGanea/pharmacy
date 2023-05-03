package com.dbproject.pharmacy.persistence.interfaces;

import com.dbproject.pharmacy.model.Medicine;

import java.sql.SQLException;
import java.util.List;

public interface IMedicineRepository {
    void createTable() throws SQLException;

    void create(Medicine newMedicine);

    List<Medicine> getAll() throws SQLException;

    Medicine getById(Integer id);

    void update(Medicine medicine);

    void deleteById(Integer id);
}
