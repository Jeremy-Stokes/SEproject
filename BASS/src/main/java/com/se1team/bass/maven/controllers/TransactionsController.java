/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1team.bass.maven.controllers;

import com.se1team.bass.maven.DbConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dcart_000
 */
public class TransactionsController implements Initializable {

    @FXML
    private Label account_id_label;
    @FXML
    private Label balance_label;
    @FXML
    private Label account_type_label;
    @FXML
    private Label main_transaction_label;
    @FXML
    private TableView<?> transaction_table;
    @FXML
    private Button back_to_accounts_button;

    /**
     * Initializes the controller class.
     */
    /**@Override
    public void initialize(URL url, ResourceBundle rb) {
        String accountInfo = account_choice.getValue();
        System.out.println(accountInfo);
        DbConnection dc = new DbConnection();
        Connection conn = dc.Connect();
        Statement stmt = null;
        String query = null;
        ResultSet rs;
        
        try{
            stmt = conn.createStatement();
            //query = "SELECT * FROM users;";
            query = "SELECT account_number, account_type, balance "
                    + "FROM account where account_type=\"" + accountInfo + "\";";
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                accout_type_label.setText(rs.getString(2));
                account_number_label.setText(rs.getString(1));
                balance_label.setText(rs.getString(3));
                pending_transaction_label.setText("0.0");
            }
                                  
        } catch(SQLException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {               
                if (rs != null)
                    rs.close();  
                if (stmt != null)
                    stmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {                
                Logger.getLogger(LoginController.class.getName()).
                        log(Level.WARNING, ex.getMessage(), ex);                
            }
        } 
    }  */  
    
    @FXML
    private void handleBackToAccounts(ActionEvent event) throws IOException{
        Stage stage;
        Parent root;
        
        stage = (Stage) back_to_accounts_button.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/accounts.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        stage.show();
    }
}
