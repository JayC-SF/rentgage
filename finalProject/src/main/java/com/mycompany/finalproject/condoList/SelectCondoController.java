package com.mycompany.finalproject.condoList;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Condo;
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

/**
 * 
 * @author Juan-Carlos Sreng-Flores
 */
public class SelectCondoController implements Initializable{

    @FXML private TableView<Condo>           condoTable;
    @FXML private TableColumn<Condo, String> columnCondoAddress;
    @FXML private TableColumn<Condo, String> columnCondoApptNumber;
    @FXML private Label                      message;
          private PropertyReceiver           propertyReceiver;
          private Parent                     propertyReceiverRoot;
          private Parent                     previous;
    private ObservableList<Condo>            condoList;
    
    /**
     * Sets the controller to the previous page.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }
    /**
     * displays the condo information in a page.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void viewCondo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("SelectCondoView");
        Parent root = fxmlLoader.load();
        SelectCondoViewController controller = fxmlLoader.getController();
        Condo selected = condoTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No selected row.", "red");
            return;
        }else{
            message.setText("");
        }
        controller.setCondo(selected);
        controller.setPropertyReceiver(propertyReceiver);
        controller.setPropertyReceiverRoot(propertyReceiverRoot);
        controller.setPrevious(Utility.getRoot(event));
        Utility.getStage(event).setHeight(900);
        Utility.setRoot(event, root);
    }
    /**
     * selects the condo to be returned to the receiver.
     * @param event
     * @throws IOException 
     */
    @FXML
    public void selectCondo(ActionEvent event) throws IOException {
        Condo selected = condoTable.getSelectionModel().getSelectedItem();
        if(selected == null){
            Utility.setLabelMessageAndColor(message, "Error: No selected row.", "Red");
            return;
        }
        propertyReceiver.setProperty(selected);
        Utility.setRoot(event, propertyReceiverRoot);
    }
    /**
     * Setter for propertyReceiver.
     * @param propertyReceiver 
     */
    public void setPropertyReceiver(PropertyReceiver propertyReceiver) {
        this.propertyReceiver = propertyReceiver;
    }
    /**
     * Setter for propertyReceiverRoot
     * @param propertyReceiverRoot 
     */
    public void setPropertyReceiverRoot(Parent propertyReceiverRoot) {
        this.propertyReceiverRoot = propertyReceiverRoot;
    }
    /**
     * setter for previous root.
     * @param root 
     */
    public void setPrevious(Parent root) {
        this.previous = root;
    }
    /**
     * initialize method used to initalize the tableView and display all the condos
     * created by the user.
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String allCondosQuery = Condo.getSelectQuery();
        condoList = (ObservableList<Condo>) DatabaseServices.loadModelsFromDb(Condo.class, allCondosQuery);
        columnCondoAddress.setCellValueFactory(new PropertyValueFactory<Condo, String>("address"));
        columnCondoApptNumber.setCellValueFactory(new PropertyValueFactory<Condo, String>("appartmentNumber"));
        condoTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        condoTable.setItems(condoList);
    }

}
