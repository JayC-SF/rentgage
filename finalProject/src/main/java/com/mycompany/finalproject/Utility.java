/**
 * Juan-Carlos Sreng-Flores
 * 1533920
 * 
 * DESCRIPTION:
 * This class it meant to be used as a utility class. It has many methods that are
 * to be used by possibly many other classes to set the root of the stage, or even
 * simply load the fxml files using the FXMLLoader.
 * 
 * HOW TO USE:
 * It needs to be used with the FXMLLoader object. It is associated with a view,
 * so the @FXML properties variables will be initialized with the FXMLLoader.
 * Once done, it get can retrieved with the getController() method in order to
 * initialize many properties. It may have some getter and setters potentially.
 * 
 */
package com.mycompany.finalproject;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author JCSF
 */
public class Utility {
    
    /**
     * 
     * This method returns a Parent object. It returns the current parent being displayed.
     * @return 
     */
    public static Parent getRoot(ActionEvent event){
        Parent root = (Parent)((Node)event.getSource()).getScene().getRoot();
        return root;
    }
    /**
     * 
     */
    public static Stage getStage(ActionEvent event){
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        return window;
    }
    /**
     * This method will set the root of the from the Stage of the event to another 
     * root given by the String argument.
     * @param event
     * @param root
     * @throws IOException 
     */
    public static void setRoot(ActionEvent event, Parent root) throws IOException{
        Scene scene = (Scene)((Node)event.getSource()).getScene();
        scene.setRoot(root);
    }
    
    /**
     * This method will set the root of the from the Stage of the event to another 
     * root given by the String argument. It uses another method to load the fxml
     * file.
     * @param event
     * @param fxml
     * @throws IOException 
     */
    public static void setRoot(ActionEvent event, String fxml) throws IOException{
        Scene scene = (Scene)((Node)event.getSource()).getScene();
        Parent root = getFXMLLoader(fxml).load();
        scene.setRoot(root);
    }
    /**
     * This method gets the FXMLLoader object from and FXML file.
     * @param fxml
     * @return FXMLLoader
     */
    public static FXMLLoader getFXMLLoader(String fxml){
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    /**
     * 
     * @param query
     * @return 
     */
    public static String getQueryFromToString(String query){
        return query.split(":")[1];
    }
    /**
     * 
     * @return 
     */
    public static String getAppartmentSizeRegex(){
        return "^(\\d+\\.|\\d*\\.5|\\d*|\\d*\\.0+)$";
    }
    /**
     * 
     * @return 
     */
    public static String getCurrencyRegex(){
        return "^(\\.\\d{1,2}|\\d+\\.|\\d+\\.\\d{1,2}|\\d*)$";
    }
    /**
     * 
     * @param label
     * @param message
     * @param color 
     */
    public static void setLabelMessageAndColor(Label label, String message, String color){
        label.setTextFill(Color.web(color));
        label.setText(message);
    }
    /**
     * 
     * @param fileName
     * @return 
     */
    public static String getFileExtension(String fileName){
        String[] split = fileName.split("\\.");
        return split[split.length-1];
    }
}
