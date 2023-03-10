package com.example.app4_bancoimobiliario.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.app4_bancoimobiliario.R;
import com.example.app4_bancoimobiliario.model.NegativeException;
import com.example.app4_bancoimobiliario.model.StarBank;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final double ROW_VALUE = 2000;
    private EditText inputEditTextCartaoMais;
    private EditText inputEditTextCartaoMenos;
    private EditText inputEditTextValor;
    private Button receiveButton;
    private Button payButton;
    private Button transfButton;
    private Button resetButton;
    private Button rowButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StarBank.getInstance().startCreditCards();

        inputEditTextCartaoMais = findViewById(R.id.edittext_input_cartao_mais);
        inputEditTextCartaoMenos = findViewById(R.id.edittext_input_cartao_menos);
        inputEditTextValor = findViewById(R.id.edittext_input_valor);

        receiveButton = findViewById(R.id.button_recv);
        receiveButton.setOnClickListener(this);
        payButton = findViewById(R.id.button_pag);
        payButton.setOnClickListener(this);
        transfButton = findViewById(R.id.button_transf);
        transfButton.setOnClickListener(this);
        resetButton = findViewById(R.id.button_RESET);
        resetButton.setOnClickListener(this);
        rowButton = findViewById(R.id.button_rodada);
        rowButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_recv:
                receive(StarBank.getInstance());
                break;
            case R.id.button_RESET:
                StarBank.getInstance().startCreditCards();
                break;
            case R.id.button_rodada:
                row(StarBank.getInstance());
                break;
            case R.id.button_pag:
                pay(StarBank.getInstance());
                break;
            case R.id.button_transf:
                transf(StarBank.getInstance());
                break;
        }

    }

    public void receive(StarBank sb){

        double valor = 0;
        int id1;
        String cartao1 = inputEditTextCartaoMais.getText().toString();
        String valorDigitado = inputEditTextValor.getText().toString();
        try{
            id1 = Integer.parseInt(cartao1);
            valor = Double.parseDouble(valorDigitado);
        } catch (NumberFormatException nfException){
            id1 = 0;
            valor = 0;
            Toast.makeText(this, getString(R.string.invalid_temp_message), Toast.LENGTH_SHORT).show();
        }

        sb.receive(sb.searchID(id1), valor);

        String balance = String.valueOf(sb.searchID(id1).getBalance());
        Toast.makeText(this, "Saldo: " + balance, Toast.LENGTH_SHORT).show();

    }

    public void pay(StarBank sb){

        double valor = 0;
        int id1;
        String cartao1 = inputEditTextCartaoMenos.getText().toString();
        String valorDigitado = inputEditTextValor.getText().toString();
        try{
            id1 = Integer.parseInt(cartao1);
            valor = Double.parseDouble(valorDigitado);
        } catch (NumberFormatException nfException){
            id1 = 0;
            valor = 0;
            Toast.makeText(this, getString(R.string.invalid_temp_message), Toast.LENGTH_SHORT).show();
        }

        try {
            sb.pay(sb.searchID(id1), valor);
        } catch (NegativeException e) {
            Toast.makeText(this, getString(R.string.invalid_temp_message2), Toast.LENGTH_SHORT).show();
        }

        String balance = String.valueOf(sb.searchID(id1).getBalance());
        Toast.makeText(this, "Saldo: " + balance, Toast.LENGTH_SHORT).show();

    }

    public void transf(StarBank sb){
        double valor = 0;
        int check = 1;
        int id2;
        String cartao2 = inputEditTextCartaoMenos.getText().toString();
        String valorDigitado = inputEditTextValor.getText().toString();
        try{
            id2 = Integer.parseInt(cartao2);
            valor = Double.parseDouble(valorDigitado);
        } catch (NumberFormatException nfException){
            id2 = 0;
            valor = 0;
            Toast.makeText(this, getString(R.string.invalid_temp_message), Toast.LENGTH_SHORT).show();
            check = 0;
        }
        try {
            sb.pay(sb.searchID(id2), valor);
        } catch (NegativeException e) {
            Toast.makeText(this, getString(R.string.invalid_temp_message2), Toast.LENGTH_SHORT).show();
            check = 0;
        }

        if (check==1){
            int id1;
            String cartao1 = inputEditTextCartaoMais.getText().toString();
            try{
                id1 = Integer.parseInt(cartao1);
            } catch (NumberFormatException nfException){
                id1 = 0;
                valor = 0;
                Toast.makeText(this, getString(R.string.invalid_temp_message), Toast.LENGTH_SHORT).show();
            }

            sb.receive(sb.searchID(id1), valor);

            String balance2= String.valueOf(sb.searchID(id2).getBalance());
            String balance1 = String.valueOf(sb.searchID(id1).getBalance());
            Toast.makeText(this, "Saldo do Pagante: " + balance2 + "  " + "Saldo do Recebedor: " + balance1, Toast.LENGTH_SHORT).show();
        }



    }

    public void row(StarBank sb){
        int id1;
        String cartao1 = inputEditTextCartaoMais.getText().toString();
        try{
            id1 = Integer.parseInt(cartao1);
        } catch (NumberFormatException nfException){
            id1 = 0;
            Toast.makeText(this, getString(R.string.invalid_temp_message), Toast.LENGTH_SHORT).show();
        }

        sb.receive(sb.searchID(id1), ROW_VALUE);

        String balance = String.valueOf(sb.searchID(id1).getBalance());
        Toast.makeText(this, "Saldo: " + balance, Toast.LENGTH_SHORT).show();

    }
}