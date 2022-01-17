package com.mycompany.finalproject.plexList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.model.Plex;
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

public class SelectPlexViewController implements Initializable{
    
    @FXML private TextField  address;
    @FXML private TextField  country;
    @FXML private TextField  state;
    @FXML private TextField  city;
    @FXML private TextField  postalCode;
    @FXML private TextArea   details;
    @FXML private Label      message;
    private Plex             plex;
    private PropertyReceiver propertyReceiver;
    private Parent propertyReceiverRoot;
    private Parent previous;

    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }

    @FXML
    public void selectPlex(ActionEvent event) throws IOException {
        propertyReceiver.setProperty(plex);
        Utility.setRoot(event, propertyReceiverRoot);
    }

    public void setPlex(Plex plex) {
        this.plex = plex;
        address.setText(plex.getAddress());
        country.setText(plex.getCountry());
        state.setText(plex.getState());
        city.setText(plex.getCity());
        postalCode.setText(plex.getPostalCode());
        details.setText(plex.getDetails());
    }

    public void setPropertyReceiver(PropertyReceiver propertyReceiver) {
        this.propertyReceiver = propertyReceiver;
    }

    public void setPropertyReceiverRoot(Parent propertyReceiverRoot) {
        this.propertyReceiverRoot = propertyReceiverRoot;
    }

    public void setPrevious(Parent root) {
        this.previous = root;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
