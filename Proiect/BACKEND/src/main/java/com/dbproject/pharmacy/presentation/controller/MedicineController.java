package com.dbproject.pharmacy.presentation.controller;

import com.dbproject.pharmacy.business.interfaces.IMedicineService;
import com.dbproject.pharmacy.model.Client;
import com.dbproject.pharmacy.model.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200", maxAge = 3600)
public class MedicineController {
    @Autowired
    private IMedicineService medicineService;



    @RequestMapping(value="/medicines", method = RequestMethod.POST)
    public ResponseEntity<String> createMedicine(@RequestBody Medicine newMedicine){
        medicineService.create(newMedicine);
        return new ResponseEntity<>("Medicine created!", HttpStatus.CREATED);
    }

    @RequestMapping(value="/medicines", method=RequestMethod.GET)
    public ResponseEntity<List<Medicine>> getAllMedicines() throws SQLException {
        List<Medicine> listOfMedicines=medicineService.getAll();
        return new ResponseEntity<>(listOfMedicines, HttpStatus.OK);
    }

    @RequestMapping(value="/medicines/{id}", method=RequestMethod.GET)
    public ResponseEntity<Medicine> getMedicineById(@PathVariable String id){
        Medicine medicine=medicineService.getById(Integer.valueOf(id));

        if(medicine!=null){
            return new ResponseEntity<>(medicine, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/medicines", method=RequestMethod.PUT)
    public ResponseEntity<Medicine> updateClient(@RequestBody Medicine medicine){
        Medicine medicineToUpdate=medicineService.getById(medicine.getId());
        if(medicineToUpdate!=null) {
            medicineService.update(medicine);
            return new ResponseEntity<>(medicine, HttpStatus.OK);
        } else{
            medicineService.create(medicine);
            return new ResponseEntity<>(medicine, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value="/medicines/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteMedicineById(@PathVariable String id){
        if(medicineService.getById(Integer.valueOf(id))!=null){
            medicineService.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>("Medicine Deleted!",HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(String.format("Medicine with id=%s not found!",id), HttpStatus.NOT_FOUND);
        }
    }
}
