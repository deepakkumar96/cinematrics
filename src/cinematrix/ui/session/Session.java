/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.session;

import cinematrix.db.DbManager;
import cinematrix.models.user.User;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public final class Session {
    
    private int screen;
    private int showNo;
    private StringProperty from = new SimpleStringProperty();
    private StringProperty to   = new SimpleStringProperty();
    private StringProperty status = new SimpleStringProperty();

    public Session(int screen, int showNo, String from, String to, String status){
        setScreen(screen);
        setShowNo(showNo);
        setFrom(from);
        setTo(to);
        setStatus(status);
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

    /**
     * @return the showNo
     */
    public int getShowNo() {
        return showNo;
    }

    /**
     * @param showNo the showNo to set
     */
    public void setShowNo(int showNo) {
        this.showNo = showNo;
    }

    /**
     * @return the from
     */
    public String getFrom() {
        return from.get();
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from.set(from);
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to.get();
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to.set(to);
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status.get();
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status.set(status);
    }
    
    
}
