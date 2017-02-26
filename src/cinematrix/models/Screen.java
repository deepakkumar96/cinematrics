/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.models;

import cinematrix.db.DbManager;
import static cinematrix.models.Language.getLanguage;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public class Screen {
    
    private int screenId;
    private String screenName;
    
    public Screen(){}
    
    public Screen(int screenId, String screenName){
        setScreenId(screenId);
        setScreenName(screenName);
    }
    
    public static ObservableList<Screen> getScreens() throws SQLException{
        ObservableList<Screen> screens = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Screen");
        while(rs.next()){
            screens.add(new Screen(
                    rs.getInt("screen_id"),
                    rs.getString("screen_name")
            ));
        }
        rs.close();
        return screens;
    }
    
    public static Screen getScreen(int id) throws SQLException{
        Screen screen = null;
        ResultSet rs = DbManager.getInstance().query("select * from Screen where screen_id = "+id);
        if(rs.next()){
            screen = new Screen();
            screen.setScreenId(rs.getInt("screen_id"));
            screen.setScreenName(rs.getString("screen_name"));
        }
        rs.close();
        return screen;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public String getScreenName() {
        return screenName;
    }

    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    
    @Override
    public String toString(){
        return screenName;
    }
    
    public static void main(String[] args){
        try{
            System.out.println(getScreen(3));
        }catch(Exception ex){
            
        }
        
    }
    
}
