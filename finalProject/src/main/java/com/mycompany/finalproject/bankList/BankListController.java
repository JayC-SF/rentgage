/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class displays a Bank List for the user when the user wants
 * to look at the list of the banks. It will open other views depending on what
 * the user clicks on. For example view bank, add bank, etc.
 * 
 * HOW TO USE:
 * It needs to be used with the FXMLLoader object. It is associated with a view,
 * so the @FXML properties variables will be initialized with the FXMLLoader.
 * Once done, it get can retrieved with the getController() method in order to
 * initialize many properties. It may have some getter and setters potentially.
 * 
 */
package com.mycompany.finalproject.bankList;
import com.mycompany.finalproject.model.Bank;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;


/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class BankListController implements Initializable{
    
    @FXML private TableView<Bank>           bankTable;
    @FXML private TableColumn<Bank, String> columnBankName;
    @FXML private TableColumn<Bank, String> columnAddress;
    @FXML private TableColumn<Bank, String> columnPhone;
    @FXML private TableColumn<Bank, String> columnEmail;
    @FXML private Label                     message;
          private Stage                     viewBank;
          private Stage                     addBank;
          private ObservableList<Bank>      bankList;
          private Alert                     alert;
    
    /**
     * It switches the view to the homepage.
     * @throws IOException 
     */
    @FXML
    private void switchToHomePage(ActionEvent event) throws IOException{
           Utility.setRoot(event,"HomePage");
    }
    
    
    /**
     * This method will open a new stage to display the bank currently selected 
     * from the user.
     * @throws IOException 
     */
    @FXML 
    private void viewBank(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("BankPageView");

        Parent root = fxmlLoader.load();
        BankPageViewController controller = (BankPageViewController) fxmlLoader.getController();
        
        Bank selected = bankTable.getSelectionModel().getSelectedItem();
        //reset the error message in case it was not empty.
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No selected row.", "Red");
            return;
        }else{
            message.setText("");
        }
            
        //PASS INFORMATION TO THE CONTROLLER METHOD
        controller.setBank(selected);
        Scene scene = viewBank.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 500);
            viewBank.setScene(scene);
        } else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        if(!viewBank.isShowing()) {
            viewBank.show();
        }
        else{
            viewBank.toFront();
        }
    }
    
    /**
     * This method will open a new page so the user can add the necessary information
     * in order to create a new bank.
     * @throws IOException 
     */
    @FXML
    private void addBank(ActionEvent event) throws IOException{
        // Load the fxml page from the url.
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("BankPageAdd");
        
        // Load the page as a javafx object. Usually a VBOX.
        Parent root = fxmlLoader.load();
        // Get the controller associated with this view.
        BankPageAddController controller =(BankPageAddController) fxmlLoader.getController();
        controller.setTableView(bankTable);
        controller.setResult(message);
        //Reset the error message text in case it was not empty.
        message.setText("");
        // Initialize the properties with the current stage. At that point
        // the initialize method should've been called by the FXMLLoader of this
        // Check if the scene currently has a scene.
        Scene scene = addBank.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 500);
            addBank.setScene(scene);
        } else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        if(!addBank.isShowing()) {
            addBank.show();
        }
        else{
            addBank.toFront();
        }
    }
    /**
     * This method deletes a bank.
     * @param event 
     */
    @FXML
    private void deleteBank(ActionEvent event) {
        Bank selected = bankTable.getSelectionModel().getSelectedItem();
        int index = bankTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No bank selected.", "Red");
            return;
        }
        Stage stage = Utility.getStage(event);
        alert.setContentText("Are you sure you want to delete "+selected.getBankName()+"?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            //delete
            try{
                DatabaseServices.deleteModelFromDb(selected);
                bankList.remove(index);
            }
            catch(SQLException e){
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: Could not delete this bank in database.", "Red");
                return;
            }
        }
        Utility.setLabelMessageAndColor(message, "Sucessfully deleted the bank.", "Green");
        
    }
    
    /**
     * This method initializes the property for this controller. These property 
     * are necessary in order to open the new stages.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewBank = new Stage();
        addBank  = new Stage();
        viewBank.setTitle("Bank Details");
        addBank.setTitle("Add New Bank");
        String allBanksQuery = Bank.getSelectQuery();
        bankList = (ObservableList<Bank>)DatabaseServices.loadModelsFromDb(Bank.class, allBanksQuery);
        columnBankName.setCellValueFactory(new PropertyValueFactory<Bank, String>("bankName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<Bank, String>("address"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Bank, String>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<Bank, String>("email"));
        bankTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        bankTable.setItems(bankList);
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }
}
