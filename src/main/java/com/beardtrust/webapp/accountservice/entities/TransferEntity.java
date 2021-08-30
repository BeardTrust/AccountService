/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beardtrust.webapp.accountservice.entities;



/**
 *
 * @author Nathanael <Nathanael.Grier at your.org>
 */
public class TransferEntity {
    
    private CurrencyValue amount;

    public CurrencyValue getAmount() {
        return amount;
    }

    public void setAmount(CurrencyValue amount) {
        this.amount = amount;
    }
    
}
