/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.session;

import cinematrix.db.DbManager;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class SessionController implements Initializable {

    @FXML private TableView<Session> tblSession;
    @FXML private TableColumn screen;
    @FXML private TableColumn show;
    @FXML private TableColumn from;
    @FXML private TableColumn to;
    @FXML private TableColumn status;
    
    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        screen.setCellValueFactory(new PropertyValueFactory("screen"));
        show.setCellValueFactory(new PropertyValueFactory("showNo"));
        from.setCellValueFactory(new PropertyValueFactory("from"));
        to.setCellValueFactory(new PropertyValueFactory("to"));
        status.setCellValueFactory(new PropertyValueFactory("status"));
        
        ObservableList<Session> list = FXCollections.observableArrayList(
              new Session(1, 1, "10", "12", "Act"),
              new Session(1, 1, "100", "122", "Act"),
              new Session(1, 1, "100", "122", "Act2")
        );
 
        
    }    
    
}
