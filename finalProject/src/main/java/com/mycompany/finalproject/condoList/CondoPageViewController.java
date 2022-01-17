package com.mycompany.finalproject.condoList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Condo;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CondoPageViewController implements Initializable{

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
    @FXML private Button     editSave;
    private Condo            condo;
    
    
    /**
     * Closes the current stage
     * @param event 
     */
    @FXML
    void close(ActionEvent event) {
        Utility.getStage(event).close();
    }
    
    /**
     * Saves the new information of the condo in the database.
     * @param event 
     */
    @FXML
    void editSave(ActionEvent event) {
        String address          = this.address.getText();
        String country          = this.country.getText();
        String state            = this.state.getText();
        String city             = this.city.getText();
        String postalCode       = this.postalCode.getText();
        String details          = this.details.getText();
        String appartmentNumber = this.appartmentNumber.getText();
        double size             = Double.parseDouble(this.condoSize.getText());
        double condoFee         = Double.parseDouble(this.condoFee.getText());
        
        if(this.address.isEditable()){
            try{
                Condo dbTestCondo = new Condo(condo.getPropertyId(), address, country, state, city, postalCode, details, appartmentNumber, size, condoFee);
                DatabaseServices.updateModelToDatabase(dbTestCondo);
            } catch(SQLException e){
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message,"Error: An error has occurred in the database. Make sure your values are correct.", "Red");
                return;
            }
            condo.setAddress(address);
            condo.setCountry(country);
            condo.setState(state);
            condo.setCity(city);
            condo.setPostalCode(postalCode);
            condo.setDetails(details);
            condo.setAppartmentNumber(appartmentNumber);
            condo.setSize(size);
            condo.setCondoFee(condoFee);
            editSave.setText("Edit");
            setTextFieldEditable(false);
            Utility.setLabelMessageAndColor(message,"Successfully saved changes.", "Green");
        }
        else{
            editSave.setText("Save");
            setTextFieldEditable(true);
            message.setText("");
        }
    }
    /**
     * Sets the textfield editable so the user can edit it.
     * @param edit 
     */
    public void setTextFieldEditable(boolean edit){
        address.setEditable(edit);
        appartmentNumber.setEditable(edit);
        country.setEditable(edit);
        state.setEditable(edit);
        city.setEditable(edit);
        postalCode.setEditable(edit);
        condoSize.setEditable(edit);
        condoFee.setEditable(edit);
        details.setEditable(edit);
    }
    /**
     * Sets the condo so it can be displayed to the user.
     * @param condo 
     */
    public void setCondo(Condo condo){
        this.condo = condo;
        address.setText(condo.getAddress());
        appartmentNumber.setText(condo.getAppartmentNumber());
        country.setText(condo.getCountry());
        state.setText(condo.getState());
        city.setText(condo.getCity());
        postalCode.setText(condo.getPostalCode());
        condoSize.setText(""+condo.getSize());
        condoFee.setText(""+condo.getCondoFee());
        details.setText(condo.getDetails());
        
    }
    /**
     * Initalizes the text field so they can have some sort of validation using regex.
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

}
