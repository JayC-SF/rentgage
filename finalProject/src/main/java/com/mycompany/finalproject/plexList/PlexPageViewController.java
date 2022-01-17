/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.plexList;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Plex;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class PlexPageViewController implements Initializable{
    
    @FXML private TextField address;
    @FXML private TextField country;
    @FXML private TextField state;
    @FXML private TextField city;
    @FXML private TextField postalCode;
    @FXML private TextArea  details;
    @FXML private Label     message;
    @FXML private Button    editSave;
    @FXML private Button    mortgageButton;
          private Plex      plex;
    
    /**
     * This method closes the current Stage
     * @param event 
     */
    @FXML
    public void close(ActionEvent event) {
        Utility.getStage(event).close();
    }

    /**
     * Sets the fields editable for changes, but also saves the data to the database
     * @param event 
     */
    @FXML
    public void editSave(ActionEvent event) {
        String address    = this.address.getText();
        String country    = this.country.getText();
        String state      = this.state.getText();
        String city       = this.city.getText();
        String postalCode = this.postalCode.getText();
        String details    = this.details.getText();
        int numOfAppt     = plex.getNumOfAppt();
        if(this.address.isEditable()){
            try{
                Plex dbTestPlex = new Plex(this.plex.getPropertyId(), address, country, state, city, postalCode, details, numOfAppt);
                DatabaseServices.updateModelToDatabase(dbTestPlex);
            }catch(SQLException e){
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message, "Error: An error has occurred in the database. Make sure your values are correct.", "Red");
                return;
            }
            plex.setAddress(address);
            plex.setCountry(country);
            plex.setState(state);
            plex.setCity(city);
            plex.setPostalCode(postalCode);
            plex.setDetails(details);
            editSave.setText("Edit");
            setTextFieldEditable(false);
            Utility.setLabelMessageAndColor(message, "Successfully saved changes.", "Green");
        }
        else{
            editSave.setText("Save");
            setTextFieldEditable(true);
            message.setText("");
        }
    }
    /**
     * Sets the fields editable for the user to make changes
     * @param edit 
     */
    private void setTextFieldEditable(boolean edit){
        address.setEditable(edit);
        country.setEditable(edit);
        state.setEditable(edit);
        city.setEditable(edit);
        postalCode.setEditable(edit);
        details.setEditable(edit);  
    }
    public void setPlex(Plex selected) {
        plex = selected;
        address.setText(selected.getAddress());
        country.setText(selected.getCountry());
        state.setText(selected.getState());
        city.setText(selected.getCity());
        postalCode.setText(selected.getPostalCode());
        details.setText(selected.getDetails());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    
    
}
