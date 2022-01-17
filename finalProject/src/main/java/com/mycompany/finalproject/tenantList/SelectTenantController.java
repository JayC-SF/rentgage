/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.tenantList;
import com.mycompany.finalproject.App;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.bankList.BankPageViewController;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.leaseList.LeasePageAddController;
import com.mycompany.finalproject.model.Tenant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.time.temporal.TemporalAdjusters.previous;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author JCSF
 */
public class SelectTenantController implements Initializable{
    
    @FXML private TableView<Tenant> tenantTable;
    @FXML private TableColumn<Tenant, String> columnLastName;
    @FXML private TableColumn<Tenant, String> columnFirstName;
    @FXML private TableColumn<Tenant, String> columnPhone;
    @FXML private TableColumn<Tenant, String> columnEmail;
    @FXML private Label                       message;
          private ObservableList<Tenant>      tenantList;
          private Stage                       viewTenant;
          private Stage                       addTenant;
          private Alert                       alert;
    private Parent previous;
    private TenantReceiver tenantReceiver;
    
    
    @FXML
    private void back(ActionEvent event) throws IOException{
           Utility.setRoot(event, previous);
    }
    /**
     * 
     * @param root 
     */
    public void setPrevious(Parent root){
        this.previous = root;
    }
        /**
     * 
     * @param bankReceiver 
     */
    public void setBankReceiver(TenantReceiver tenantReceiver) {
        this.tenantReceiver = tenantReceiver;
    }
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    public void selectTenant(ActionEvent event) throws IOException {
        Tenant selected = tenantTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            message.setText("Error: No selected row.");
            return;
        }
        tenantReceiver.setTenant(selected);
        back(event);
    }
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void viewTenant(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectTenantView");
        Parent root = fxmlLoader.load();
        SelectTenantViewController controller = fxmlLoader.getController();
        controller.setTenant(tenantTable.getSelectionModel().getSelectedItem());
        controller.setTenantReceiver(tenantReceiver);
        controller.setTenantReceiverRoot(previous);
        controller.setPrevious(Utility.getRoot(event));
        Scene scene = viewTenant.getScene();
        Utility.setRoot(event, root);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewTenant = new Stage();
        viewTenant.setTitle("Tenant Details");
        String allTenantsQuery = Tenant.getSelectQuery();
        tenantList = (ObservableList<Tenant>) DatabaseServices.loadModelsFromDb(Tenant.class, allTenantsQuery);
        columnFirstName.setCellValueFactory(new PropertyValueFactory<Tenant, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<Tenant, String>("lastName"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Tenant, String>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Tenant, String>("email"));
        tenantTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        tenantTable.setItems(tenantList);
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

    public void setTenantReceiver(TenantReceiver tenantReceiver) {
        this.tenantReceiver = tenantReceiver;
    }
    
    
}
