package com.mycompany.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.mycompany.finalproject.Utility;
import com.mycompany.finalproject.database.DatabaseServices;
import java.io.File;
import java.io.IOException;

import javafx.application.HostServices;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static HostServices host;
    //private static Stage window;

    /**
     * This method is used to start the application.
     * @param stage
     * @throws IOException 
     */
    @Override
    public void start(Stage stage) throws IOException {
        File file = new File("src\\main\\resources\\com\\mycompany\\finalproject\\leases\\");
        host = getHostServices();
        System.out.println(file.createNewFile());
        scene = new Scene(loadFXML("HomePage"), 1200, 900);
        stage.setScene(scene);
        stage.show();
        
    }

    /**
     * This method sets the root 
     * @param fxml
     * @throws IOException 
     */
    public static void setRoot(String fxml) throws IOException {
        Parent currRoot = scene.getRoot();
        scene.setRoot(loadFXML(fxml));
        //return currRoot;
    }
    /**
     * 
     * @param root
     * @throws IOException 
     */
    
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    public static HostServices getHostServicesInstance(){
        return host;
    }
    public static void main(String[] args) {
        launch();
    }

}