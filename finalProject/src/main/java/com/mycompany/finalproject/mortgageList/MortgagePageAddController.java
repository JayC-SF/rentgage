package com.mycompany.finalproject.mortgageList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.bankList.BankReceiver;
import com.mycompany.finalproject.bankList.SelectBankController;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Bank;
import com.mycompany.finalproject.model.Mortgage;
import com.mycompany.finalproject.model.Property;
import com.mycompany.finalproject.propertyList.PropertyReceiver;
import com.mycompany.finalproject.propertyList.SelectPropertyController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MortgagePageAddController implements Initializable, PropertyReceiver, BankReceiver{
    private TableView<Mortgage> mortgageTable;
    @FXML private DatePicker    startDate;
    @FXML private DatePicker    endDate;
    @FXML private TextField     monthlyPayment;
    @FXML private TextField     numOfYearsContract;
    @FXML private TextField     totalLoan;
    @FXML private TextField     amountPaid;
    @FXML private TextField     interestRate;
    @FXML private Button        selectRemoveBank;
    @FXML private Button        selectRemoveProperty;
    @FXML private Label         message;
    @FXML private Label         labelBank;
    @FXML private Label         labelProperty;
          private int           bankId;
          private String        bankName;
          private int           propertyId;
          private String        propertyAddress;
          private Label         result;
    
    
    @FXML
    void add(ActionEvent event) {
        //Check if values are valid.
        if(anyFieldEmpty()){
                Utility.setLabelMessageAndColor(message, "Error: Some fields are left blank.", "Red");
                return;
        }
        String startDate       = this.startDate.getValue().toString();
        String endDate         = this.endDate.getValue().toString();
        double monthlyPayment  = Double.parseDouble(this.monthlyPayment.getText());
        int numOfYearsContract = Integer.parseInt(this.numOfYearsContract.getText());
        double totalLoan       = Double.parseDouble(this.totalLoan.getText());
        double amountPaid      = Double.parseDouble(this.amountPaid.getText());
        double interestRate    = Double.parseDouble(this.interestRate.getText());
        Mortgage mortgage      = new Mortgage(startDate, endDate, monthlyPayment, numOfYearsContract, totalLoan, amountPaid, interestRate, bankId, bankName, propertyId, propertyAddress);
        try{
            DatabaseServices.addModelToDb(mortgage);
        }catch(SQLException e){
            e.printStackTrace();
            Utility.setLabelMessageAndColor(message, "Error: New Mortgage information could not be added into the database.", "Red");
            return;
        }
        mortgageTable.getItems().add(mortgage);
        Utility.setLabelMessageAndColor(result, "Mortgage successfully created.", "Green");
        close(event);
    }

    @FXML
    void close(ActionEvent event) {
        Utility.getStage(event).close();
    }

    @FXML
    void selectRemoveBank(ActionEvent event) throws IOException{
        if(selectRemoveBank.getText().equals("Select Bank")){
            //select a bank
            FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectBank");
            Parent root = fxmlLoader.load();
            SelectBankController controller= (SelectBankController)fxmlLoader.getController();
            controller.setPrevious(Utility.getRoot(event));
            controller.setBankReceiver(this);
            Utility.setRoot(event, root);
        }
        else{
            bankId = -1;
            Utility.setLabelMessageAndColor(message, "Sucessfully removed the bank "+labelBank.getText(), "Green");
            labelBank.setText("Currently No Bank Selected");
            selectRemoveBank.setText("Select Bank");
        }
    }

    @FXML
    void selectRemoveProperty(ActionEvent event) throws IOException {
        if(selectRemoveProperty.getText().equals("Select Property")){
            //select a property
            FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectProperty");
            Parent root = fxmlLoader.load();
            SelectPropertyController controller = fxmlLoader.getController();
            controller.setPrevious(Utility.getRoot(event));
            controller.setPropertyReceiver(this);
            Utility.setRoot(event, root);
        }
        else{
            propertyId = -1;
            Utility.setLabelMessageAndColor(message, "Sucessfully removed the property "+labelProperty.getText(), "Green");
            labelProperty.setText("Currently No Property Selected");
            selectRemoveProperty.setText("Select Property");
        }
    }
    
    public void setTableView(TableView<Mortgage> mortgageTable){
        this.mortgageTable = mortgageTable;
    }

    
    public void setResult(Label result){
        this.result = result;
    }
    @Override
    public void setBank(Bank bank){
        bankId = bank.getBankId();
        bankName = bank.getBankName();
        labelBank.setText(bankName);
        Utility.setLabelMessageAndColor(message, "Bank successfully selected.", "Green");
        selectRemoveBank.setText("Remove bank");
    }
    @Override
    public void setProperty(Property property){
        propertyId = property.getPropertyId();
        propertyAddress = property.getAddress();
        labelProperty.setText(propertyAddress);
        Utility.setLabelMessageAndColor(message, "Property successfully selected.", "Green");
        selectRemoveProperty.setText("Remove Property");
    }
    
    private boolean anyFieldEmpty() {
        boolean isValid;
        String startDate           = this.startDate.getValue().toString();
        String endDate             = this.endDate.getValue().toString();
        String monthlyPayment      = this.monthlyPayment.getText();
        String numOfYearsContract  = this.numOfYearsContract.getText();
        String totalLoan           = this.totalLoan.getText();
        String amountPaid          = this.amountPaid.getText();
        String interestRate        = this.interestRate.getText();
        isValid = startDate.equals("");
        isValid = isValid || endDate.equals("");
        isValid = isValid || monthlyPayment.equals("");
        isValid = isValid || numOfYearsContract.equals("");
        isValid = isValid || totalLoan.equals("");
        isValid = isValid || amountPaid.equals("");
        isValid = isValid || interestRate.equals("");
        isValid = isValid || propertyId == -1;
        isValid = isValid || bankId == -1;
        return isValid;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bankId = -1;
        propertyId = -1;
    }

}
