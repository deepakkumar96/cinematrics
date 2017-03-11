/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.mics;

import cinematrix.models.Certification;
import cinematrix.models.Screen;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class ScreenFormController implements Initializable {

    @FXML private TableColumn<Screen, String> screenIdColumn;
    @FXML private TableColumn<Screen, String> screenNameColumn;
    @FXML private TableView<Screen> tblScreen;
    @FXML private TextField txtScreenName;
    ObservableList screens = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        screenIdColumn.setCellValueFactory(new PropertyValueFactory("screenId"));
        screenNameColumn.setCellValueFactory(new PropertyValueFactory("screenName"));
        
        //handling table update
        screenNameColumn.setCellFactory(TextFieldTableCell.<Screen>forTableColumn());
        screenNameColumn.setOnEditCommit((TableColumn.CellEditEvent<Screen, String> t) -> {
            Screen screen = t.getTableView().getItems().get(t.getTablePosition().getRow());
            screen.setScreenName(t.getNewValue());
            try{
                Screen.update(screen);
            }catch(SQLException sqlex){
                System.out.println(sqlex.getMessage());
            }
        });
        
        // handling deleting row
        tblScreen.setOnKeyReleased((KeyEvent e) ->{
            if(e.getCode() == KeyCode.DELETE){
                try {
                    Screen.delete(tblScreen.getSelectionModel().getSelectedItem());
                    loadScreens();
                } catch (Exception sqlex) {
                    System.out.println("Unable to delete.");
                }
                
            }
        });
        
        loadScreens();
        
        
    } 
    
    public void loadScreens(){
        try {
            screens = Screen.all();
            tblScreen.setItems(screens);
        } catch (SQLException e) {
            System.out.println("Unable to fetch data from screen.");
        }
    }
    
    @FXML public void btnAddScreenHandler(ActionEvent evt){
        try{
            Screen.add(new Screen(txtScreenName.getText()));
            loadScreens();
        }catch(SQLException sqlex){
            System.out.println("Unable to fetch screens.");
        }
    }
    
}
