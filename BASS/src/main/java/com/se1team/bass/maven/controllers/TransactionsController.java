/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1team.bass.maven.controllers;

import com.se1team.bass.maven.DbConnection;
import com.se1team.bass.maven.models.Transaction;
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
    private Label account_number_label;
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
    
    private ObservableList<Transaction> transList;
    private DbConnection dc;
    private Connection conn;
    private Statement stmt;
    private static String accountNum;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  

    }    
     
    public void getAccoutInfo(String accountType, String accountNumber, 
            String balance){
        accountNum = accountNumber;
        account_type_label.setText(accountType);
        account_number_label.setText(accountNumber);
        balance_label.setText(balance);
        getTransactionHistory();
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
            query = "SELECT u.user_name, t.* FROM transaction t, users u, account a"
                    + " WHERE t_user_id=user_id AND a.account_id=t.account_id "
                    + "AND a.account_number=\""+ accountNum
                    +"\";";
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                transList.add(new Transaction(rs.getString(1), rs.getString(6),
                        rs.getString(5), rs.getString(4)));
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

    
}
