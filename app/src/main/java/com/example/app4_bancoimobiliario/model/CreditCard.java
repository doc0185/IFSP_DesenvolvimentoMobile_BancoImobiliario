package com.example.app4_bancoimobiliario.model;

import android.widget.Toast;

public class CreditCard {

    public static class NegativeException extends Exception {};
    private static int LAST_CARD_ID = 0;
    private double balance;

    private int id;

    public CreditCard() {
        this.id = LAST_CARD_ID + 1;
        this.balance = 15000;
        LAST_CARD_ID++;
    }

    public void creditValue(double valor) {
        balance = balance + valor;
    }

    public void debitValue(double valor) throws NegativeException {
            if (balance - valor < 0) {
                throw new NegativeException();
            } else {
                balance = balance - valor;
            }

    }
}
