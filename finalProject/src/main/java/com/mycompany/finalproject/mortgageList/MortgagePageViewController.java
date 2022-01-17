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
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MortgagePageViewController implements Initializable, PropertyReceiver, BankReceiver{

    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private TextField  monthlyPayment;
    @FXML private TextField  numOfYearsContract;
    @FXML private TextField  totalLoan;
    @FXML private TextField  amountPaid;
    @FXML private TextField  interestRate;
    @FXML private Label      labelBank;
    @FXML private Button     selectRemoveBank;
    @FXML private Label      labelProperty;
    @FXML private Button     selectRemoveProperty;
    @FXML private Label      message;
    @FXML private Button     editSave;
          private int        bankId;
          private String     bankName;
          private Label      result;
          private int        propertyId;
          private String     propertyAddress;
          private Mortgage mortgage;

    @FXML
    void close(ActionEvent event) {
        Utility.getStage(event).close();
    }

    @FXML
    void editSave(ActionEvent event) {
        if(editSave.getText().equals("Save")){
            if(anyFieldEmpty()){
                Utility.setLabelMessageAndColor(message, "Error: Some fields are left blank.", "Red");
                return;
            }
            String startDate        = this.startDate.getValue().toString();
            String endDate          = this.endDate.getValue().toString();
            double monthlyPayment   = Double.parseDouble(this.monthlyPayment.getText());
            int numOfYearsContract  = Integer.parseInt(this.numOfYearsContract.getText());
            double totalLoan        = Double.parseDouble(this.totalLoan.getText());
            double amountPaid       = Double.parseDouble(this.amountPaid.getText());
            double interestRate     = Double.parseDouble(this.interestRate.getText());
            
            Mortgage dbTestMortgage = new Mortgage(
                mortgage.getMortgageId(), 
                startDate, 
                endDate, 
                monthlyPayment, 
                numOfYearsContract, 
                totalLoan, 
                amountPaid, 
                interestRate, 
                bankId, 
                bankName, 
                propertyId, 
                propertyAddress
            );
            
            try{
                DatabaseServices.updateModelToDatabase(dbTestMortgage);
            }catch(SQLException e){
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: An error has occurred in the database.", "Red");
                return;
            }
            mortgage.setStartDate(startDate);
            mortgage.setEndDate(endDate);
            mortgage.setMonthlyPayment(monthlyPayment);
            mortgage.setNumOfYearsContract(numOfYearsContract);
            mortgage.setTotalLoanValue(totalLoan);
            mortgage.setAmountPaid(amountPaid);
            mortgage.setInterestRate(interestRate);
            mortgage.setBankId(bankId);
            mortgage.setPropertyId(propertyId);
            mortgage.setBankName(bankName);
            mortgage.setPropertyAddress(propertyAddress);
            setEditable(false);
            editSave.setText("Edit");
            Utility.setLabelMessageAndColor(message, "Successfully saved your changes", "Green");
        }else{
            editSave.setText("Save");
            setEditable(true);
            message.setText("");
        }
    }
    void setEditable(boolean edit){
        startDate.setEditable(edit);
        endDate.setEditable(edit);
        monthlyPayment.setEditable(edit);
        numOfYearsContract.setEditable(edit);
        totalLoan.setEditable(edit);
        amountPaid.setEditable(edit);
        interestRate.setEditable(edit);
        selectRemoveBank.setVisible(edit);
        selectRemoveProperty.setVisible(edit);
    }
    @FXML
    void selectRemoveBank(ActionEvent event) throws IOException {
        if(selectRemoveBank.getText().equals("Select Bank")){
            //select a bank
            FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectBank");
            Parent root = fxmlLoader.load();
            SelectBankController controller = fxmlLoader.getController();
            controller.setPrevious(Utility.getRoot(event));
            controller.setBankReceiver(this);
            Utility.setRoot(event, root);
        }
        else{
            int bankId = -1;
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

    public void setResult(Label result){
        this.result = result;
    }
    @Override
    public void setBank(Bank bank){
        this.bankId = bank.getBankId();
        this.bankName = bank.getBankName();
        labelBank.setText(bank.getBankName());
        Utility.setLabelMessageAndColor(message, "Bank successfully selected.", "Green");
        selectRemoveBank.setText("Remove bank");
    }
    @Override
    public void setProperty(Property property){
        this.propertyId = property.getPropertyId();
        this.propertyAddress = property.getAddress();
        labelProperty.setText(property.getAddress());
        Utility.setLabelMessageAndColor(message, "Property successfully selected.", "Green");
        selectRemoveProperty.setText("Remove Property");
    }

    void setMortgage(Mortgage selected) {
        this.mortgage        = selected;
        this.bankId          = selected.getBankId();
        this.propertyId      = selected.getPropertyId();
        this.bankName        = selected.getBankName();
        this.propertyAddress = selected.getPropertyAddress();
        startDate.setValue(LocalDate.parse(selected.getStartDate()));
        endDate.setValue(LocalDate.parse(selected.getEndDate()));
        monthlyPayment.setText(""+selected.getMonthlyPayment());
        numOfYearsContract.setText(""+selected.getNumOfYearsContract());
        totalLoan.setText(""+selected.getTotalLoanValue());
        amountPaid.setText(""+selected.getAmountPaid());
        interestRate.setText(""+selected.getInterestRate());
        labelBank.setText(selected.getBankName());
        labelProperty.setText(selected.getPropertyAddress());
    }

    private boolean anyFieldEmpty() {
        boolean isValid;
        String startDate           = this.startDate.getValue().toString();
        System.out.println("This is start date: <"+startDate+">");
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
        
    }

}
