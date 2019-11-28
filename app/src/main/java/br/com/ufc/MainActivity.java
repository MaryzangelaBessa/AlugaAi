package br.com.ufc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.com.ufc.data.UsuarioDAO;
import br.com.ufc.data.UsuarioDBMemory;

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

        if(email.equals("") || senha.equals("")){
            Toast.makeText( this,"Os campos não podem ser nulos!",
                    Toast.LENGTH_SHORT).show();
        }else {
            if (usuarioDAO.getUsuario(email).getSenha().equals(senha)) {
                intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
            } else {
                intent = new Intent(this, CadastroActivity.class);
                startActivity(intent);
            }
        }
    }

    private void inicializarComponentes(){
        entradaEmail = findViewById(R.id.editEmail);
        entradaSenha = findViewById(R.id.editSenha);
        botaoEntrar = findViewById(R.id.btEntrarID);
        textView1 = findViewById(R.id.textView);
    }

    public void onClickCadastro(View view){
        Intent intent = new Intent(this, CadastroActivity.class);
        startActivity(intent);
    }
}
