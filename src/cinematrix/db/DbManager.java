/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author deepak
 */
public class DbManager {
 
    private static DbManager dbm = null;
    private Connection conn;
    
    static {
        try{
            dbm = new DbManager("/home/deepak/Desktop/Cinematrix.accdb");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public DbManager() throws ClassNotFoundException, SQLException{
        this("/home/deepak/Desktop/Cinematrix.accdb");
    }
    
    public DbManager(String dbUrl) throws ClassNotFoundException, SQLException{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");  
        conn = DriverManager.getConnection("jdbc:ucanaccess://"+dbUrl);
        System.out.println("jdbc:ucanaccess://"+dbUrl);
        try{
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("Insert into User (full_name, user_name, password, user_type, phone_no, email_id) "
                           + "VALUES ('a', 'b', 'c', 1, 'aaa', 'hj')");
            
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
        
    }
    
    public static DbManager getInstance(){
        return dbm;
    }
    
    public ResultSet query(String query) throws SQLException{
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(query);
    }
    
    public boolean update(String query) throws SQLException{
        Statement stmt = conn.createStatement();
        stmt.executeUpdate(query);
        return true;
    }
    
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        
        DbManager db = new DbManager("/home/deepak/Desktop/Cinematrix.accdb");
        
        
        
        
    }
    
}
