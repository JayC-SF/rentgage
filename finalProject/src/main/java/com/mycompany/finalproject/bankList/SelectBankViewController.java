
package com.mycompany.finalproject.bankList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.model.Bank;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class SelectBankViewController implements Initializable{
    
    @FXML private TextField            bankName;
    @FXML private TextField            address;
    @FXML private TextField            phone;
    @FXML private TextField            email;
    @FXML private Label                message;
    private BankReceiver               bankReceiver;
          private Parent               previous;
          private Parent               bankReceiverRoot;
          private Bank                 bank;
          
    
    /**
     * This method returns back to the previous page
     * @param event
     * @throws IOException 
     */
    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }
    /**
     * This method returns the bank to the BankReceiver, the one requesting for 
     * a bank.
     * @param event
     * @throws IOException 
     */
    @FXML 
    public void selectBank(ActionEvent event) throws IOException{
        bankReceiver.setBank(bank);
        Utility.setRoot(event, bankReceiverRoot);
    }

    /**
     * This method sets the bank for display in the view.
     * @param selected 
     */
    public void setBank(Bank bank) {
        this.bank = bank;
        bankName.setText(bank.getBankName());
        address.setText(bank.getAddress());
        phone.setText(bank.getPhone());
        email.setText(bank.getEmail());
    }
    /**
     * This method sets the bankReceiver for the request of the bank
     * @param bankReceiver 
     */
    public void setBankReceiver(BankReceiver bankReceiver) {
        this.bankReceiver = bankReceiver;
    }
    /**
     * This method sets the previous root of the view before this current view.
     * @param root 
     */
    public void setPrevious(Parent root) {
        this.previous = root;
    }
    /**
     * This method sets the root of the bankReceiver caller, so once the bank
     * is selected, the stage can return to the beginning.
     * @param bankReceiverRoot 
     */
    public void setBankReceiverRoot(Parent bankReceiverRoot) {
        this.bankReceiverRoot = bankReceiverRoot;
    }
    /**
     * This method initializes data, currently no data is being initialized, but it
     * is still practical to keep it around.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    
}
