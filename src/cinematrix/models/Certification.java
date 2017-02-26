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
public class Certification {
    private int certificationId;
    private String certificationName;
    
    public Certification(){}
    
    public Certification(int certificationId, String certificationName){
        setCertificationId(certificationId);
        setCertificationName(certificationName);
    }

    public static ObservableList<Certification> getCertifications() throws SQLException{
        ObservableList<Certification> certifications = FXCollections.observableArrayList();
        ResultSet rs = DbManager.getInstance().query("Select * from Certification");
        while(rs.next()){
            certifications.add(new Certification(
                    rs.getInt("certification_id"),
                    rs.getString("certification_name")
            ));
        }
        rs.close();
        return certifications;
    }
    
    public static Certification getCertification(int id) throws SQLException{
        Certification certification = null;
        ResultSet rs = DbManager.getInstance().query("select * from Certification where certification_id = "+id);
        if(rs.next()){
            certification = new Certification();
            certification.setCertificationId(rs.getInt("certification_id"));
            certification.setCertificationName(rs.getString("certification_name"));
        }
        rs.close();
        return certification;
    }
    
    public int getCertificationId() {
        return certificationId;
    }

    public void setCertificationId(int certificationId) {
        this.certificationId = certificationId;
    }

    public String getCertificationName() {
        return certificationName;
    }

    public void setCertificationName(String certificationName) {
        this.certificationName = certificationName;
    }
    
    @Override
    public String toString(){
        //return "{" + certificationId + ", " + certificationName + " }";
        return certificationName;
    }
    
    public static void main(String[] args){
        try{
            System.out.println(getCertification(0));
        }catch(Exception ex){
            
        }
        
    }
}
