/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.tenantList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Tenant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class SelectTenantViewController implements Initializable{
    
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private Label message;    
    private TenantReceiver tenantReceiver;    
    private Parent previous;    
    private Parent tenantReceiverRoot;  
    private Tenant tenant;
    
    @FXML
    public void back(ActionEvent event) throws IOException{
        Utility.setRoot(event, previous);
    }
    
    @FXML
    public void select(ActionEvent event) throws IOException{
        tenantReceiver.setTenant(tenant);
        Utility.setRoot(event, tenantReceiverRoot);
    }


    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
        firstName.setText(tenant.getFirstName());
        lastName.setText(tenant.getLastName());
        phone.setText(tenant.getPhone());
        email.setText(tenant.getEmail());
    }
    public void setTenantReceiver(TenantReceiver tenantReceiver){
        this.tenantReceiver = tenantReceiver;
    }
    
    /**
     * 
     * @param root 
     */
    public void setPrevious(Parent root) {
        this.previous = root;
    }
    public void setTenantReceiverRoot(Parent tenantReceiverRoot) {
        this.tenantReceiverRoot = tenantReceiverRoot;
    }
        
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
}
