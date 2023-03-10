package com.example.app4_bancoimobiliario.model;

import static java.sql.DriverManager.println;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class StarBank {
    private static StarBank instance = null;
    private StarBank(){};
    private CreditCard cartaoCredito;
    List<CreditCard> cartoes;


    public static StarBank getInstance(){
        if (instance == null){
            instance = new StarBank();
        }
        return instance;
    }

    public void startCreditCards(){
        cartoes = new ArrayList<>();
        for (int i= 1; i<=6; i++){
            cartaoCredito = new CreditCard();
            cartoes.add(cartaoCredito);
        }
    }

    public void receive(CreditCard cc, Double valor){
        cc.creditValue(valor);
    }

    public void pay(CreditCard cc, Double valor) throws NegativeException {
        cc.debitValue(valor);

    }

    public CreditCard searchID(int id){
        for (CreditCard cartao : cartoes){
            if (id == cartao.getId()){
                return cartao;
            }
        }
        return null;
    }

}
