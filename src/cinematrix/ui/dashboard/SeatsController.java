/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cinematrix.ui.dashboard;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author deepak
 */
public class SeatsController implements Initializable {

    @FXML private GridPane gridGold;
    @FXML private GridPane gridPrime;
    @FXML private GridPane gridNormal;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        System.out.println("INIT - SEAT-CONTROLLER");
        
        final double V_GAP = 20;
        
        /*      GOLD        */
        gridGold.setVgap(V_GAP);
        HBox hbSeatGold1 = new HBox();
        hbSeatGold1.setSpacing(8d);
        hbSeatGold1.setPadding(new Insets(0, 0, 0, 120));
        
        
        Label mLabel = new Label("M");
        mLabel.setPadding(new Insets(5, 10, 0, 0));
        hbSeatGold1.getChildren().add(mLabel);
        
        for(int i=3; i>=1; i--){
            Button btnSeat = new Button(i+"");
            btnSeat.setFont(Font.font(8));
            //btnSeat.setButtonType(JFXButton.ButtonType.RAISED);
            //if (i==5)
              //  btnSeat.setPadding(new Insets(5, 30, 0, 0));
            hbSeatGold1.getChildren().add(btnSeat);
        }
        gridGold.add(hbSeatGold1, 0, 1, 1, 2);
        
        
        /***********   PRIME   *************/
        //1st row
        
        gridPrime.setVgap(V_GAP);
        HBox hbSeatPrimeRow1 = new HBox();
        hbSeatPrimeRow1.setPadding(new Insets(0, 0, 0, 120));
        hbSeatPrimeRow1.setSpacing(8d);
        
        Label lLabel = new Label("L");
        lLabel.setPadding(new Insets(5, 10, 0, 0));
        hbSeatPrimeRow1.getChildren().add(lLabel);
        
        for(int i=10; i>=1; i--){
            Button btnSeat = new Button(i+"");
            btnSeat.setFont(Font.font(8));
            //btnSeat.setButtonType(JFXButton.ButtonType.RAISED);
            //if (i==5)
              //  btnSeat.setPadding(new Insets(5, 30, 0, 0));
            hbSeatPrimeRow1.getChildren().add(btnSeat);
        }
        gridPrime.add(hbSeatPrimeRow1, 0, 1, 1, 2);
        
        
        //2nd row
        HBox hbSeatPrimeRow2 = new HBox();
        hbSeatPrimeRow2.setPadding(new Insets(0, 0, 0, 120));
        hbSeatPrimeRow2.setSpacing(8d);
        
        Label kLabel = new Label("k");
        kLabel.setPadding(new Insets(5, 10, 0, 0));
        hbSeatPrimeRow2.getChildren().add(kLabel);
        
        for(int i=10; i>=1; i--){
            Button btnSeat = new Button(i+"");
            btnSeat.setFont(Font.font(8));
            //btnSeat.setButtonType(JFXButton.ButtonType.RAISED);
            //if (i==5)
              //  btnSeat.setPadding(new Insets(5, 30, 0, 0));
            btnSeat.setDisable(true);
            hbSeatPrimeRow2.getChildren().add(btnSeat);
        }
        gridPrime.add(hbSeatPrimeRow2, 0, 2, 1, 2);
        
        
        /*      Normal      */
//        Label lblNoral = new Label("Normal-Rs. 100");
//        lblNoral.setFont(Font.font(16));
//        gridNormal.setAlignment(Pos.CENTER);
//        gridNormal.add(lblNoral, 0, 0, 1, 2);
//      
        gridNormal.setPadding(new Insets(40, 0, 0, 0));
        gridNormal.setVgap(V_GAP);
        
        HBox hbLabel = new HBox();
        hbLabel.setPadding(new Insets(0, 0, 0, 120));
        hbLabel.setSpacing(8d);
        Label lblNormalText = new Label("Normal");
        GridPane.setHalignment(hbLabel, HPos.CENTER);
        hbLabel.getChildren().add(lblNormalText);
        gridNormal.add(hbLabel, 0, 0, 1, 2);
        
        for(int row=1; row<9; row++){
            HBox hbSeatNormalRow = new HBox();
            hbSeatNormalRow.setPadding(new Insets(0, 0, 0, 120));
            hbSeatNormalRow.setSpacing(8d);
            //hbSeatNormalRow.setStyle("-fx-border-color:red;");
            
            char labelText = (char)((9-row)+65);
            Label label = new Label("J");
            label.setPadding(new Insets(5, 10, 0, 0));
            hbSeatNormalRow.getChildren().add(label);

            for(int i=(row>2?13:16); i>=1; i--){
                Button btnSeat = new Button(i+"");
                btnSeat.setFont(Font.font(8));
                btnSeat.setPrefHeight(10);
                btnSeat.setPrefWidth(100);
                hbSeatNormalRow.getChildren().add(btnSeat);
            }
            gridNormal.add(hbSeatNormalRow, 0, row, 1, 2);
            System.out.println("ROW : " + row);
        }
        gridNormal.setGridLinesVisible(false);
        
    }    
    
}
