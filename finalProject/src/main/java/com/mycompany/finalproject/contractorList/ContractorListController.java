/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class displays a Contractor List for the user when the user wants
 * to look at the list of the contractors. It will open other views depending on what
 * the user clicks on. For example view bank, add bank, etc.
 * 
 * HOW TO USE:
 * It needs to be used with the FXMLLoader object. It is associated with a view,
 * so the @FXML properties variables will be initialized with the FXMLLoader.
 * Once done, it get can retrieved with the getController() method in order to
 * initialize many instance properties. It may have some getters and setters potentially
 * to initialize properties that shall be set by the creator of the instance.
 * 
 */
package com.mycompany.finalproject.contractorList;
import com.mycompany.finalproject.App;
import com.mycompany.finalproject.bankList.BankPageAddController;
import com.mycompany.finalproject.bankList.BankPageViewController;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Bank;
import com.mycompany.finalproject.model.Contractor;
import com.mycompany.finalproject.model.Model;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class ContractorListController implements Initializable{
    @FXML private TableView<Contractor>           contractorTable;
    @FXML private TableColumn<Contractor, String> columnContactName;
    @FXML private TableColumn<Contractor, String> columnCompanyName;
    @FXML private TableColumn<Contractor, String> columnAddress;
    @FXML private TableColumn<Contractor, String> columnSpecialty;
    @FXML private TableColumn<Contractor, String> columnPhone;
    @FXML private TableColumn<Contractor, String> columnEmail;
    @FXML private Label message;
    ObservableList<Contractor>      contractorList;
    Alert               alert;
    Stage               viewContractor;
    Stage               addContractor;
    
    /**
     * This method switches the root to the Home Page.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void switchToHomePage(ActionEvent event) throws IOException{
        Utility.setRoot(event, "HomePage");
    }
    /**
     * This method opens a new page to see more details of the selected contractor.
     * @param event
     * @throws IOException 
     */
    @FXML 
    private void viewContractor(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("ContractorPageView");
        Parent root = fxmlLoader.load();
        ContractorPageViewController controller = fxmlLoader.getController();
        
        //extract the selected item.
        Contractor selected = contractorTable.getSelectionModel().getSelectedItem();
        //set error message in case no selected item.
        if(selected == null){
            message.setText("Error: No selected row.");
            return;
        }
        else{
            message.setText("");
        }
        //Pass information to the controller.
        controller.setContractor(selected);
        
        Scene scene = viewContractor.getScene();
        if(scene == null){
            scene = new Scene(root, 730, 650);
            viewContractor.setScene(scene);
        }else{
         scene.setRoot(root);
        }
        // New window (Stage)
        //Set Stage title TODO HERE
        viewContractor.setTitle("");
        // Set position of second window, related to primary window.
        if(!viewContractor.isShowing()) {
            viewContractor.show();
        }
        viewContractor.toFront();
    }
    
    /**
     * This method opens a new page to add a new Contractor.
     * @param event
     * @throws IOException 
     */
    @FXML
    private void addContractor(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("ContractorPageAdd");
        Parent root = fxmlLoader.load();
        ContractorPageAddController controller = (ContractorPageAddController) fxmlLoader.getController();
        //set the contractor table to add in there.
        controller.setTableView(contractorTable);
        
        //Reset error message in case there was an error previously
        message.setText("");
        Scene scene = addContractor.getScene();
        if(scene == null){
            scene = new Scene(root, 730, 650);
        }else{
         scene.setRoot(root);
        }
        
        addContractor.setScene(scene);
        // Set position of second window, related to primary window.
        if(!addContractor.isShowing()) {
            addContractor.show();
        }
        
    }
    
        @FXML
    void deleteContractor(ActionEvent event) {
        Contractor selected = contractorTable.getSelectionModel().getSelectedItem();
        int index = contractorTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            message.setText("Error: Could not delete this contractor in database");
            return;
        }
        Stage stage = Utility.getStage(event);
        alert.setContentText("Are you sure you want to delete "+selected.getCompanyName()+"?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            //delete
            try{
                DatabaseServices.deleteModelFromDb(selected);
                contractorList.remove(index);
            }
            catch(SQLException e){
                e.printStackTrace();
                message.setText("Error: Could not delete this bank in database.");
            }
        }
        message.setText("");
    }
    
    /**
     * This method initializes the necessary things for displaying the content properly.
     * Essentially just like a constructor taking no inputs. This method will initialize 
     * this class's properties of this controller that needs to be initialized independently from 
     * inputs of the creator of this instance.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        viewContractor = new Stage();
        viewContractor.setMinHeight(750);
        viewContractor.setTitle("Contractor Details");
        addContractor = new Stage();
        addContractor.setTitle("Add New Contractor");
        addContractor.setMinHeight(750);
        //Set up the columns to receive data and observer objects.
        columnContactName.setCellValueFactory(new PropertyValueFactory<>("contactName"));
        columnCompanyName.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        columnSpecialty.setCellValueFactory(new PropertyValueFactory<>("specialty"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        //setup table to only have 1 selection at a time.
        contractorTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //insert the data in the table that were loaded from db.
        String allContractorsQuery = Contractor.getSelectQuery();
        contractorList = (ObservableList<Contractor>)DatabaseServices.loadModelsFromDb(Contractor.class, allContractorsQuery);
        contractorTable.setItems(contractorList);
        // set the delete alert.
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        
    }
}
