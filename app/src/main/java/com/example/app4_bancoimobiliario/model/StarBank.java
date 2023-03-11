package com.example.app4_bancoimobiliario.model;


import java.util.ArrayList;
import java.util.List;

public class StarBank {
    private static StarBank instance = null;
    public static final double ROW_VALUE = 2000;
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
        cartaoCredito = new CreditCard();
        cartaoCredito.setLastCardId();
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

    public boolean transfer(CreditCard cc1, CreditCard cc2, Double valor){
        try {
            cc2.debitValue(valor);
        } catch (NegativeException e) {
            return false;
        }
        cc1.creditValue(valor);
        return true;
    }

    public void roundCompleted(CreditCard cc){
        cc.creditValue(ROW_VALUE);
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
