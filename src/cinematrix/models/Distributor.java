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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author deepak
 */
public class Distributor {
    private int distId;
    private String distName;
    private String distCity;
    private String distOwner;
    private String distManager;
    private String distMobileNo;
    private String distEmailId;
    
    public Distributor(){}

    public static ObservableList<Distributor> getDistributors() throws SQLException{
        ObservableList<Distributor> distributors = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Distributor");
        while(rs.next()){
            distributors.add(createDistributorFromResultSet(rs));
        }
        return distributors;
    }
    
    public static Distributor getDistributor(int id) throws SQLException{
        Distributor distributor = null;
        ResultSet rs = DbManager.getInstance().query("select * from Distributor where dist_id = "+id);
        if(rs.next()){
            distributor = createDistributorFromResultSet(rs);
        }
        rs.close();
        return distributor;
    }
    
    /**
     * 
     * @param ResultSet
     * @return Creates a Distributor from ResultSet 
     * @throws SQLException 
     */
    public static Distributor createDistributorFromResultSet(ResultSet rs) throws SQLException {
            Distributor distributor = new Distributor();
            distributor.setDistId(rs.getInt("dist_id"));
            distributor.setDistName(rs.getString("dist_name"));
            distributor.setDistCity(rs.getString("dist_city"));
            distributor.setDistOwner(rs.getString("dist_owner"));
            distributor.setDistManager(rs.getString("dist_manager"));
            distributor.setDistMobileNo(rs.getString("dist_mobile_no"));
            distributor.setDistEmailId(rs.getString("dist_email_id"));
            return distributor;
    }
    
    public int getDistId() {
        return distId;
    }

    public void setDistId(int distId) {
        this.distId = distId;
    }

    public String getDistName() {
        return distName;
    }

    public void setDistName(String distName) {
        this.distName = distName;
    }

    public String getDistCity() {
        return distCity;
    }

    public void setDistCity(String distCity) {
        this.distCity = distCity;
    }

    public String getDistOwner() {
        return distOwner;
    }

    public void setDistOwner(String distOwner) {
        this.distOwner = distOwner;
    }

    public String getDistManager() {
        return distManager;
    }

    public void setDistManager(String distManager) {
        this.distManager = distManager;
    }

    public String getDistMobileNo() {
        return distMobileNo;
    }

    public void setDistMobileNo(String distMobileNo) {
        this.distMobileNo = distMobileNo;
    }

    public String getDistEmailId() {
        return distEmailId;
    }

    public void setDistEmailId(String distEmailId) {
        this.distEmailId = distEmailId;
    }
    
    @Override
    public  String toString(){
        return distName;
    }
    
    public static void main(String[] args){
        try{
            System.out.println("Dist : " + getDistributor(1));
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
}
