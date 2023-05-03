package com.dbproject.pharmacy.model;

import java.util.Date;

public class Client {

    private Integer id;
    private String firstName;
    private String lastName;
    private String cnp;
    private Date dateOfBirth;

    public Client(Integer id, String firstName, String lastName, String cnp, Date dateOfBirth){
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.cnp=cnp;
        this.dateOfBirth=dateOfBirth;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstname){
        this.firstName=firstname;
    }

    public String getLastName(){
        return lastName;
    }
    public void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getCnp(){
        return cnp;
    }
    public void setCnp(String cnp){
        this.cnp=cnp;
    }

    public Date getDateOfBirth(){
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth){
        this.dateOfBirth=dateOfBirth;
    }
}
