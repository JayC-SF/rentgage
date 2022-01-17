package com.mycompany.finalproject.plexList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Mortgage;
import com.mycompany.finalproject.model.Plex;
import com.mycompany.finalproject.model.Unit;
import com.mycompany.finalproject.unitList.UnitPageAddController;
import com.mycompany.finalproject.unitList.UnitPageViewController;
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

public class PlexListController implements Initializable{

    @FXML private TableView<Plex>           plexTable;
    @FXML private TableColumn<Plex, String> columnAddress;
    @FXML private TableColumn<Plex, String> columnNumAppt;
    @FXML private TableView<Unit>           unitTable;
    @FXML private TableColumn<Unit, String> columnUnitNumber;
    @FXML private TableColumn<Unit, String> columnSize;
    @FXML private Label                     message;
          private Stage                     addPlex;
          private Stage                     viewPlex;
          private Alert                     alert;
          private ObservableList<Plex>      plexList;
          private Stage                     addUnit;
          private Stage                     viewUnit;
          private ObservableList<Unit>      unitList;
    /**
     * 
     * @param event 
     */
    @FXML
    public void addPlex(ActionEvent event) throws IOException{
        FXMLLoader loader = Utility.getFXMLLoader("PlexPageAdd");
        Parent root       = loader.load();
        PlexPageAddController controller = (PlexPageAddController) loader.getController();
        controller.setTableView(plexTable);
        message.setText("");
        Scene scene = addPlex.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 750);
            addPlex.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        if(!addPlex.isShowing()) {
            addPlex.show();
        }else{
            addPlex.toFront();
        }
    }
    /**
     * 
     * @param event 
     */
    @FXML
    public void addUnit(ActionEvent event) throws IOException{
        FXMLLoader loader = Utility.getFXMLLoader("UnitPageAdd");
        Parent root       = loader.load();
        UnitPageAddController controller = (UnitPageAddController) loader.getController();
        controller.setTableView(unitTable);
        Plex selected = plexTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            message.setText("Error: No plex selected. Please select a plex in to add a unit. Adding a new plex may be necessary if no plexes available.");
            return;
        }
        message.setText("");
        controller.setPlex(selected);
        
        Scene scene = addUnit.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 550);
            addUnit.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        if(!addUnit.isShowing()) {
            addUnit.show();
        }else{
            addUnit.toFront();
        }
    }
    /**
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    public void back(ActionEvent event) throws IOException{
        Utility.setRoot(event, "PropertyList");
    }
    /**
     * 
     * @param event 
     */
    @FXML
    public void viewPlex(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("PlexPageView");
        Parent root = fxmlLoader.load();
        PlexPageViewController controller = (PlexPageViewController) fxmlLoader.getController();
        Plex selected = plexTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            message.setText("Error: No selected row.");
            return;
        }else{
            message.setText("");
        }
        
        controller.setPlex(selected);
        Scene scene = viewPlex.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 750);
            viewPlex.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        if(!viewPlex.isShowing()) {
            viewPlex.show();
        }else{
            viewPlex.toFront();
        }
    }
     @FXML
    void viewUnit(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("UnitPageView");
        Parent root = fxmlLoader.load();
        UnitPageViewController controller = (UnitPageViewController) fxmlLoader.getController();
        Unit unit = unitTable.getSelectionModel().getSelectedItem();
        Plex plex = plexTable.getSelectionModel().getSelectedItem();
        if(plex == null){
            message.setText("Error: No plex selected.");
            return;
        }
        else if(unit == null){
            message.setText("Error: No unit selected.");
            return;
        }
        else{
            message.setText("");
        }
        controller.setUnit(unit);
        controller.setPlex(plex);
        Scene scene = viewUnit.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 550);
            viewUnit.setScene(scene);
        }else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        if(!viewUnit.isShowing()) {
            viewUnit.show();
        }else{
            viewUnit.toFront();
        }
    }

    
    @FXML
    public void deletePlex(ActionEvent event){
        Plex selected = plexTable.getSelectionModel().getSelectedItem();
        int index = plexTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            message.setText("Error: No plex selected.");
            return;
        }
        alert.setContentText("Are you sure you want to delete the "+selected.getAddress()+" plex ?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            //delete
            try{
                DatabaseServices.deleteModelFromDb(selected);
                plexList.remove(index);
            } catch(SQLException e){
                e.printStackTrace();
                message.setText("Error: Could not delete this plex in the database.");
                return;
            }
        }
        message.setText("");
    }
    @FXML
    void deleteUnit(ActionEvent event) {
        Unit selected = unitTable.getSelectionModel().getSelectedItem();
        int index = unitTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            message.setText("Error: No unit selected.");
            return;
        }
        alert.setContentText("Are you sure you want to delete the "+selected.getAppartmentNumber()+" unit?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            try{
                DatabaseServices.deleteModelFromDb(selected);
                unitList.remove(index);
            }catch(SQLException e){
                message.setText("Error: Could not delete this unit in the database.");
                return;
            }
        }
        message.setText("");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        //Create unit related stuff.
        viewUnit = new Stage();
        viewUnit.setTitle("Unit Details");
        addUnit = new Stage();
        addUnit.setTitle("Add New Unit");
        columnUnitNumber.setCellValueFactory(new PropertyValueFactory<Unit, String>("appartmentNumber"));
        columnSize.setCellValueFactory(new PropertyValueFactory<Unit, String>("size"));
        unitTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        
        //Create plex stuff.
        viewPlex = new Stage();
        viewPlex.setTitle("Plex Details");
        addPlex = new Stage();
        addPlex.setTitle("Add New Plex");
        String allPlexsQuery = Plex.getSelectQuery();
        plexList = (ObservableList<Plex>)DatabaseServices.loadModelsFromDb(Plex.class, allPlexsQuery);
        columnAddress.setCellValueFactory(new PropertyValueFactory<Plex, String>("address"));
        columnNumAppt.setCellValueFactory(new PropertyValueFactory<Plex, String>("numOfAppt"));
        plexTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        plexTable.setItems(plexList);
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