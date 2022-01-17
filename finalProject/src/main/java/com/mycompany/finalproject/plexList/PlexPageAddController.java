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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 *
 * @author JCSF
 */
public class PlexPageAddController implements Initializable{
    
    @FXML private TextField address;
    @FXML private TextField country;
    @FXML private TextField state;
    @FXML private TextField city;
    @FXML private TextField postalCode;
    @FXML private TextArea  details;
    @FXML private Label     message;
    private TableView<Plex> plexTable; 
    
    @FXML
    public void add(ActionEvent event) {
        String address    = this.address.getText();
        String country    = this.country.getText();
        String state      = this.country.getText();
        String city       = this.city.getText();
        String postalCode = this.postalCode.getText();
        String details    = this.details.getText();
        Plex plex         = new Plex(address, country, state, city, postalCode, details);
        try{
            DatabaseServices.addModelToDb(plex);
        }catch(SQLException e){
            e.printStackTrace();
            message.setText("Error: New Plex information could not be added to the database.");
            return;
        }
        message.setText("");
        plexTable.getItems().add(plex);
        close(event);
    }
    public void setTableView(TableView<Plex> plexTable){
        this.plexTable = plexTable;
    }
    @FXML
    public void close(ActionEvent event) {
        Utility.getStage(event).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
