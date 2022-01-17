/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class is used to represent an Object as type Contractor.
 * 
 * HOW TO USE:
 * Use the constructor in order to create the Bank instance. If no contractorId is provided,
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
public class Contractor implements Model{
    private SimpleIntegerProperty contractorId;
    private SimpleStringProperty  contactName;
    private SimpleStringProperty  companyName;
    private SimpleStringProperty  address;
    private SimpleStringProperty  specialty;
    private SimpleStringProperty  phone;
    private SimpleStringProperty  email;
    /**
     * Constructor method for the Contractor Object.
     * @param contactName
     * @param companyName
     * @param specialty
     * @param phone
     * @param email 
     */
    public Contractor(int contractorId, String contactName, String companyName,String address,String specialty, String phone, String email){
        this.contractorId = new SimpleIntegerProperty(contractorId);
        this.contactName  = new SimpleStringProperty(contactName);
        this.companyName  = new SimpleStringProperty(companyName);
        this.address      = new SimpleStringProperty(address);
        this.specialty    = new SimpleStringProperty(specialty);
        this.phone        = new SimpleStringProperty(phone);
        this.email        = new SimpleStringProperty(email);
        
    }
    /**
     * 
     * @param contactName
     * @param companyName
     * @param specialty
     * @param phone
     * @param email 
     */
    public Contractor(String contactName, String companyName, String address, String specialty, String phone, String email){
        this.contractorId = new SimpleIntegerProperty(-1);
        this.contactName  = new SimpleStringProperty(contactName);
        this.companyName  = new SimpleStringProperty(companyName);
        this.address      = new SimpleStringProperty(address);
        this.specialty    = new SimpleStringProperty(specialty);
        this.phone        = new SimpleStringProperty(phone);
        this.email        = new SimpleStringProperty(email);
        
    }
    /**
     * Used to create a listing of contractors to display in the tableview.
     * @param contractorId
     * @param companyName 
     */
    public Contractor(int contractorId, String companyName){
        this.contractorId = new SimpleIntegerProperty(contractorId);
        this.companyName = new SimpleStringProperty(companyName);
        this.contactName  = new SimpleStringProperty(null);
        this.address      = new SimpleStringProperty(null);
        this.specialty    = new SimpleStringProperty(null);
        this.phone        = new SimpleStringProperty(null);
        this.email        = new SimpleStringProperty(null);
    }
    //SETTERS
    /**
     * Setter for the contractorId attribute.
     * @param contractorId 
     */
    public void setContractorId(int contractorId){
        this.contractorId.set(contractorId);
    }
    /**
     * Setter for the contactName attribute.
     * @param contactName 
     */
    public void setContactName(String contactName){
        this.contactName.set(contactName);
    }
    /**
     * Setter for the comapanyName attribute.
     * @param companyName 
     */
    public void setCompanyName(String companyName){
        this.companyName.set(companyName);
    }
    /**
     * Setter for the address attribute.
     * @param address 
     */
    public void setAddress(String address){
        this.address.set(address);
    }
    /**
     * Setter for the specialty attribute.
     * @param spcecialty 
     */
    public void setSpecialty(String specialty){
        this.specialty.set(specialty);
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
    public int getContractorId(){
        return contractorId.get();
    }
    /**
     * Getter for the contactName attribute.
     * @return 
     */
    public String getContactName(){
        return contactName.get();
    }
    /**
     * Getter for the companyName attribute.
     * @return 
     */
    public String getCompanyName(){
        return companyName.get();
    }
    /**
     * Getter for the address attribute.
     * @return 
     */
    public String getAddress(){
        return address.get();
    }
    /**
     * Getter for the specialty attribute.
     * @return 
     */
    public String getSpecialty(){
        return specialty.get();
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
    /**
     * This method is used to retrieve the hashcode of the contractor.
     * @return 
     */
    @Override
    public int hashCode(){
        String hash = contactName.get()+companyName.get()+specialty.get()+email.get();
        return hash.hashCode();
    }
    /**
     * 
     * @return 
     */
    @Override
    public boolean equals(Object obj){
        if(obj.getClass() != Contractor.class){
            return false;
        }
        Contractor otherContractor = (Contractor)obj;
        boolean returnValue = otherContractor.contractorId.get() == this.contractorId.get();
        returnValue = returnValue && otherContractor.contactName.get().equals(this.contactName.get());
        returnValue = returnValue && otherContractor.companyName.get().equals(this.companyName.get());
        returnValue = returnValue && otherContractor.specialty.get().equals(this.specialty.get());
        returnValue = returnValue && otherContractor.email.get().equals(this.email.get());
        
        return returnValue;
    }
    

    public SimpleIntegerProperty contractorIdProperty(){
        return contractorId;
    }
    public SimpleStringProperty contactNameProperty(){
        return contactName;
    }
    public SimpleStringProperty companyNameProperty(){
        return companyName;    
    }
    public SimpleStringProperty addressProperty(){
        return address;    
    }
    public SimpleStringProperty specialtyProperty(){
        return specialty;    
    }
    public SimpleStringProperty phoneProperty(){
        return phone;    
    }
    public SimpleStringProperty emailProperty(){
        return email;    
    }

    @Override
    public String getUpdateQuery() {
        String query = "update contractor set contact_name = ?, company_name = ?, address = ?, specialty = ?, phone = ?, email = ? where contractor_id = ?";
        return query;
    }

    @Override
    public String getInsertQuery() {
        String query = "insert into contractor(contact_name, company_name, address, specialty, phone, email) values(?,?,?,?,?,?)";
        return query;
    }

    @Override
    public String getDeleteQuery() {
        String query = "delete from contractor where contractor_id = ?";
        return query;
    }
    /**
     * 
     * @return 
     */
    public static String getSelectQuery(){
       String query  = "select * from contractor"; 
       return query;
    }
}
