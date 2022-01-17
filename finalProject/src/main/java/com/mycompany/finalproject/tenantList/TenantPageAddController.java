
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
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class TenantPageAddController implements Initializable{
    
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private Label message;
    TableView<Tenant> tenantTable;
    
    @FXML
    void add(ActionEvent event) {
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String phone = this.phone.getText();
        String email = this.email.getText();
        Tenant tenant = new Tenant(firstName, lastName, phone, email);
        try{
            DatabaseServices.addModelToDb(tenant);
        }catch(SQLException e){
            e.printStackTrace();
            message.setText("Error: New tenant information could not be added to the database.");
            return;
        }
        message.setText("");
        tenantTable.getItems().add(tenant);
        close(event);
    }
    
    public void setTableView(TableView<Tenant> tenantTable){
        this.tenantTable = tenantTable;
    }
    @FXML
    private void close(ActionEvent event){
        Utility.getStage(event).close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
}
