package com.dbproject.pharmacy.presentation.controller;

import com.dbproject.pharmacy.business.interfaces.ISupplyService;
import com.dbproject.pharmacy.model.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200", maxAge = 3600)
public class SupplyController {
    @Autowired
    private ISupplyService supplyService;

    @RequestMapping(value="/supplies", method = RequestMethod.POST)
    public ResponseEntity<String> createSupply(@RequestBody Supply supply){
        supplyService.create(supply);
        return new ResponseEntity<>("Supply created!", HttpStatus.CREATED);
    }

    @RequestMapping(value="/supplies", method=RequestMethod.GET)
    public ResponseEntity<List<Supply>> getAllSupply() throws SQLException {
        List<Supply> listOfSupplies=supplyService.getAll();
        return new ResponseEntity<>(listOfSupplies, HttpStatus.OK);
    }

    @RequestMapping(value="/supplies/{id}", method=RequestMethod.GET)
    public ResponseEntity<Supply> getSupplyById(@PathVariable String id){
        Supply supply=supplyService.getById(Integer.valueOf(id));

        if(supply!=null){
            return new ResponseEntity<>(supply, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/supplies", method=RequestMethod.PUT)
    public ResponseEntity<Supply> updateClient(@RequestBody Supply supply) throws SQLException {
        Supply supplyToUpdate=supplyService.getById(supply.getId());
        if(supplyToUpdate!=null) {
            supplyService.update(supply);
            return new ResponseEntity<>(supply, HttpStatus.OK);
        } else{
            supplyService.create(supply);
            return new ResponseEntity<>(supply, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value="/supplies/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteSupplyById(@PathVariable String id) throws SQLException {
        if(supplyService.getById(Integer.valueOf(id))!=null){
            supplyService.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>("Supply Deleted!",HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(String.format("Supply with id=%s not found!",id), HttpStatus.NOT_FOUND);
        }
    }
}
