/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JCSF
 */
public class Condo extends Property{
    protected SimpleStringProperty appartmentNumber;
    protected SimpleDoubleProperty size;
    protected SimpleDoubleProperty condoFee;
    /**
     * 
     * @param propertyId
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode
     * @param details
     * @param appartmentNumber
     * @param size
     * @param condoFee 
     */
    public Condo(int propertyId, String address, String country, String state, String city, String postalCode, String details,String appartmentNumber,double size, double condoFee){
        super(propertyId, address, country, state, city, postalCode, details);
        //Do some validation checks.
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2."); 
        }
        if(condoFee < 0 ){
            throw new IllegalArgumentException("Condo fee can not be negative");
        }
        this.appartmentNumber = new SimpleStringProperty(appartmentNumber);
        this.size             = new SimpleDoubleProperty(size);
        this.condoFee         = new SimpleDoubleProperty(condoFee);
    }
    
    /**
     * 
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode
     * @param details
     * @param appartmentNumber
     * @param size
     * @param condoFee 
     */
    public Condo(String address, String country, String state, String city, String postalCode, String details,String appartmentNumber,double size, double condoFee){
        super(address, country, state, city, postalCode, details);
        
        //Do some validation checks
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2."); 
        }
        if(condoFee < 0 ){
            throw new IllegalArgumentException("Condo fee can not be negative");
        }
        this.appartmentNumber = new SimpleStringProperty(appartmentNumber);
        this.size             = new SimpleDoubleProperty(size);
        this.condoFee         = new SimpleDoubleProperty(condoFee);
    }
    
    //GETTERS
    /**
     * 
     * @return 
     */
    public String getAppartmentNumber(){
        return appartmentNumber.get();
    }
    /**
     * 
     * @return 
     */
    public double getSize(){
        return size.get();
    }
    /**
     * 
     * @return 
     */
    public double getCondoFee(){
        return condoFee.get();
    }
    /**
     * 
     * @param appartmentNumber 
     */
    //SETTERS
    public void setAppartmentNumber(String appartmentNumber){
        this.appartmentNumber.set(appartmentNumber);
    }
    /**
     * 
     * @param size 
     */
    public void setSize(double size){
        //Do some validation checks
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2."); 
        }
        this.size.set(size);
    }
    /**
     * 
     * @param condoFee 
     */
    public void setCondoFee(double condoFee){
        if(condoFee < 0){
            throw new IllegalArgumentException("Cannot set the condoFee smaller than 0");
        }
        this.condoFee.set(condoFee);
    }
    /**
     * 
     * @return 
     */
    //PROPERTIES
    public SimpleStringProperty appartmentNumberProperty(){
        return appartmentNumber;
    }
    /**
     * 
     * @return 
     */
    public SimpleDoubleProperty sizeProperty(){
        return size;
    }
    /**
     * 
     * @return 
     */
    public SimpleDoubleProperty condoFeeProperty(){
        return condoFee;
    }

    @Override
    public String getUpdateQuery() {
        String query = "update condo, property "
                + "set "
                + "property.address = ?, "
                + "property.country = ?, "
                + "property.state = ?, "
                + "property.city = ?, "
                + "property.postal_code = ?, "
                + "property.details = ?, "
                + "condo.appartment_number = ?, "
                + "condo.size = ?, "
                + "condo.condo_fee = ? "
                + "where "
                + "property.property_id = ? "
                + "and "
                + "condo.property_id = property.property_id";
        return query;
    }

    @Override
    public String getInsertQuery() {
        String query = "insert into condo(property_id, appartment_number, size, condo_fee) values(?,?,?,?)";
        return query;
    }
    
    /**
     * 
     * @return 
     */
    public static String getSelectQuery(){
        String query = "select * from condo inner join property using(property_id)";
        return query;
    }
}
