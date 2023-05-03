package com.dbproject.pharmacy.business.service;

import com.dbproject.pharmacy.business.interfaces.ISupplyService;
import com.dbproject.pharmacy.model.Medicine;
import com.dbproject.pharmacy.model.Sale;
import com.dbproject.pharmacy.model.Supply;
import com.dbproject.pharmacy.persistence.interfaces.IMedicineRepository;
import com.dbproject.pharmacy.persistence.interfaces.ISaleRepository;
import com.dbproject.pharmacy.persistence.interfaces.ISupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SupplyService implements ISupplyService {
    @Autowired
    private ISupplyRepository supplyRepository;

    @Autowired
    private ISaleRepository saleRepository;

    @Autowired
    private IMedicineRepository medicineRepository;
    @Override
    public void create(Supply supply) {
        //adaugam un supply:
        //	actualizam cantitate in medicament
        Integer id_medicament=supply.getId_medicament();
        Medicine medicine=medicineRepository.getById(id_medicament);
        medicine.setQuantity(supply.getQuantity());
        medicineRepository.update(medicine);

        supplyRepository.create(supply);
    }

    @Override
    public List<Supply> getAll() throws SQLException {
        return supplyRepository.getAll();
    }

    @Override
    public Supply getById(Integer id_s) {
        return supplyRepository.getById(id_s);
    }

    @Override
    //actualizam supply:
    //	1) modificam data:
    //		toate vanzarile sa fie dupa data noua
    //	2) modificam cantitate:
    //		suma cantitatilor de la toate vanzarile sa fie mai mici sau egale cu cantitatea din supply
    //
    public void update(Supply supply) throws SQLException {
        Calendar c = Calendar.getInstance();
        c.setTime(supply.getDate_supply());
        c.add(Calendar.DATE, 1);
        supply.setDate_supply(c.getTime());
        List<Sale> sales = saleRepository.getAll();
        sales=sales.stream()
                .filter(sale -> Objects.equals(sale.getId_medicament(), supply.getId_medicament()))
                .collect(Collectors.toList());
        Integer sum=0;
        for(Sale sale : sales) {
            if(supply.getDate_supply().after(sale.getDate_sale()) || supply.getDate_supply().equals(sale.getDate_sale())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            sum+=sale.getQuantity();
        }

        if(sum>supply.getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Integer id_medicament=supply.getId_medicament();
        Medicine medicine=medicineRepository.getById(id_medicament);
        medicine.setQuantity(supply.getQuantity()-sum);
        medicineRepository.update(medicine);
        supplyRepository.update(supply);
    }

    @Override
    //delete supply -> setare cantitate 0 la medicament pt ca am doar o aprov, verificare sa nu fie nicio vanzare
    public void deleteById(Integer id_s) throws SQLException {
        Supply supply=supplyRepository.getById(id_s);
        Integer id_medicament=supply.getId_medicament();
        List<Sale> sales=saleRepository.getAll();
        for( Sale sale : sales){
            if(sale.getId_medicament()==id_medicament){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        Medicine medicine=medicineRepository.getById(id_medicament);
        medicine.setQuantity(0);
        medicineRepository.update(medicine);

        supplyRepository.deleteById(id_s);
    }
}
