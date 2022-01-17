/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class is used to represent an Object as type Property.
 * 
 * HOW TO USE:
 * Use the constructor in order to create the Property instance. If no propertyId is provided,
 * the it will be set to -1 by default. This class is used to insert information 
 * to the database.
 * 
 */
package com.mycompany.finalproject.model;

import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author JCSF
 */
public class Plex extends Property{
    protected SimpleIntegerProperty numOfAppt;
    /**
     * 
     * @param propertyId
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode
     * @param details
     * @param numOfAppt 
     */
    public Plex(int propertyId, String address, String country, String state, String city, String postalCode, String details, int numOfAppt){
        super(propertyId, address, country, state, city, postalCode, details);
        this.numOfAppt = new SimpleIntegerProperty(numOfAppt);
        
    }
    
    /**
     * 
     * @param address
     * @param country
     * @param state
     * @param city
     * @param postalCode 
     */
    public Plex(String address, String country, String state, String city, String postalCode, String details){
        super(address, country, state, city, postalCode, details);
        this.propertyId = new SimpleIntegerProperty(-1);
        this.numOfAppt = new SimpleIntegerProperty(0);
    }
    
    //INHERITS GETTERS AND SETTERS
    //SETTERS
    /**
     * 
     * @param numOfAppt 
     */
    public void setNumOfAppt(int numOfAppt){
        this.numOfAppt.set(numOfAppt);
    }
    //GETTERS
    /**
     * 
     * @return 
     */
    public int getNumOfAppt(){
        return this.numOfAppt.get();
    }
    //SIMPLE PROPERTY OBSERVER METHODS
    /**
     * 
     * @return 
     */
    public SimpleIntegerProperty numOfApptProperty(){
        return this.numOfAppt;
    }
    //MODEL IMPLEMENTATION
    /**
     * 
     * @return 
     */
    @Override
    public String getUpdateQuery() {
        String query = "update property "
                + "set "
                + "address = ?, "
                + "country = ?, "
                + "state = ?, "
                + "city = ?, "
                + "postal_code = ?, "
                + "details = ? "
                + "where "
                + "property_id = ?";
        return query;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String getInsertQuery() {
        String query = "insert into plex(property_id) values(?)";
        return query;
    }
    
    /**
     * 
     * @return 
     */
    public String getUnitSelectQuery(){
        String query = "select * from unit inner join plex using(property_id) where "
                + "property_id = "+getPropertyId();
        return query;
    }
    /**
     * 
     * @return 
     */
    public static String getSelectQuery(){
        String query = "select * from plex inner join property using(property_id)";
        return query;
    }
}
