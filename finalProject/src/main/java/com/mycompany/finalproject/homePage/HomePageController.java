/*
 This class controlls the buttons of the HomePage fxml file.
 */
package com.mycompany.finalproject.homePage;
import com.mycompany.finalproject.Utility;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.event.ActionEvent;


/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class HomePageController {
    @FXML
    private void switchToPropertyList(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("PropertyList");
        Parent root = fxmlLoader.load();
        Utility.setRoot(event, root);
    }
    
    @FXML
    private void switchToContractorList(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("ContractorList");
        Parent root = fxmlLoader.load();
        Utility.setRoot(event, root);
    }
    
    @FXML
    private void switchToLeaseList(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("LeaseList");
        Parent root = fxmlLoader.load();
        Utility.setRoot(event, root);
    }
    
    @FXML
    private void switchToTenanList(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("TenantList");
        Parent root = fxmlLoader.load();
        Utility.setRoot(event, root);
    }
    
    @FXML
    private void switchToBankList(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("BankList");
        Parent root = fxmlLoader.load();
        Utility.setRoot(event, root);
    }
    
    @FXML
    private void switchToMaintenanceList(ActionEvent event) throws IOException{
        FXMLLoader fxmlLoader = Utility.getFXMLLoader("MaintenanceList");
        Parent root = fxmlLoader.load();
        Utility.setRoot(event, root);
    }
    
    @FXML
    private void switchToMortgageList(ActionEvent event) throws IOException{
        Utility.setRoot(event, "MortgageList");
    }
}
