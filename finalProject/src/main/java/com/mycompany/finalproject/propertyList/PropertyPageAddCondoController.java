/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.propertyList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Condo;
import java.io.IOException;
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
import javafx.stage.Stage;

/**
 *
 * @author JCSF
 */
public class PropertyPageAddCondoController implements Initializable{
    private TableView<Condo> condoTable;
    @FXML private TextField  address;
    @FXML private TextField  country;
    @FXML private TextField  state;
    @FXML private TextField  city;
    @FXML private TextField  postalCode;
    @FXML private TextField  appartmentNumber;
    @FXML private TextField  condoSize;
    @FXML private TextField  condoFee;
    @FXML private TextArea   details;
    @FXML private Label      message;

    @FXML
    public void add(ActionEvent event) {
        ObservableList<Condo> obs = condoTable.getItems();
        String address          = this.address.getText();
        String country          = this.country.getText();
        String state            = this.state.getText();
        String city             = this.city.getText();
        String postalCode       = this.postalCode.getText();
        String details          = this.details.getText();
        double size             = Double.parseDouble(this.condoSize.getText());
        String appartmentNumber = this.appartmentNumber.getText();
        double condoFee         = Double.parseDouble(this.condoFee.getText());
        Condo condo             = new Condo(address,country,state,city,postalCode,details,appartmentNumber, size, condoFee);
        try {
            DatabaseServices.addModelToDb(condo);
            obs.add(condo);
        } catch (SQLException ex) {
            ex.printStackTrace();
            message.setText("Error: New House information could not be added to the database.");
            return;
        }
        close(event);
    }

    @FXML
    public void back(ActionEvent event) throws IOException{
        Utility.setRoot(event, "PropertyPageAdd");
    }

    @FXML
    public void close(ActionEvent event) {
        Stage stage = Utility.getStage(event);
        stage.close();
    }
    public void setTableView(TableView<Condo> table){
        condoTable = table;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        condoSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(Utility.getAppartmentSizeRegex())) {
                    condoSize.setText(oldValue);
                }
            }
        });
        condoFee.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(Utility.getCurrencyRegex())) {
                    condoFee.setText(oldValue);
                }
            }
        });
    
    }
    
}
