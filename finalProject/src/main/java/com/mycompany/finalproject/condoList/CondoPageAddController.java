
package com.mycompany.finalproject.condoList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Condo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author Juan-Carlos Sreng-Flores
 */
public class CondoPageAddController implements Initializable{

    @FXML private TextField  address;
    @FXML private TextField  appartmentNumber;
    @FXML private TextField  country;
    @FXML private TextField  state;
    @FXML private TextField  city;
    @FXML private TextField  postalCode;
    @FXML private TextField  condoSize;
    @FXML private TextField  condoFee;
    @FXML private TextArea   details;
    @FXML private Label      message;
    @FXML private Button     mortgageButton;
    private TableView<Condo> condoTable;
    private Label result;
    
    @FXML
    void addEditMortgage(ActionEvent event){
        
    }
    /**
     * 
     * @param event 
     */
    @FXML
    private void add(ActionEvent event){
        String address          = this.address.getText();
        String appartmentNumber = this.appartmentNumber.getText();
        String country          = this.country.getText();
        String state            = this.state.getText();
        String city             = this.city.getText();
        String postalCode       = this.postalCode.getText();
        double condoSize        = Double.parseDouble(this.condoSize.getText());
        double condoFee         = Double.parseDouble(this.condoFee.getText());
        String details          = this.details.getText();
        Condo condo             = new Condo(address, country, state, city, postalCode, details, appartmentNumber, condoSize, condoFee);
        try{
            DatabaseServices.addModelToDb(condo);
        } catch(SQLException e){
           e.printStackTrace();
           Utility.setLabelMessageAndColor(message,"Error: New Condo information could not be added to the database.", "Red");
           return;
        }
        Utility.setLabelMessageAndColor(result,"Condo successfully created.", "Green");
        condoTable.getItems().add(condo);
        close(event);
    }
    /**
     * 
     * @param event 
     */
    @FXML
    private void close(ActionEvent event) {
        Utility.getStage(event).close();
    }
    
    /**
     * 
     * @param condoTable 
     */
    public void setTableView(TableView<Condo> condoTable) {
        this.condoTable = condoTable;
    }
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        condoSize.textProperty().addListener(new ChangeListener<String>() {
            @Override 
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(!newValue.matches(Utility.getAppartmentSizeRegex())){
                    condoSize.setText(oldValue);
                }
            }
        });
        condoFee.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                if(!newValue.matches(Utility.getCurrencyRegex())){
                    condoFee.setText(oldValue);
                }
            }
        });
        
    }
    /**
     * 
     * @param message 
     */
    public void setResult(Label message) {
        this.result = message;
    }
    

}
