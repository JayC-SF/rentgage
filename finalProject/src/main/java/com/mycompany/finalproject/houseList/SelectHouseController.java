package com.mycompany.finalproject.houseList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.House;
import com.mycompany.finalproject.propertyList.PropertyReceiver;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class SelectHouseController implements Initializable{
    
    @FXML private TableView<House>           houseTable;
    @FXML private TableColumn<House, String> columnAddress;
    @FXML private Label                      message;
          private Parent                     previous;
          private Parent                     propertyReceiverRoot;
          private PropertyReceiver           propertyReceiver;
          private ObservableList<House>      houseList;

    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }

    @FXML
    public void viewHouse(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectHouseView");
        Parent root = fxmlLoader.load();
        SelectHouseViewController controller = fxmlLoader.getController();
        House selected = houseTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No selected row.", "red");
            return;
        }else{
            message.setText("");
        }
        controller.setHouse(selected);
        controller.setPropertyReceiver(propertyReceiver);
        controller.setPropertyReceiverRoot(propertyReceiverRoot);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);
        
    }
    @FXML
    public void selectHouse(ActionEvent event) throws IOException{
        House selected = houseTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No selected row.", "Red");
            return;
        }
        propertyReceiver.setProperty(selected);
        Utility.setRoot(event, propertyReceiverRoot);
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
        String allHousesQuery = House.getSelectQuery();
        houseList = (ObservableList<House>)DatabaseServices.loadModelsFromDb(House.class, allHousesQuery);
        columnAddress.setCellValueFactory(new PropertyValueFactory<House, String>("address"));
        houseTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        houseTable.setItems(houseList);       
    }

}
