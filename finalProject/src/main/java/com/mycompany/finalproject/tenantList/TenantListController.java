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
import com.mycompany.finalproject.model.Tenant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class TenantListController implements Initializable{
    
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
    
    
    @FXML
    private void switchToHomePage(ActionEvent event) throws IOException{
           Utility.setRoot(event, "HomePage");
    }
    
    @FXML
    private void viewTenant(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("TenantPageView");
        Parent root = fxmlLoader.load();
        TenantPageViewController controller = (TenantPageViewController)fxmlLoader.getController();
        controller.setTenant(tenantTable.getSelectionModel().getSelectedItem());
        Scene scene = viewTenant.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 600);
            viewTenant.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        // Set position of second window, related to primary window.
        if(!viewTenant.isShowing()) {
            viewTenant.show();
        }
        viewTenant.toFront();
    }
    
    @FXML
    private void addTenant(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("TenantPageAdd");
        Parent root = fxmlLoader.load();
        TenantPageAddController controller = (TenantPageAddController)fxmlLoader.getController();
        controller.setTableView(tenantTable);
        Scene scene = addTenant.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 600);
            addTenant.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        // New window (Stage)
        // Set position of second window, related to primary window.
        if(!addTenant.isShowing()) {
            addTenant.show();
        }
        addTenant.toFront();
    }
    
    @FXML
    void deleteTenant(ActionEvent event){
        Tenant selected = tenantTable.getSelectionModel().getSelectedItem();
        int index = tenantTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            message.setText("Error: No bank selected.");
            return;
        }
        Stage stage = Utility.getStage(event);
        alert.setContentText("Are you sure you want to delete "+selected.getLastName()+", "+selected.getFirstName()+"?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            //delete
            try{
                DatabaseServices.deleteModelFromDb(selected);
                tenantList.remove(index);
            }
            catch(SQLException e){
                e.printStackTrace();
                message.setText("Error: Could not delete this bank in database.");
            }
        }
        message.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewTenant = new Stage();
        viewTenant.setTitle("Tenant Details");
        addTenant = new Stage();
        addTenant.setTitle("Add New Tenant");
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
    
    
}
