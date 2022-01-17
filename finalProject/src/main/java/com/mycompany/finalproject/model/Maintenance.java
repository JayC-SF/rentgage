/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author JCSF
 */
public class Maintenance implements Model{
    
    private SimpleIntegerProperty maintenanceId;
    private SimpleStringProperty  maintenanceType;
    private SimpleStringProperty  startDate;
    private SimpleStringProperty  endDate;
    private SimpleDoubleProperty  totalCost;
    private SimpleStringProperty  details;
    private SimpleIntegerProperty propertyId;
    private SimpleStringProperty  propertyAddress;
    private SimpleIntegerProperty contractorId;
    private SimpleStringProperty  contractorCompanyName;
    
    /**
     * 
     * @param maintenanceId
     * @param maintenanceType
     * @param startDate
     * @param endDate
     * @param totalCost
     * @param details
     * @param propertyId
     * @param contractorId 
     */
    public Maintenance( int maintenanceId, String maintenanceType, String startDate,String endDate, double totalCost, String details, int propertyId, int contractorId){
        if(totalCost < 0){
            throw new IllegalArgumentException("Cost is less than 0.00$.");
        }
        this.maintenanceId         = new SimpleIntegerProperty(maintenanceId);
        this.maintenanceType       = new SimpleStringProperty(maintenanceType);
        this.startDate             = new SimpleStringProperty(startDate);
        this.endDate               = new SimpleStringProperty(endDate);
        this.totalCost             = new SimpleDoubleProperty(totalCost);
        this.details               = new SimpleStringProperty(details);
        this.propertyId             = new SimpleIntegerProperty(propertyId);
        this.propertyAddress       = new SimpleStringProperty(null);
        this.contractorId          = new SimpleIntegerProperty(contractorId);
        this.contractorCompanyName = new SimpleStringProperty(null);
    }
    
    /**
     * 
     * @param maintenanceType
     * @param startDate
     * @param endDate
     * @param totalCost
     * @param details
     * @param propertyId
     * @param contractorId 
     */
    public Maintenance( String maintenanceType, String startDate,String endDate, double totalCost, String details, int propertyId, String propertyAddress, int contractorId, String contractorCompanyName){
        if(totalCost < 0){
            throw new IllegalArgumentException("Cost is less than 0.00$.");
        }
        this.maintenanceId         = new SimpleIntegerProperty(-1);
        this.maintenanceType       = new SimpleStringProperty(maintenanceType);
        this.startDate             = new SimpleStringProperty(startDate);
        this.endDate               = new SimpleStringProperty(endDate);
        this.totalCost             = new SimpleDoubleProperty(totalCost);
        this.details               = new SimpleStringProperty(details);
        this.propertyId             = new SimpleIntegerProperty(propertyId);
        this.propertyAddress       = new SimpleStringProperty(propertyAddress);
        this.contractorId          = new SimpleIntegerProperty(contractorId);
        this.contractorCompanyName = new SimpleStringProperty(contractorCompanyName);
    }
    
    /**
     * 
     * @param maintenanceId
     * @param maintenanceType
     * @param startDate
     * @param endDate
     * @param totalCost
     * @param details
     * @param propertyId
     * @param contractorId 
     */
    public Maintenance( int maintenanceId, String maintenanceType, String startDate,String endDate, double totalCost, String details, int propertyId, String propertyAddress, int contractorId, String contractorCompanyName){
        if(totalCost < 0){
            throw new IllegalArgumentException("Cost is less than 0.00$.");
        }
        this.maintenanceId         = new SimpleIntegerProperty(maintenanceId);
        this.maintenanceType       = new SimpleStringProperty(maintenanceType);
        this.startDate             = new SimpleStringProperty(startDate);
        this.endDate               = new SimpleStringProperty(endDate);
        this.totalCost             = new SimpleDoubleProperty(totalCost);
        this.details               = new SimpleStringProperty(details);
        this.propertyId             = new SimpleIntegerProperty(propertyId);
        this.propertyAddress       = new SimpleStringProperty(propertyAddress);
        this.contractorId          = new SimpleIntegerProperty(contractorId);
        this.contractorCompanyName = new SimpleStringProperty(contractorCompanyName);
    }
    @Override
    public String getUpdateQuery() {
        String query = "update maintenance set maintenance_type = ?, start_date = ?, end_date = ?, total_cost = ?, details = ?, property_id = ?, contractor_id = ? where maintenance_id = ?;";
        return query;
    }

    @Override
    public String getInsertQuery() {
        String query ="insert into maintenance(maintenance_type, start_date, end_date, total_cost, details, property_id, contractor_id) values(?,?,?,?,?,?,?);";
        return query;
    }

    @Override
    public String getDeleteQuery() {
        String query = "delete from maintenance where maintenance_id = ?";
        return query;
    }

    public int getMaintenanceId() {
        return maintenanceId.get();
    }

    public SimpleIntegerProperty maintenanceIdProperty() {
        return maintenanceId;
    }

    public void setMaintenanceId(int maintenanceId) {
        this.maintenanceId.set(maintenanceId);
    }

    public String getMaintenanceType() {
        return maintenanceType.get();
    }

    public SimpleStringProperty maintenanceTypeProperty() {
        return maintenanceType;
    }

    public void setMaintenanceType(String maintenanceType) {
        this.maintenanceType.set(maintenanceType);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public SimpleStringProperty startDateProperty() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public SimpleStringProperty endDateProperty() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public double getTotalCost() {
        return totalCost.get();
    }

    public SimpleDoubleProperty totalCostProperty() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost.set(totalCost);
    }

    public String getDetails() {
        return details.get();
    }

    public SimpleStringProperty detailsProperty() {
        return details;
    }

    public void setDetails(String details) {
        this.details.set(details);
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

    public int getContractorId() {
        return contractorId.get();
    }

    public SimpleIntegerProperty contractorIdProperty() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId.set(contractorId);
    }

    public String getContractorCompanyName() {
        return contractorCompanyName.get();
    }

    public SimpleStringProperty contractorCompanyNameProperty() {
        return contractorCompanyName;
    }

    public void setContractorCompanyName(String contractorCompanyName) {
        this.contractorCompanyName.set(contractorCompanyName);
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
    
    /**
     * 
     * @return 
     */
    public static String getSelectQuery() {
        String query = "select maintenance.*, property.address, company_name from maintenance " +
            "inner join contractor using(contractor_id) " +
            "inner join property using(property_id)";
        return query;
    }
}
