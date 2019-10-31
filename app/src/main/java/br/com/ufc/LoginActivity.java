package br.com.ufc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText entradaEmail, entradaSenha;
    private Button botaoEntrar;
    private TextView textView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        inicializarComponentes();

    }

    private void inicializarComponentes(){
        entradaEmail = findViewById(R.id.editEmailCadastro);
        entradaSenha = findViewById(R.id.editSenhaCadastro);
        botaoEntrar = findViewById(R.id.btEntrarID);
        textView1 = findViewById(R.id.textView);
    }


    public void onClickCadastro(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }

    public void onClickCreateHome(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
