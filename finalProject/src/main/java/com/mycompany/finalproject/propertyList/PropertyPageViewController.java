
package com.mycompany.finalproject.propertyList;

import com.mycompany.finalproject.Utility;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author Juan-Carlos Sreng-Flores
 */
public class PropertyPageViewController implements Initializable{
    
    @FXML
    private void close(ActionEvent event){
        Stage stage = Utility.getStage(event);
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
    }
    
}
