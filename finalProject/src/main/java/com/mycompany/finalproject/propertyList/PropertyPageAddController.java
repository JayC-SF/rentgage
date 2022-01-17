/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.propertyList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.houseList.HousePageAddController;
import com.mycompany.finalproject.model.Condo;
import com.mycompany.finalproject.model.House;
import com.mycompany.finalproject.model.Plex;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

/**
 *
 * @author JCSF
 */
public class PropertyPageAddController implements Initializable{
    private TableView<Plex> plexTable;
    private TableView<Condo> condoTable;
    private TableView<House> houseTable;
    @FXML
    private void close(ActionEvent event){
        Stage stage = Utility.getStage(event);
        stage.close();
    }
    
    @FXML
    public void addCondo(ActionEvent event) throws IOException{
        FXMLLoader loader = Utility.getFXMLLoader("PropertyPageAddCondo");
        Parent root = loader.load();
        PropertyPageAddCondoController controller = (PropertyPageAddCondoController) loader.getController();
        controller.setTableView(condoTable);
        Utility.setRoot(event, root);
    }

    @FXML
    public void addHouse(ActionEvent event) throws IOException{
        FXMLLoader loader = Utility.getFXMLLoader("PropertyPageAddHouse");
        Parent root = loader.load();
        HousePageAddController controller = (HousePageAddController) loader.getController();
        controller.setTableView(houseTable);
        Utility.setRoot(event, root);
    }

    @FXML
    public void addPlex(ActionEvent event) {
        throw new UnsupportedOperationException("addPlex is not supported yet");
    }
    
    public void setTableViews(TableView<Plex> plexTable, TableView<Condo> condoTable, TableView<House> houseTable){
        this.plexTable  = plexTable;
        this.condoTable = condoTable;
        this.houseTable = houseTable;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
    
    
    
}
