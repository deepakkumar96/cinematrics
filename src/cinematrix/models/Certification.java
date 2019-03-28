/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.models;

import cinematrix.db.DbManager;
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
    
    public Certification(String certificationName){
        this(0, certificationName);
    }
    
    public Certification(int certificationId, String certificationName){
        setCertificationId(certificationId);
        setCertificationName(certificationName);
    }

    public static ObservableList<Certification> all() throws SQLException{
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
    
    public static Certification get(int id) throws SQLException{
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
    
    public static boolean add(Certification certification) throws SQLException{
        DbManager.getInstance().update(
            "INSERT INTO Certification (certification_name)" +
            "VALUES( '"+ certification.getCertificationName()+"' )"
       );
        return true;
    }
        
    public static boolean update(Certification certification) throws SQLException{
        DbManager.getInstance().update(
            "UPDATE Certification set certification_name = '"+ certification.getCertificationName() + "' " +
            "WHERE certification_id = " + certification.getCertificationId()
       );
        return true;
    }
    
    public static boolean delete(int id) throws SQLException{
        DbManager.getInstance().update(
            "delete from Certification where certification_id = " + id
        );
        return true;
    }
    
    public static boolean delete(Certification certification) throws SQLException{
        return delete(certification.getCertificationId());
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
    
    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        
        if(!(obj instanceof Certification))
            return false;
        
        Certification other = (Certification) obj;
        return other.certificationId == this.certificationId;
    }
    
    public static void main(String[] args){
        try{
            System.out.println(all());
            //add(new Certification("Adulta"));
            delete(3);
            System.out.println(all());
        }catch(SQLException sqlex){
            System.out.println(sqlex.getMessage());
        }
        
    }
}
