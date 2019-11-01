package br.com.ufc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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
        entradaNomeCadastro = findViewById(R.id.editNomeCadastro);
        entradaEmailCadastro = findViewById(R.id.editEmailCadastro);
        entradaSenhaCadastro = findViewById(R.id.editSenhaCadastro);
    }
    public void onClickLogin(View view){
        String nome = entradaNomeCadastro.getText().toString();
        String email = entradaEmailCadastro.getText().toString();
        String senha = entradaSenhaCadastro.getText().toString();
        Intent intent = new Intent();
        Usuario usuario = new Usuario("gui","gui", "123");

        usuarioDAO.addUsuario(usuario);
        setResult(Constants.RESULT_ADD, intent );
        finish();
    }

}
