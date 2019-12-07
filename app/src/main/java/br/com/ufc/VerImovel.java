package br.com.ufc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.annotation.Nullable;

import br.com.ufc.adapters.LineAdpater;
import br.com.ufc.transactions.Imovel;

public class VerImovel extends AppCompatActivity {

    Imovel imovel;
    public LineAdpater adpater;
    public FirebaseStorage storage;
    public StorageReference storageReference;
    public FirebaseAuth auth;
    public ArrayList<Uri> mArrayUri;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imovel);
        inicializarComponentes();
        imovel = (Imovel) getIntent().getParcelableExtra("imovel");

        RecyclerView recyclerView = findViewById(R.id.rc);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adpater = new LineAdpater(mArrayUri);
        recyclerView.setAdapter(adpater);


        TextView nomeProprietario = findViewById(R.id.proprietario);
        nomeProprietario.setText(imovel.getNomeProprietario());
        TextView endereco = findViewById(R.id.endereco);
        endereco.setText(imovel.getEndereco());
        TextView telefone= findViewById(R.id.telefone);
        telefone.setText(imovel.getNomeTelefone());
        TextView valor= findViewById(R.id.preco);
        valor.setText(String.valueOf(imovel.getNomeValor()));
        TextView tempo= findViewById(R.id.tempo);
        tempo.setText(String.valueOf(imovel.getNomeTempo()));
        TextView qtdQuartos= findViewById(R.id.quartos);
        qtdQuartos.setText(String.valueOf(imovel.getQuantidadeQuarto()));
        TextView qtdBanheiros= findViewById(R.id.banheiros);
        qtdBanheiros.setText(String.valueOf(imovel.getQuantidadeBanheiro()));
        TextView garagem= findViewById(R.id.garagem);
        garagem.setText(String.valueOf(imovel.isGaragem()));


    }

    public void inicializarComponentes() {
        //Firebase
        auth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("Imoveis");
        mArrayUri = new ArrayList<>();

        db.collection("imoveis").whereEqualTo("idDono",auth.getUid()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    
            }
        });

        StorageReference foto = storageReference.child("images/imoveis" + auth.getUid());
        foto.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                mArrayUri.add(uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("IMAGEM ERRO", e.getMessage());
            }
        });


    }





    }
