package br.com.ufc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import br.com.ufc.data.ImagemPerfil;

public class Perfil_Usuario extends AppCompatActivity {

    private EditText editTextemail;
    private Button buttonEditar, buttonApagar;
    private ImageView imageView;

    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    public FirebaseAuth auth;
    public FirebaseStorage storage;
    public StorageReference storageReference;
    public FirebaseAuth.AuthStateListener mauthlistener;
    public DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__usuario);
        inicializarComponentes();

        mauthlistener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("AUTH", "signed_in:" + user.getUid());
                } else {
                    Log.d("AUTH", "signed_out:");
                }
            }
        };
    }


    public void inicializarComponentes()  {
        //Firebase

        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        db = FirebaseFirestore.getInstance();
        storageReference = storage.getReference().child("FotoPerfil");

        //Layout


        editTextemail = (EditText) findViewById(R.id.editTextEmail);
        editTextemail.setText(auth.getCurrentUser().getEmail());
        buttonEditar = (Button) findViewById(R.id.buttonEditar);
        buttonApagar = (Button) findViewById(R.id.buttonApagarConta);
        imageView = (ImageView) findViewById(R.id.imageView2);

        StorageReference foto = storageReference.child("images/" + auth.getUid());
       foto.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //imageView.setImageURI(uri);
                Picasso.with(getApplicationContext()).load(uri).into(imageView);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("IMAGEM ERRO", e.getMessage());
            }
        });


    }


}
