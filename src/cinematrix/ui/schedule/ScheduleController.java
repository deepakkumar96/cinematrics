/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.schedule;

import java.net.URL;
import java.util.Date;
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

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class ScheduleController implements Initializable {

    @FXML private DatePicker schedule;
    @FXML private ComboBox cmbScreen;
    @FXML private TableView<Show> tblShows;
    @FXML private ComboBox<String> cmbMovies;
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
        
        cmbScreen.setItems(FXCollections.observableArrayList("Veronica", "Mariegold"));
        cmbMovies.setItems(FXCollections.observableArrayList("Raees", "Batman", "Superman"));
        cmbMovies.getSelectionModel().select(0);
        cmbScreen.getSelectionModel().select(0);
        
    }    
    
    @FXML public void handleAddScheduleButtnAction(ActionEvent evt){
        //Add to database
        System.out.println("SCH : " + schedule.getValue());
        Schedule newSchedule = new Schedule(
                schedule.getValue().toString(),
                cmbScreen.getSelectionModel().getSelectedIndex(),
                shows);
        System.out.println("Schedule : " + newSchedule);
    }
    
    @FXML public void handleAddShowButtnAction(ActionEvent evt){
        System.out.println("Show");
        shows.add(new Show(
                1,
                cmbMovies.getSelectionModel().getSelectedItem(),
                txtFrom.getText(),
                txtTo.getText(),
                Double.parseDouble(txtMemberPrice.getText()),
                Double.parseDouble(txtGuestPrice.getText())
        ));
        System.out.println("Shows : " + shows);
        System.out.println("Movies : " +    cmbScreen.getSelectionModel().getSelectedItem());
        
    }
    
}
