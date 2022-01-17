package com.mycompany.finalproject.contractorList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Contractor;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SelectContractorController implements Initializable{

    @FXML private TableView<Contractor>           contractorTable;
    @FXML private TableColumn<Contractor, String> columnContactName;
    @FXML private TableColumn<Contractor, String> columnCompanyName;
    @FXML private TableColumn<Contractor, String> columnAddress;
    @FXML private TableColumn<Contractor, String> columnSpecialty;
    @FXML private TableColumn<Contractor, String> columnPhone;
    @FXML private TableColumn<Contractor, String> columnEmail;
    @FXML private Label                           message;
          private Parent                          previous;
          private ContractorReceiver              contractorReceiver;
          private ObservableList<Contractor>      contractorList;

    @FXML
    public void selectContractor(ActionEvent event) throws IOException {
        Contractor selected = contractorTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            message.setText("Error: No selected row.");
            return;
        }
        contractorReceiver.setContractor(selected);
        back(event);
    }

    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }

    @FXML
    public void viewContractor(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectContractorView");
        Parent root = fxmlLoader.load();
        SelectContractorViewController controller = fxmlLoader.getController();
        Contractor selected = contractorTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No selected row.", "Red");
            return;
        }else{
            message.setText("");
        }
        controller.setContractorReceiver(contractorReceiver);
        controller.setContractor(selected);
        controller.setContractorReceiverRoot(previous);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);
    }
    
    public void setPrevious(Parent root){
        this.previous = root;
    }
    public void setContractorReceiver(ContractorReceiver contractorReceiver){
        this.contractorReceiver = contractorReceiver;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        columnContactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        columnCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //setup table to only have 1 selection at a time.
        contractorTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //insert the data in the table that were loaded from db.
        String allContractorsQuery = Contractor.getSelectQuery();
        contractorList = (ObservableList<Contractor>)DatabaseServices.loadModelsFromDb(Contractor.class, allContractorsQuery);
        contractorTable.setItems(contractorList);
    }

}
