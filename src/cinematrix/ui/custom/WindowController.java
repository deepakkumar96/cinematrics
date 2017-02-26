/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.custom;

import com.jfoenix.controls.JFXNodesList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class WindowController implements Initializable {

    @FXML private Label lblTitle;
    @FXML private AnchorPane parent;
    //@FXML private AnchorPane b;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        //System.out.println(lblTitle.prefWidthProperty().get()-140);
        lblTitle.layoutXProperty().set((primaryScreenBounds.getWidth()/2));
        
        Button btnMenu = new Button("H");
        btnMenu.setPadding(new Insets(15, 0, 0, 8));
        btnMenu.setId("round_button");
        
        Button m2 = new Button("Dash");
        //m2.setPadding(new Insets(15, 0, 0, 8));
        
        Button m3 = new Button("Main");
        //m2.setPadding(new Insets(15, 0, 0, 8));
        
        Button m4 = new Button("Logout");
        //m2.setPadding(new Insets(15, 0, 0, 8));
        
        JFXNodesList menuList = new JFXNodesList();
        menuList.setPadding(new Insets(15, 0, 0, 8));
        menuList.addAnimatedNode(btnMenu);
        menuList.addAnimatedNode(m2);
        menuList.addAnimatedNode(m3);
        //menuList.addAnimatedNode(m4);
        menuList.setSpacing(5d);
        
       // AnchorPane.setLeftAnchor(menuList, 10.0);
        parent.getChildren().add(menuList);
        

        
    }
    
}
