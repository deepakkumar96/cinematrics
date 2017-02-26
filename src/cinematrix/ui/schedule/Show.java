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
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public class Show {
    
    private int screen;
    private final StringProperty movie = new SimpleStringProperty();
    private final StringProperty from = new SimpleStringProperty();
    private final StringProperty to   = new SimpleStringProperty();
    private final DoubleProperty memberPrice = new SimpleDoubleProperty();
    private final DoubleProperty guestPrice = new SimpleDoubleProperty();

    public Show(int screen, String movie, String from, String to, double memberPrice, double guestPrice){
        setScreen(screen);
        setMovie(movie);
        setTo(to);
        setFrom(from);
        setMemberPrice(memberPrice);
        setGuestPrice(guestPrice);
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
    
    /* GETTERS */
    
    /**
     * @return the screen
     */
    public int getScreen() {
        return screen;
    }

    
    /**
     * @return the movie
     */
    public String getMovie() {
        return movie.get();
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from.get();
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to.get();
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
    public void setScreen(int screen) {
        this.screen = screen;
    }

    /**
     * @param movie the movie to set
     */
    public void setMovie(String movie) {
        this.movie.set(movie);
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from.set(from);
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to.set(to);
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
    
    @Override public String toString(){
        return movie+", "+ from + ", " + to + ", " + memberPrice;
    }
    
    
}
