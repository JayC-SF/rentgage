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
public class Mortgage implements Model{
    private SimpleIntegerProperty mortgageId;
    private SimpleStringProperty  startDate;
    private SimpleStringProperty  endDate;
    private SimpleDoubleProperty  monthlyPayment;
    private SimpleIntegerProperty numOfYearsContract;
    private SimpleDoubleProperty  totalLoanValue;
    private SimpleDoubleProperty  amountPaid;
    private SimpleDoubleProperty  interestRate;
    private SimpleIntegerProperty bankId;
    private SimpleStringProperty  bankName;
    private SimpleIntegerProperty propertyId;
    private SimpleStringProperty  propertyAddress;
    public Mortgage(
            int    mortgageId, 
            String startDate, 
            String endDate, 
            double monthlyPayment, 
            int    numOfYearsContract, 
            double totalLoanValue,
            double amountPaid,
            double interestRate,
            int    bankId,
            String bankName,
            int    propertyId,
            String propertyAddress
            ){
        this.mortgageId         = new SimpleIntegerProperty(mortgageId);
        this.startDate          = new SimpleStringProperty(startDate);
        this.endDate            = new SimpleStringProperty(endDate);
        this.monthlyPayment     = new SimpleDoubleProperty(monthlyPayment);
        this.numOfYearsContract = new SimpleIntegerProperty(numOfYearsContract);
        this.totalLoanValue     = new SimpleDoubleProperty(totalLoanValue);
        this.amountPaid         = new SimpleDoubleProperty(amountPaid);
        this.interestRate       = new SimpleDoubleProperty(interestRate);
        this.bankId             = new SimpleIntegerProperty(bankId);
        this.bankName           = new SimpleStringProperty(bankName);
        this.propertyId         = new SimpleIntegerProperty(propertyId);
        this.propertyAddress    = new SimpleStringProperty(propertyAddress);
        
    }
    /**
     * 
     * @param startDate
     * @param endDate
     * @param monthlyPayment
     * @param numOfYearsContract
     * @param totalLoanValue
     * @param amountPaid
     * @param interestRate
     * @param bankId
     * @param bankName
     * @param propertyId
     * @param propertyAddress 
     */
    public Mortgage(
            String startDate, 
            String endDate, 
            double monthlyPayment, 
            int    numOfYearsContract, 
            double totalLoanValue,
            double amountPaid,
            double interestRate,
            int    bankId,
            String bankName,
            int    propertyId,
            String propertyAddress
            ){
        this.mortgageId         = new SimpleIntegerProperty(-1);
        this.startDate          = new SimpleStringProperty(startDate);
        this.endDate            = new SimpleStringProperty(endDate);
        this.monthlyPayment     = new SimpleDoubleProperty(monthlyPayment);
        this.numOfYearsContract = new SimpleIntegerProperty(numOfYearsContract);
        this.totalLoanValue     = new SimpleDoubleProperty(totalLoanValue);
        this.amountPaid         = new SimpleDoubleProperty(amountPaid);
        this.interestRate       = new SimpleDoubleProperty(interestRate);
        this.bankId             = new SimpleIntegerProperty(bankId);
        this.bankName           = new SimpleStringProperty(bankName);
        this.propertyId         = new SimpleIntegerProperty(propertyId);
        this.propertyAddress    = new SimpleStringProperty(propertyAddress);
        
    }

