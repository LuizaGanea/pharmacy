package com.dbproject.pharmacy.business.service;

import com.dbproject.pharmacy.business.interfaces.IMedicineService;
import com.dbproject.pharmacy.model.Medicine;
import com.dbproject.pharmacy.persistence.interfaces.IMedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MedicineService implements IMedicineService {
    @Autowired
    private IMedicineRepository medicineRepository;

    @Override
    public void create(Medicine newMedicine) {
        medicineRepository.create(newMedicine);
    }

    @Override
    public List<Medicine> getAll() throws SQLException {
        return medicineRepository.getAll();
    }

    @Override
    public Medicine getById(Integer id) {
        return medicineRepository.getById(id);
    }

    @Override
    public void update(Medicine medicine) {
        medicineRepository.update(medicine);
    }

    @Override
    public void deleteById(Integer id) {
        medicineRepository.deleteById(id);
    }
}
