
package com.mycompany.finalproject.propertyList;
import com.mycompany.finalproject.Utility;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class PropertyListController {

    @FXML
    public void switchToCondoList(ActionEvent event) throws IOException{
        Utility.setRoot(event, "CondoList");
    }

    @FXML
    public void switchToHomePage(ActionEvent event) throws IOException{
        Utility.setRoot(event, "HomePage");
    }

    @FXML
    public void switchToHouseList(ActionEvent event) throws IOException{
        Utility.setRoot(event, "HouseList");
    }

    @FXML
    public void switchToPlexList(ActionEvent event) throws IOException{
        Utility.setRoot(event, "PlexList");
    }
}