    /**
     * 
     * @return
     */
    public int getMortgageId() {
        return mortgageId.get();
    }
    /**
     * 
     * @return 
     */      
    public SimpleIntegerProperty mortgageIdProperty() {
        return mortgageId;
    }
    /**  
     * 
     * @param mortgageId 
     */  
    public void setMortgageId(int mortgageId) {
        this.mortgageId.set(mortgageId);
    }
    /**
     * 
     * @return 
     */
    public String getStartDate() {
        return startDate.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty startDateProperty() {
        return startDate;
    }
    /**
     * 
     * @param startDate 
     */
    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }
    /**
     * 
     * @return 
     */
    public String getEndDate() {
        return endDate.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty endDateProperty() {
        return endDate;
    }
    /**
     * 
     * @param endDate 
     */
    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }
    /**
     * 
     * @return 
     */
    public double getMonthlyPayment() {
        return monthlyPayment.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleDoubleProperty monthlyPaymentProperty() {
        return monthlyPayment;
    }
    /**
     * 
     * @param monthlyPayment 
     */
    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment.set(monthlyPayment);
    }
    /**
     * 
     * @return 
     */
    public int getNumOfYearsContract() {
        return numOfYearsContract.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleIntegerProperty numOfYearsContractProperty() {
        return numOfYearsContract;
    }
    /**
     * 
     * @param numOfYearsContract 
     */
    public void setNumOfYearsContract(int numOfYearsContract) {
        this.numOfYearsContract.set(numOfYearsContract);
    }
    /**
     * 
     * @return 
     */
    public double getTotalLoanValue() {
        return totalLoanValue.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleDoubleProperty totalLoanValueProperty() {
        return totalLoanValue;
    }
    /**
     * 
     * @param totalLoanValue 
     */
    public void setTotalLoanValue(double totalLoanValue) {
        this.totalLoanValue.set(totalLoanValue);
    }
    /**
     * 
     * @return 
     */
    public double getAmountPaid() {
        return amountPaid.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleDoubleProperty amountPaidProperty() {
        return amountPaid;
    }
    /**
     * 
     * @param amountPaid 
     */
    public void setAmountPaid(double amountPaid) {
        this.amountPaid.set(amountPaid);
    }
    /**
     * 
     * @return 
     */
    public double getInterestRate() {
        return interestRate.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleDoubleProperty interestRateProperty() {
        return interestRate;
    }
    /**
     * 
     * @param interestRate 
     */
    public void setInterestRate(double interestRate) {
        this.interestRate.set(interestRate);
    }
    /**
     * 
     * @return 
     */
    public int getBankId() {
        return bankId.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleIntegerProperty bankIdProperty() {
        return bankId;
    }
    /**
     * 
     * @param bankId 
     */
    public void setBankId(int bankId) {
        this.bankId.set(bankId);
    }
    /**
     * 
     * @return 
     */
    public String getBankName(){
        return bankName.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty bankNameProperty(){
        return bankName;
    }
    /**
     * 
     * @param bankName 
     */
    public void setBankName(String bankName){
        this.bankName.set(bankName);
    }
    /**
     * 
     * @return 
     */
    public int getPropertyId() {
        return propertyId.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleIntegerProperty propertyIdProperty() {
        return propertyId;
    }
    /**
     * 
     * @param propertyId 
     */
    public void setPropertyId(int propertyId) {
        this.propertyId.set(propertyId);
    }
    /**
     * 
     * @return 
     */
    public String getPropertyAddress(){
        return propertyAddress.get();
    }
    /**
     * 
     * @return 
     */
    public SimpleStringProperty propertyAddressProperty(){
        return propertyAddress;
    }
    /**
     * 
     * @param propertyAddress 
     */
    public void setPropertyAddress(String propertyAddress){
        this.propertyAddress.set(propertyAddress);
    }
    /**
     * 
     * @return 
     */
    @Override
    public String getUpdateQuery() {
        String query = "update mortgage set "
                + "start_date = ?, "
                + "end_date = ?, "
                + "monthly_payment = ?, "
                + "num_years_contract = ?, "
                + "total_loan_value = ?, "
                + "amount_paid = ?, "
                + "interest_rate = ?, "
                + "bank_id = ?, "
                + "property_id = ? "
                + "where mortgage_id = ?";
        return query;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String getInsertQuery() {
        String query = "insert into mortgage(start_date, end_date, monthly_payment, num_years_contract, total_loan_value, amount_paid, interest_rate, bank_id, property_id)\n" +
"	values(?,?,?,?,?,?,?,?,?)";
        return query;
    }
    /**
     * 
     * @return 
     */
    @Override
    public String getDeleteQuery() {
        String query = "delete from mortgage where mortgage_id = ?";
        return query;
    }
    
    
    public static String getSelectQuery(){
        String query = "select mortgage.*, property.address, bank.bank_name from mortgage inner join bank using(bank_id) inner join property using(property_id);";
        return query;
    }
}
