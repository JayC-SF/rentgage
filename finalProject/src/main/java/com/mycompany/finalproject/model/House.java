/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.model;

import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author JCSF
 */
public class House extends Property{
    protected SimpleDoubleProperty size;
    /**
     * 
     * @param propertyId
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode
     * @param details
     * @param size 
     */
    public House(int propertyId, String address, String country, String state, String city, String postalCode, String details, double size){
        super(propertyId, address, country, state, city, postalCode, details);
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2."); 
        }
        this.size = new SimpleDoubleProperty(size);
        
    }
    /**
     * 
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode
     * @param details
     * @param size 
     */
    public House(String address, String country, String state, String city, String postalCode, String details, double size){
        super(address, country, state, city, postalCode, details);
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2."); 
        }
        this.size = new SimpleDoubleProperty(size);
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
     * @return 
     */
    public double getSize(){
        return size.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleDoubleProperty sizeProperty(){
        return size;
    }

    @Override
    public String getUpdateQuery() {
        String query = "update house, property "
                + "set "
                + "property.address = ?, "
                + "property.country = ?, "
                + "property.state = ?, "
                + "property.city = ?, "
                + "property.postal_code = ?, "
                + "property.details = ?, "
                + "house.size = ? "
                + "where "
                + "property.property_id = ? "
                + "and "
                + "house.property_id = property.property_id";
        return query;
    }

    @Override
    public String getInsertQuery() {
        String query = "insert into house(property_id, size) values(?,?)";
        return query;
    }
    
    /**
     * 
     * @return 
     */
    public static String getSelectQuery(){
        String query = "select * from house inner join property using(property_id)";
        return query;
    }
}
