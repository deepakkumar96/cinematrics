/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.schedule;

import cinematrix.models.Screen;
import cinematrix.ui.movies.Movie;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.text.DateFormatter;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class ScheduleController implements Initializable {

    @FXML private DatePicker schedule;
    @FXML private ComboBox cmbScreen;
    @FXML private TableView<Show> tblShows;
    @FXML private ComboBox<Movie> cmbMovies;
    @FXML private TextField txtFrom;
    @FXML private TextField txtTo;
    @FXML private TextField txtMemberPrice;
    @FXML private TextField txtGuestPrice;
    
    //Table Column
    @FXML private TableColumn movieColumn;
    @FXML private TableColumn fromColumn;
    @FXML private TableColumn toColumn;
    @FXML private TableColumn memberPriceColumn;
    @FXML private TableColumn guestPriceColumn;
    
    ObservableList<Show> shows = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Schdule Ctrl");
        movieColumn.setCellValueFactory(new PropertyValueFactory("movie"));
        fromColumn.setCellValueFactory(new PropertyValueFactory("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory("to"));
        memberPriceColumn.setCellValueFactory(new PropertyValueFactory("memberPrice"));
        guestPriceColumn.setCellValueFactory(new PropertyValueFactory("guestPrice"));
        
        tblShows.setItems(shows);
        
        try{
            cmbScreen.setItems(Screen.all());
            cmbMovies.setItems(Movie.all());
            cmbMovies.getSelectionModel().select(0);
            cmbScreen.getSelectionModel().select(0);
        }catch(SQLException sqlex){
            System.out.println(sqlex.getMessage());
        }
        
    }    
    
    @FXML public void handleAddScheduleButtnAction(ActionEvent evt){
        //Add to database
        System.out.println("SCH : " + schedule.getValue());
        Schedule newSchedule = new Schedule(
                0,
                Date.valueOf(schedule.getValue()),
                cmbScreen.getSelectionModel().getSelectedIndex()
        );
        newSchedule.setShows(shows);
        System.out.println("Schedule : " + newSchedule);
        try{
            Schedule.addScheduleWithShows(newSchedule);
        }catch(SQLException sqlex){
            System.out.println(sqlex);
        }
    }
    
    @FXML public void handleAddShowButtnAction(ActionEvent evt){
        System.out.println("Show");
        
        try{
            
            DateFormat formatter = new SimpleDateFormat("HH:mm");
            shows.add(new Show(
                    0,
                    cmbMovies.getSelectionModel().getSelectedItem().getId(),
                    new Time(formatter.parse(txtFrom.getText()).getTime()),
                    new Time(formatter.parse(txtTo.getText()).getTime()),
                    Double.parseDouble(txtMemberPrice.getText()),
                    Double.parseDouble(txtGuestPrice.getText())
            ));
        }catch(ParseException parseEx){
            System.err.println("Time Parsing Error");
        }
        System.out.println("Shows : " + shows);
        System.out.println("Movies : " +    cmbScreen.getSelectionModel().getSelectedItem());
        
    }
    
}
