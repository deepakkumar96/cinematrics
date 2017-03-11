/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.mics;

import cinematrix.models.Screen;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class MicsController implements Initializable {

    @FXML private ScrollPane scrollPane;
    @FXML private VBox vBoxContainer;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
            BorderPane screens = (BorderPane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/mics/screen_form.fxml"));
            BorderPane languages = (BorderPane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/mics/language_form.fxml"));
            BorderPane categories = (BorderPane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/mics/category_form.fxml"));
            BorderPane certifications = (BorderPane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/mics/certification_form.fxml"));
            
            //screens.setStyle("-fx-border-color:green;");
            // vBoxContainer.setPrefWidth(Double.MAX_VALUE);

            vBoxContainer.getChildren().addAll(
                    screens,
                    languages,
                    categories,
                    certifications
            );
            
            
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
        
        
    }    
    
}
