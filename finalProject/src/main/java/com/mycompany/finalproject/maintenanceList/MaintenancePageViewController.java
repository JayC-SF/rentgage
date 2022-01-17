package com.mycompany.finalproject.maintenanceList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.contractorList.ContractorReceiver;
import com.mycompany.finalproject.contractorList.SelectContractorController;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Contractor;
import com.mycompany.finalproject.model.Maintenance;
import com.mycompany.finalproject.model.Property;
import com.mycompany.finalproject.propertyList.PropertyReceiver;
import com.mycompany.finalproject.propertyList.SelectPropertyController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MaintenancePageViewController implements Initializable, PropertyReceiver, ContractorReceiver{

    @FXML private DatePicker  startDate;
    @FXML private DatePicker  endDate;
    @FXML private TextField   maintenanceType;
    @FXML private TextField   totalCost;
    @FXML private TextArea    details;
    @FXML private Label       labelContractor;
    @FXML private Button      selectRemoveContractor;
    @FXML private Label       labelProperty;
    @FXML private Button      selectRemoveProperty;
    @FXML private Label       message;
    @FXML private Button      editSave;
          private int         contractorId;
          private String      contractorCompanyName;
          private int         propertyId;
          private String      propertyAddress;
          private Maintenance maintenance;
          private Label result;

    @FXML
    public void close(ActionEvent event) {
        Utility.getStage(event).close();
    }

    @FXML
    public void editSave(ActionEvent event) {
        if(editSave.getText().equals("Save")){
            if(anyFieldEmpty()){
                Utility.setLabelMessageAndColor(message, "Error: Some fields are left blank.", "Red");
                return;
            }
            String startDate = this.startDate.getValue().toString();
            String endDate = this.endDate.getValue().toString();
            String maintenanceType = this.maintenanceType.getText();
            double totalCost = Double.parseDouble(this.totalCost.getText());
            String details = this.details.getText();
            Maintenance dbTestMaintenance = new Maintenance(maintenance.getMaintenanceId(), maintenanceType, startDate, endDate, totalCost, details, propertyId, propertyAddress, contractorId, contractorCompanyName);

            try{
                DatabaseServices.updateModelToDatabase(dbTestMaintenance);
            }catch(SQLException e){
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: An error has occurred in the database.", "Red");
                return;
            }
            maintenance.setStartDate(startDate);
            maintenance.setEndDate(endDate);
            maintenance.setMaintenanceType(maintenanceType);
            maintenance.setTotalCost(totalCost);
            maintenance.setDetails(details);
            maintenance.setPropertyId(propertyId);
            maintenance.setPropertyAddress(propertyAddress);
            maintenance.setContractorId(contractorId);
            maintenance.setContractorCompanyName(contractorCompanyName);
            setEditable(false);
            editSave.setText("Edit");
            Utility.setLabelMessageAndColor(message, "Successfully saved your changes", "Green");
        }else{
            editSave.setText("Save");
            setEditable(true);
            message.setText("");
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

     @FXML
    public void selectRemoveContractor(ActionEvent event) throws IOException {
        if(selectRemoveContractor.getText().equals("Select Contractor")){
            //select a contractor
            FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectContractor");
            Parent root = fxmlLoader.load();
            SelectContractorController controller = fxmlLoader.getController();
            controller.setPrevious(Utility.getRoot(event));
            controller.setContractorReceiver(this);
            Utility.setRoot(event, root);
        }
        else{
            contractorId = -1;
            Utility.setLabelMessageAndColor(message, "Sucessfully removed the contractor "+labelContractor.getText(), "Green");
            labelContractor.setText("Currently No Contractor Selected");
            selectRemoveContractor.setText("Select Contractor");
        }
    }
    
    public void setEditable(boolean edit){
        startDate.setEditable(edit);
        endDate.setEditable(edit);
        maintenanceType.setEditable(edit);
        totalCost.setEditable(edit);
        details.setEditable(edit);
        selectRemoveContractor.setVisible(edit);
        selectRemoveProperty.setVisible(edit);
    }
    
    public void setResult(Label result){
        this.result = result;
        
    }
    
    public void setMaintenance(Maintenance maintenance){
        this.maintenance = maintenance;
        contractorId = maintenance.getContractorId();
        contractorCompanyName = maintenance.getContractorCompanyName();
        propertyId = maintenance.getPropertyId();
        propertyAddress = maintenance.getPropertyAddress();
        startDate.setValue(LocalDate.parse(maintenance.getStartDate()));
        endDate.setValue(LocalDate.parse(maintenance.getEndDate()));
        maintenanceType.setText(maintenance.getMaintenanceType());
        totalCost.setText(""+maintenance.getTotalCost());
        details.setText(maintenance.getDetails());
        labelProperty.setText(""+maintenance.getPropertyId());
    }
    
    private boolean anyFieldEmpty() {
        boolean isValid;
        String startDate = this.startDate.getValue().toString();
        String endDate = this.endDate.getValue().toString();
        String maintenanceType = this.maintenanceType.getText();
        String totalCost = this.totalCost.getText();
        String details = this.details.getText();
        isValid = startDate.equals("");
        isValid = isValid || endDate.equals("");
        isValid = isValid || maintenanceType.equals("");
        isValid = isValid || totalCost.equals("");
        isValid = isValid || details.equals("");
        isValid = isValid || propertyId == -1;
        isValid = isValid || contractorId == -1;
        
        return isValid;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        propertyId = -1;
        contractorId = -1;
    }

    @Override
    public void setProperty(Property property){
        propertyId = property.getPropertyId();
        propertyAddress = property.getAddress();
        labelProperty.setText(propertyAddress);
        Utility.setLabelMessageAndColor(message, "Property successfully selected.", "Green");
        selectRemoveProperty.setText("Remove Property");
    }

    @Override
    public void setContractor(Contractor contractor) {
        contractorId = contractor.getContractorId();
        contractorCompanyName = contractor.getCompanyName();
        labelContractor.setText(contractorCompanyName);
        Utility.setLabelMessageAndColor(message, "Contractor successfully selected.", "Green");
        selectRemoveContractor.setText("Remove Contractor");
    }

}
