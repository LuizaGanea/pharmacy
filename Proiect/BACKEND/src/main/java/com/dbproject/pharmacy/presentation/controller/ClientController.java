package com.dbproject.pharmacy.presentation.controller;

import com.dbproject.pharmacy.business.interfaces.IClientService;
import com.dbproject.pharmacy.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@CrossOrigin( origins = "http://localhost:4200", maxAge = 3600)
public class ClientController {
    @Autowired
    private IClientService clientService;

    @RequestMapping(value="/clients", method = RequestMethod.POST)
    public ResponseEntity<String> createClient(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>("Client created!", HttpStatus.CREATED);
    }

    @RequestMapping(value="/clients", method=RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients() throws SQLException {
        List<Client> listOfClients=clientService.getAll();
        return new ResponseEntity<>(listOfClients, HttpStatus.OK);
    }

    @RequestMapping(value="/clients/{id}", method=RequestMethod.GET)
    public ResponseEntity<Client> getClientById(@PathVariable String id){
        Client client=clientService.getById(Integer.valueOf(id));

        if(client!=null){
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value="/clients", method=RequestMethod.PUT)
    public ResponseEntity<Client> updateClient(@RequestBody Client client){
        Client clientToUpdate=clientService.getById(client.getId());
        if(clientToUpdate!=null) {
            clientService.update(client);
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else{
            clientService.create(client);
            return new ResponseEntity<>(client, HttpStatus.CREATED);
        }
    }

    @RequestMapping(value="/clients/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<String> deleteClientById(@PathVariable String id){
        if(clientService.getById(Integer.valueOf(id))!=null){
            clientService.deleteById(Integer.valueOf(id));
            return new ResponseEntity<>("Client Deleted!",HttpStatus.OK);
        } else
        {
            return new ResponseEntity<>(String.format("Client with id=%s not found!",id), HttpStatus.NOT_FOUND);
        }
    }
}
