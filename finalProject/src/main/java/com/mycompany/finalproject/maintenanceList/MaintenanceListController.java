/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.maintenanceList;
import com.mycompany.finalproject.App;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.mycompany.finalproject.model.Maintenance;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 *
 * @author JCSF
 */
public class MaintenanceListController implements Initializable{
    
    @FXML private TableView<Maintenance> maintenanceTable;
    @FXML private TableColumn<Maintenance, String> columnContractorCompanyName;
    @FXML private TableColumn<Maintenance, String> columnPropertyAddress;
    @FXML private TableColumn<Maintenance, String> details;
    @FXML private TableColumn<Maintenance, String> columnStartDate;
    @FXML private TableColumn<Maintenance, String> columnEndDate;
    @FXML private Label                            message;
          private Stage                            viewMaintenance;
          private Stage                            addMaintenance;
          private ObservableList<Maintenance>      maintenanceList;
          private Alert                            alert;
    
    
    @FXML
    private void switchToHomePage(ActionEvent event) throws IOException{
           App.setRoot("HomePage");
    }
    
    @FXML 
    private void viewMaintenance(ActionEvent event) throws IOException{
        Maintenance selected = maintenanceTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No maintenance selected.", "Red");
            return;
        }else{
            message.setText("");
        }
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("MaintenancePageView");
        Parent root = fxmlLoader.load();
        MaintenancePageViewController controller = fxmlLoader.getController();
        controller.setMaintenance(selected);
        Scene scene = viewMaintenance.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 750);
            viewMaintenance.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        viewMaintenance.showAndWait();
    }
    
    @FXML
    private void addMaintenance(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("MaintenancePageAdd");
        Parent root = fxmlLoader.load();
        
        MaintenancePageAddController controller = fxmlLoader.getController();
        controller.setTableView(maintenanceTable);
        controller.setResult(message);
        Scene scene = addMaintenance.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 750);
            addMaintenance.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        addMaintenance.showAndWait();
        
    }
    @FXML
    void deleteMaintenance(ActionEvent event) {
        Maintenance selected = maintenanceTable.getSelectionModel().getSelectedItem();
        int index = maintenanceTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No maintenance selected.", "Red");
            return;
        }
        alert.setContentText("Are you sure you want to delete the selected maintenance ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            try{
                DatabaseServices.deleteModelFromDb(selected);
                maintenanceList.remove(index);
                Utility.setLabelMessageAndColor(message, "Successfully removed the maintenance.", "Green");
            } catch (SQLException e) {
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: Could not delete this maintenance in the database.", "Red");
            }
        }else{
            message.setText("");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb){
        alert           = new Alert(Alert.AlertType.CONFIRMATION);
        addMaintenance  = new Stage();
        viewMaintenance = new Stage();
        addMaintenance.setTitle("Add New Maintenance");
        viewMaintenance.setTitle("Maintenance Details");
        addMaintenance.initModality(Modality.APPLICATION_MODAL);
        viewMaintenance.initModality(Modality.APPLICATION_MODAL);
        
        
        String allMaintenancesQuery = Maintenance.getSelectQuery();
        maintenanceList = (ObservableList<Maintenance>) DatabaseServices.loadModelsFromDb(Maintenance.class, allMaintenancesQuery);
        columnContractorCompanyName.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("contractorCompanyName"));
        columnPropertyAddress.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("propertyAddress"));
        details.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("details"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("startDate"));
        columnEndDate.setCellValueFactory(new PropertyValueFactory<Maintenance, String>("endDate"));
        
        maintenanceTable.setItems(maintenanceList);
    
    }
    
}
