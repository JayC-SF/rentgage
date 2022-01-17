package com.mycompany.finalproject.unitList;

import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.model.Unit;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SelectUnitViewController implements Initializable{

    @FXML private TextField appartmentNumber;
    @FXML private TextField unitSize;
    @FXML private TextArea  details;
    @FXML private Label     message;
          private Unit      unit;
    private Parent previous;

    @FXML
    public void back(ActionEvent event) throws IOException {
        Utility.setRoot(event, previous);
    }
   public void setUnit(Unit unit){
        this.unit = unit;
        this.appartmentNumber.setText(unit.getAppartmentNumber());
        this.unitSize.setText(""+unit.getSize());
        this.details.setText(unit.getDetails());
    }

    public void setPrevious(Parent root) {
        this.previous = root;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
}
