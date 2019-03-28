/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.dashboard;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author deepak
 */
public class DashBoardTest extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = null;
        
        try{
            root = (Parent) FXMLLoader.load(getClass().getResource("/cinematrix/ui/dashboard/Dashboard.fxml"));
            
        }catch(Exception ex){
            ex.printStackTrace();
        }   
        
        Scene scene = new Scene(root, 600, 400);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    /**^
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
