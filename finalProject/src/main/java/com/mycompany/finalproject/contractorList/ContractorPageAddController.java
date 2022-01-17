/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class displays a Add Contractor Page for the user when the user wants
 * to add a contractor to the database. It will take the inputs from the user and add
 * them accordingly.
 * 
 * HOW TO USE:
 * It needs to be used with the FXMLLoader object. It is associated with a view,
 * so the @FXML properties variables will be initialized with the FXMLLoader.
 * Once done, it get can retrieved with the getController() method in order to
 * initialize the stage and many more variables.
 * 
 */
package com.mycompany.finalproject.contractorList;
import com.mycompany.finalproject.model.Contractor;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class ContractorPageAddController implements Initializable{
    private Stage currentStage;
    private TableView<Contractor> contractorTable;
    
    @FXML private TextField contactName;
    @FXML private TextField companyName;
    @FXML private TextField specialty;
    @FXML private TextField address;
    @FXML private TextField phone;
    @FXML private TextField email;
    @FXML private Label     message;
    
    //SETTERS
    /**
     * 
     * @param table 
     */
    public void setTableView(TableView<Contractor> table){
        contractorTable = table;
    }
    /**
     * 
     * @param event 
     */
    //ON ACTION METHOD;
    @FXML
    private void close(ActionEvent event){
        Stage window = Utility.getStage(event);
        window.close();
    }
    /**
     * 
     * @param event 
     */
    @FXML
    private void add(ActionEvent event){
       ObservableList<Contractor> list = contractorTable.getItems();
       String contactName = this.contactName.getText();
       String companyName = this.companyName.getText();
       String specialty   = this.specialty.getText();
       String address     = this.address.getText();
       String phone       = this.phone.getText();
       String email       = this.email.getText();
       Contractor contractor    = new Contractor(contactName, companyName,address, specialty, phone, email);
       try{
           DatabaseServices.addModelToDb(contractor);
       } catch(SQLException e){
           e.printStackTrace();
           message.setText("Error: New bank information could not be added to the database.");
           return;
       }
       message.setText("");
       list.add(contractor);
       Stage stage = Utility.getStage(event);
       stage.close();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
}
