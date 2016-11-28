/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1team.bass.maven.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
