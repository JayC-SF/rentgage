/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class is used to represent an Object as type Unit.
 * 
 * HOW TO USE:
 * Use the constructor in order to create the Unit instance. If no unitId is provided,
 * the it will be set to -1 by default. This class is used to insert information 
 * to the database.
 * 
 */
package com.mycompany.finalproject.model;
import java.lang.IllegalArgumentException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class Unit implements Model{
    private SimpleIntegerProperty unitId;
    private SimpleStringProperty  appartmentNumber;
    private SimpleDoubleProperty  size;
    private SimpleStringProperty  details;
    private SimpleIntegerProperty propertyId;

    /**
     * 
     * @param unitId
     * @param appartmentNumber
     * @param size
     * @param details
     * @param propertyId 
     */
    public Unit(int unitId, String appartmentNumber, double size, String details, int propertyId){
        this.unitId = new SimpleIntegerProperty(unitId);
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2."); 
        }
        this.appartmentNumber = new SimpleStringProperty(appartmentNumber);
        this.size    = new SimpleDoubleProperty(size);
        this.details = new SimpleStringProperty(details);
        this.propertyId = new SimpleIntegerProperty(propertyId);
    }
    /**
     * 
     * @param appartmentNumber
     * @param size
     * @param details
     * @param propertyId 
     */
    public Unit(String appartmentNumber, double size, String details,  int propertyId){
        this.unitId = new SimpleIntegerProperty(-1);
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2."); 
        }
        this.appartmentNumber = new SimpleStringProperty(appartmentNumber);
        this.size     = new SimpleDoubleProperty(size);
        this.details  = new SimpleStringProperty(details);
        this.propertyId = new SimpleIntegerProperty(propertyId);
    }
    
    //SETTERS
    /**
     * Setter for the unitId attribute.
     * @param unitId 
     */
    public void setUnitId(int unitId){
        this.unitId.set(unitId);
    }
    /**
     * Setter for the unitNumber attribute.
     * @param unitNumber 
     */
    public void setAppartmentNumber(String appartmentNumber){
        this.appartmentNumber.set(appartmentNumber);
    }
    /**
     * Setter for the size attribute.
     * @param size 
     */
    public void setSize(double size){
        if((size*10)%5 != 0){
            throw new IllegalArgumentException("Size does not respect the correct unit. Attempted to enter "+"'"+size+"' can only accept in steps of 1/2.");
        }
        this.size.set(size);
    }
    /**
     * Setter for the details attribute.
     * @param details 
     */
    public void setDetails(String details){
        this.details.set(details);
    }
    /**
     * 
     * @param propertyId 
     */
    public void setPropertyId(int propertyId){
        this.propertyId.set(propertyId);
    }
    //GETTERS
    /**
     * Getter for the unitId attribute.
     * @return 
     */
    public int getUnitId(){
        return unitId.get();
    }
    /**
     * 
     * @return 
     */
    public String getAppartmentNumber(){
       return appartmentNumber.get(); 
    }
    /**
     * Getter for the size attribute.
     * @return 
     */
    public double getSize(){
        return size.get();
    }
    /**
     * Getter for the details attribute.
     * @return 
     */
    public String getDetails(){
        return details.get();
    }
    /**
     * 
     * @return 
     */
    public int getPropertyId(){
        return propertyId.get();
    }

    public SimpleIntegerProperty unitIdProperty(){
        return unitId;
    }
    public SimpleStringProperty appartmentNumberProperty(){
        return appartmentNumber;
    }
    public SimpleDoubleProperty sizeProperty(){
        return size;
    }
    public SimpleStringProperty detailsProperty(){
        return details;
    }
    public SimpleIntegerProperty propertyIdProperty(){
        return propertyId;
    }
    
    

    @Override
    public String getUpdateQuery() {
        String query = "update unit set appartment_number = ?, size = ?, details = ? where unit_id = ?";
        return query;
    }

    @Override
    public String getInsertQuery() {
        String query = "insert into unit(appartment_number, size, details, property_id) values(?,?,?,?)";
        return query;
    }

    @Override
    public String getDeleteQuery() {
        String query = "delete from unit where unit_id = ?";
        return query;
    }
    
}
