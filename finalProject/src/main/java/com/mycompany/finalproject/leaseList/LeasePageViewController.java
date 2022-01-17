package com.mycompany.finalproject.leaseList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Lease;
import com.mycompany.finalproject.model.Property;
import com.mycompany.finalproject.model.Tenant;
import com.mycompany.finalproject.propertyList.PropertyReceiver;
import com.mycompany.finalproject.propertyList.SelectPropertyController;
import com.mycompany.finalproject.tenantList.SelectTenantController;
import com.mycompany.finalproject.tenantList.TenantReceiver;
import java.io.File;
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
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class LeasePageViewController implements Initializable, PropertyReceiver, TenantReceiver{

    @FXML private Label  labelTenant;
    @FXML private Button selectRemoveTenant;
    @FXML private Label  labelProperty;
    @FXML private Button selectRemoveProperty;
    @FXML private Label  labelLease;
    @FXML private Button selectRemoveLease;
    @FXML private Label  message;
    @FXML private Button editSave;
    private int propertyId;
    private String propertyAddress;
    private int tenantId;
    private String tenantFirstName; 
    private String tenantLastName; 
    
    private Lease lease;
    private File oldFile;
    private File leaseFile;

    @FXML
    public void editSave(ActionEvent event) {
        if(editSave.getText().equals("Save")){
            if(anyFieldEmpty()){
                Utility.setLabelMessageAndColor(message, "Error: Some fields are left blank", "Red");
                return;
            }
            String extension = Utility.getFileExtension(leaseFile != null? leaseFile.getName():oldFile.getName());
            Lease testLeaseDb = new Lease(propertyId, propertyAddress, tenantId, tenantFirstName, tenantLastName, extension);
            try{
                DatabaseServices.updateModelToDatabase(testLeaseDb);
            } catch (SQLException ex) {
                ex.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: New Lease information could not be saved to the database.", "Red");
                return;
            }
            lease.setPropertyId(propertyId);
            lease.setPropertyAddress(propertyAddress);
            lease.setTenantId(tenantId);
            lease.setTenantFirstName(tenantFirstName);
            lease.setTenantLastName(tenantLastName);
            lease.setExtension(extension);
            File newFile = new File(Lease.LEASE_URL+lease.getLeaseId()+"."+lease.getExtension());
            oldFile.renameTo(newFile);
            Utility.setLabelMessageAndColor(message, "Successfully saved changes.", "Green");
            setEditable(false);
        }
        else{
            setEditable(true);
            message.setText("");
        }
    }

    @FXML
    public void close(ActionEvent event) {
        Utility.getStage(event).close();
    }

    @FXML
    public void selectRemoveProperty(ActionEvent event) throws IOException {
        if(selectRemoveProperty.getText().equals("Select Property")){
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
    public void selectRemoveLease(ActionEvent event) throws IOException {
        if(selectRemoveLease.getText().equals("Select Lease")){
            FileChooser fc = new FileChooser();
            fc.setTitle("Attach a file");
            leaseFile = fc.showOpenDialog(null);
            if (leaseFile != null) {
                labelLease.setText(leaseFile.getName());
                Utility.setLabelMessageAndColor(message, "Successfully selected lease.", "Green");
            }
        }
        else{
            lease = null;
            Utility.setLabelMessageAndColor(message, "Sucessfully removed the lease "+labelLease.getText(), "Green");
            labelLease.setText("Currently No Lease Selected");
            selectRemoveLease.setText("Select Lease");
        }
    }

    @FXML
    public void selectRemoveTenant(ActionEvent event) throws IOException {
        if(selectRemoveTenant.getText().equals("Select Tenant")){
            FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectTenant");
            Parent root = fxmlLoader.load();
            SelectTenantController controller = fxmlLoader.getController();
            controller.setPrevious(Utility.getRoot(event));
            controller.setTenantReceiver(this);
            Utility.setRoot(event, root);
        }
        else{
            tenantId = -1;
            Utility.setLabelMessageAndColor(message, "Sucessfully removed the property "+labelTenant.getText(), "Green");
            labelTenant.setText("Currently No Tenant Selected");
            selectRemoveTenant.setText("Select Tenant");
        }
    }

    void setLease(Lease selected) {
        this.lease = selected;
        labelTenant.setText(selected.getTenantFirstName()+" "+selected.getTenantLastName());
        labelProperty.setText(selected.getPropertyAddress());
        labelLease.setText(selected.getLeaseId()+"."+selected.getExtension());
        propertyId = selected.getPropertyId();
        propertyAddress = selected.getPropertyAddress();
        tenantId = selected.getTenantId();
        tenantFirstName = selected.getTenantFirstName();
        tenantLastName = selected.getTenantLastName();
        oldFile = new File(Lease.LEASE_URL+selected.getLeaseId()+"."+selected.getExtension());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tenantId = -1;
        propertyId = -1;
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
    public void setTenant(Tenant tenant) {
        tenantId = tenant.getTenantId();
        tenantFirstName = tenant.getFirstName();
        tenantLastName = tenant.getLastName();
        labelTenant.setText(tenantFirstName+" "+tenantLastName);
        Utility.setLabelMessageAndColor(message, "Tenant successfully selected.", "Green");
        selectRemoveTenant.setText("Remove Tenant");
    }
    
    private boolean anyFieldEmpty() {
        boolean isValid = propertyId == -1;
        isValid = isValid || tenantId == -1;
        return isValid;
    }
    
    private void setEditable(boolean edit){
        selectRemoveLease.setVisible(edit);
        selectRemoveProperty.setVisible(edit);
        selectRemoveTenant.setVisible(edit);
        editSave.setText(edit?"Save":"Edit");
    }
}
