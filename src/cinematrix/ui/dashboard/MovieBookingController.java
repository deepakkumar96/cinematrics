/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.dashboard;

import cinematrix.models.Screen;
import cinematrix.ui.movies.Movie;
import cinematrix.ui.schedule.Show;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class MovieBookingController implements Initializable {

    @FXML private ComboBox<Movie> cmbMovies;
    @FXML private ComboBox<Show>  cmbShows;
    @FXML private ComboBox<Screen> cmbScreens;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            
            cmbMovies.setItems(Movie.all());
            cmbShows.setItems(Show.all());
            cmbScreens.setItems(Screen.all());
            
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        }
      
        
    }    
    
}
