package com.mycompany.finalproject.mortgageList;

import com.mycompany.finalproject.model.Mortgage;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;

public class MortgageListController implements Initializable{

    @FXML private TableView<Mortgage>           mortgageTable;
    @FXML private TableColumn<Mortgage, String> columnBankName;
    @FXML private TableColumn<Mortgage, String> columnPropertyAddress;
    @FXML private TableColumn<Mortgage, String> columnStartDate;
    @FXML private TableColumn<Mortgage, String> columnEndDate;
    @FXML private Label                         message;
          private Stage                         addMortgage;
          private Stage                         viewMortgage;
          private ObservableList<Mortgage>      mortgageList;
          private Alert                         alert;
    
    
    @FXML
    void addMortgage(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("MortgagePageAdd");
        Parent root = fxmlLoader.load();
        MortgagePageAddController controller = (MortgagePageAddController) fxmlLoader.getController();
        
        controller.setTableView(mortgageTable);
        controller.setResult(message);
        message.setText("");
        
        Scene scene = addMortgage.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 850);
            addMortgage.setScene(scene);
        } else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        addMortgage.showAndWait();
    }

    @FXML
    void deleteMortgage(ActionEvent event) {
        Mortgage selected = mortgageTable.getSelectionModel().getSelectedItem();
        int index = mortgageTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No mortgage selected.", "Red");
            return;
        }
        alert.setContentText("Are you sure you want to delete the selected mortgage ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            try{
                DatabaseServices.deleteModelFromDb(selected);
                mortgageList.remove(index);
                Utility.setLabelMessageAndColor(message, "Successfully removed the mortgage.", "Green");
            } catch (SQLException e) {
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: Could not delete this mortgage in the database.", "Red");
            }
        }else{
            message.setText("");
        }
    }

    @FXML
    void switchToHomePage(ActionEvent event) throws IOException{
        Utility.setRoot(event, "HomePage");
    }

    @FXML
    void viewMortgage(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("MortgagePageView");
        Parent root = fxmlLoader.load();
        MortgagePageViewController controller = (MortgagePageViewController) fxmlLoader.getController();
        
        Mortgage selected = mortgageTable.getSelectionModel().getSelectedItem();
        
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No mortgage selected.", "Red");
            return;
        }else{
            message.setText("");
        }
        
        controller.setMortgage(selected);
        Scene scene = viewMortgage.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 850);
            viewMortgage.setScene(scene);
        } else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        viewMortgage.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alert        = new Alert(Alert.AlertType.CONFIRMATION);
        addMortgage  = new Stage();
        viewMortgage = new Stage();
        addMortgage.setTitle("Add New Mortgage");
        viewMortgage.setTitle("Mortgage Details");
        addMortgage.initModality(Modality.APPLICATION_MODAL);
        viewMortgage.initModality(Modality.APPLICATION_MODAL);
        String allMortgagesQuery = Mortgage.getSelectQuery();
        mortgageList = (ObservableList<Mortgage>) DatabaseServices.loadModelsFromDb(Mortgage.class, allMortgagesQuery);
        columnBankName.setCellValueFactory(new PropertyValueFactory<Mortgage, String>("bankName"));
        columnPropertyAddress.setCellValueFactory(new PropertyValueFactory<Mortgage, String>("propertyAddress"));
        columnStartDate.setCellValueFactory(new PropertyValueFactory<Mortgage, String>("startDate"));
        columnEndDate.setCellValueFactory(new PropertyValueFactory<Mortgage, String>("endDate"));
        mortgageTable.setItems(mortgageList);
    }

}
