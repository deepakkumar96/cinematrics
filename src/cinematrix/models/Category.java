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
public class Category {
    private int categoryId;
    private String categoryName;
    
    public Category(){}
    
    public Category(int categoryId, String categoryName){
        setCategoryId(categoryId);
        setCategoryName(categoryName);
    }
    
    public Category(String categoryName){
        this(0, categoryName);
    }
    
    public static ObservableList<Category> all() throws SQLException{
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
    
    public static Category get(int id) throws SQLException{
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
    
    public static boolean update(Category category) throws SQLException{
        DbManager.getInstance().update(
            "UPDATE Movie_category set category_name = '"+ category.getCategoryName()+ "' " +
            "WHERE category_id = " + category.getCategoryId()
       );
        return true;
    }
    
    public static boolean add(Category category) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Movie_category (category_name)" +
            "VALUES( '"+ category.getCategoryName()+"' )"
       );
        return true;
    }
    
    public static boolean delete(int id) throws SQLException{
        DbManager.getInstance().update(
            "delete from Movie_category where category_id = " + id
        );
        return true;
    }
    
    public static boolean delete(Category category) throws SQLException{
        return delete(category.getCategoryId());
    }
    
    public static boolean delete(int...ids) throws SQLException{
        for(int id : ids)
            Category.delete(id);
        return true;
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
    
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        
        if(!(obj instanceof Category))
            return false;
        
        Category other = (Category) obj;
        return other.categoryId == this.categoryId;
    }
    
    public static void main(String[] args){
        try{
            System.out.println(all());
            delete(4,5,6,7,8);
            System.out.println(all());
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        
    }
}
