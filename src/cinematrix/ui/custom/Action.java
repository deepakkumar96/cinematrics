/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.custom;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;


/**
 *
 * @author deepak
 */
public class Action {
    
    private Image image;
    private StringProperty action;
    
    public Action(Image image, String action){
        this.image = image;
        this.action = new SimpleStringProperty(action);
    }
    
    public String getAction(){
        return action.get();
    }
    
    public void setAction(String action){
        this.action.setValue(action);
    }
    
    public Image getImage(){
        return image;
    }
    
    public void setImage(Image image){
        this.image = image;
    }
    
}
