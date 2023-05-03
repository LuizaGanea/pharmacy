package com.dbproject.pharmacy.model;

import java.util.Date;

public class Medicine {
    private Integer id;
    private String name;
    private String description;
    private Integer quantity;
    private Boolean requiresPrescription;
    private Integer minAge;

    public Medicine(Integer id, String name, String description,
                    Integer quantity, Boolean requiresPrescription, Integer minAge){
        this.id=id;
        this.name=name;
        this.description=description;
        this.quantity=quantity;
        this.requiresPrescription=requiresPrescription;
        this.minAge=minAge;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name=name; }

    public String getDescription() {return description;}
    public void setDescription(String description){this.description=description;}

    public Integer getQuantity(){return quantity;}
    public void setQuantity(Integer quantity){this.quantity=quantity;}

    public Integer getMinAge(){return minAge;}
    public void setMinAge(Integer minAge){this.minAge=minAge;}

    public Boolean getRequiresPrescription(){return requiresPrescription;}
    public void setRequiresPrescription(Boolean requiresPrescription){this.requiresPrescription=requiresPrescription;}


}
