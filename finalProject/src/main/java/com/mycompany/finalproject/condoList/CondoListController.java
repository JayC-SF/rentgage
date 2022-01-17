package com.mycompany.finalproject.condoList;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Condo;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;

/**
 * 
 * @author Juan-Carlos Sreng-Flores
 */
public class CondoListController implements Initializable{

    @FXML private TableView<Condo>           condoTable;
    @FXML private TableColumn<Condo, String> columnCondoAddress;
    @FXML private TableColumn<Condo, String> columnCondoApptNumber;
    @FXML private Label                      message;
          private Stage                      addCondo;
          private Stage                      viewCondo;
          private ObservableList<Condo>      condoList;
          private Alert                      alert;
          
    /**
     * This method adds a condo to the database, and updates the table view for display.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void addCondo(ActionEvent event) throws IOException{
        // Load the fxml page from the url.
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("CondoPageAdd");
        
        // Load the page as a javafx object. Usually a VBOX.
        Parent root = fxmlLoader.load();
        // Get the controller associated with this view.
        CondoPageAddController controller =(CondoPageAddController) fxmlLoader.getController();
        controller.setTableView(condoTable);
        controller.setResult(message);
        //Reset the error message text in case it was not empty.
        message.setText("");
        // Initialize the properties with the current stage. At that point
        // the initialize method should've been called by the FXMLLoader of this
        // Check if the scene currently has a scene.
        Scene scene = addCondo.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 900);
            addCondo.setScene(scene);
        } else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        if(!addCondo.isShowing()) {
            addCondo.show();
        }
        else{
            addCondo.toFront();
        }
    
    }

    /**
     * This method displays a condo in a new stage.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void viewCondo(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("CondoPageView");

        Parent root = fxmlLoader.load();
        CondoPageViewController controller = (CondoPageViewController) fxmlLoader.getController();
        
        Condo selected = condoTable.getSelectionModel().getSelectedItem();
        //reset the error message in case it was not empty.
        if(selected == null){
            Utility.setLabelMessageAndColor(message,"Error: No condo selected.", "Red");
            return;
        }else{
            message.setText("");
        }
            
        //PASS INFORMATION TO THE CONTROLLER METHOD
        controller.setCondo(selected);
        
        Scene scene = viewCondo.getScene();
        if(scene == null){
            scene = new Scene(root, 700, 900);
            viewCondo.setScene(scene);
        } else{
            scene.setRoot(root);
        }
        // show the new window if not already been shown.
        if(!viewCondo.isShowing()) {
            viewCondo.show();
        }
        else{
            viewCondo.toFront();
        }
    }
    /**
     * This method deletes a condo from the database.
     * @param event 
     */
    @FXML 
    public void deleteCondo(ActionEvent event){
        Condo selected = condoTable.getSelectionModel().getSelectedItem();
        int index = condoTable.getSelectionModel().getSelectedIndex();
        if(selected == null){
            Utility.setLabelMessageAndColor(message,"Error: No condo selected.", "Red");
            return;
        }
        Stage stage = Utility.getStage(event);
        alert.setContentText("Are you sure you want to delete the condo address "+selected.getAddress()+"?");
        Optional<ButtonType> option = alert.showAndWait();
        if(option.get() == ButtonType.OK){
            //delete
            try{
                DatabaseServices.deleteModelFromDb(selected);
                condoList.remove(index);
            }
            catch(SQLException e){
                e.printStackTrace();
                Utility.setLabelMessageAndColor(message,"Error: Could not delete this condo in database.", "Red");
            }
        }
        message.setText("");
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
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        viewCondo = new Stage();
        viewCondo.setTitle("Condo Details");
        addCondo  = new Stage();
        addCondo.setTitle("Add New Condo");
        String allCondosQuery = Condo.getSelectQuery();
        condoList = (ObservableList<Condo>) DatabaseServices.loadModelsFromDb(Condo.class, allCondosQuery);
        columnCondoAddress.setCellValueFactory(new PropertyValueFactory<Condo, String>("address"));
        columnCondoApptNumber.setCellValueFactory(new PropertyValueFactory<Condo, String>("appartmentNumber"));
        condoTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        condoTable.setItems(condoList);
        alert    = new Alert(Alert.AlertType.CONFIRMATION);
        
    }

}