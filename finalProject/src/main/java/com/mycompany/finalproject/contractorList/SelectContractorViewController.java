package com.mycompany.finalproject.contractorList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.model.Contractor;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SelectContractorViewController {

    @FXML private TextField    contactName;
    @FXML private TextField    companyName;
    @FXML private TextField    address;
    @FXML private TextField    specialty;
    @FXML private TextField    phone;
    @FXML private TextField    email;
    @FXML private Label        message;
    @FXML private Button       select;
    private ContractorReceiver contractorReceiver;
    private Parent             previous;
    private Parent             contractorReceiverRoot;
    private Contractor         contractor;

    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }

    @FXML
    public void select(ActionEvent event) throws IOException {
        contractorReceiver.setContractor(contractor);
        Utility.setRoot(event, contractorReceiverRoot);
    }

    public void setContractorReceiver(ContractorReceiver contractorReceiver) {
        this.contractorReceiver = contractorReceiver;
    }

    public void setContractor(Contractor contractor){
        this.contractor = contractor;
        contactName.setText(contractor.getContactName());
        companyName.setText(contractor.getCompanyName());
        address.setText(contractor.getAddress());
        specialty.setText(contractor.getSpecialty());
        phone.setText(contractor.getPhone());
        email.setText(contractor.getEmail());
    }

    public void setContractorReceiverRoot(Parent previous) {
        this.contractorReceiverRoot = previous;
    }

    public void setPrevious(Parent root) {
        this.previous = root;
    }

}
