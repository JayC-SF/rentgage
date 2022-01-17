/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.finalproject.unitList;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import com.mycompany.finalproject.model.Plex;
import com.mycompany.finalproject.model.Unit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author JCSF
 */
public class UnitPageAddController implements Initializable{
    
    @FXML private TextField appartmentNumber;
    @FXML private TextField unitSize;
    @FXML private TextArea  details;
    @FXML private Label     message;
    private TableView<Unit> unitTable;
    private Plex            plex;

    @FXML
    void add(ActionEvent event) {
        String appartmentNumber = this.appartmentNumber.getText();
        double unitSize         = Double.parseDouble(this.unitSize.getText());
        String details          = this.details.getText();
        Unit unit               = new Unit(appartmentNumber, unitSize, details, plex.getPropertyId());
        try{
            DatabaseServices.addModelToDb(unit);
            unitTable.getItems().add(unit);
            plex.setNumOfAppt(plex.getNumOfAppt()+1);
        }catch(SQLException e){
            e.printStackTrace();
            message.setText("Error: New Unit information could not be added to the database.");
            return;
        }
        close(event);
    }

    @FXML
    void close(ActionEvent event) {
        Utility.getStage(event).close();
    }
    public void setTableView(TableView<Unit> unitTable) {
        this.unitTable = unitTable;
    }
    public void setPlex(Plex plex){
        this.plex = plex;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        unitSize.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
               if(!newValue.matches(Utility.getAppartmentSizeRegex())){
                   unitSize.setText(oldValue);
               }
            }
        });
    }
    
}
