/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.houseList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.House;
import com.mycompany.finalproject.model.Mortgage;
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
 * @author JCSF
 */
public class HousePageViewController implements Initializable{
    
    @FXML private TextField address;
    @FXML private TextField country;
    @FXML private TextField state;
    @FXML private TextField city;
    @FXML private TextField postalCode;
    @FXML private TextField houseSize;
    @FXML private TextArea  details;
    @FXML private Label     message;
    @FXML private Button    editSave;
    @FXML private Button    mortgageButton;
          private House     house;
    /**
     * 
     * @param event 
     */
   @FXML
   public void close(ActionEvent event) {
       Utility.getStage(event).close();
    }
   /**
    * 
    * @param event 
    */
    @FXML
    public void editSave(ActionEvent event) {
        if(address.isEditable()){
            String address    = this.address.getText();
            String country    = this.country.getText();
            String state      = this.state.getText();
            String city       = this.city.getText();
            String postalCode = this.postalCode.getText();
            double houseSize  = Double.parseDouble(this.houseSize.getText());
            String details    = this.details.getText();
            try{
                House dbTestHouse = new House(house.getPropertyId(), address, country, state, city, postalCode, details, houseSize);
                DatabaseServices.updateModelToDatabase(dbTestHouse);
            } catch(SQLException e){
                e.printStackTrace();
                message.setText("Error: An error has occurred in the database. Make sure your values are correct.");
                return;
            }
            house.setAddress(address);
            house.setCountry(country);
            house.setState(state);
            house.setCity(city);
            house.setPostalCode(postalCode);
            house.setSize(houseSize);
            house.setDetails(details);
            editSave.setText("Edit");
            setEditable(false);
            message.setText("");
        }
        else{
            editSave.setText("Save");
            setEditable(true);
            message.setText("");
        }
    }
    @FXML
    void addViewMortgage(ActionEvent event){
        
    }
    /**
     * 
     * @param edit 
     */
    private void setEditable(boolean edit){
        address.setEditable(edit);
        country.setEditable(edit);
        state.setEditable(edit);
        city.setEditable(edit);
        postalCode.setEditable(edit);
        houseSize.setEditable(edit);
        details.setEditable(edit);
        mortgageButton.setVisible(edit);
    }
    
    /**
     * 
     * @param house 
     */
    public void setHouse(House house){
        this.house = house;
        address.setText(house.getAddress());
        country.setText(house.getCountry());
        state.setText(house.getState());
        city.setText(house.getCity());
        postalCode.setText(house.getPostalCode());
        houseSize.setText((""+house.getSize()));
        System.out.println(house.getSize());
        System.out.println(houseSize.getText());
        details.setText(house.getDetails());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
