/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class displays a View Bank Page for the user when the user wants
 * to view a specific bank. It will simply show the necessary data that the user
 * wants to see.
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
import java.sql.*;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class BankPageViewController implements Initializable{
    @FXML private TextField bankName;
    @FXML private TextField address;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private Button    editSave;
    @FXML private Label     message;
    private Bank            bank;
    private TableView<Bank> bankTable;
    
    /**
     * This method closes the current stage.
     * @param event 
     */
    @FXML
    private void close(ActionEvent event){
        Stage stage = Utility.getStage(event);
        stage.close();
    }
    /**
     * This method saves the changes made on a current bank.
     * @param event 
     */
    @FXML
    private void editSave(ActionEvent event){
        if(bankName.isEditable()){
            try{
                Bank dbTestBank = new Bank(bank.getBankId(), bankName.getText(), address.getText(), phone.getText(), email.getText());
                DatabaseServices.updateModelToDatabase(dbTestBank);
            } catch(SQLException e){
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message,"Error: An error has occurred in the database. Make sure your values are correct.", "Red");
                return;
            }
            bank.setBankName(bankName.getText());
            bank.setAddress(address.getText());
            bank.setPhone(phone.getText());
            bank.setEmail(email.getText());
            editSave.setText("Edit");
            setTextFieldEditable(false);
            Utility.setLabelMessageAndColor(message,"Successfully saved changes.", "Green");
        }
        else{
            editSave.setText("Save");
            setTextFieldEditable(true);
            message.setText("");
        }
    }
    
    /**
     * This method sets the interface editable or not to the user.
     * @param edit 
     */
    private void setTextFieldEditable(boolean edit){
        bankName.setEditable(edit);
        address.setEditable(edit);
        phone.setEditable(edit);
        email.setEditable(edit);
    }
    
    /**
     * Setter for bank attribute. Necessary for information display.
     * @param bank 
     */
    public void setBank(Bank bank){
        this.bank = bank;
        bankName.setText(bank.getBankName());
        address.setText(bank.getAddress());
        phone.setText(bank.getPhone());
        email.setText(bank.getEmail());
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
    
    
    
}
