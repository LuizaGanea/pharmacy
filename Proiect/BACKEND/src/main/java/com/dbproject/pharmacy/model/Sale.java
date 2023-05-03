package com.dbproject.pharmacy.model;

import java.time.LocalDate;
import java.util.Date;

public class Sale {

    private Integer id;
    private Date date_sale;
    private Integer quantity;
    private Integer id_client;
    private Integer id_medicament;

    public Sale(Integer id, Date date_sale, Integer quantity, Integer id_client, Integer id_medicament){
        this.id=id;
        this.date_sale=date_sale;
        this.quantity=quantity;
        this.id_client=id_client;
        this.id_medicament=id_medicament;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public Date getDate_sale(){
        return date_sale;
    }
    public void setDate_sale(Date date_sale){
        this.date_sale=date_sale;
    }

    public Integer getQuantity(){
        return quantity;
    }
    public void setQuantity(Integer quantity){
        this.quantity=quantity;
    }

    public Integer getId_client(){
        return id_client;
    }
    public void setId_client(Integer id_client){
        this.id_client=id_client;
    }

    public Integer getId_medicament(){
        return id_medicament;
    }
    public void setId_medicament(Integer id_medicament){
        this.id_medicament=id_medicament;
    }
}
