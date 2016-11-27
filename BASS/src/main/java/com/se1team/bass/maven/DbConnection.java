/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1team.bass.maven;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dcart_000
 */
public class DbConnection {
    public Connection Connect(){
        try{
            String url = "jdbc:mysql://bass-rds.c8q0vfhmcvye.us-east-1.rds.amazonaws.com:3306/schema1";
            String user = "se1project";
            String password = "se1project";
            
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn=DriverManager.getConnection(url, user, password);
            
            return conn;
            
        } catch(ClassNotFoundException ex){
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch(SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
