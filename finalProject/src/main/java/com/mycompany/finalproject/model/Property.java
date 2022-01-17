/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JCSF
 */
public abstract class Property implements Model{
    protected SimpleIntegerProperty propertyId;
    protected SimpleStringProperty  address;
    protected SimpleStringProperty  country;
    protected SimpleStringProperty  state;
    protected SimpleStringProperty  city;
    protected SimpleStringProperty  postalCode;
    protected SimpleStringProperty  details;
    
    /**
     * 
     * @param propertyId
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode
     * @param details 
     */
    public Property(int propertyId, String address, String country, String state, String city, String postalCode, String details){
        this.propertyId = new SimpleIntegerProperty(propertyId);
        this.address    = new SimpleStringProperty(address);
        this.country    = new SimpleStringProperty(country);
        this.state      = new SimpleStringProperty(state);
        this.city       = new SimpleStringProperty(city);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.details    = new SimpleStringProperty(details);
    }   
    
    /**
     * 
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode 
     */
    public Property(String address, String country, String state, String city, String postalCode, String details){
        this.propertyId = new SimpleIntegerProperty(-1);
        this.address  = new SimpleStringProperty(address);
        this.country    = new SimpleStringProperty(country);
        this.state      = new SimpleStringProperty(state);
        this.city       = new SimpleStringProperty(city);
        this.postalCode = new SimpleStringProperty(postalCode);
        this.details    = new SimpleStringProperty(details);
    }
    

    //SETTERS
    public void setPropertyId(int propertyId){
        this.propertyId.set(propertyId);
    }
    /**
     * Setter method for address attribute.
     * @param address 
     */
    public void setAddress(String address){
        this.address.set(address);
    }
    /**
     * Setter method for country attribute.
     * @param country 
     */
    public void setCountry(String country){
        this.country.set(country);
    }
    /**
     * Setter method for state attribute.
     * @param state 
     */
    public void setState(String state){
        this.state.set(state);
    }
    /**
     * Setter method for city attribute.
     * @param city 
     */
    public void setCity(String city){
        this.city.set(city);
    }
    /**
     * Setter method for postalCode attribute.
     * @param postalCode 
     */
    public void setPostalCode(String postalCode){
        this.postalCode.set(postalCode);
    }
    /**
     * 
     * @param details 
     */
    public void setDetails(String details){
        this.details.set(details);
    }
    //GETTERS
    /**
     * Getter method for propertyId attribute.
     * @return 
     */
    public int getPropertyId(){
        return propertyId.get();
    }
    /**
     * Getter method for address attribute.
     * @return 
     */
    public String getAddress(){
        return address.get();
    }
    /**
     * Getter method for country attribute.
     * @return 
     */
    public String getCountry(){
        return country.get();
    }
    /**
     * Getter method for state attribute.
     * @return 
     */
    public String getState(){
        return state.get();
    }
    /**
     * Getter method for city attribute.
     * @return 
     */
    public String getCity(){
        return city.get();
    }
    /**
     * Getter method for postalCode attribute.
     * @return 
     */
    public String getPostalCode(){
        return postalCode.get();
    }
    /**
     * Getter method for postalCode attribute.
     * @return 
     */
    public String getDetails(){
        return details.get();
    }
    
    
    
    //ABSTRACT METHODS
    
    //
    
    /**
     * 
     * @return 
     */
    public SimpleIntegerProperty propertyIdProperty(){
        return propertyId;
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty addressProperty(){
        return address;
    }
    /**
    public SimpleStringProperty countryProperty(){
        return country;
    }
    /**
    * 
    * @return 
    */
    public SimpleStringProperty stateProperty(){
        return state;
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty cityProperty(){
        return city;
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty postalCodeProperty(){
        return postalCode;
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty detailsProperty(){
        return details;
    }
    
    
    
    // Other methods
    /**
     * Returns the type of Property.
     * @return 
     */
    public String getPropertyType() {
        return this.getClass().getName();
    }  
    public String getPropertyInsertQuery(){
        String query = "insert into property(address, country, state, city, postal_code, details)"
                + "values (?,?,?,?,?,?)";
        return query;
    }
    
    //MODEL INTERFACE
    @Override
    public String getDeleteQuery() {
        String query = "delete from property where property_id = ?";
        return query;
    }
    
}
