/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1team.bass.maven.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author dcart_000
 */
public class LoansController implements Initializable {

    @FXML
    private Label total_interest_label;
    @FXML
    private Label total_amount_label;
    @FXML
    private Label monthly_payments_label;
    @FXML
    private Label name_label;
    @FXML
    private Label account_id_label;
    @FXML
    private Label email_label;
    @FXML
    private Button accept_button;
    @FXML
    private TextField loan_textfield;
    @FXML
    private TextField interest_textfield;
    @FXML
    private TextField loan_term_textfield;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleAcceptAction(ActionEvent event) {
    }

    @FXML
    private void handleLoanChange(KeyEvent event) {
    }

    @FXML
    private void handleInterestChange(KeyEvent event) {
    }

    @FXML
    private void handleTermChange(KeyEvent event) {
    }
    
}
