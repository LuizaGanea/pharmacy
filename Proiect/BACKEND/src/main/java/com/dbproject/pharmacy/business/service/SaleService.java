package com.dbproject.pharmacy.business.service;

import com.dbproject.pharmacy.business.interfaces.ISaleService;
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
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SaleService implements ISaleService {
    @Autowired
    private ISaleRepository saleRepository;
    @Autowired
    private ISupplyRepository supplyRepository;

    @Autowired
    private IMedicineRepository medicineRepository;



    @Override
    public void create(Sale sale) {
        Calendar c = Calendar.getInstance();
        c.setTime(sale.getDate_sale());
        c.add(Calendar.DATE, 1);
        sale.setDate_sale(c.getTime());
        //adaugame un sale:
        //	verificam ca data este dupa supply
        //	verificam :cantitatea e mai mica sau egala cu cea din medicament
        Integer id_medicament=sale.getId_medicament();
        Supply supply=supplyRepository.getByMedicineId(id_medicament);

        if(supply == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Date data_supply=supply.getDate_supply();

        Medicine medicine=medicineRepository.getById(id_medicament);
        Integer cantitate_medicament=medicine.getQuantity();
        System.out.println(sale.getDate_sale());

        if(data_supply.before(sale.getDate_sale()))
        {
            if(cantitate_medicament<=sale.getQuantity()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
            saleRepository.create(sale);
            medicine.setQuantity(medicine.getQuantity()-sale.getQuantity());
            medicineRepository.update(medicine);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<Sale> getAll() throws SQLException {
        return saleRepository.getAll();
    }

    @Override
    public Sale getById(Integer id_s) {
        return saleRepository.getById(id_s);
    }

    @Override
    //actualizam sale:
    //	1) modificam data:
    //		sa fie dupa data supply
    //	2) modificam cantitate:
    //		diferenta de cantitate sa fie mai mica sau egala cu cantitatatea de medicament
    public void update(Sale sale) {
        Calendar c = Calendar.getInstance();
        c.setTime(sale.getDate_sale());
        c.add(Calendar.DATE, 1);
        sale.setDate_sale(c.getTime());
        Integer id_medicament=sale.getId_medicament();
        Supply supply=supplyRepository.getByMedicineId(id_medicament);
        Medicine medicine=medicineRepository.getById(id_medicament);
        Integer cantitate_medicament=medicine.getQuantity();

        if(sale.getDate_sale().before(supply.getDate_supply()) || sale.getDate_sale().equals(supply.getDate_supply())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Sale old_sale=saleRepository.getById(sale.getId());
        Integer diff=sale.getQuantity()-old_sale.getQuantity();
        if(diff>medicine.getQuantity()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        System.out.println(old_sale.getQuantity());
        System.out.println(supply.getQuantity());
        System.out.println(diff);
        System.out.println(medicine.getQuantity());
        System.out.println("**********");
        medicine.setQuantity(medicine.getQuantity()-diff);
        medicineRepository.update(medicine);
        saleRepository.update(sale);
    }

    @Override
    //delete sale -> creste cantitate medicament cu cantitate vanzare stearsas
    public void deleteById(Integer id_s) {
        Sale sale=saleRepository.getById(id_s);
        Integer id_medicament=sale.getId_medicament();
        Medicine medicine=medicineRepository.getById(id_medicament);
        medicine.setQuantity(medicine.getQuantity()+ sale.getQuantity());
        medicineRepository.update(medicine);

        saleRepository.deleteById(id_s);
    }
}
