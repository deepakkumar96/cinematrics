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
public class CategoryFormController implements Initializable {

    @FXML private TableColumn<Category, String> categoryIdColumn;
    @FXML private TableColumn<Category, String> categoryNameColumn;
    @FXML private TableView<Category> tblCategory;
    @FXML private TextField txtCategoryName;
    ObservableList categories = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        categoryIdColumn.setCellValueFactory(new PropertyValueFactory("categoryId"));
        categoryNameColumn.setCellValueFactory(new PropertyValueFactory("categoryName"));
        
        categoryNameColumn.setCellFactory(TextFieldTableCell.<Category>forTableColumn());
        categoryNameColumn.setOnEditCommit((TableColumn.CellEditEvent<Category, String> t) -> {
            Category category = t.getTableView().getItems().get(t.getTablePosition().getRow());
            category.setCategoryName(t.getNewValue());
            try{
                Category.update(category);
            }catch(SQLException sqlex){
                System.out.println(sqlex.getMessage());
            }
        });
        
        // handling deleting row
        tblCategory.setOnKeyReleased((KeyEvent e) ->{
            if(e.getCode() == KeyCode.DELETE){
                try {
                    Category.delete(tblCategory.getSelectionModel().getSelectedItem());
                    loadCategories();
                } catch (Exception sqlex) {
                    System.out.println("Unable to delete category.");
                }
                
            }
        });
        
        loadCategories();
        
        
    } 
    
    public void loadCategories(){
        try {
            categories = Category.all();
            tblCategory.setItems(categories);
        } catch (SQLException e) {
            System.out.println("Unable to fetch data from screen.");
        }
    }
    
    @FXML public void  btnAddCategoryHandler(ActionEvent evt){
        try{
            Category.add(new Category(txtCategoryName.getText()));
            loadCategories();
        }catch(SQLException sqlex){
            System.out.println("Unable to fetch screens.");
        }
    }
    
}
