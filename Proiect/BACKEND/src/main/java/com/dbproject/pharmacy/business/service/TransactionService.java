package com.dbproject.pharmacy.business.service;

import com.dbproject.pharmacy.business.interfaces.ITransactionService;
import com.dbproject.pharmacy.persistence.interfaces.ITransactionRepository;
import com.dbproject.pharmacy.persistence.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

 //   @Autowired
   // private ITransactionRepository transactionRepository;
//
//    public TransactionService(JdbcTemplate jdbcTemplate){
//        this.transactionRepository=new TransactionRepository(jdbcTemplate);
//    }
    @Override
    public void save() {
       // transactionRepository.save();
    }

    @Override
    public void restore() {
        //transactionRepository.restore();

    }
}
