/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.schedule;

import cinematrix.db.DbManager;
import cinematrix.ui.session.Session;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public class Schedule {
    
    private StringProperty schedule = new SimpleStringProperty();
    private int screen;
    private ObservableList<Show> shows;
    
    public Schedule(String schedule, int screen, ObservableList<Show> shows){
        setScreen(screen);
        setSchedule(schedule);
        setShows(shows);
    }
    
    public static ObservableList<Session> getSessions() throws SQLException{
        ObservableList<Session> sessions = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Session");
        while(rs.next()){
            sessions.add(new Session(
                    rs.getInt("screen"),
                    rs.getInt("showNo"),
                    rs.getString("from"),
                    rs.getString("to"),
                    rs.getString("status")
            ));
        }
        return sessions;
    }
    
    /**
     * @return the screen
     */
    public int getScreen() {
        return screen;
    }

    /**
     * @param screen the screen to set
     */
    public void setScreen(int screen) {
        this.screen = screen;
    }

    private void setSchedule(String schedule) {
        this.schedule.set(schedule);
    }
    
    private String getSchedule(){
        return schedule.get();
    }
    
    public ObservableList<Show> getShows(){
        return shows;
    }
    
    public void setShows(ObservableList<Show> shows){
        this.shows = shows;
    }
    
    @Override public String toString(){
        String data =  schedule.toString() + ", " + screen + "[\n";
        for(Show s : shows){
            data += s;
        }
        return data + "]";
    }
    
}
