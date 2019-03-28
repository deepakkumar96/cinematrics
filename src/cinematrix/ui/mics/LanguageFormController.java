/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.mics;

import cinematrix.models.Category;
import cinematrix.models.Language;
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
public class LanguageFormController implements Initializable {

    @FXML private TableColumn<Language, String> languageIdColumn;
    @FXML private TableColumn<Language, String> languageNameColumn;
    @FXML private TableView<Language> tblLanguages;
    @FXML private TextField txtLanguageName;
    ObservableList languages = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        languageIdColumn.setCellValueFactory(new PropertyValueFactory("languageId"));
        languageNameColumn.setCellValueFactory(new PropertyValueFactory("languageName"));
        
        languageNameColumn.setCellFactory(TextFieldTableCell.<Language>forTableColumn());
        languageNameColumn.setOnEditCommit((TableColumn.CellEditEvent<Language, String> t) -> {
            Language language = t.getTableView().getItems().get(t.getTablePosition().getRow());
            language.setLanguageName(t.getNewValue());
            try{
                Language.update(language);
            }catch(SQLException sqlex){
                System.out.println(sqlex.getMessage());
            }
        });
        
        // handling deleting row
        tblLanguages.setOnKeyReleased((KeyEvent e) ->{
            if(e.getCode() == KeyCode.DELETE){
                try {
                    Language.delete(tblLanguages.getSelectionModel().getSelectedItem());
                    loadLanguages();
                } catch (Exception sqlex) {
                    System.out.println("Unable to delete category.");
                }
                
            }
        });
        
        loadLanguages();
        
        
    } 
    
    public void loadLanguages(){
        try {
            languages = Language.all();
            tblLanguages.setItems(languages);
        } catch (SQLException e) {
            System.out.println("Unable to fetch data from screen.");
        }
    }
    
    @FXML public void  btnAddLanguageHandler(ActionEvent evt){
        try{
            Language.add(new Language(txtLanguageName.getText()));
            loadLanguages();
        }catch(SQLException sqlex){
            System.out.println("Unable to fetch screens.");
        }
    }
    
}
