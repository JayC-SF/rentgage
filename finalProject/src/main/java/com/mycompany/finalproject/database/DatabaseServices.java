/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.database;
import com.mycompany.finalproject.model.*;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;

/**
 *
 * @author JCSF
 */
public class DatabaseServices {
    private static DatabaseServices instance;
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/rentgage_1533920_juan-carlos_sreng-flores";
    private String username = "student";
    private String password = "student54321";
    
    private DatabaseServices() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connection successful!");
        } catch (ClassNotFoundException ex) {
            System.out.println("Database Connection Creation Failed : " + ex.getMessage());
        }
    }
    
    public Connection getConnection() {
        return connection;
    }
    
    public static DatabaseServices getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseServices();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseServices();
        }
        return instance;
    }
    
    public static int getLastAutoIncrement(String table) throws SQLException{
        /*String query = "SELECT IFNULL(AUTO_INCREMENT,0) as AUTO_INCREMENT FROM information_schema.TABLES WHERE TABLE_SCHEMA = ?"
                + "AND TABLE_NAME = ?";*/
        String query = "select ifnull(max("+table+"_id),0) as AUTO_INCREMENT from "+table;
        //String query = "select max(bank_id) as `auto_increment` from "+table;
        Statement stm = getInstance().getConnection().createStatement();
        ResultSet rs = stm.executeQuery(query);
        if(rs.next()){
            return rs.getInt("AUTO_INCREMENT");
        }else{
            throw new SQLException("Error occurred: ResultSet did not return anything.");
        }
    }
    /**
     * 
     * @param model
     * @throws SQLException 
     */
    public static void addModelToDb(Model model) throws SQLException{
        if(model.getClass().equals(Bank.class)){
            addBankToDb((Bank)model);
        }
        else if(model.getClass().equals(Condo.class)){
            addCondoToDb((Condo)model);
        }
        else if(model.getClass().equals(Contractor.class)){
            addContractorToDb((Contractor)model);
        }
        else if(model.getClass() == House.class){
            addHouseToDb((House)model);
        }
        else if(model.getClass() == Lease.class){
            addLeaseToDb((Lease)model);
        }
        else if(model.getClass() == Mortgage.class){
            addMortgageToDb((Mortgage)model);
        }
        else if(model.getClass() == Maintenance.class){
            addMaintenanceToDb((Maintenance)model);
        }
        else if(model.getClass() == Plex.class){
            addPlexToDb((Plex)model);
        }
        else if(model.getClass() == Tenant.class){
            addTenantToDb((Tenant)model);
        }
        else if(model.getClass() == Unit.class){
            addUnitToDb((Unit)model);
        }   
    }
    /**
     * 
     * @param bank
     * @throws SQLException 
     */
    private static void addBankToDb(Bank bank) throws SQLException{
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(bank.getInsertQuery());
        pstm.setString(1, bank.getBankName());
        pstm.setString(2, bank.getAddress());
        pstm.setString(3, bank.getPhone());
        pstm.setString(4, bank.getEmail());
        pstm.execute();
        int bankId = DatabaseServices.getLastAutoIncrement("bank");
        bank.setBankId(bankId);
    }
    /**
     * 
     * @param contractor
     * @throws SQLException 
     */
    private static void addContractorToDb(Contractor contractor) throws SQLException{
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(contractor.getInsertQuery());
        pstm.setString(1, contractor.getContactName());
        pstm.setString(2, contractor.getCompanyName());
        pstm.setString(3, contractor.getAddress());
        pstm.setString(4, contractor.getSpecialty());
        pstm.setString(5, contractor.getPhone());
        pstm.setString(6, contractor.getEmail());
        pstm.execute();
        int bankId = DatabaseServices.getLastAutoIncrement("contractor");
        contractor.setContractorId(bankId);
    }
    private static void addCondoToDb(Condo condo) throws SQLException{
                //getInstance().getConnection().setAutoCommit(false);
        Statement stm = getInstance().getConnection().createStatement();
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(condo.getPropertyInsertQuery());
        pstm.setString(1, condo.getAddress());
        pstm.setString(2, condo.getCountry());
        pstm.setString(3, condo.getState());
        pstm.setString(4, condo.getCity());
        pstm.setString(5, condo.getPostalCode());
        pstm.setString(6, condo.getDetails());
        //clean the data from toString of Prepared Statement.
        pstm.execute();
        pstm = getInstance().getConnection().prepareStatement(condo.getInsertQuery());
        int autoIncrementId = getLastAutoIncrement("property");
        pstm.setInt(1, autoIncrementId);
        pstm.setString(2, condo.getAppartmentNumber());
        pstm.setDouble(3, condo.getSize());
        pstm.setDouble(4, condo.getCondoFee());
        boolean successful = false;
        try{
            pstm.execute();
            successful = true;
        }
        finally{
            condo.setPropertyId(autoIncrementId);
            if(!successful){
                deleteModelFromDb(condo);
            }
        }
    }
    /**
     * 
     * @param house
     * @throws SQLException 
     */
    private static void addHouseToDb(House house) throws SQLException{
        Statement stm = getInstance().getConnection().createStatement();
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(house.getPropertyInsertQuery());
        pstm.setString(1, house.getAddress());
        pstm.setString(2, house.getCountry());
        pstm.setString(3, house.getState());
        pstm.setString(4, house.getCity());
        pstm.setString(5, house.getPostalCode());
        pstm.setString(6, house.getDetails());
        pstm.execute();
        pstm = getInstance().getConnection().prepareStatement(house.getInsertQuery());
        int autoIncrementId = getLastAutoIncrement("property");
        pstm.setInt(1, autoIncrementId);
        pstm.setDouble(2, house.getSize());
        boolean successful = false;
        try{
            pstm.execute();
            successful = true;
        }
        finally{
            house.setPropertyId(autoIncrementId);
            if(!successful){
                deleteModelFromDb(house);
            }
        }  
    }
    /**
     * 
     * @param lease
     * @throws SQLException 
     */
    private static void addLeaseToDb(Lease lease) throws SQLException{
        Statement stm = getInstance().getConnection().createStatement();
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(lease.getInsertQuery());
        pstm.setInt(1, lease.getPropertyId());
        pstm.setInt(2, lease.getTenantId());
        pstm.setString(3, lease.getExtension());
        pstm.execute();
        lease.setLeaseId(getLastAutoIncrement("lease"));
    }
    private static void addMaintenanceToDb(Maintenance maintenance) throws SQLException{
        Statement stm = getInstance().getConnection().createStatement();
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(maintenance.getInsertQuery());
        pstm.setString(1, maintenance.getMaintenanceType());
        pstm.setString(2, maintenance.getStartDate());
        pstm.setString(3, maintenance.getEndDate());
        pstm.setDouble(4, maintenance.getTotalCost());
        pstm.setString(5, maintenance.getDetails());
        pstm.setInt(6, maintenance.getPropertyId());
        pstm.setInt(7, maintenance.getContractorId());
        pstm.execute();
        maintenance.setMaintenanceId(getLastAutoIncrement("maintenance"));
    }
    private static void addMortgageToDb(Mortgage mortgage) throws SQLException{
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(mortgage.getInsertQuery());
        pstm.setString(1, mortgage.getStartDate());
        pstm.setString(2, mortgage.getEndDate());
        pstm.setDouble(3, mortgage.getMonthlyPayment());
        pstm.setInt(4, mortgage.getNumOfYearsContract());
        pstm.setDouble(5, mortgage.getTotalLoanValue());
        pstm.setDouble(6, mortgage.getAmountPaid());
        pstm.setDouble(7, mortgage.getInterestRate());
        pstm.setInt(8, mortgage.getBankId());
        pstm.setInt(9, mortgage.getPropertyId());
        pstm.execute();
        mortgage.setMortgageId(getLastAutoIncrement("mortgage"));
        
    }
    private static void addPlexToDb(Plex plex) throws SQLException{
        Statement stm = getInstance().getConnection().createStatement();
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(plex.getPropertyInsertQuery());
        pstm.setString(1, plex.getAddress());
        pstm.setString(2, plex.getCountry());
        pstm.setString(3, plex.getState());
        pstm.setString(4, plex.getCity());
        pstm.setString(5, plex.getPostalCode());
        pstm.setString(6, plex.getDetails());
        pstm.execute();
        pstm = getInstance().getConnection().prepareStatement(plex.getInsertQuery());
        int autoIncrementId = getLastAutoIncrement("property");
        pstm.setInt(1, autoIncrementId);
        boolean successful = false;
        try{
            pstm.execute();
            successful = true;
        }
        finally{
            plex.setPropertyId(autoIncrementId);
            if(!successful){
                deleteModelFromDb(plex);
            }
        }
    }
    private static void addTenantToDb(Tenant tenant) throws SQLException{
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(tenant.getInsertQuery());
        pstm.setString(1, tenant.getFirstName());
        pstm.setString(2, tenant.getLastName());
        pstm.setString(3, tenant.getPhone());
        pstm.setString(4, tenant.getEmail());
        pstm.execute();
        int bankId = DatabaseServices.getLastAutoIncrement("bank");
        tenant.setTenantId(bankId);
    }
    private static void addUnitToDb(Unit unit) throws SQLException{
        PreparedStatement pstm = getInstance().getConnection().prepareStatement(unit.getInsertQuery());
        pstm.setString(1, unit.getAppartmentNumber());
        pstm.setDouble(2, unit.getSize());
        pstm.setString(3, unit.getDetails());
        pstm.setInt(4, unit.getPropertyId());
        pstm.execute();
        int bankId = DatabaseServices.getLastAutoIncrement("unit");
        unit.setUnitId(bankId);
    }
    /**
     * 
     * @param model
     * @throws SQLException 
     */
    public static void updateModelToDatabase(Model model) throws SQLException{
        if(model.getClass() == Bank.class){
            updateBankFromDb((Bank)model);
        }
        else if(model.getClass() == Condo.class){
            updateCondoFromDb((Condo)model);
        }
        else if(model.getClass() == Contractor.class){
            updateContractorFromDb((Contractor)model);
        }
        else if(model.getClass() == House.class){
            updateHouseFromDb((House)model);
        }
        else if(model.getClass() == Lease.class){
            updateLeaseFromDb((Lease)model);
        }
        else if(model.getClass() == Maintenance.class){
            updateMaintenanceFromDb((Maintenance)model);
        }
        else if(model.getClass() == Mortgage.class){
            updateMortgageFromDb((Mortgage)model);
        }
        else if(model.getClass() == Plex.class){
            updatePlexFromDb((Plex)model);
        }
        else if(model.getClass() == Tenant.class){
            updateTenantFromDb((Tenant)model);
        }
        else if(model.getClass() == Unit.class){
            updateUnitFromDb((Unit)model);
        }
    }
    /**
     * 
     * @param bank
     * @throws SQLException 
     */
    private static void updateBankFromDb(Bank bank) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(bank.getUpdateQuery());
        pstm.setString(1, bank.getBankName());
        pstm.setString(2, bank.getAddress());
        pstm.setString(3, bank.getPhone());
        pstm.setString(4, bank.getEmail());
        pstm.setInt(5, bank.getBankId());
        pstm.execute();
    }
    private static void updateCondoFromDb(Condo condo) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(condo.getUpdateQuery());
        pstm.setString(1, condo.getAddress());
        pstm.setString(2, condo.getCountry());
        pstm.setString(3, condo.getState());
        pstm.setString(4, condo.getCity());
        pstm.setString(5, condo.getPostalCode());
        pstm.setString(6, condo.getDetails());
        pstm.setString(7, condo.getAppartmentNumber());
        pstm.setDouble(8, condo.getSize());
        pstm.setDouble(9, condo.getCondoFee());
        pstm.setInt(10, condo.getPropertyId());
        pstm.execute();
    }
    private static void updateContractorFromDb(Contractor contractor) throws SQLException{
        DatabaseServices service = DatabaseServices.getInstance();
        Connection conn = service.getConnection();
        PreparedStatement pstm = conn.prepareStatement(contractor.getUpdateQuery());
        pstm.setString(1, contractor.getContactName());
        pstm.setString(2, contractor.getCompanyName());
        pstm.setString(3, contractor.getAddress());
        pstm.setString(4, contractor.getSpecialty());
        pstm.setString(5, contractor.getPhone());
        pstm.setString(6, contractor.getEmail());
        pstm.setInt(7, contractor.getContractorId());
        pstm.execute();
    }
    /**
     * 
     * @param house
     * @throws SQLException 
     */
    private static void updateHouseFromDb(House house) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(house.getUpdateQuery());
        pstm.setString(1, house.getAddress());
        pstm.setString(2, house.getCountry());
        pstm.setString(3, house.getState());
        pstm.setString(4, house.getCity());
        pstm.setString(5, house.getPostalCode());
        pstm.setString(6, house.getDetails());
        pstm.setDouble(7, house.getSize());
        pstm.setInt(8, house.getPropertyId());
        pstm.execute();
    }
    private static void updateLeaseFromDb(Lease house) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(house.getUpdateQuery());
        pstm.setInt(1, house.getPropertyId());
        pstm.setInt(2, house.getTenantId());
        pstm.setString(3, house.getExtension());
        pstm.setInt(4, house.getLeaseId());
        pstm.execute();
    }
    private static void updateMaintenanceFromDb(Maintenance maintenance) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(maintenance.getUpdateQuery());
        pstm.setString(1, maintenance.getMaintenanceType());
        pstm.setString(2, maintenance.getStartDate());
        pstm.setString(3, maintenance.getEndDate());
        pstm.setDouble(4, maintenance.getTotalCost());
        pstm.setString(5, maintenance.getDetails());
        pstm.setInt(6, maintenance.getPropertyId());
        pstm.setDouble(7, maintenance.getContractorId());
        pstm.setInt(8, maintenance.getMaintenanceId());
        pstm.execute();
    }
    private static void updateMortgageFromDb(Mortgage mortgage) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(mortgage.getUpdateQuery());
        pstm.setString(1, mortgage.getStartDate());
        pstm.setString(2, mortgage.getEndDate());
        pstm.setDouble(3, mortgage.getMonthlyPayment());
        pstm.setInt(4, mortgage.getNumOfYearsContract());
        pstm.setDouble(5, mortgage.getTotalLoanValue());
        pstm.setDouble(6, mortgage.getAmountPaid());
        pstm.setDouble(7, mortgage.getInterestRate());
        pstm.setInt(8, mortgage.getBankId());
        pstm.setInt(9, mortgage.getPropertyId());
        pstm.setInt(10, mortgage.getMortgageId());
        pstm.execute();
    }
    private static void updatePlexFromDb(Plex plex) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(plex.getUpdateQuery());
        pstm.setString(1, plex.getAddress());
        pstm.setString(2, plex.getCountry());
        pstm.setString(3, plex.getState());
        pstm.setString(4, plex.getCity());
        pstm.setString(5, plex.getPostalCode());
        pstm.setString(6, plex.getDetails());
        pstm.setInt(7, plex.getPropertyId());
        pstm.execute();
    }
    private static void updateTenantFromDb(Tenant tenant) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(tenant.getUpdateQuery());
        pstm.setString(1, tenant.getFirstName());
        pstm.setString(2, tenant.getLastName());
        pstm.setString(3, tenant.getPhone());
        pstm.setString(4, tenant.getEmail());
        pstm.setInt(5, tenant.getTenantId());
        pstm.execute();
    }
    private static void updateUnitFromDb(Unit unit) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(unit.getUpdateQuery());
        pstm.setString(1, unit.getAppartmentNumber());
        pstm.setDouble(2, unit.getSize());
        pstm.setString(3, unit.getDetails());
        pstm.setInt(4, unit.getUnitId());
        pstm.execute();
    }
    /**
     * 
     * @param model
     * @throws SQLException 
     */
    
    
    public static void deleteModelFromDb(Model model) throws SQLException{
        if(model.getClass() == Bank.class){
            deleteBankFromDb((Bank)model);
        }
        else if(model.getClass() == Condo.class){
            deleteCondoFromDb((Condo)model);
        }
        else if(model.getClass() == Contractor.class){
            deleteContractorFromDb((Contractor)model);
        }
        else if(model.getClass() == House.class){
            deleteHouseFromDb((House)model);
        }
        else if(model.getClass() == Lease.class){
            deleteLeaseFromDb((Lease)model);
        }
        else if(model.getClass() == Maintenance.class){
            deleteMaintenanceFromDb((Maintenance)model);
        }
        else if(model.getClass() == Mortgage.class){
            deleteMortgageFromDb((Mortgage)model);
        }
        else if(model.getClass() == Plex.class){
            deletePlexFromDb((Plex)model);
        }
        else if(model.getClass() == Tenant.class){
            deleteTenantFromDb((Tenant)model);
        }
        else if(model.getClass() == Unit.class){
            deleteUnitFromDb((Unit)model);
        }
        else{
            throw new UnsupportedOperationException("The model is not defined for the function. Class "+model.getClass()+" was not found.");
        }
    }
    /**
     * 
     * @param bank
     * @throws SQLException 
     */
    private static void deleteBankFromDb(Bank bank) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(bank.getDeleteQuery());
        pstm.setInt(1, bank.getBankId());
        pstm.execute();
    }
    private static void deleteCondoFromDb(Condo condo) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(condo.getDeleteQuery());
        pstm.setInt(1, condo.getPropertyId());
        pstm.execute(); 
    }
    /**
     * 
     * @param contractor
     * @throws SQLException 
     */
    private static void deleteContractorFromDb(Contractor contractor) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(contractor.getDeleteQuery());
        pstm.setInt(1, contractor.getContractorId());
        pstm.execute();
    }
    /**
     * 
     * @param house
     * @throws SQLException 
     */
    private static void deleteHouseFromDb(House house) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(house.getDeleteQuery());
        pstm.setInt(1, house.getPropertyId());
        pstm.execute();        
    }
    private static void deleteLeaseFromDb(Lease lease) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(lease.getDeleteQuery());
        pstm.setInt(1, lease.getLeaseId());
        pstm.execute();        
    }
    private static void deleteMaintenanceFromDb(Maintenance maintenance) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(maintenance.getDeleteQuery());
        pstm.setInt(1, maintenance.getMaintenanceId());
        pstm.execute();
        System.out.println(maintenance.getMaintenanceId());
    }
    private static void deleteMortgageFromDb(Mortgage mortgage) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(mortgage.getDeleteQuery());
        pstm.setInt(1, mortgage.getMortgageId());
        pstm.execute();
    }
    private static void deletePlexFromDb(Plex plex) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(plex.getDeleteQuery());
        pstm.setInt(1, plex.getPropertyId());
        pstm.execute(); 
    }
    private static void deleteTenantFromDb(Tenant tenant) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(tenant.getDeleteQuery());
        pstm.setInt(1, tenant.getTenantId());
        pstm.execute(); 
    }
    private static void deleteUnitFromDb(Unit unit) throws SQLException{
        Connection conn = getInstance().getConnection();
        PreparedStatement pstm = conn.prepareStatement(unit.getDeleteQuery());
        pstm.setInt(1, unit.getUnitId());
        pstm.execute();
    }
    
    
    /**
     * 
     * @param model
     * @param query
     * @return 
     */
    public static ObservableList<? extends Model> loadModelsFromDb(Class model, String query) {
        if(model == Bank.class){
            return loadBanksFromDb(query);
        }
        else if(model == Condo.class){
            return loadCondosFromDb(query);
        }
        else if(model == Contractor.class){
            return loadContractorsFromDb(query);
        }
        else if(model == House.class){
            return loadHousesFromDb(query);
        }
        else if(model == Lease.class){
            return loadLeasesFromDb(query);
        }
        else if(model == Maintenance.class){
            return loadMaintenancesFromDb(query);
        }
        else if(model == Mortgage.class){
            return loadMortgagesFromDb(query);
        }
        else if(model == Plex.class){
            return loadPlexesFromDb(query);
        }
        else if(model == Tenant.class){
            return loadTenantsFromDb(query);
        }
        else if(model == Unit.class){
            return loadUnitsFromDb(query);
        }
        return null;
    }
    /**
     * 
     * @param query
     * @return 
     */
    private static ObservableList<? extends Model> loadBanksFromDb(String query){
        ObservableList<Bank> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service = DatabaseServices.getInstance();
            Connection connection    = service.getConnection();
            //String query  = "select * from bank";
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int bankId      = rs.getInt("bank_id");
                String bankName = rs.getString("bank_name");
                String address  = rs.getString("address");
                String phone    = rs.getString("phone");
                String email    = rs.getString("email");
                Bank bank       = new Bank(bankId, bankName, address, phone, email);
                obs.add(bank);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the banks.");
            e.printStackTrace();
        }
        return obs;
    }
    /**
     * 
     * @param query
     * @return 
     */
    private static ObservableList<? extends Model> loadCondosFromDb(String query){
        ObservableList<Condo> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service = DatabaseServices.getInstance();
            Connection connection      = service.getConnection();
            //String query  = "select * from property";
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int propertyId          = rs.getInt("property_id");
                String address          = rs.getString("address");
                String country          = rs.getString("country");
                String state            = rs.getString("state");
                String city             = rs.getString("city");
                String postalCode       = rs.getString("postal_code");
                String details          = rs.getString("details");
                String appartmentNumber = rs.getString("appartment_number");
                double size             = rs.getDouble("size");
                double condoFee         = rs.getDouble("condo_fee");
                Condo condo             = new Condo(propertyId, address, country, state, city, postalCode, details, appartmentNumber, size, condoFee);
                obs.add(condo);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the plexes.");
            e.printStackTrace();
        }
        return obs;
    }
    /**
     * 
     * @param query
     * @return 
     */
    private static ObservableList<? extends Model> loadContractorsFromDb(String query){
        ObservableList<Contractor> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service = DatabaseServices.getInstance();
            Connection connection      = service.getConnection();
            //String query  = "select * from contractor";
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int contractorId      = rs.getInt("contractor_id");
                String contactName    = rs.getString("contact_name");
                String companyName    = rs.getString("company_name");
                String address        = rs.getString("address");
                String specialty      = rs.getString("specialty");
                String phone          = rs.getString("phone");
                String email          = rs.getString("email");
                Contractor contractor = new Contractor(contractorId, contactName,companyName, address, specialty, phone, email);
                obs.add(contractor);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the contractors.");
            e.printStackTrace();
        }
        return obs;
    }
    /**
     * 
     * @param query
     * @return 
     */
    private static ObservableList<? extends Model> loadHousesFromDb(String query){
        ObservableList<House> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service   = DatabaseServices.getInstance();
            Connection connection      = service.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int propertyId    = rs.getInt("property_id");
                String address    = rs.getString("address");
                String country    = rs.getString("country");
                String state      = rs.getString("state");
                String city       = rs.getString("city");
                String postalCode = rs.getString("postal_code");
                String details    = rs.getString("details");
                int size          = rs.getInt("size");
                House house       = new House(propertyId, address, country, state, city, postalCode, details, size);
                obs.add(house);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the houses.");
            e.printStackTrace();
        }
        return obs;
    }
    private static ObservableList<? extends Model> loadLeasesFromDb(String query){
        ObservableList<Lease> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service   = DatabaseServices.getInstance();
            Connection connection      = service.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int leaseId            = rs.getInt("lease_id");
                int propertyId         = rs.getInt("property_id");
                String propertyAddress = rs.getString("address");
                int tenantId           = rs.getInt("tenant_id");
                String firstName      = rs.getString("first_name");
                String lastName        = rs.getString("last_name");
                String extension       = rs.getString("extension");
                Lease lease            = new Lease(leaseId, propertyId, propertyAddress, tenantId, firstName, lastName, extension);
                obs.add(lease);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the leases.");
            e.printStackTrace();
        }
        return obs;
    }
    /**
     * 
     * @param query
     * @return 
     */
    private static ObservableList<? extends Model> loadMaintenancesFromDb(String query){
        ObservableList<Maintenance> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service   = DatabaseServices.getInstance();
            Connection connection      = service.getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int maintenanceId            = rs.getInt("maintenance_id");
                String maintenanceType       = rs.getString("maintenance_type");
                String startDate             = rs.getString("start_date");
                String endDate               = rs.getString("end_date");
                double totalCost             = rs.getDouble("total_cost");
                String details               = rs.getString("details");
                int propertyId               = rs.getInt("property_id");
                String propertyAddress       = rs.getString("address");
                int contractorId             = rs.getInt("contractor_id");
                String contractorCompanyName = rs.getString("company_name");
                Maintenance maintenance      = new Maintenance(
                        maintenanceId, 
                        maintenanceType, 
                        startDate, 
                        endDate, 
                        totalCost, 
                        details, 
                        propertyId, 
                        propertyAddress, 
                        contractorId, 
                        contractorCompanyName
                );
                
                obs.add(maintenance);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the maintenances.");
            e.printStackTrace();
        }
        return obs;
    }
    private static ObservableList<? extends Model> loadMortgagesFromDb(String query){
        ObservableList<Mortgage> obs = FXCollections.observableArrayList();
        try{
            Connection connection = DatabaseServices.getInstance().getConnection();
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while(rs.next()){
                int mortgageId = rs.getInt("mortgage_id");
                Date startDate = rs.getDate("start_date");
                Date endDate   = rs.getDate("end_date");
                double monthlyPayment = rs.getDouble("monthly_payment");
                int numYearsContract = rs.getInt("num_years_contract");
                double totalLoanValue = rs.getDouble("total_loan_value");
                double amountPaid = rs.getDouble("amount_paid");
                double interestRate = rs.getDouble("interest_rate");
                int bankId = rs.getInt("bank_id");
                String bankName = rs.getString("bank_name");
                int propertyId = rs.getInt("property_id");
                String propertyAddress = rs.getString("address");
                Mortgage mortgage = new Mortgage(
                        mortgageId, 
                        startDate.toString(),
                        endDate.toString(), 
                        monthlyPayment, 
                        numYearsContract, 
                        totalLoanValue,
                        amountPaid, 
                        interestRate, bankId,
                        bankName, 
                        propertyId, 
                        propertyAddress);
                obs.add(mortgage);
            }
        }catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the mortgages.");
            e.printStackTrace();
        }
        return obs;
    }
    /**
     * 
     * @param query
     * @return 
     */
    private static ObservableList<? extends Model> loadPlexesFromDb(String query){
        ObservableList<Plex> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service = DatabaseServices.getInstance();
            Connection connection      = service.getConnection();
            //String query  = "select * from property";
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int propertyId    = rs.getInt("property_id");
                String address    = rs.getString("address");
                String country    = rs.getString("country");
                String state      = rs.getString("state");
                String city       = rs.getString("city");
                String postalCode = rs.getString("postal_code");
                String details    = rs.getString("details");
                int numOfApprt    = rs.getInt("num_of_apprt");
                Plex plex         = new Plex(propertyId, address, country, state, city, postalCode, details, numOfApprt);
                obs.add(plex);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the plexes.");
            e.printStackTrace();
        }
        return obs;
    }
    private static ObservableList<? extends Model> loadTenantsFromDb(String query){
        ObservableList<Tenant> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service = DatabaseServices.getInstance();
            Connection connection      = service.getConnection();
            //String query  = "select * from property";
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int tenantId    = rs.getInt("tenant_id");
                String firstName= rs.getString("first_name");
                String lastName = rs.getString("last_name");
                String phone    = rs.getString("phone");
                String email    = rs.getString("email");
                Tenant tenant= new Tenant(tenantId, firstName, lastName, phone, email);
                obs.add(tenant);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the tenants.");
            e.printStackTrace();
        }
        return obs;
    }
    private static ObservableList<? extends Model> loadUnitsFromDb(String query){
        ObservableList<Unit> obs = FXCollections.observableArrayList();
        try{
            DatabaseServices service = DatabaseServices.getInstance();
            Connection connection    = service.getConnection();
            //String query  = "select * from property";
            Statement stm = connection.createStatement();
            ResultSet rs  = stm.executeQuery(query);
            while(rs.next()){
                int unitId              = Integer.parseInt(rs.getString("unit_id"));
                String appartmentNumber = rs.getString("appartment_number");
                double unitSize         = Double.parseDouble(rs.getString("size"));
                String details          = rs.getString("details");
                int propertyId          = rs.getInt("property_id");
                Unit unit               = new Unit(unitId, appartmentNumber, unitSize, details, propertyId);
                obs.add(unit);
            }
        } catch(SQLException e){
            System.out.println("Error with Database Connection: Could not load the plexes.");
            e.printStackTrace();
        }
        return obs;        
    }
}
