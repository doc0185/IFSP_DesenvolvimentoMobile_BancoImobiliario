package com.example.app4_bancoimobiliario.model;

public class StarBank {
    private static StarBank instance = null;
    private StarBank(){};
    private CreditCard CartaoCredito;

    public static StarBank getInstance(){
        if (instance == null){
            instance = new StarBank();
        }
        return instance;
    }

    public void startCreditCards(){
        for (int i= 1; i<=6; i++){
            CartaoCredito = new CreditCard();
        }
    }

    public void receive(CreditCard cc, Double valor){
        cc.creditValue(valor);
    }

    public void pay(CreditCard cc, Double valor) throws CreditCard.NegativeException {
        cc.debitValue(valor);
    }


}
