/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.models;

import cinematrix.db.DbManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public class Screen{
    
    private IntegerProperty screenId = new SimpleIntegerProperty();
    private StringProperty screenName = new SimpleStringProperty();
    
    public Screen(){}
    public Screen(String screenName){
        this(0, screenName);
    }
    
    public Screen(int screenId, String screenName){
        setScreenId(screenId);
        setScreenName(screenName);
    }
    
    public static ObservableList<Screen> all() throws SQLException{
        ObservableList<Screen> screens = FXCollections.observableArrayList();
        try (ResultSet rs = DbManager.getInstance().query("Select * from Screen")) {
            while(rs.next()){
                screens.add(new Screen(
                        rs.getInt("screen_id"),
                        rs.getString("screen_name")
                ));
            }
        }
        return screens;
    }
    
    public static Screen get(int id) throws SQLException{
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
    
    public static boolean add(Screen screen) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Screen (screen_name)" +
            "VALUES( '"+ screen.getScreenName() +"' )"
       );
        return true;
    }
    
    public static boolean update(Screen screen) throws SQLException{
        DbManager.getInstance().update(
            "UPDATE Screen set screen_name = '"+ screen.getScreenName()+ "' " +
            "WHERE screen_id = " + screen.getScreenId()
       );
        return true;
    }
    
    public static boolean delete(int id) throws SQLException{
        DbManager.getInstance().update(
            "delete from Screen where screen_id = " + id
       );
        return true;
    }
    public static boolean delete(Screen screen) throws SQLException{
        return delete(screen.getScreenId());
    }
    
    
    public int getScreenId() {
        return screenId.get();
    }

    public void setScreenId(int screenId) {
        this.screenId.set(screenId);
    }

    public String getScreenName() {
        return screenName.get();
    }
    
    public StringProperty getScreenNameProperty() {
        return screenName;
    }

    
    public void setScreenName(String screenName) {
        this.screenName.set(screenName);
    }
    
    @Override
    public String toString(){
        return screenName.get();
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        
        if(!(obj instanceof Screen))
            return false;
        
        Screen other = (Screen) obj;
        return other.screenId == this.screenId;
    }
    
    public static void main(String[] args){
        try{
            System.out.println(all());
            add(new Screen("Veronica"));
            add(new Screen("Marigold"));
            
            for(Screen s : Screen.all()){
                System.out.println(s.screenId + " => " + s.screenName);
            }
            
            
        }catch(Exception ex){
            
        }
        
    }

    
    
}
