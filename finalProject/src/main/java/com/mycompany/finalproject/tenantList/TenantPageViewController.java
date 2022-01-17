/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.tenantList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Tenant;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class TenantPageViewController implements Initializable{
    
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private Button editSave;  
    @FXML private Label message;
    private Tenant tenant;
    
    @FXML
    private void close(ActionEvent event){
        Utility.getStage(event).close();
    }
    
    @FXML
    public void editSave(ActionEvent event){
        if(editSave.getText().equals("Save")){
            String firstName = this.firstName.getText();
            String lastName = this.lastName.getText();
            String phone = this.phone.getText();
            String email = this.email.getText();
            Tenant dbTestTenant = new Tenant(tenant.getTenantId(),firstName, lastName, phone, email);
            try{
                DatabaseServices.updateModelToDatabase(dbTestTenant);
            }catch(SQLException e){
                e.printStackTrace();
                message.setText("Error: An error has occurred in the database. Make sure your values are correct.");
            }
            tenant.setFirstName(firstName);
            tenant.setLastName(lastName);
            tenant.setPhone(phone);
            tenant.setEmail(email);
            editSave.setText("Edit");
            setTextFieldEditable(false);
            message.setText("");
        }else{
            editSave.setText("Save");
            setTextFieldEditable(true);
            message.setText("");
        }
    }
    
    private void setTextFieldEditable(boolean edit){
    firstName.setEditable(edit);
    lastName.setEditable(edit);
    phone.setEditable(edit);
    email.setEditable(edit);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }

    void setTenant(Tenant tenant) {
        this.tenant = tenant;
        firstName.setText(tenant.getFirstName());
        lastName.setText(tenant.getLastName());
        phone.setText(tenant.getPhone());
        email.setText(tenant.getEmail());
        
    }
    
}
