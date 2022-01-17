package com.mycompany.finalproject.condoList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.model.Condo;
import com.mycompany.finalproject.propertyList.PropertyReceiver;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
/**
 * 
 * @author Juan-Carlos Sreng-Flores
 */
public class SelectCondoViewController implements Initializable{

    @FXML private TextField           address;
    @FXML private TextField           appartmentNumber;
    @FXML private TextField           country;
    @FXML private TextField           state;
    @FXML private TextField           city;
    @FXML private TextField           postalCode;
    @FXML private TextField           condoSize;
    @FXML private TextField           condoFee;
    @FXML private TextArea            details;
    private PropertyReceiver propertyReceiver;
    private Parent                    propertyReceiverRoot;
    private Parent                    previous;
    private Condo                     condo;

    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }
    @FXML
    void select(ActionEvent event) throws IOException {
        propertyReceiver.setProperty(condo);
        Utility.setRoot(event, propertyReceiverRoot);
    }
    /*
     * 
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
     * 
     * @param propertyReceiver 
     */
    void setPropertyReceiver(PropertyReceiver propertyReceiver) {
        this.propertyReceiver = propertyReceiver;
    }
    /**
     * 
     * @param propertyReceiverRoot 
     */
    void setPropertyReceiverRoot(Parent propertyReceiverRoot) {
        this.propertyReceiverRoot = propertyReceiverRoot;
    }
    /**
     * 
     * @param root 
     */
    void setPrevious(Parent root) {
        this.previous = root;
    }
    /**
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
