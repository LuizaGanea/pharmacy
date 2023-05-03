package com.dbproject.pharmacy.presentation.controller;

import com.dbproject.pharmacy.business.interfaces.ISaleService;
import com.dbproject.pharmacy.business.interfaces.ISupplyService;
import com.dbproject.pharmacy.model.Sale;
import com.dbproject.pharmacy.model.Supply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200", maxAge = 3600)
public class SaleController {
    @Autowired
    private ISaleService saleService;

    @RequestMapping(value="/sales", method = RequestMethod.POST)
    public ResponseEntity<String> createSale(@RequestBody Sale sale){
        saleService.create(sale);
        return new ResponseEntity<>("Sale created!", HttpStatus.CREATED);
    }

    @RequestMapping(value="/sales", method=RequestMethod.GET)
    public ResponseEntity<List<Sale>> getAllSale() throws SQLException {
        List<Sale> listOfSupplies=saleService.getAll();
        return new ResponseEntity<>(listOfSupplies, HttpStatus.OK);
    }

    @RequestMapping(value="/sales/{id}", method=RequestMethod.GET)
    public ResponseEntity<Sale> getSaleById(@PathVariable String id){
        Sale sale=saleService.getById(Integer.valueOf(id));

        if(sale!=null){
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/sales", method=RequestMethod.PUT)
    public ResponseEntity<Sale> updateClient(@RequestBody Sale sale){
        Sale saleToUpdate=saleService.getById(sale.getId());
        if(saleToUpdate!=null) {
            saleService.update(sale);
            return new ResponseEntity<>(sale, HttpStatus.OK);
        } else{
            saleService.create(sale);
            return new ResponseEntity<>(sale, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value="/sales/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteSupplyById(@PathVariable String id){
        if(saleService.getById(Integer.valueOf(id))!=null){
            saleService.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>("Sale Deleted!",HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(String.format("Sale with id=%s not found!",id), HttpStatus.NOT_FOUND);
        }
    }
}
