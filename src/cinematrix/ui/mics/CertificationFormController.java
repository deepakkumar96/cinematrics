/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.mics;

import cinematrix.models.Category;
import cinematrix.models.Certification;
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
import javafx.scene.control.TableColumn.CellEditEvent;
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
public class CertificationFormController implements Initializable {

    @FXML private TableColumn<Certification, String> certificationIdColumn;
    @FXML private TableColumn<Certification, String> certificationNameColumn;
    @FXML private TableView<Certification> tblCertifications;
    @FXML private TextField txtCertificationName;
    ObservableList certifications = null;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        certificationIdColumn.setCellValueFactory(new PropertyValueFactory("certificationId"));
        certificationNameColumn.setCellValueFactory(new PropertyValueFactory("certificationName"));
        
        //handling editing database directly from TableView
        certificationNameColumn.setCellFactory(TextFieldTableCell.<Certification>forTableColumn());
        certificationNameColumn.setOnEditCommit((CellEditEvent<Certification, String> t) -> {
            Certification certification = t.getTableView().getItems().get(t.getTablePosition().getRow());
            certification.setCertificationName(t.getNewValue());
            try{
                Certification.update(certification);
            }catch(SQLException sqlex){
                System.out.println(sqlex.getMessage());
            }
        });
        
        // handling deleting row
        tblCertifications.setOnKeyReleased((KeyEvent e) ->{
            if(e.getCode() == KeyCode.DELETE){
                try {
                    Certification.delete(tblCertifications.getSelectionModel().getSelectedItem());
                    loadCertifications();
                } catch (Exception sqlex) {
                    System.out.println("Unable to delete category.");
                }
                
            }
        });
        
        loadCertifications();
        
        
    } 
    
    public void loadCertifications(){
        try {
            certifications = Certification.all();
            tblCertifications.setItems(certifications);
        } catch (SQLException e) {
            System.out.println("Unable to fetch data from screen.");
        }
    }
    
    @FXML public void  btnAddCertificationHandler(ActionEvent evt){
        try{
            Certification.add(new Certification(txtCertificationName.getText()));
            loadCertifications();
        }catch(SQLException sqlex){
            System.out.println("Unable to fetch screens.");
        }
    }
    
}
