/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1team.bass.maven.controllers;

import com.se1team.bass.maven.DbConnection;
import com.se1team.bass.maven.Transaction;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private TableView<Transaction> transaction_table;
    @FXML
    private TableColumn<Transaction, String> creatorColumn;
    @FXML
    private TableColumn<Transaction, String> dateColumn;
    @FXML
    private TableColumn<Transaction, String> amountColumn;
    @FXML
    private TableColumn<Transaction, String> nameColumn;
    @FXML
    private Button back_to_accounts_button;
    
    private ObservableList<Transaction> transList;
    private DbConnection dc;
    private Connection conn;
    private Statement stmt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        getTransactionHistory();

    }    
     
    public void getAccoutInfo(String accountType, String accountNumber, String balance){
        account_type_label.setText(accountType);
        account_id_label.setText(accountNumber);
        balance_label.setText(balance);
    }   
    
    private void getTransactionHistory() {
        dc = new DbConnection();
        conn = dc.Connect();
        stmt = null;
        String query = null;
        ResultSet rs = null;
        transList = FXCollections.observableArrayList();
        
        try{
            stmt = conn.createStatement();
            //query = "SELECT * FROM users;";
            query = "SELECT * "
                    + "FROM transaction where account_id=\"" + 1 + "\";";
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                transList.add(new Transaction(rs.getString(6), rs.getString(5),
                        rs.getString(4), rs.getString(3)));
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
            creatorColumn.setCellValueFactory(
                    new PropertyValueFactory<Transaction, String>("creator"));
            dateColumn.setCellValueFactory(
                    new PropertyValueFactory<Transaction, String>("transDate"));
            amountColumn.setCellValueFactory(
                    new PropertyValueFactory<Transaction, String>("amount"));
            nameColumn.setCellValueFactory(
                    new PropertyValueFactory<Transaction, String>("transName"));
            
            transaction_table.setItems(transList);
        } 
    }
    @FXML
    private void handleBackToAccounts(ActionEvent event) throws IOException{
//        Stage stage;
//        Parent root;
//        
//        stage = (Stage) login_button.getScene().getWindow();
//        root = FXMLLoader.load(getClass().getResource("/fxml/accounts.fxml"));
//        Scene scene = new Scene(root);
//        stage.setScene(scene); 
//        stage.show();
    }
    
}
