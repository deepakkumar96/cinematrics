/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.movies;

import cinematrix.db.DbManager;
import cinematrix.models.Distributor;
import static cinematrix.models.Distributor.createDistributorFromResultSet;
import cinematrix.models.user.User;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
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
public class Movie {
    public int id;
    private StringProperty movieCode = new SimpleStringProperty();
    private StringProperty movieName = new SimpleStringProperty();
    private int distributer;
    private Date startDate;
    private Date endDate;
    private int run;
    private int week;
    private StringProperty starCast  = new SimpleStringProperty();
    private DoubleProperty rating = new SimpleDoubleProperty();
    private int language;
    private int movieLength;
    private int category;
    
    public Movie(){}
    
    public Movie(int id, String movieCode, String movieName, int distributer, Date startDate, 
                 Date endDate, int run, int week, String starCast, double rating,
                 int language, int movieLength, int category){
            this.id = id;
            setMovieCode(movieCode);
            setMovieName(movieName);
            setDistributer(distributer);
            setStartDate(startDate);
            setEndDate(endDate);
            setRun(run);
            setWeek(week);
            setStarCast(starCast);
            setRating(rating);
            setLanguage(language);
            setMovieLength(movieLength);
            setCategory(category);
    }
    
    
    public static ObservableList<Movie> all() throws SQLException{
        ObservableList<Movie> movies = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Movie");
        while(rs.next()){
            movies.add(createMovieFromResultSet(rs));
        }
        return movies;
    }
    
    public static Movie get(int id) throws SQLException{
        Movie movie = null;
        ResultSet rs = DbManager.getInstance().query("select * from Movie where id = "+id);
        if(rs.next()){
            movie = createMovieFromResultSet(rs);   
        }
        rs.close();
        return movie;
    }
    
    public static Movie createMovieFromResultSet(ResultSet rs) throws SQLException{
        return new Movie(
                    rs.getInt("id"),
                    rs.getString("code"),
                    rs.getString("title"),
                    rs.getInt("distributor_id"),
                    rs.getDate("release_date"),
                    rs.getDate("runout_date"),
                    rs.getInt("id"),
                    rs.getInt("id"),
                    rs.getString("starcast"),
                    rs.getDouble("rating"),
                    rs.getInt("language"),
                    rs.getInt("length"),
                    rs.getInt("category")
        );
    }
    
    public static boolean addMovie(Movie movie) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Movie (code, title, distributor_id, release_date, runout_date, "+
            "no_of_shows, no_of_screens,"+
            "starcast, rating, language, length, category) " +
            "VALUES( "+ movie.getCommaSeparatedValues()+ " )"
       );
        return true;
    }
    
    public String getCommaSeparatedValues(){
        return  "'" + movieCode.get() + "' , "+
                "'"+ movieName.get() + "', "+   
                "" + distributer + ", "+
                "#"+ ((startDate != null)?startDate.toString():"") + "#, "+
                "#"+ ((endDate != null)?endDate.toString():null) + "#, "+
                "" + run + ", "+
                "" + week + ", "+
                "'"+ starCast.get() + "', "+
                "" + rating.get() + ", "+
                "" + language + ", "+
                ""+ movieLength + ", " +
                ""+ category + ""; 
    }//Mv-2, 'Raeeszz', 0, 2, 2, 'shahruk khan', 5.0, 0, '154''0'
    
    @Override
    public String toString(){
        return movieName.get();
    }
    
    
    /*  Testing Movie */
    public static void main(String[] args){
        
        try{
            System.out.println(get(1).getCommaSeparatedValues());
            
        }catch(SQLException sqlex){
            System.out.println(sqlex.getMessage());
        }
        
    }
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * @return the movieCode
     */
    public String getMovieCode() {
        return movieCode.get();
    }

    /**
     * @param movieCode the movieCode to set
     */
    public void setMovieCode(String movieCode) {
        this.movieCode.set(movieCode);
    }

    /**
     * @return the movieName
     */
    public String getMovieName() {
        return movieName.get();
    }
    
    public StringProperty getMovieNameProperty() {
        return movieName;
    }

    /**
     * @param movieName the movieName to set
     */
    public void setMovieName(String movieName) {
        this.movieName.set(movieName);
    }

    /**
     * @return the distributer
     */
    public int getDistributer() {
        return distributer;
    }

    /**
     * @param distributer the distributer to set
     */
    public void setDistributer(int distributer) {
        this.distributer = distributer;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }
    
    public LocalDate getStartLocalDate(){
        return (startDate != null)?startDate.toLocalDate(): null;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }
    
    public LocalDate getEndLocalDate(){
        return (endDate != null)?endDate.toLocalDate(): null;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the run
     */
    public int getRun() {
        return run;
    }

    /**
     * @param run the run to set
     */
    public void setRun(int run) {
        this.run = run;
    }

    /**
     * @return the week
     */
    public int getWeek() {
        return week;
    }

    /**
     * @param week the week to set
     */
    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * @return the starCast
     */
    public String getStarCast() {
        return starCast.get();
    }

    /**
     * @param starCast the starCast to set
     */
    public void setStarCast(String starCast) {
        this.starCast.set(starCast);
    }

    /**
     * @return the rating
     */
    public double getRating() {
        return rating.get();
    }

    /**
     * @param rating the rating to set
     */
    public void setRating(double rating) {
        this.rating.set(rating);
    }

    /**
     * @return the language
     */
    public int getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(int language) {
        this.language = language;
    }

    /**
     * @return the movieLength
     */
    public int getMovieLength() {
        return movieLength;
    }

    /**
     * @param movieLength the movieLength to set
     */
    public void setMovieLength(int movieLength) {
        this.movieLength = movieLength;
    }
   
    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }
    
}
