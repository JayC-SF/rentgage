package com.mycompany.finalproject.houseList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.House;
import com.mycompany.finalproject.model.Mortgage;
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


/**
 *
 * @author JCSF
 */
public class HousePageAddController implements Initializable{
    @FXML private TextField  address;
    @FXML private TextField  country;
    @FXML private TextField  state;
    @FXML private TextField  city;
    @FXML private TextField  postalCode;
    @FXML private TextField  houseSize;
    @FXML private TextArea   details;
    @FXML private Label      message;
    @FXML private Button     mortgageButton;
    private TableView<House> houseTable;

    /**
     * 
     * @param event 
     */
    @FXML
    void addEditMortgage(ActionEvent event){
        
    }
    /**
     * 
     * @param event 
     */
    @FXML
    void close(ActionEvent event) {
        Utility.getStage(event).close();
    }
    /**
     * 
     * @param event 
     */
    @FXML
    void add(ActionEvent event) {
        ObservableList<House> obs = houseTable.getItems();
        String address    = this.address.getText();
        String country    = this.country.getText();
        String state      = this.state.getText();
        String city       = this.city.getText();
        String postalCode = this.postalCode.getText();
        String details    = this.details.getText();
        double size       = Double.parseDouble(this.houseSize.getText());
        House house       = new House(address,country,state,city,postalCode,details,size);
        try {
            DatabaseServices.addModelToDb(house);
            obs.add(house);
        }catch(SQLException ex){
            ex.printStackTrace();
            message.setText("Error: New House information could not be added to the database.");
            return;
        }
        close(event);
    }
    
    public void setTableView(TableView<House> table){
        houseTable = table;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        houseSize.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches(Utility.getAppartmentSizeRegex())) {
                    houseSize.setText(oldValue);
                }
            }
        });
        
    }

}

