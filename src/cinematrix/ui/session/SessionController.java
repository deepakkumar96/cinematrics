/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.session;

import cinematrix.db.DbManager;
import cinematrix.models.Screen;
import cinematrix.ui.movies.Movie;
import cinematrix.ui.schedule.Schedule;
import cinematrix.ui.schedule.Show;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class SessionController implements Initializable {

    @FXML private TableView<Show> tblSession;
    @FXML private TableColumn<Show , String> screenColumn;
    @FXML private TableColumn<Show , String> movieColumn;  
    @FXML private TableColumn<Show , String> fromColumn;
    @FXML private TableColumn<Show , String> toColumn;
    @FXML private TableColumn<Show , String> guestPriceMember;
    @FXML private TableColumn<Show , String> memberPriceColumn;
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        screenColumn.setCellValueFactory((CellDataFeatures<Show, String> data) -> {
            try {
                //data.getValue().setScheduleId(7);
                int screenId = 7;//Schedule.get(data.getValue().getScheduleId()).getScreen();
                return Screen.get(screenId).getScreenNameProperty();
            } catch (SQLException e) {
                System.err.println("Unable to fatch Screen Information(Session-View)");
                return null;
            } catch (NullPointerException e) {
                System.err.println("Unable to fatch Screen Information(Session-View)");
                StringProperty temp = new SimpleStringProperty();
                temp.set(data.getValue().getScheduleId()+"");
                return temp;
            }
        });
        
        
        movieColumn.setCellValueFactory(new Callback<CellDataFeatures<Show, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Show, String> p) {
                // p.getValue() returns the Person instance for a particular TableView row
                try {
                    return Movie.get(p.getValue().getMovie()).getMovieNameProperty();
                } catch (SQLException sqlex) {
                }
                return null;
            }
        });
        fromColumn.setCellValueFactory(new PropertyValueFactory("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory("to"));
        guestPriceMember.setCellValueFactory(new PropertyValueFactory("guestPrice"));
        memberPriceColumn.setCellValueFactory(new PropertyValueFactory("memberPrice"));
        
        try{
            ObservableList<Show> list = Show.all();
            tblSession.setItems(list);
        }catch(SQLException sqlex){
            System.err.println("Unable To Display Show");
        }
        
        
    }    
    
}
