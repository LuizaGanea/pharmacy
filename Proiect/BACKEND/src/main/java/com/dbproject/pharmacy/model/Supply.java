package com.dbproject.pharmacy.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

public class Supply {

    private Integer id;

    private Date date_supply;
    private Integer quantity;
    private Integer id_medicament;

    public Supply(Integer id, Date date_supply, Integer quantity,  Integer id_medicament){
        this.id=id;
        this.date_supply=date_supply;
        this.quantity=quantity;
        this.id_medicament=id_medicament;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public Date getDate_supply(){
        return date_supply;
    }
    public void setDate_supply(Date date_supply){
        this.date_supply=date_supply;
    }

    public Integer getQuantity(){
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }

    public Integer getId_medicament(){
        return id_medicament;
    }
    public void setId_medicament(Integer id_medicament){
        this.id_medicament=id_medicament;
    }
}
