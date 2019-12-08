package br.com.ufc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import br.com.ufc.adapters.AdpaterImagensImoveis;
import br.com.ufc.data.ImagensImovel;
import br.com.ufc.transactions.Imovel;

public class VerImovel extends AppCompatActivity {

    Imovel imovel;
    public AdpaterImagensImoveis adpater;
    public FirebaseStorage storage;
    public StorageReference storageReference;
    public FirebaseAuth auth;
    public ArrayList<Uri> mArrayUri;
    public FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<ImagensImovel> imagensImovelList;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_imovel);

        inicializarComponentes();

        startImagens();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adpater);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

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
        mArrayUri = new ArrayList<Uri>();
        imagensImovelList = new ArrayList<>();
        imovel = (Imovel) getIntent().getParcelableExtra("imovel");
        adpater = new AdpaterImagensImoveis();
        recyclerView = findViewById(R.id.rc);

    }

    public void startImagens(){
        db.collection("imagensImoveis").whereEqualTo("idDono",imovel.getIdDono()).whereEqualTo("nomeEndereco", imovel.getEndereco()).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {


                if(e != null){
                    Log.w("=======", "A escuta falhou");
                    return;
                }
                Gson gson = new Gson();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    JsonElement jsonElement = gson.toJsonTree(documentSnapshot.getData());
                    ImagensImovel pojo = gson.fromJson(jsonElement,ImagensImovel.class);
                    imagensImovelList.add(pojo);

                }

                Log.d("======================", imagensImovelList.get(0).getCaminhos().get(0).toString());
                createData(imagensImovelList.get(0).getCaminhos());
            }
        });


    }

    public void createData(List<String> data){
        adpater.setData(data);
    }

}
