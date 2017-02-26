/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.movies;

import cinematrix.models.Category;
import cinematrix.models.Distributor;
import cinematrix.models.Language;
import com.jfoenix.controls.JFXSlider;
import com.sun.javafx.css.Combinator;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class MoviesController implements Initializable {

    @FXML private TextField txtMovieCode;
    @FXML private TextField txtMovieName;
    @FXML private ComboBox<Distributor> cmbDistributer;
    @FXML private DatePicker startDate;
    @FXML private DatePicker endDate;
    @FXML private TextField txtRun;
    @FXML private TextField txtWeek;
    @FXML private TextField txtStarCast;
    @FXML private JFXSlider rating;
    @FXML private ComboBox<Language> cmbLanguage;
    @FXML private TextField txtMovieLength;
    @FXML private Button btnSaveMovie;
    @FXML private ComboBox<Category> cmbCategory;
    @FXML private TableView<Movie> tblMovies;
    
    @FXML private TableColumn movieNameColumn;
    ObservableList<Movie> totalMovies = null;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("MOVIE CTRL");
        //attaching table columns with entity class field
        movieNameColumn.setCellValueFactory(new PropertyValueFactory("movieName"));
        
        try{
            cmbDistributer.setItems(Distributor.getDistributors()); // DB
            cmbLanguage.setItems(Language.getLanguages()); // DB
            
            cmbCategory.setItems(Category.getCategories());
            
            
            System.out.println("WORK");
            loadMovies();
            
        }catch(SQLException sqlex){
            System.out.println(sqlex.getMessage());
        }
        
        
        //Responding To Movie Selection Event of Movie Table(tblMovies)
        tblMovies.getSelectionModel().selectedItemProperty().addListener((movies, oldMovie, selectedMovie) -> {
            try {
                System.out.println(selectedMovie);
                clearMovieForm();
                txtMovieCode.setText(selectedMovie.getMovieCode());
                txtMovieName.setText(selectedMovie.getMovieName());

                    Distributor dist = Distributor.getDistributor(selectedMovie.getDistributer());
                    System.out.println("DIST : " + dist);
                    cmbDistributer.getItems().add(dist);
                    cmbDistributer.getSelectionModel().select(0);
                
                System.out.println(selectedMovie.getStartDate());
                //startDate.setValue(selectedMovie.getStartDate());
                txtRun.setText(selectedMovie.getRun()+"");
                txtWeek.setText(selectedMovie.getWeek()+"");
                txtStarCast.setText(selectedMovie.getStarCast());
                rating.setValue(selectedMovie.getRating());
                
                cmbLanguage.getItems().add(Language.getLanguage(selectedMovie.getLanguage()));
                cmbLanguage.getSelectionModel().select(0);
                
                txtMovieLength.setText(selectedMovie.getMovieLength()+"");
                
                cmbCategory.getItems().add(Category.getCategory(selectedMovie.getCategory()));
                cmbCategory.getSelectionModel().select(0);
            }catch(SQLException sqlex){
                System.out.println(sqlex.getMessage());
            }
            
        });
        
    }    
    
    @FXML public void handleBtnAddMovieAction(ActionEvent evt){
        
        Movie movie = new Movie(
                0,
                txtMovieCode.getText(),
                txtMovieName.getText(),
                cmbDistributer.getSelectionModel().getSelectedIndex(),
                startDate.getValue().toString(),
                endDate.getValue().toString(),
                Integer.parseInt(txtRun.getText()),
                Integer.parseInt(txtWeek.getText()),
                txtStarCast.getText(),
                rating.getValue(),
                cmbLanguage.getSelectionModel().getSelectedIndex(),
                Integer.parseInt(txtMovieLength.getText()),
                cmbCategory.getSelectionModel().getSelectedIndex()
        );
        System.out.println("MOVIEW : " + movie);
        try{
            Movie.addMovie(movie);
        }catch(SQLException sqlex){
            System.out.println("Unable TO ADD Movie : " + sqlex.getMessage());
        }   
    }
    
    public void loadMovies(){
        try {
            totalMovies = Movie.getMovies();
            tblMovies.getItems().clear();
            System.out.println(totalMovies);
            tblMovies.setItems(totalMovies);
        } catch (SQLException sqlex) {
            System.out.println(sqlex.getMessage());
        }
    }
    
    public void clearMovieForm(){
        txtMovieCode.clear();
        txtMovieName.clear();
        txtRun.clear();
        txtWeek.clear();
        cmbDistributer.getItems().clear();
        cmbLanguage.getItems().clear();
        rating.setValue(0.0);
        cmbCategory.getItems().clear();
        txtStarCast.clear();
        txtMovieLength.clear();
    }
    
    
}
