/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.schedule;

import cinematrix.db.DbManager;
import cinematrix.ui.session.Session;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public class Schedule {
    
    private int scheduleId;
    private Date schedule; // #Format : MM/DD/YYYY
    private int screen;
    private ObservableList<Show>  shows = FXCollections.emptyObservableList();
    
    
    public Schedule(int scheduleId, Date schedule, int screen, ObservableList<Show>  shows){
        setScheduleId(scheduleId);
        setScreen(screen);
        setSchedule(schedule);
        setShows(shows);
    }
    
    public Schedule(Date schedule, int screen, ObservableList<Show> shows){
        this(0, schedule, screen, null);
    }
    
    
    public Schedule(int scheduleId, Date schedule, int screen){
        this(scheduleId, schedule, screen, null);
    }
    
    public Schedule(Date schedule, int screen){
        this(0, schedule, screen, null);
    }
    
    public static ObservableList<Schedule> all() throws SQLException{
        ObservableList<Schedule> Schedules = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Schedule");
        while(rs.next()){
            Schedules.add(new Schedule(
                    rs.getInt("id"),
                    rs.getDate("schedule"),
                    rs.getInt("screen")
            ));
        }
        return Schedules;
    }
    
    public static Schedule get(int id) throws SQLException{
        Schedule schedule = null;
        ResultSet rs = DbManager.getInstance().query("select * from Schedule where id = "+id);
        if(rs.next()){
            schedule = new Schedule(
                    rs.getInt("id"),
                    rs.getDate("schedule"),
                    rs.getInt("screen")
            );
        }
        rs.close();
        return schedule;
    }
    
    
    public static boolean add(Schedule schedule) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Schedule (schedule, screen)" +
            "VALUES( "+ schedule.getCommaSeparatedValues()+ " )"
        );
        return true;
    }
    
    public static boolean addScheduleWithShows(Schedule schedule) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Schedule (schedule, screen)" +
            "VALUES( "+ schedule.getCommaSeparatedValues()+ " )"
        );
        if(schedule.getShows() != null){
            for(Show show : schedule.getShows())
                Show.add(show);
        }
        return true;
    }
    
    public static boolean delete(int id) throws SQLException{
        DbManager.getInstance().update(
            "delete from Schedule where id = " + id
        );
        return true;
    }
    
    public static boolean delete(int...ids) throws SQLException{
        for(int id : ids)
            Schedule.delete(id);
        return true;
    }
    
    public int getScheduleId(){
        return scheduleId;
    }
    
    public void setScheduleId(int scheduleId){
        this.scheduleId = scheduleId;
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

    private void setSchedule(Date schedule) {
        this.schedule = schedule;
    }
    
    private Date getSchedule(){
        return schedule;
    }
    
    private LocalDate getSheduleInLocalDate(){
        return schedule.toLocalDate();
    }
    
    public ObservableList<Show> getShows(){
        return shows;
    }
    
    public void setShows(ObservableList<Show> shows){
        this.shows = shows;
    }
    
    public String getCommaSeparatedValues(){
        return "#" + getSchedule().toString() + "#, " + getScreen();
    }
    
    @Override public String toString(){
        String data =  getSchedule() + ", " + getScreen() + "[";
        if(shows != null){
            data += "\n";
            for(Show s : shows){
                data += s + "\n";
            }
            data += "\n";
        }
        return data + "]";
    }
    
    public static void main(String[] args){
        try {
            for(Schedule s : Schedule.all())
            {
                System.out.println(s.scheduleId);
            }
//            ResultSet rs = DbManager.getInstance().query("Select * from Schedule");
//            while(rs.next()){
//                System.out.print("ID : " + rs.getInt("id") + ", ");
//                Date date = rs.getDate("schedule");
//                System.out.println("Date : " + date.toString());
//                LocalDate ld = date.toLocalDate();
//                System.out.println(ld);
//            }
//            Date date = Date.valueOf("2022-4-2");
            //DbManager.getInstance().update(
            //"INSERT INTO Schedule (schedule, screen)" +
            //"VALUES( #"+ date.toString() + "#, " + 1 + ")"
            //);
            
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}
