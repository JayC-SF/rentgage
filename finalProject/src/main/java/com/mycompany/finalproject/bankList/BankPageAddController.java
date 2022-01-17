/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class displays a Add Bank Page for the user when the user wants
 * to add a bank to the database. It will take the inputs from the user and add
 * them accordingly.
 * 
 * HOW TO USE:
 * It needs to be used with the FXMLLoader object. It is associated with a view,
 * so the @FXML properties variables will be initialized with the FXMLLoader.
 * Once done, it get can retrieved with the getController() method in order to
 * initialize the stage and many more variables.
 * 
 */
package com.mycompany.finalproject.bankList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Bank;
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
public class BankPageAddController implements Initializable{
    TableView<Bank>         bankTable;
    @FXML private Label     textFieldBank;
    @FXML private TextField bankName;
    @FXML private TextField address;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private Button    add;
    @FXML private Label     message;
    private Label result;
    /**
     * This method is used to close the view for the user when the user
     * clicks on close or add. 
     */
    @FXML
    private void close(ActionEvent event){
        Utility.getStage(event).close();
    }
    
    /**
     * This method adds a bank to the database. And shows a new bank 
     * in the user interface as well.
     * @param event 
     */
    @FXML
    private void add(ActionEvent event){
       String bankName = this.bankName.getText();
       String address  = this.address.getText();
       String phone    = this.phone.getText();
       String email    = this.email.getText();
       Bank bank = new Bank(bankName, address, phone, email);
       try{
           DatabaseServices.addModelToDb(bank);
       } catch(SQLException e){
           e.printStackTrace();
           Utility.setLabelMessageAndColor(message, "Error: New bank information could not be added to the database.", "Red");
           return;
       }
       Utility.setLabelMessageAndColor(result, "Bank successfully created.", "Green");
       bankTable.getItems().add(bank);
        close(event);
    }
    
    /**
     * Setter for TableView in the caller of this controller.
     * @param table 
     */
    public void setTableView(TableView<Bank> table){
        bankTable = table;
    }
    
    /**
     * This method initializes the necessary things for displaying the content properly.
     * Essentially just like a constructor taking no inputs. This method will initialize 
     * any properties of this controller that needs to be initialized independently from 
     * inputs of the creator of this instance.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
      
    }

    void setResult(Label message) {
        this.result = message;
    }
}
