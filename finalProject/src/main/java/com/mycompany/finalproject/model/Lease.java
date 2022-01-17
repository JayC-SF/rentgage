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
public class Lease implements Model{
    
    private SimpleIntegerProperty leaseId;
    private SimpleIntegerProperty propertyId;
    private SimpleStringProperty propertyAddress;
    private SimpleIntegerProperty tenantId;
    private SimpleStringProperty tenantFirstName;
    private SimpleStringProperty tenantLastName;
    private SimpleStringProperty extension;
    public static final String LEASE_URL = "src\\main\\resources\\com\\mycompany\\finalproject\\leases\\";
    /**
     * 
     * @param leaseId
     * @param leaseName
     * @param propertyId
     * @param propertyAddress
     * @param tenantId
     * @param tenantName 
 */
    public Lease(int leaseId, int propertyId, String propertyAddress, int tenantId, String firstName, String lastName, String extension){
        this.leaseId = new SimpleIntegerProperty(leaseId);
        this.propertyId = new SimpleIntegerProperty(propertyId);
        this.propertyAddress = new SimpleStringProperty(propertyAddress);
        this.tenantId = new SimpleIntegerProperty(tenantId);
        this.tenantFirstName = new SimpleStringProperty(firstName);
        this.tenantLastName = new SimpleStringProperty(lastName);
        this.extension = new SimpleStringProperty(extension);
    }
    /**
     * 
     * @param leaseName
     * @param propertyId
     * @param propertyAddress
     * @param tenantId
     * @param tenantName 
     */
    public Lease(int propertyId, String propertyAddress, int tenantId, String firstName, String lastName, String extension){
        this.leaseId = new SimpleIntegerProperty(-1);
        this.propertyId = new SimpleIntegerProperty(propertyId);
        this.propertyAddress = new SimpleStringProperty(propertyAddress);
        this.tenantId = new SimpleIntegerProperty(tenantId);
        this.tenantFirstName = new SimpleStringProperty(firstName);
        this.tenantLastName = new SimpleStringProperty(lastName);
        this.extension = new SimpleStringProperty(extension);
    }
    @Override
    public String getUpdateQuery() {
        String query = "update lease set property_id = ?, tenant_id = ?, extension = ? where lease_id = ?";
        return query;
    }

    @Override
    public String getInsertQuery() {
        String query = "insert into lease(property_id, tenant_id, extension) values(?,?,?)";
        return query;
    }

    @Override
    public String getDeleteQuery() {
        String query = "delete from lease where lease_id = ?";
        return query;
    }

    public int getLeaseId() {
        return leaseId.get();
    }

    public SimpleIntegerProperty leaseIdProperty() {
        return leaseId;
    }

    public void setLeaseId(int leaseId) {
        this.leaseId.set(leaseId);
    }


    public int getPropertyId() {
        return propertyId.get();
    }

    public SimpleIntegerProperty propertyIdProperty() {
        return propertyId;
    }

    public void setPropertyId(int propertyId) {
        this.propertyId.set(propertyId);
    }

    public String getPropertyAddress() {
        return propertyAddress.get();
    }

    public SimpleStringProperty propertyAddressProperty() {
        return propertyAddress;
    }

    public void setPropertyAddress(String propertyAddress) {
        this.propertyAddress.set(propertyAddress);
    }

    public int getTenantId() {
        return tenantId.get();
    }

    public SimpleIntegerProperty tenantIdProperty() {
        return tenantId;
    }

    public void setTenantId(int tenantId) {
        this.tenantId.set(tenantId);
    }

    public String getExtension() {
        return extension.get();
    }

    public SimpleStringProperty extensionProperty() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension.set(extension);
    }

    public String getTenantFirstName() {
        return tenantFirstName.get();
    }

    public SimpleStringProperty tenantFirstNameProperty() {
        return tenantFirstName;
    }

    public void setTenantFirstName(String tenantFirstName) {
        this.tenantFirstName.set(tenantFirstName);
    }

    public String getTenantLastName() {
        return tenantLastName.get();
    }

    public SimpleStringProperty tenantLastNameProperty() {
        return tenantLastName;
    }

    public void setTenantLastName(String tenantLastName) {
        this.tenantLastName.set(tenantLastName);
    }
    
        public static String getSelectQuery(){
        String query = "select lease.*, address, first_name, last_name, extension "
                + "from lease "
                + "inner join property using(property_id) "
                + "inner join tenant using(tenant_id) ";
        return query;
    }
}
