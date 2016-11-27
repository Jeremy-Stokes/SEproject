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
import javafx.scene.control.*;
import javafx.stage.Stage;


public class LoginController implements Initializable {
    
    @FXML
    private Label login_label;
    @FXML
    private TextField username_textfield;
    @FXML
    private PasswordField password_textfield;
    @FXML
    private Button login_button;
    
    @FXML
    private void handleLoginAction(ActionEvent event) throws IOException {
        Boolean foundUserName = false;
        Boolean foundPassword = false;
        DbConnection dc = new DbConnection();
        Connection conn = dc.Connect();
        String userName = username_textfield.getText();
        String password = password_textfield.getText();
        Statement stmt = null;
        String query = null;
        ResultSet rs = null;
        
        try{
            stmt = conn.createStatement();
            //query = "SELECT * FROM users;";
            query = "SELECT user_name, password FROM users"
                    + " WHERE user_name=\""+userName+"\" AND password=\""+password+"\";";
            //System.out.println(query);
            rs = stmt.executeQuery(query);
            while(rs.next()){
                if(rs.getString(1).equals(userName)){
                    foundUserName = true;
                    System.out.println("Username verified");
                }                    
                if(rs.getString(2).equals(password)){
                    foundPassword = true;
                    System.out.println("Password verified");
                }    
            }
            if(foundUserName && foundPassword){
                login_label.setText("Logging In");
                switchScene();   
            }else if(!foundUserName){
                login_label.setText("Sorry! Can't find that username!");
            }else if(!foundPassword){
                login_label.setText("Incorrect password!");
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
    
    private void switchScene() throws IOException{
        Stage stage;
        Parent root;
        
        stage = (Stage) login_button.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("/fxml/accounts.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        stage.show();
    } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
