/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class is used to represent an Object as type Tenant.
 * 
 * HOW TO USE:
 * Use the constructor in order to create the Tenant instance. If no tenantId is provided,
 * the it will be set to -1 by default. This class is used to insert information 
 * to the database.
 * 
 */
package com.mycompany.finalproject.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class Tenant implements Model{
    private SimpleIntegerProperty tenantId;
    private SimpleStringProperty  firstName;
    private SimpleStringProperty  lastName;
    private SimpleStringProperty  phone;
    private SimpleStringProperty  email;
    
    /**
     * This method is the constructor to create a tenant object.
     * @param tenantId
     * @param firstName
     * @param lastName
     * @param phone
     * @param email 
     */
    public Tenant(int tenantId, String firstName, String lastName, String phone, String email){
        this.tenantId  = new SimpleIntegerProperty(tenantId);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName  = new SimpleStringProperty(lastName);
        this.phone     = new SimpleStringProperty(phone);
        this.email     = new SimpleStringProperty(email);
    }
    /**
     * This method is the constructor to create a tenant object. It will be used for
     * creating newly units that are not registered in the database yet.
     * @param firstName
     * @param lastName
     * @param phone
     * @param email 
     */
    public Tenant(String firstName, String lastName, String phone, String email){
        this.tenantId  = new  SimpleIntegerProperty(-1);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName  = new SimpleStringProperty(lastName);
        this.phone     = new SimpleStringProperty(phone);
        this.email     = new SimpleStringProperty(email);
    }
    //SETTERS
    /**
     * Setter for the tenantId attribute.
     * @param tenantId 
     */
    public void setTenantId(int tenantId){
        this.tenantId.set(tenantId);
    }
    /**
     * Setter for the firstName attribute.
     * @param firstName 
     */
    public void setFirstName(String firstName){
        this.firstName.set(firstName);
    }
    /**
     * Setter for the lastName attribute.
     * @param lastName 
     */
    public void setLastName(String lastName){
        this.lastName.set(lastName);
    }
    /**
     * Setter for the phone attribute.
     * @param phone 
     */
    public void setPhone(String phone){
        this.phone.set(phone);
    }
    /**
     * Setter for the email attribute.
     * @param email 
     */
    public void setEmail(String email){
        this.email.set(email);
    }
    
    //GETTERS 
    /**
     * Getter for the tenantId attribute.
     * @return int
     */
    public int getTenantId(){
        return tenantId.get();
    }
    /**
     * Getter for firstName attribute.
     * @return 
     */
    public String getFirstName(){
        return firstName.get();
    }
    /**
     * Getter for the lastName attribute.
     * @return 
     */
    public String getLastName(){
        return lastName.get();
    }
    /**
     * Getter for the phone attribute.
     * @return 
     */
    public String getPhone(){
        return phone.get();
    }
    /**
     * Getter for the email attribute.
     * @return 
     */
    public String getEmail(){
        return email.get();
    }

    public SimpleIntegerProperty tenantIdProperty(){
        return tenantId;
    }
    public SimpleStringProperty firstNameProperty(){
        return firstName;
    }
    public SimpleStringProperty lastNameProperty(){
        return lastName;
    }
    public SimpleStringProperty phoneProperty(){
        return phone;
    }
    public SimpleStringProperty emailProperty(){
        return email;
    }

    @Override
    public String getUpdateQuery() {
        String query = "update tenant set first_name = ?, last_name = ?, phone = ?, email = ? where tenant_id = ?";
        return  query;
    }

    @Override
    public String getInsertQuery() {
        String query = "insert into tenant(first_name, last_name, phone, email) values(?,?,?,?)";
        return query;
    }

    @Override
    public String getDeleteQuery() {
        String query = "delete from tenant where tenant_id = ?";
        return query;
    }
    
    public static String getSelectQuery(){
        String query = "select * from tenant";
        return query;
    }
}
