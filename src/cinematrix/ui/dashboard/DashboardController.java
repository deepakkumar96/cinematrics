/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXNodesList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class DashboardController implements Initializable {

    @FXML private BorderPane parent;
    @FXML private GridPane grid;
    @FXML private GridPane gridPosters;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        System.out.println("INIT -- ");
        try{
            Pane bookingPane = (VBox) FXMLLoader.load(getClass().getResource("/cinematrix/ui/dashboard/movie_booking.fxml"));
            
            GridPane seatsPane = (GridPane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/dashboard/seats.fxml"));
            
            // adding movie posters to top of dashboard
            ImageView[] posters = new ImageView[8];
            Image[] posterImages = {
                new Image(getClass().getResourceAsStream("/cinematrix/images/batman.png")),
                new Image(getClass().getResourceAsStream("/cinematrix/images/star_wars.png")),
                new Image(getClass().getResourceAsStream("/cinematrix/images/xman.png")),
                new Image(getClass().getResourceAsStream("/cinematrix/images/titanic.png")),
                new Image(getClass().getResourceAsStream("/cinematrix/images/fight_club.png")),
                new Image(getClass().getResourceAsStream("/cinematrix/images/batman2.png")),
                new Image(getClass().getResourceAsStream("/cinematrix/images/star_wars.png")),
                new Image(getClass().getResourceAsStream("/cinematrix/images/titanic.png")),
            };
            
            for(int i=0; i<posters.length; i++){
                System.out.println(posterImages[i]);
                posters[i] = new ImageView(posterImages[i]);
                //posters[i].setPreserveRatio(true);
                posters[i].fitWidthProperty().bind(parent.widthProperty().divide(posters.length));
                //posters[i].fitWidthProperty().setValue(600/6);
                //posters[i].fitHeightProperty().bind(parent.heightProperty().divide(3));
                
                posters[i].fitHeightProperty().bind(gridPosters.heightProperty());
                
                System.out.println(parent.getPrefWidth() + " : " + (parent.getPrefWidth()/6));
                
                gridPosters.add(posters[i], i, 0);
            }
            gridPosters.prefHeightProperty().bind(parent.heightProperty().divide(3.2));
            gridPosters.setStyle("-fx-border-color: red;");
            
            grid.add(bookingPane, 1, 0);
            grid.add(seatsPane, 0, 0);
            
            
            //Adding Floating Button
            JFXButton b1 = new JFXButton("start");
            b1.setButtonType(JFXButton.ButtonType.RAISED);
            
            JFXButton b2 = new JFXButton("B1");
            b2.setButtonType(JFXButton.ButtonType.RAISED);
            
            JFXButton b3 = new JFXButton("B1");
            b3.setButtonType(JFXButton.ButtonType.RAISED);
            
            JFXNodesList buttonList = new JFXNodesList();
            buttonList.addAnimatedNode(b1);
            buttonList.addAnimatedNode(b2);
            buttonList.addAnimatedNode(new ComboBox());
            buttonList.setSpacing(10d);
            
            bookingPane.getChildren().add(b1);
            
            bookingPane.setStyle("-fx-border-color:red;");
            seatsPane.setStyle("-fx-border-color:red;");
            
        
        }catch(Exception ex){
            ex.printStackTrace();
        }    
        
    }    
    
}
