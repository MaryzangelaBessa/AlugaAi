package br.com.ufc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import br.com.ufc.data.UsuarioDAO;
import br.com.ufc.data.UsuarioDBMemory;
import br.com.ufc.transactions.Constants;
import br.com.ufc.transactions.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText entradaEmail, entradaSenha;
    private Button botaoEntrar;
    private TextView textView1;
    private UsuarioDAO usuarioDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        inicializarComponentes();
        usuarioDAO = UsuarioDBMemory.getInstance();

    }

    public void onClickCreateHome(View view){

        String email = entradaEmail.getText().toString();
        String senha = entradaSenha.getText().toString();
        Intent intent;

        if(usuarioDAO.getUsuario(email).getSenha().equals(senha)){
            intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        }else {
            intent = new Intent(this, CadastroActivity.class);
            startActivity(intent);
        }
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
}
