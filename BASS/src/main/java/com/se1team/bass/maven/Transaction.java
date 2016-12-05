/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.se1team.bass.maven;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author dcart
 */
public class Transaction {
    private final SimpleStringProperty creator;
    private final SimpleStringProperty transDate;
    private final SimpleStringProperty amount;
    private final SimpleStringProperty transName;
    
    public Transaction(String creator, String transDate, String amount, String transName){
        this.creator = new SimpleStringProperty(creator) ;
        this.transDate = new SimpleStringProperty(transDate);
        this.amount = new SimpleStringProperty(amount);
        this.transName = new SimpleStringProperty(transName);     
    }

    public String getCreator() {
        return creator.get();
    }

    public String getTransDate() {
        return transDate.get();
    }

    public String getAmount() {
        return amount.get();
    }

    public String getTransName() {
        return transName.get();
    }
    
    public void setCreator(String creator) {
        this.creator.set(creator);
    }

    public void setTransDate(String transDate) {
        this.transDate.set(transDate);
    }

    public void setAmount(String amount) {
        this.amount.set(amount);
    }

    public void setTransName(String transName) {
        this.transName.set(transName);
    }
}
