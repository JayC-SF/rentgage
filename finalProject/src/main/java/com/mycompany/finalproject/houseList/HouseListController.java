package com.mycompany.finalproject.houseList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.House;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class HouseListController implements Initializable{
    @FXML private TableView<House>           houseTable;
    @FXML private TableColumn<House, String> columnAddress;    
    @FXML private Label                      message;
          private Stage                      addHouse;
          private Stage                      viewHouse;
          private Alert                      alert;    
          private ObservableList<House>      houseList;
    @FXML
    public void addHouse(ActionEvent event) throws IOException{
        FXMLLoader loader = Utility.getFXMLLoader("HousePageAdd");
        Parent root       = loader.load();
        HousePageAddController controller = (HousePageAddController) loader.getController();
        controller.setTableView(houseTable);
        message.setText("");
        Scene scene = addHouse.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 750);
            addHouse.setScene(scene);
        } else {
            scene.setRoot(root);
        }
        if(!addHouse.isShowing()) {
            addHouse.show();
        }
        else{
            addHouse.toFront();
        }
    }

    @FXML
    public void back(ActionEvent event) throws IOException{
        Utility.setRoot(event, "PropertyList");
    }

    @FXML
    public void viewHouse(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("HousePageView");
        Parent root = fxmlLoader.load();
        HousePageViewController controller = (HousePageViewController) fxmlLoader.getController();
        House selected = houseTable.getSelectionModel().getSelectedItem();
        //reset the error message in case it was not empty.
        if(selected == null){
            message.setText("Error: No selected row.");
            return;
        }else{
            message.setText("");
        }
        //PASS INFORMATION TO THE CONTROLLER METHOD
        controller.setHouse(selected);
        Scene scene = viewHouse.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 750);
            viewHouse.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        if(!viewHouse.isShowing()) {
            viewHouse.show();
        }else{
            viewHouse.toFront();
        }
    }
    
    @FXML
    public void deleteHouse(ActionEvent event){
        House selected = houseTable.getSelectionModel().getSelectedItem();
        int index = houseTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            message.setText("Error: No house selected.");
            return;
        }
        alert.setContentText("Are you sure you want to delete the "+selected.getAddress()+" house?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            //delete
            try{
                DatabaseServices.deleteModelFromDb(selected);
                houseList.remove(index);
            }
            catch(SQLException e){
                e.printStackTrace();
                message.setText("Error: Could not delete this house in database.");
                return;
            }
        }
        message.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewHouse = new Stage();
        viewHouse.setTitle("House Details");
        addHouse = new Stage();
        addHouse.setTitle("Add New House");
        String allHousesQuery = House.getSelectQuery();
        houseList = (ObservableList<House>)DatabaseServices.loadModelsFromDb(House.class, allHousesQuery);
        columnAddress.setCellValueFactory(new PropertyValueFactory<House, String>("address"));
        houseTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        houseTable.setItems(houseList);
        alert = new Alert(Alert.AlertType.CONFIRMATION);
    }

}