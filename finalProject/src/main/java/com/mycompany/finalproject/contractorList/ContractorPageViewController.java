/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.contractorList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Contractor;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author JCSF
 */
public class ContractorPageViewController implements Initializable{
     @FXML
    private TextField contactName;

    @FXML
    private TextField companyName;

    @FXML
    private TextField address;

    @FXML
    private TextField specialty;

    @FXML
    private TextField phone;

    @FXML
    private TextField email;
    
    @FXML
    private Label message;
    
    @FXML
    private Button editSave;
    
    private Contractor contractor;
    private TableView<Contractor> contractorTable;
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void close(ActionEvent event){
        Stage stage = Utility.getStage(event);
        stage.close();
    }
    
    /**
     * 
     * @param event 
     */
    @FXML
    private void editSave(ActionEvent event) {
        if(contactName.isEditable()){
            try{
                Contractor dbTestContractor = new Contractor(contractor.getContractorId(), contactName.getText(), companyName.getText(), address.getText(), specialty.getText(), phone.getText(), email.getText());
                DatabaseServices.updateModelToDatabase(dbTestContractor);
            }
            catch(SQLException e){
                e.printStackTrace();
                message.setText("An error has occurred with the database.");
                return;
            }
            contractor.setContactName(contactName.getText());
            contractor.setCompanyName(companyName.getText());
            contractor.setAddress(address.getText());
            contractor.setSpecialty(specialty.getText());
            contractor.setPhone(phone.getText());
            contractor.setEmail(email.getText());
            editSave.setText("Edit");
            setTextFieldEditable(false);
            message.setText("");
            
        }
        else{
            editSave.setText("Save");
            setTextFieldEditable(true);
            message.setText("");
        }
    }
    private void setTextFieldEditable(boolean edit){
        contactName.setEditable(edit);
        companyName.setEditable(edit);
        address.setEditable(edit);
        specialty.setEditable(edit);
        phone.setEditable(edit);
        email.setEditable(edit);
        
    }
    public void setTableView(TableView<Contractor> table){
        this.contractorTable = table;
    }
    
    public void setContractor(Contractor contractor){
        this.contractor = contractor;
        contactName.setText(contractor.getContactName());
        companyName.setText(contractor.getCompanyName());
        address.setText(contractor.getAddress());
        specialty.setText(contractor.getSpecialty());
        phone.setText(contractor.getPhone());
        email.setText(contractor.getEmail());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
}
