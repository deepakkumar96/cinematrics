/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.schedule;

import cinematrix.db.DbManager;
import cinematrix.ui.movies.Movie;
import cinematrix.ui.session.Session;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public class Show {
    
    private int showId;
    private int scheduleId;
    private final IntegerProperty movie = new SimpleIntegerProperty();
    private Time from;
    private Time to;
    private final DoubleProperty memberPrice = new SimpleDoubleProperty();
    private final DoubleProperty guestPrice = new SimpleDoubleProperty();

    public Show(){}
    
    public Show(int scheduleId, int movie, Time from, Time to, double memberPrice, double guestPrice){
        setScheduleId(scheduleId);
        setMovie(movie);
        setTo(to);
        setFrom(from);
        setMemberPrice(memberPrice);
        setGuestPrice(guestPrice);
    }
    
    public static ObservableList<Show> all() throws SQLException{
        ObservableList<Show> shows = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Show");
        while(rs.next()){
            shows.add(new Show(
                    rs.getInt("schedule_id"),
                    rs.getInt("movie"),
                    rs.getTime("start_time"),
                    rs.getTime("end_time"),
                    rs.getDouble("guest_price"),
                    rs.getDouble("memeber_price")
            ));
        }
        return shows;
    }
    
    public static Show get(int id) throws SQLException{
        Show show = null;
        try (ResultSet rs = DbManager.getInstance().query("select * from Show where id = "+id)) {
            if(rs.next()){
                show = new Show(
                        rs.getInt("schedule_id"),
                        rs.getInt("movie"),
                        rs.getTime("start_time"),
                        rs.getTime("end_time"),
                        rs.getDouble("guest_price"),
                        rs.getDouble("memeber_price")
                );
            }
        }
        return show;
    }
    
    public static boolean add(Show show) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Show (schedule_id, movie, start_time, end_time, guest_price, memeber_price)" +
            "VALUES( "+ show.scheduleId + ", " + show.getMovie() + " , #"+ show.getFrom() + 
               "#, #" + show.getTo() + "#," + show.getGuestPrice()+ ", " + show.getMemberPrice()+ " )"
       );
        return true;
    }
    
    /* GETTERS */
    
    
    public int showId(){
        return showId;
    }
    
    public void setShowId(int showId){
        this.showId = showId;
    }
    
    /**
     * @return the screen
     */
    public int getScheduleId() {
        return scheduleId;
    }

    
    /**
     * @return the movie
     */
    public int getMovie() {
        return movie.get();
    }

    /**
     * @return the from
     */
    public Time getFrom() {
        return from;
    }

    /**
     * @return the to
     */
    public Time getTo() {
        return to;
    }

    /**
     * @return the memberPrice
     */
    public double getMemberPrice() {
        return memberPrice.get();
    }

    /**
     * @return the guestPrice
     */
    public double getGuestPrice() {
        return guestPrice.get();
    }
    
    /*  SETTERS */
    
    /**
     * @param screen the screen to set
     */
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    /**
     * @param movie the movie to set
     */
    public void setMovie(int movie) {   
        this.movie.set(movie);
    }

    /**
     * @param from the from to set
     */
    public void setFrom(Time from) {
        this.from = from;
    }

    /**
     * @param to the to to set
     */
    public void setTo(Time to) {
        this.to = to;
    }

    /**
     * @param memberPrice the memberPrice to set
     */
    public void setMemberPrice(double memberPrice) {
        this.memberPrice.set(memberPrice);
    }

    /**
     * @param guestPrice the guestPrice to set
     */
    public void setGuestPrice(double guestPrice) {
        this.guestPrice.set(guestPrice);
    }
    
    public String getCommaSeparatedValues(){
        return scheduleId + ", " + getMovie() + " , '"+ getFrom() + 
               "', '" + getTo() + "'," + getGuestPrice()+ ", " + getMemberPrice();
    }
    
    @Override public String toString(){
        return getCommaSeparatedValues();
    }
    
    public static void main(String[] args){
        try{
            Show.all().forEach(System.out::println);
            
            
            Time t = Show.get(2).getFrom();
            System.err.println(t);
            Show.add(new Show(0, 12, t, t, 4, 5));
            
            System.out.println(Show.all());
            
        }catch(SQLException sqlex){
                System.out.println(sqlex.getMessage()); 
        }
        
    }
    
    
}
