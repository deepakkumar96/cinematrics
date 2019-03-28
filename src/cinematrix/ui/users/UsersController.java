/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.users;

import cinematrix.models.user.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import sun.security.util.Password;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class UsersController implements Initializable {

    @FXML private TextField txtUsername;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtFullName;
    @FXML private TextField txtPhoneNo;
    @FXML private TextField txtEmail;
    @FXML private ComboBox<String> cmbAuthority;
    
    
    @FXML private TableView<User> tblUsers;
    @FXML private TableColumn username;
    @FXML private TableColumn fullName;
    @FXML private TableColumn phone;
    @FXML private TableColumn email;
    @FXML private TableColumn authority;
    @FXML private Button btnAdd;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("UserCtrl");
        txtUsername.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
        Tooltip toolTip = new Tooltip("Errors\none");
        toolTip.setStyle("-fx-background-color: red,linear-gradient(to bottom, derive(red,60%) 5%,derive(red,90%) 40%);");
        txtUsername.setTooltip(toolTip);
        username.setCellValueFactory(new PropertyValueFactory("username"));
        fullName.setCellValueFactory(new PropertyValueFactory("fullName"));
        phone.setCellValueFactory(new PropertyValueFactory("phoneNo"));
        email.setCellValueFactory(new PropertyValueFactory("email"));
        authority.setCellValueFactory(new PropertyValueFactory("authority"));
        
        cmbAuthority.setItems(FXCollections.observableArrayList("Admin", "Staff"));
        
        loadUsers();
        
        btnAdd.setOnAction(e -> {
            try {
                User user = new User(
                        1, 
                        txtUsername.getText(), 
                        txtFullName.getText(), 
                        txtPassword.getText(), 
                        cmbAuthority.getSelectionModel().getSelectedIndex(),
                        txtPhoneNo.getText(), 
                        txtEmail.getText()
                );
                User.addUser(user);
                System.out.println("User Added! : " + user);
                loadUsers();
            } catch (SQLException ex) {
                Logger.getLogger(UsersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
    }   
    
    public void loadUsers(){
        try{
            tblUsers.setItems(null);
            tblUsers.setItems(User.getUsers());
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
}
