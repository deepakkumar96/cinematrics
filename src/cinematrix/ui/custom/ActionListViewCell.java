/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.custom;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 *
 * @author deepak
 */
public class ActionListViewCell extends ListCell<Action> implements Initializable{
    
    @FXML private ImageView image;
    @FXML private Label actionText;
    @FXML private GridPane grid;
    
    FXMLLoader mLLoader;
    
    @Override
    protected void updateItem(Action action, boolean empty) {
        super.updateItem(action, empty);

        if(empty || action == null) {

            setText(null);
            setGraphic(null);

        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/cinematrix/ui/custom/Actions.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                

            }

            //label1.setText(String.valueOf(student.getStudentId()));
            image.setImage(action.getImage());
            actionText.setText(action.getAction());
            
            /*
            if(student.getGender().equals(Student.GENDER.MALE)) {
                fxIconGender.setIcon(FontAwesomeIcon.MARS);
            } else if(student.getGender().equals(Student.GENDER.FEMALE)) {
                fxIconGender.setIcon(FontAwesomeIcon.VENUS);
            } else {
                fxIconGender.setIcon(FontAwesomeIcon.GENDERLESS);
            }*/

            setText(null);
            setGraphic(grid);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("INIT");
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
