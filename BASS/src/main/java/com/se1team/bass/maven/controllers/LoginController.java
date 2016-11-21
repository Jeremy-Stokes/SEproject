package com.se1team.bass.maven.controllers;

import com.se1team.bass.maven.DbConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class LoginController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private void handleLoginAction(ActionEvent event) throws SQLException {
        System.out.println("You clicked me!");
        //label.setText("Hello World!");
        DbConnection dc = new DbConnection();
        Connection conn = dc.Connect();
        Statement stmt = conn.createStatement() ;
        String query = "select * from users;" ;
        ResultSet rs = stmt.executeQuery(query) ;
        while(rs.next())
        {
            System.out.println("HERE");
            System.out.println(rs.getString(1));
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
