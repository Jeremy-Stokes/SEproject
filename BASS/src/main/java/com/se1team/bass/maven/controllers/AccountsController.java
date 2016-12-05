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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import java.math.BigDecimal;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dcart_000
 */
public class AccountsController implements Initializable {
    
    @FXML
    private TextField amount_text;
    @FXML
    private TextField search_text;
    @FXML
    private Button search_button;
    @FXML
    private Button go_to_button;
    @FXML
    private ChoiceBox<String> account_choice;
    @FXML
    private ChoiceBox<String> page_choice;
    @FXML
    private ChoiceBox<String> loan_choice;
    @FXML
    private Label account_name;
    @FXML
    private Label account_number_label;
    @FXML
    private Label accout_type_label;
    @FXML
    private Label balance_label;
    @FXML
    private Label pending_transaction_label;
    @FXML
    private Label loan_type_label;
    @FXML
    private Label loan_balance_label;
    @FXML
    private Label payment_due_label;
    @FXML
    private Label due_date_label;
    @FXML
    private Label go_to_label;
    @FXML
    private RadioButton withdrawButton;
    @FXML
    private RadioButton depositButton;
    @FXML
    private ToggleGroup accountButtons;
            
    private ResultSet rs = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        page_choice.getItems().add("Transactions");
        page_choice.getItems().add("Users");
        
    }  
    
    @FXML
    private void handleGoToAction(ActionEvent event) throws IOException{
        String pageChoice = page_choice.getValue();
        String pageName = "";
        Stage stage = new Stage();
        Parent root;
        
        if (account_number_label.getText().isEmpty()){
            go_to_label.setText("Please select an account first!");
            return;
        }
        if(pageChoice.equals("Transactions"))
            pageName = "/fxml/transactions.fxml";
        else if(pageChoice.equals("Users"))
            pageName = "/fxml/users.fxml";
        else{
            go_to_label.setText("Please select an appropriate value!");
            return;
        }          

        System.out.println(""+ accout_type_label.getText()+ 
                account_number_label.getText()+ balance_label.getText());
        FXMLLoader loader = new FXMLLoader();
        root = loader.load(getClass().getResource(pageName).openStream());
        TransactionsController transController = (TransactionsController)loader.getController();
        transController.getAccoutInfo(accout_type_label.getText(),
            account_number_label.getText(), balance_label.getText());
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        stage.show();

    }
    
    @FXML
    private void handleProcess(ActionEvent event) throws IOException{
        String resultSt;
        String transType;
        String transName;
        BigDecimal amount = new BigDecimal(amount_text.getText());
        BigDecimal balance = new BigDecimal(balance_label.getText());
        BigDecimal result;
        
        if(withdrawButton.isSelected()){
            result = balance.subtract(amount);
            resultSt = result.toString();
            balance_label.setText(resultSt);
            transType = "W";
            transName = "Withdraw";
        }
        else{
            result = balance.add(amount);
            resultSt = result.toString();
            balance_label.setText(resultSt);
            transType = "D";
            transName = "Deposit";
        }
        DbConnection dc = new DbConnection();
        Connection conn = dc.Connect();
        Statement stmt = null;
        Statement stmt2 = null;
        String query = null;
        String query2 = null;
        
        try{
            stmt = conn.createStatement();
            //query = "SELECT * FROM users;";
            query = "UPDATE account SET balance='" + result + "' WHERE account_number=(\""
                    + account_number_label.getText()+ "\");";
            stmt.executeUpdate(query);
            stmt2 = conn.createStatement();
            query2 = "INSERT INTO transaction(transaction_type, transaction_name,"
                    + " amount, modified_date, t_user_id) values"+
                    "(\""+ transType +"\", \""+ transName +"\", "+ result +
                    ", now(), 1);";
            stmt2.executeUpdate(query2);
        } catch(SQLException ex){
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {               
                if (rs != null)
                    rs.close();  
                if (stmt != null)
                    stmt.close();
                if (stmt2 != null)
                    stmt2.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException ex) {                
                Logger.getLogger(LoginController.class.getName()).
                        log(Level.WARNING, ex.getMessage(), ex);                
            }
        } 
    }
    
    @FXML
    private void handleAccountChangeAction(ActionEvent event) throws IOException{
        
        String accountInfo = account_choice.getValue();
        System.out.println(accountInfo);
        DbConnection dc = new DbConnection();
        Connection conn = dc.Connect();
        Statement stmt = null;
        String query = null;
        
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
    }
    
    @FXML
    private void handleLoanChangeAction(ActionEvent event) throws IOException{
        
        String loanName = loan_choice.getValue();
        DbConnection dc = new DbConnection();
        Connection conn = dc.Connect();
        Statement stmt = null;
        String query = null;
        
        try{
            stmt = conn.createStatement();
            query = "SELECT * \n" +
                    "FROM loan\n" +
                    "WHERE loan_type=\""+ loanName +"\";";
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                //account_name.setText("David Carter");
                loan_type_label.setText(rs.getString(3));
                loan_balance_label.setText(rs.getString(4));
                payment_due_label.setText(rs.getString(5));
                due_date_label.setText(rs.getString(6));
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
    }
    
    
    @FXML
    private void handleSearchAction(ActionEvent event) throws IOException{
        System.out.println("HERE");
        DbConnection dc = new DbConnection();
        Connection conn = dc.Connect();
        String lastName = search_text.getText();
        Statement stmt = null;
        Statement stmt2 = null;
        String query = null;
        account_choice.getItems().removeAll(account_choice.getItems());
        try{
            stmt = conn.createStatement();
            query = "SELECT account_id, account_number, account_type, balance "
                    + "FROM account where user_id_fk=(select user_id from users"
                    + " where last_name=\"" + lastName + "\");";  
            
            rs = stmt.executeQuery(query);
            
            while(rs.next()){
                account_choice.getItems().add(rs.getString(3));            
            }
            
            stmt2 = conn.createStatement();
            query = "SELECT u.first_name, u.last_name, l.* \n" +
                    "FROM loan l, users u\n" +
                    "WHERE user_id_fk= user_id\n" +
                    "AND u.last_name = \""+lastName+"\";";
            rs = stmt2.executeQuery(query);
            while(rs.next()){
                //displays name
                account_name.setText((rs.getString(1)+" "+ rs.getString(2)));
                
                //adds to choice box
                loan_choice.getItems().add(rs.getString(5));
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
    
    }
}
