package com.mycompany.finalproject.houseList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.model.House;
import com.mycompany.finalproject.propertyList.PropertyReceiver;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SelectHouseViewController implements Initializable{

    @FXML private TextField  address;
    @FXML private TextField  country;
    @FXML private TextField  state;
    @FXML private TextField  city;
    @FXML private TextField  postalCode;
    @FXML private TextField  houseSize;
    @FXML private TextArea   details;
    @FXML private Label      message;
    private Parent           previous;
    private Parent           propertyReceiverRoot;
    private PropertyReceiver propertyReceiver;
    private House            house;

    @FXML
    void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }

    @FXML
    void selectHouse(ActionEvent event) throws IOException {
        propertyReceiver.setProperty(house);
        Utility.setRoot(event, propertyReceiverRoot);
    }

    void setHouse(House house) {
        this.house = house;
        address.setText(house.getAddress());
        country.setText(house.getCountry());
        state.setText(house.getState());
        city.setText(house.getCity());
        postalCode.setText(house.getPostalCode());
        houseSize.setText(""+house.getSize());
        details.setText(house.getDetails());
    }

    void setPropertyReceiver(PropertyReceiver propertyReceiver) {
        this.propertyReceiver = propertyReceiver;
    }

    void setPropertyReceiverRoot(Parent propertyReceiverRoot) {
        this.propertyReceiverRoot = propertyReceiverRoot;
    }

    void setPrevious(Parent root) {
        previous = root;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}

