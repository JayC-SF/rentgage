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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UnitPageViewController implements Initializable{
    
    @FXML private TextField appartmentNumber;
    @FXML private TextField unitSize;
    @FXML private TextArea  details;
    @FXML private Label     message;
    @FXML private Button    editSave;
          private Unit      unit;
          private Plex      plex;
    

    @FXML
    void close(ActionEvent event) {
        Utility.getStage(event).close();
    }

    @FXML
    void editSave(ActionEvent event) {
        if(appartmentNumber.isEditable()){
            String appartmentNumber = this.appartmentNumber.getText();
            Double unitSize         = Double.parseDouble(this.unitSize.getText());
            String details          = this.details.getText();
            Unit dbTestUnit         = new Unit(this.unit.getUnitId(), appartmentNumber, unitSize, details, plex.getPropertyId());
            try{
                DatabaseServices.updateModelToDatabase(dbTestUnit);
            }catch(SQLException e ){
                e.printStackTrace();
                message.setText("Error: An error has occurred in the database, make sure your values are correct.");
                return;
            }
            unit.setAppartmentNumber(appartmentNumber);
            unit.setSize(unitSize);
            unit.setDetails(details);
            editSave.setText("Edit");
            setTextFieldEditable(false);
            message.setText("");
        }
        else{
            editSave.setText("Save");
            setTextFieldEditable(true);
            message.setText("");
        }
    }
    
    public void setUnit(Unit unit){
        this.unit = unit;
        this.appartmentNumber.setText(unit.getAppartmentNumber());
        this.unitSize.setText(""+unit.getSize());
        this.details.setText(unit.getDetails());
    }
    public void setPlex(Plex plex){
        this.plex = plex;
    }
    private void setTextFieldEditable(boolean edit){
        appartmentNumber.setEditable(edit);
        unitSize.setEditable(edit);
        details.setEditable(edit);
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
