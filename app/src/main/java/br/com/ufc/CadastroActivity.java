package br.com.ufc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import br.com.ufc.data.UsuarioDAO;

public class CadastroActivity extends AppCompatActivity {

    private EditText entradaNomeCadastro, entradaEmailCadastro, entradaSenhaCadastro;
    private Button botaoVoltar, botaoCadastrar;
    private UsuarioDAO usuarioDAO;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_layout);
        inicializarComponentes();
        eventoClick();
        auth = FirebaseAuth.getInstance();

    }

    public void onClickCadastrar(View view){
        final Intent intent = new Intent(getApplicationContext(),HomeActivity.class );
        String nome = entradaNomeCadastro.getText().toString();
        String email = entradaEmailCadastro.getText().toString();
        String senha = entradaSenhaCadastro.getText().toString();
        HashMap<String,Object> usuario = new HashMap<String, Object>();
        usuario.put("nome", nome);
        usuario.put("nomeEmail", email);
        usuario.put("nomeSenha", senha);
        db.collection("usuario").add(usuario);
        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("=========", "createUserWithEmail:success");
                    //FirebaseUser user = auth.getCurrentUser();
                    finish();
                    startActivity(intent);
                    //updateUI(user);
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("==========", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                    //updateUI(null);
                }
            }
        });

    }
    private void eventoClick(){
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void inicializarComponentes(){
        entradaNomeCadastro = findViewById(R.id.editNome);
        entradaEmailCadastro = findViewById(R.id.editEmail);
        entradaSenhaCadastro = findViewById(R.id.editSenha);
        botaoVoltar = (Button) findViewById(R.id.buttonVoltar);
        botaoCadastrar = (Button) findViewById(R.id.bttCadastrar);

    }
    private void alert(String msg){
        Toast.makeText(CadastroActivity.this, msg, Toast.LENGTH_LONG);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = this.auth.getCurrentUser();

    }
}
