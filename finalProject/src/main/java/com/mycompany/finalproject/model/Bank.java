/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class is used to represent an Object as type Bank.
 * 
 * HOW TO USE:
 * Use the constructor in order to create the Bank instance. If no bankId is provided,
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
public class Bank implements Model{
    
    private SimpleIntegerProperty bankId;
    private SimpleStringProperty  bankName;
    private SimpleStringProperty  address;
    private SimpleStringProperty  phone;
    private SimpleStringProperty  email;
    
    /**
     * Constructor method for the bank model.
     * @param bankId
     * @param bankName
     * @param address
     * @param phone
     * @param email 
     */
    public Bank(int bankId, String bankName, String address, String phone, String email){
        this.bankId   = new SimpleIntegerProperty(bankId);
        this.bankName = new SimpleStringProperty(bankName);
        this.address  = new SimpleStringProperty(address);
        this.phone    = new SimpleStringProperty(phone);
        this.email    = new SimpleStringProperty(email);
    }
    /**
     * Constructor method for banks that are not initialized with an id.
     * If they don't have an id, it probably means they are not registered in the
     * database. And therefore, they are maybe a new bank to be added to the database.
     * @param bankName
     * @param address
     * @param phone
     * @param email 
     */
    public Bank(String bankName, String address, String phone, String email){
        this.bankId   = new SimpleIntegerProperty(-1);
        this.bankName = new SimpleStringProperty(bankName);
        this.address  = new SimpleStringProperty(address);
        this.phone    = new SimpleStringProperty(phone);
        this.email    = new SimpleStringProperty(email);
    }
    public Bank(int bankId, String bankName){
        this.bankId   = new SimpleIntegerProperty(bankId);
        this.bankName = new SimpleStringProperty(bankName);
        this.address  = new SimpleStringProperty(null);
        this.phone    = new SimpleStringProperty(null);
        this.email    = new SimpleStringProperty(null);
    }
    //SETTERS
    /**
     * Setter for bankId attribute.
     * @param bankId
     * @return 
     */
    public void setBankId(int bankId){
        this.bankId.set(bankId);
    }/**
     * Setter for bankName attribute.
     * @param bankName
     * @return 
     */
    public void setBankName(String bankName){
        this.bankName.set(bankName);
    }
    /**
     * Setter for address attribute.
     * @param address
     * @return 
     */
    public void setAddress(String address){
        this.address.set(address);
    }
    /**
     * Setter for phone attribute.
     * @param phone
     * @return 
     */
    public void setPhone(String phone){
        this.phone.set(phone);
    }
    /**
     * Setter for email attribute.
     * @param email
     * @return 
     */
    public void setEmail(String email){
        this.email.set(email);
    }
    //GETTERS
    /**
     * GSetter for bankId attribute.
     * @return 
     */
    public int getBankId(){
        return bankId.get();
    }
    /**
     * Getter for bankName attribute.
     * @return 
     */
    public String getBankName(){
        return bankName.get();
    }
    /**
     * Getter for address attribute.
     * @return 
     */
    public String getAddress(){
        return address.get();
    }
    /**
     * Getter for phone attribute.
     * @return 
     */
    public String getPhone(){
        return phone.get();
    }
    /**
     * Getter for email attribute.
     * @return 
     */
    public String getEmail(){
        return email.get();
    }
    
    //PROPERTIES
    public SimpleIntegerProperty bankIdProperty(){
        return bankId;
    }
    public SimpleStringProperty bankNameProperty(){
        return bankName;
    }
    public SimpleStringProperty addressProperty(){
        return address;
    }
    public SimpleStringProperty phoneProperty(){
        return phone;
    }
    public SimpleStringProperty emailProperty(){
        return email;
    }
    
    @Override
    public String getUpdateQuery(){
        String query = "update bank set bank_name = ?, address = ?, phone = ?, email = ? where bank_id = ?";
        return query;
    }
    
    @Override
    public String getInsertQuery(){
        String query = "insert into bank(bank_name, address, phone, email) values(?,?,?,?)";
        return query;
    }
    @Override
    public String getDeleteQuery(){
        String query = "delete from bank where bank_id = ?";
        return query;
    }
    
    /**
     * 
     * @return 
     */
    public static String getSelectQuery(){
       String query  = "select * from bank"; 
       return query;
    }
}
