/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.models;

import cinematrix.db.DbManager;
import static cinematrix.models.Language.getLanguages;
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
public class Category {
    private int categoryId;
    private String categoryName;
    
    public Category(){}
    
    public Category(int categoryId, String categoryName){
        setCategoryId(categoryId);
        setCategoryName(categoryName);
    }
    
    public static ObservableList<Category> getCategories() throws SQLException{
        ObservableList<Category> categories = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Movie_category");
        while(rs.next()){
            categories.add(new Category(
                    rs.getInt("category_id"),
                    rs.getString("category_name")
            ));
        }
        return categories;
    }
    
    public static Category getCategory(int id) throws SQLException{
        Category category = null;
        ResultSet rs = DbManager.getInstance().query("select * from Movie_category where category_id = "+id);
        if(rs.next()){
            category = new Category();
            category.setCategoryId(rs.getInt("category_id"));
            category.setCategoryName(rs.getString("category_name"));
        }
        rs.close();
        return category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    @Override
    public String toString(){
        return categoryName;
    }
    
    public static void main(String[] args){
        try{
            System.out.println(getCategory(1));
        }catch(Exception ex){
            
        }
        
    }
}
