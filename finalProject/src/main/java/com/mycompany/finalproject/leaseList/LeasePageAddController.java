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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;

public class LeasePageAddController implements Initializable, PropertyReceiver, TenantReceiver{

    @FXML private Label            labelTenant;
    @FXML private Button           selectRemoveTenant;
    @FXML private Label            labelProperty;
    @FXML private Button           selectRemoveProperty;
    @FXML private Label            labelLease;
    @FXML private Button           selectRemoveLease;
    @FXML private Label            message;
          private File             lease;
          private int              propertyId;
          private String           propertyAddress;
          private int              tenantId;
          private String           tenantFirstName;
          private String           tenantLastName;
          private TableView<Lease> leaseTable;
          private Parent           result;
    

    @FXML
    public void add(ActionEvent event) throws IOException, SQLException {
        if(anyFieldEmpty()){
            Utility.setLabelMessageAndColor(message, "Error: Some fields are left empty", "Red");
            return;
        }
        String extension = Utility.getFileExtension(lease.getName());
        Lease newLease = new Lease(propertyId, propertyAddress, tenantId, tenantFirstName, tenantLastName, extension);
        try{
            DatabaseServices.addModelToDb(newLease);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Utility.setLabelMessageAndColor(message, "Error: New Lease information could not be added to the database.", "Red");
            return;
        }
        boolean successful = false;
        try{
            Path from = Paths.get(lease.toURI());
            Path to = Paths.get(Lease.LEASE_URL + lease.getName());
            // Files.copy(from.toFile(), to.toFile()); //gives a 'cannot resolve method error 
            Files.copy(from, to);
            File renamedLease = new File(to.toUri());
            renamedLease.renameTo(new File(Lease.LEASE_URL+newLease.getLeaseId()+"."+extension));
            leaseTable.getItems().add(newLease);
            Utility.setLabelMessageAndColor(message, "Successfully selected lease.", "Green");
            close(event);
            successful = true;
            return;
        }
        finally{
            if(!successful){
                DatabaseServices.deleteModelFromDb(newLease);
            }
        }
    }

    @FXML
    public void close(ActionEvent event) {
        Utility.getStage(event).close();
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
            lease = fc.showOpenDialog(null);
            if (lease != null) {
                labelLease.setText(lease.getName());
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tenantId = -1;
        propertyId = -1;
    }

    void setTableView(TableView<Lease> leaseTable) {
        this.leaseTable = leaseTable;
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
        isValid = isValid || lease == null;
        return isValid;
    }

}
