/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.leaseList;
import com.mycompany.finalproject.App;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Lease;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.HostServices;
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
public class LeaseListController implements Initializable{
    
    @FXML private TableView<Lease> leaseTable;
    @FXML private TableColumn<Lease, String> columnPropertyAddress;
    @FXML private TableColumn<Lease, String> columnTenantFirstName;
    @FXML private TableColumn<Lease, String> columnTenantLastName;
    @FXML private Label                      message;
          private Alert                      alert;
          private ObservableList<Lease>      leaseList;
          private Stage                      viewLease;
          private Stage                      addLease;
    
    @FXML
    private void switchToHomePage(ActionEvent event) throws IOException{
           App.setRoot("HomePage");
    }
    
    @FXML 
    private void viewLease(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("LeasePageView");
        Parent root = fxmlLoader.load();
        LeasePageViewController controller = (LeasePageViewController) fxmlLoader.getController();
        
        Lease selected = leaseTable.getSelectionModel().getSelectedItem();
        
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No Lease selected.", "Red");
            return;
        }else{
            message.setText("");
        }
        
        controller.setLease(selected);
        Scene scene = viewLease.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 700);
            viewLease.setScene(scene);
        } else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        viewLease.showAndWait();
        /*Lease selected = leaseTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No lease selected.", "Red");
            return;
        }
        else{
            message.setText("");
        }
        File leaseFile = new File(Lease.LEASE_URL+selected.getLeaseId()+"."+selected.getExtension());
        App.getHostServicesInstance().showDocument(leaseFile.getAbsolutePath());*/
    }
    
    @FXML
    private void viewLeasePdf(ActionEvent event){
        Lease selected = leaseTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No lease selected.", "Red");
            return;
        }
        else{
            message.setText("");
        }
        File leaseFile = new File(Lease.LEASE_URL+selected.getLeaseId()+"."+selected.getExtension());
        App.getHostServicesInstance().showDocument(leaseFile.getAbsolutePath());
    }
    
    @FXML
    private void addLease(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("LeasePageAdd");
        Parent root = fxmlLoader.load();
        LeasePageAddController controller = fxmlLoader.getController();
        
        controller.setTableView(leaseTable);
        Scene scene = addLease.getScene();
        
        if(scene == null){
            scene = new Scene(root, 700, 700);
            addLease.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        //addLease.setScene(scene);
        // Set position of second window, related to primary window.
        if(!addLease.isShowing()) {
            addLease.show();
        }
        addLease.toFront();
    }
    @FXML 
    private void deleteLease(ActionEvent event){
        Lease selected = leaseTable.getSelectionModel().getSelectedItem();
        int index = leaseTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No lease selected.", "Red");
            return;
        }
        alert.setContentText("Are you sure you want to delete the selected lease ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            try{
                DatabaseServices.deleteModelFromDb(selected);
                File selectedFile = new File(Lease.LEASE_URL+selected.getLeaseId()+"."+selected.getExtension());
                leaseList.remove(index);
                Utility.setLabelMessageAndColor(message, "Successfully removed the lease.", "Green");
            } catch (SQLException e) {
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: Could not delete this lease in the database.", "Red");
            }
        }else{
            message.setText("");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb){
        viewLease = new Stage();
        addLease = new Stage();
        viewLease.setTitle("Lease details");
        addLease.setTitle("Add New Lease");
        String allLeasesQuery = Lease.getSelectQuery();
        leaseList = (ObservableList<Lease>)DatabaseServices.loadModelsFromDb(Lease.class, allLeasesQuery);
        columnPropertyAddress.setCellValueFactory(new PropertyValueFactory<Lease, String>("propertyAddress"));
        columnTenantFirstName.setCellValueFactory(new PropertyValueFactory<Lease, String>("tenantFirstName"));
        columnTenantLastName.setCellValueFactory(new PropertyValueFactory<Lease, String>("tenantLastName"));
        leaseTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        leaseTable.setItems(leaseList);
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }
    
    
}
