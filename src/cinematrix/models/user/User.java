/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.models.user;

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


public class User {
    private int userId;
    private final StringProperty username = new SimpleStringProperty();
    private final StringProperty fullName = new SimpleStringProperty();
    private final StringProperty password = new SimpleStringProperty();
    private int authority;
    private int userType;
    private final StringProperty phoneNo = new SimpleStringProperty();
    private final StringProperty email = new SimpleStringProperty();
    
    public User(int userId, String username, String fullName, String password, int userType, String phone, String email)
    {
        setUserId(userId);
        setUsername(username);
        setFullName(fullName);
        setPassword(password);
        setUserType(userType);
        setPhoneNo(phone);
        setEmail(email);
    }
    
    public static ObservableList<User> getUsers() throws SQLException{
        ObservableList<User> users = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from User");
        while(rs.next()){
            users.add(new User(
                    rs.getInt("user_id"),
                    rs.getString("user_name"),
                    rs.getString("full_name"),
                    rs.getString("password"),
                    rs.getInt("user_type"),
                    rs.getString("phone_no"),
                    rs.getString("email_id")
            ));
        }
        return users;
    }
    
    public static boolean addUser(User user) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO USER (full_name, user_name, password, user_type, phone_no, email_id) VALUES("+ 
                    "'" + user.getFullName()+ "', " +
                    "'" + user.getUsername()+ "', " +
                    "'" + user.getPassword()+ "', " +
                     "" + user.getUserType()+ ", " +
                    "'" + user.getPhoneNo()+ "', " +
                    "'" + user.getEmail()+ "')"
       );
        return true;
    }
    
    public static String getAuthority(int num){
        switch (num) {
            case 0:
                return "Admin";
            case 1:
                return "Staff";
            default:
                return "None";
        }
    }
    
    /**
     * @param username the username to set
     */
    public void setFullName(String username) {
        this.fullName.set(username);
    }
    
    public String getFullName(){
        return fullName.get();
    }
    
    /**
     * @param username the username to set
     */
    public void setEmail(String email) {
        this.email.set(email);
    }
    
    public String getEmail(){
        return email.get();
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password.get();
    }
    
    /**
     * @return the username
     */
    public String getUsername() {
        return username.get();
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username.set(username);
    }

    

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password.set(password);
    }
    
    /**
     * @param password the password to set
     */
    public void setPhoneNo(String phoneNo){
        this.phoneNo.set(phoneNo);
    }
    
    /**
     * @param password the password to set
     */
    public String getPhoneNo(){
        return phoneNo.get();
    }

    /**
     * @return the authority
     */
    public int getAuthority() {
        return authority;
    }

    /**
     * @param authority the authority to set
     */
    public void setAuthority(int authority) {
        this.authority = authority;
    }
    
    /**
     * @return the authority
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param authority the authority to set
     */
    public void setUserId(int id) {
        this.userId = id;
    }
    
    /**
     * @return the authority
     */
    public int getUserType() {
        return userType;
    }

    /**
     * @param authority the authority to set
     */
    public void setUserType(int userType) {
        this.userType = this.userType;
    }
    
    @Override
    public String toString(){
        return  "'" + getFullName()+ "', " +
                "'" + getUsername()+ "', " +
                "'" + getPassword()+ "', " +
                "" + getUserType()+ ", " +
                "'" + getPhoneNo()+ "', " +
                "'" + getEmail()+ "'";
    }
}
