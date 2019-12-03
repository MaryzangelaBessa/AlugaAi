package br.com.ufc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import br.com.ufc.data.UsuarioDAO;

public class MainActivity extends AppCompatActivity {

    private Button botaoEntrar;
    private TextView textCadastrar;
    private UsuarioDAO usuarioDAO;
    FirebaseFirestore db;
    FirebaseAuth auth;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        FirebaseApp.initializeApp(MainActivity.this);
        this.auth = FirebaseAuth.getInstance();
        final Intent intent = new Intent(this, HomeActivity.class);
        final EditText email = findViewById(R.id.editEmail);
        final EditText senha = findViewById(R.id.editSenha);

        final Activity activity = (Activity) this;
        this.db = FirebaseFirestore.getInstance();
        inicializarComponentes();
        botaoEntrar = (Button) findViewById(R.id.btEntrarID);
        botaoEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(email.getText().toString(), senha.getText().toString())
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                               // Log.d("===========", task.isSuccessful()+"");
                                if (task.isSuccessful()) {
                                    finish();
                                    startActivity(intent);
                                } else {
                                    Log.d("===========", task.getException().getMessage());
                                    Toast.makeText(MainActivity.this, "Authentication failed.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        super.startActivityForResult(intent, requestCode);

    }


    public void inicializarComponentes(){
       textCadastrar = (TextView) findViewById(R.id.textCadastrar);
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = this.auth.getCurrentUser();

    }

    public  void alert(String s){
        Toast.makeText(this, s,Toast.LENGTH_LONG).show();
    }


    public void onClickCadastro(View view){
        Intent intent = new Intent(getApplicationContext(), CadastroUserActivity.class);
        startActivity(intent);
    }
}
