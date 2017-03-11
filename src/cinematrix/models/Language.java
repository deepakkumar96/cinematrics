/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.models;

import cinematrix.db.DbManager;
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
public class Language {
    private int languageId;
    private String languageName;
    
    public Language(){}
    public Language(String languageName){
        this(0, languageName);
    }
    
    public Language(int languageId, String languageName){
        setLanguageId(languageId);
        setLanguageName(languageName);
    }
    
    public static ObservableList<Language> all() throws SQLException{
        ObservableList<Language> languages = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Language");
        while(rs.next()){
            languages.add(new Language(
                    rs.getInt("language_id"),
                    rs.getString("language_name")
            ));
        }
        return languages;
    }
    public static Language get(int id) throws SQLException{
        Language language = null;
        try (ResultSet rs = DbManager.getInstance().query("select * from Language where language_id = "+id)) {
            if(rs.next()){
                language = new Language();
                language.setLanguageId(rs.getInt("language_id"));
                language.setLanguageName(rs.getString("language_name"));
            }
        }
        return language;
    }
    
    public static boolean update(Language language) throws SQLException{
        DbManager.getInstance().update(
            "UPDATE Language set language_name = '"+ language.getLanguageName()+ "' " +
            "WHERE language_id = " + language.getLanguageId()
       );
        return true;
    }   
    
    
    public static boolean add(Language language) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Language (language_name)" +
            "VALUES( '"+ language.getLanguageName()+"' )"
       );
        return true;
    }
    
    public static boolean delete(int id) throws SQLException{
        DbManager.getInstance().update(
            "delete from Language where language_id = " + id
       );
        return true;
    }
    
    public static boolean delete(Language language) throws SQLException{
        return delete(language.getLanguageId());
    }
    

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int id) {
        this.languageId = id;
    }

    public String getLanguageName() {
        return languageName;
    }

    public void setLanguageName(String languageName) {
        this.languageName = languageName;
    }
    
    @Override
    public String toString(){
        return languageName;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        
        if(!(obj instanceof Language))
            return false;
        
        Language other = (Language) obj;
        return other.languageId == this.languageId;
    }
    
    public static void main(String[] args){
        try{
            System.out.println(Language.all());
            delete(3);
            System.out.println(Language.all());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
