/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.main;

import cinematrix.ui.custom.Action;
import cinematrix.ui.custom.ActionListViewCell;
import com.sun.javafx.scene.layout.region.SliceSequenceConverter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Accordion;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class MainController implements Initializable {
    
    @FXML private BorderPane pane;
    @FXML private ListView<Action> lstActions;
    
    
    private ObservableList<Action> actions = FXCollections.observableArrayList();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        
        System.out.println("INIT MAIN CTRL ");
        try{
            Pane borderWindow = (Pane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/custom/window.fxml"));
            Pane shows   = (Pane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/shows/Shows.fxml"));
            Pane session = (Pane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/session/Session.fxml"));
            Pane movies = (Pane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/movies/Movies.fxml"));
            Pane users = (Pane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/users/Users.fxml"));
            Pane schedule = (Pane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/schedule/schedule.fxml"));
            ScrollPane mics = (ScrollPane) FXMLLoader.load(getClass().getResource("/cinematrix/ui/mics/mics.fxml"));
            
            
            Insets centerPadding = new Insets(5, 10, 100, 10);
              
            shows.setPadding(centerPadding);
            session.setPadding(centerPadding);
            movies.setPadding(centerPadding);
            users.setPadding(centerPadding);
            schedule.setPadding(new Insets(5, 10, 50, 10));
            mics.setPadding(new Insets(5, 10, 10, 10));
            
            //pane.setCenter(new TableView());
            movies.setManaged(true);
            pane.setTop(borderWindow);
            pane.setCenter(session);
            //pane.setLeft(null);
            
            //lstActions.getItems().addAll("Hello", "Dude");
            
            actions.addAll(
                  new Action(new Image(getClass().getResourceAsStream("/cinematrix/images/kitty.png")), "Session Details"),
                  new Action(new Image(getClass().getResourceAsStream("/cinematrix/images/kitty.png")), "Add Shows"),
                  new Action(new Image(getClass().getResourceAsStream("/cinematrix/images/kitty.png")), "Movie Information"),
                  new Action(new Image(getClass().getResourceAsStream("/cinematrix/images/kitty.png")), "Add Users"),
                  new Action(new Image(getClass().getResourceAsStream("/cinematrix/images/kitty.png")), "Add Schedule"),
                  new Action(new Image(getClass().getResourceAsStream("/cinematrix/images/kitty.png")), "Mics")
                  
            );
            
            lstActions.setCellFactory(a -> new ActionListViewCell());
            lstActions.setItems(actions);
            
            lstActions.getSelectionModel().selectedItemProperty().addListener((items, ov, nv) ->{
                switch(lstActions.getSelectionModel().getSelectedIndex()){
                    case 0:
                        pane.setCenter(session); break;
                    case 1:
                        pane.setCenter(shows); break;
                    case 2:
                        pane.setCenter(movies); break;
                    case 3:
                        pane.setCenter(users); break;
                    case 4:
                        pane.setCenter(schedule); break;
                    case 5:
                        pane.setCenter(mics); break;
                }
            });
            

        }catch(IOException ioex){
            
        }
        
        System.out.println("MAIN CTRL COMPLETE");
    }    
    
}
