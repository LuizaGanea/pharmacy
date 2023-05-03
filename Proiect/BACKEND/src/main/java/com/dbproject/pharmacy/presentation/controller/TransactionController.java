package com.dbproject.pharmacy.presentation.controller;

import com.dbproject.pharmacy.business.interfaces.ITransactionService;
import com.dbproject.pharmacy.business.service.TransactionService;
import com.dbproject.pharmacy.model.Supply;
import com.dbproject.pharmacy.persistence.interfaces.ITransactionRepository;
import com.dbproject.pharmacy.persistence.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
@CrossOrigin( origins = "http://localhost:4200", maxAge = 3600)
public class TransactionController {

  //  private ITransactionService transactionService;
    private final ITransactionRepository transactionRepository;

    TransactionController() throws SQLException {
        transactionRepository=new TransactionRepository();
    }

    @RequestMapping(value="/save", method = RequestMethod.POST)
    public ResponseEntity<Void> save() throws SQLException {
        transactionRepository.save();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value="/restore", method = RequestMethod.POST)
    public ResponseEntity<Void> restore() throws SQLException {
        transactionRepository.restore();
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
