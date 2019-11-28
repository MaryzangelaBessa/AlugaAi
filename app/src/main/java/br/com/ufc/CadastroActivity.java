package br.com.ufc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.ufc.data.UsuarioDAO;
import br.com.ufc.data.UsuarioDBMemory;
import br.com.ufc.transactions.Constants;
import br.com.ufc.transactions.Usuario;

public class CadastroActivity extends AppCompatActivity {

    private EditText entradaNomeCadastro, entradaEmailCadastro, entradaSenhaCadastro;
    private UsuarioDAO usuarioDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_layout);
        inicializarComponentes();
        usuarioDAO = UsuarioDBMemory.getInstance();
    }

    private void inicializarComponentes(){
        entradaNomeCadastro = findViewById(R.id.editNome);
        entradaEmailCadastro = findViewById(R.id.editEmail);
        entradaSenhaCadastro = findViewById(R.id.editSenha);
    }
    public void onClickLogin(View view){
        String nome = entradaNomeCadastro.getText().toString();
        String email = entradaEmailCadastro.getText().toString();
        String senha = entradaSenhaCadastro.getText().toString();

        if(nome.equals("") || email.equals("") || senha.equals("")){
            Toast.makeText( this,"Os campos não podem ser nulos!",
                    Toast.LENGTH_SHORT).show();
        }else {

            Intent intent = new Intent();
            Usuario usuario = new Usuario(nome, email, senha);

            usuarioDAO.addUsuario(usuario);
            setResult(Constants.RESULT_ADD, intent);
            finish();
        }
    }

}
