package br.com.ufc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class CadastroActivity extends AppCompatActivity {

    private EditText entradaNomeCadastro, entradaEmailCadastro, entradaSenhaCadastro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();
    }

    private void inicializarComponentes(){
        entradaNomeCadastro = findViewById(R.id.editNomeCadastro);
        entradaEmailCadastro = findViewById(R.id.editEmailCadastro);
        entradaSenhaCadastro = findViewById(R.id.editSenhaCadastro);
    }
    public void onClickLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
