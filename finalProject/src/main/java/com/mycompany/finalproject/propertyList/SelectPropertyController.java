package com.mycompany.finalproject.propertyList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.condoList.SelectCondoController;
import com.mycompany.finalproject.houseList.SelectHouseController;
import com.mycompany.finalproject.plexList.SelectPlexController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;


public class SelectPropertyController implements Initializable{

    private Parent previous;
    private PropertyReceiver propertyReceiver;

    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }

    @FXML
    public void switchToCondoList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectCondo");
        Parent root = fxmlLoader.load();
        SelectCondoController controller = fxmlLoader.getController();
        controller.setPropertyReceiver(propertyReceiver);
        controller.setPropertyReceiverRoot(previous);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);
    }

    @FXML
    public void switchToHouseList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectHouse");
        Parent root = fxmlLoader.load();
        SelectHouseController controller = fxmlLoader.getController();
        controller.setPropertyReceiver(propertyReceiver);
        controller.setPropertyReceiverRoot(previous);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);
    }

    @FXML
    public void switchToPlexList(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectPlex");
        Parent root = fxmlLoader.load();
        SelectPlexController controller = fxmlLoader.getController();
        controller.setPropertyReceiver(propertyReceiver);
        controller.setPropertyReceiverRoot(previous);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);
    }

    public void setPrevious(Parent root) {
        previous = root;
    }

    public void setPropertyReceiver(PropertyReceiver propertyReceiver) {
        this.propertyReceiver = propertyReceiver;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
