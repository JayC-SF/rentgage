package com.mycompany.finalproject.bankList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Bank;
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

public class SelectBankController implements Initializable{

    @FXML private TableView<Bank>           bankTable;
    @FXML private TableColumn<Bank, String> columnBankName;
    @FXML private TableColumn<Bank, String> columnAddress;
    @FXML private TableColumn<Bank, String> columnPhone;
    @FXML private TableColumn<Bank, String> columnEmail;
    @FXML private Label                     message;
          private Parent                    previous;
          private BankReceiver              bankReceiver;
          private ObservableList<Bank>      bankList;
    
    /**
     * This method sets the previous root of the next root/
     * @param root 
     */
    public void setPrevious(Parent root){
        this.previous = root;
    }
    /**
     * This method will set the receiver of the bank, once the bank is 
     * selected.
     * @param bankReceiver 
     */
    public void setBankReceiver(BankReceiver bankReceiver) {
        this.bankReceiver = bankReceiver;
    }
    /**
     * This method will return to the previous root.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }
    /**
     * This method will select the bank selected by the user, 
     * and return back to the page dealing with the information of the selected bank
     * @param event
     * @throws IOException 
     */
    @FXML
    public void selectBank(ActionEvent event) throws IOException {
        Bank selected = bankTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            message.setText("Error: No selected row.");
            return;
        }
        bankReceiver.setBank(selected);
        back(event);
    }
    /**
     * This method will display the currently selected bank.
     * @param event 
     */
    @FXML
    public void viewBank(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectBankView");
        Parent root = fxmlLoader.load();
        SelectBankViewController controller = fxmlLoader.getController();
        Bank selected = bankTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No selected row.", "Red");
            return;
        }else{
            message.setText("");
        }
        controller.setBankReceiver(bankReceiver);
        controller.setBank(selected);
        controller.setBankReceiverRoot(previous);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);
    }
    /**
     * This method initializes the tableView in order to display all of the banks
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String allBanksQuery = Bank.getSelectQuery();
        bankList = (ObservableList<Bank>)DatabaseServices.loadModelsFromDb(Bank.class, allBanksQuery);
        columnBankName.setCellValueFactory(new PropertyValueFactory<Bank, String>("bankName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Bank, String>("address"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Bank, String>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Bank, String>("email"));
        bankTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        bankTable.setItems(bankList);
    }
}
