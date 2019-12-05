package br.com.ufc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import br.com.ufc.data.UsuarioDAO;

public class CadastroUserActivity extends AppCompatActivity {

    private EditText entradaNomeCadastro, entradaEmailCadastro, entradaSenhaCadastro;
    private Button botaoVoltar, botaoCadastrar,buttonCamera,buttonGaleria;
    private ImageView imageView;
    private UsuarioDAO usuarioDAO;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private FirebaseAuth.AuthStateListener mauthlistener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_user_layout);
        inicializarComponentes();
        mauthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Log.d("AUTH", "signed_in:" + user.getUid());
                }else {
                    Log.d("AUTH", "signed_out:");
                }
            }
        };
        eventoClick();
    }

    public void onClickCadastrar(View view){
        final Intent intent = new Intent(getApplicationContext(),HomeActivity.class );
        String nome = entradaNomeCadastro.getText().toString();
        String email = entradaEmailCadastro.getText().toString();
        String senha = entradaSenhaCadastro.getText().toString();

        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("=========", "createUserWithEmail:success");
                    finish();
                    startActivity(intent);

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("==========", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getApplicationContext(), "Authentication failed. " + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


        HashMap<String,Object> usuario = new HashMap<String, Object>();
        usuario.put("id", auth.getUid());
        usuario.put("nome", nome);

        db.collection("usuario").add(usuario);

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
        //Firebase
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference = storage.getReference().child("FotoPerfil");

        //Layout
        entradaNomeCadastro = findViewById(R.id.editNome);
        entradaEmailCadastro = findViewById(R.id.editEmail);
        entradaSenhaCadastro = findViewById(R.id.editSenha);
        botaoVoltar = (Button) findViewById(R.id.buttonVoltar);
        botaoCadastrar = (Button) findViewById(R.id.bttCadastrar);
        buttonCamera = (Button) findViewById(R.id.buttonCamera);
        buttonGaleria = (Button) findViewById(R.id.buttonGaleria);
        imageView = (ImageView) findViewById(R.id.imageCadastro);
    }
    private void alert(String msg){
        Toast.makeText(CadastroUserActivity.this, msg, Toast.LENGTH_LONG);
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(mauthlistener);

    }

    @Override
    protected void onStop() {
        super.onStop();
        auth.removeAuthStateListener(mauthlistener);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onCamera(View view){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},1000);
        }else{
            tirarFoto();
        }
    }
    public void tirarFoto(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1000);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onGaleria(View view){
        if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            String[] permissao = (Manifest.permission.READ_EXTERNAL_STORAGE).split(" ");
            requestPermissions(permissao, 1001);

        }else{
            escolherImagem();
        }
    }

    private void escolherImagem() {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1001);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == RESULT_OK && requestCode == 1000 ){
            Bundle extras = data.getExtras();
            Bitmap imagem = (Bitmap) extras.get("data");
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imagem.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            byte[] bytes = byteArrayOutputStream.toByteArray();

            StorageReference foto = storageReference.child("images/" + auth.getUid());
            UploadTask uploadTask = foto.putBytes(bytes);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("LOG_TAG","FALHOU");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("LOG_TAG","SUCESSO");
                }
            });
            imageView.setImageBitmap(imagem);
        }else if(resultCode == RESULT_OK && requestCode == 1001 ){

            Uri imagem = data.getData();
            StorageReference foto = storageReference.child("images/" + auth.getUid());
            UploadTask uploadTask = foto.putFile(imagem);

            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("LOG_TAG","FALHOU");
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.d("LOG_TAG","SUCESSO");
                }
            });

            imageView.setImageURI(data.getData());
        }
        HashMap<String, Object> imgs = new HashMap<String, Object>();
        imgs.put("idDono", auth.getUid());
        imgs.put("caminhoImagem","images/" + auth.getUid() );
        //DatabaseReference ref = db.
        db.collection("fotosPerfil").add(imgs);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1001:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    escolherImagem();
                }else{
                    Toast.makeText(this, "Permissão Negada!", Toast.LENGTH_LONG).show();
                }
                break;

            case 1000:

                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    tirarFoto();
                }else{
                    Toast.makeText(this, "Permissão Negada!", Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}
