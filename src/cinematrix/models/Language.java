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
    
    public Language(int languageId, String languageName){
        setLanguageId(languageId);
        setLanguageName(languageName);
    }
    
    public static ObservableList<Language> getLanguages() throws SQLException{
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
    public static Language getLanguage(int id) throws SQLException{
        Language language = null;
        ResultSet rs = DbManager.getInstance().query("select * from Language where language_id = "+id);
        if(rs.next()){
            language = new Language();
            language.setLanguageId(rs.getInt("language_id"));
            language.setLanguageName(rs.getString("language_name"));
        }
        rs.close();
        return language;
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
    
    public static void main(String[] args){
        try{
            System.out.println("Lang");
            System.out.println(Distributor.getDistributor(1));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
