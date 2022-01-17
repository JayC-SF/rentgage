package com.mycompany.finalproject.plexList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Plex;
import com.mycompany.finalproject.model.Unit;
import com.mycompany.finalproject.propertyList.PropertyReceiver;
import com.mycompany.finalproject.unitList.SelectUnitViewController;
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

public class SelectPlexController implements Initializable{

    @FXML private TableView<Plex>           plexTable;
    @FXML private TableColumn<Plex, String> columnAddress;
    @FXML private TableColumn<Plex, String> columnNumAppt;
    @FXML private TableView<Unit>           unitTable;
    @FXML private TableColumn<Unit, String> columnUnitNumber;
    @FXML private TableColumn<Unit, String> columnSize;
    @FXML private Label                     message;
          private PropertyReceiver propertyReceiver;
          private Parent                    propertyReceiverRoot;
          private Parent                    previous;
          private ObservableList<Plex>      plexList;
          private ObservableList<Unit>      unitList;

    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }

    @FXML
    public void selectPlex(ActionEvent event) throws IOException {
        Plex selected = plexTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No plex selected.", "Red");
            return;
        }
        propertyReceiver.setProperty(selected);
        Utility.setRoot(event, propertyReceiverRoot);
    }

    @FXML
    public void viewPlex(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectPlexView");
        Parent root = fxmlLoader.load();
        SelectPlexViewController controller = fxmlLoader.getController();
        Plex selected = plexTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No plex selected.", "red");
            return;
        }else{
            message.setText("");
        }
        controller.setPlex(selected);
        controller.setPropertyReceiver(propertyReceiver);
        controller.setPropertyReceiverRoot(propertyReceiverRoot);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);        
    }

    @FXML
    public void viewUnit(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectUnitView");
        Parent root = fxmlLoader.load();
        SelectUnitViewController controller = fxmlLoader.getController();
        Unit selected = unitTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No plex selected.", "red");
            return;
        }else{
            message.setText("");
        }
        controller.setUnit(selected);
        controller.setPrevious(Utility.getRoot(event));
        Utility.setRoot(event, root);   
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
        
        //Set up the unit Table View.
        columnUnitNumber.setCellValueFactory(new PropertyValueFactory<Unit, String>("appartmentNumber"));
        columnSize.setCellValueFactory(new PropertyValueFactory<Unit, String>("size"));
        unitTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        //Set up the Plex Table View
        String allPlexsQuery = Plex.getSelectQuery();
        plexList = (ObservableList<Plex>)DatabaseServices.loadModelsFromDb(Plex.class, allPlexsQuery);
        columnAddress.setCellValueFactory(new PropertyValueFactory<Plex, String>("address"));
        columnNumAppt.setCellValueFactory(new PropertyValueFactory<Plex, String>("numOfAppt"));
        plexTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        plexTable.setItems(plexList);
        //Create dynamic change of unit table, so the unit table shows the units of the selected plex in the table view.
        plexTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            Plex plex = plexTable.getSelectionModel().getSelectedItem();
            
            if(plex != null){
                String allUnitsQuery = plex.getUnitSelectQuery();
                unitList = (ObservableList<Unit>) DatabaseServices.loadModelsFromDb(Unit.class, allUnitsQuery);
                unitTable.setItems(unitList);
            }
            else{
                unitTable.setItems(null);
            }
        });
    }

}
