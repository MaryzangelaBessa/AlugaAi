package br.com.ufc;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import br.com.ufc.transactions.Imovel;
import br.com.ufc.adapters.AdapterImoveis;

public class FragLocatario extends Fragment {

    public FragLocatario() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private List<Imovel> listaImoveis = new ArrayList<Imovel>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;
    FirebaseFirestoreSettings settings;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_locatario_layout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerImoveis);
        settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .setPersistenceEnabled(true)
                .build();

       this.getImoveis();

        AdapterImoveis adapterImoveis = new AdapterImoveis(listaImoveis);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterImoveis);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    public void getImoveis() {

        db.collection("imovel").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if(queryDocumentSnapshots.isEmpty()){
                    Log.w("======", "erro");
                    return;
                }else {
                    List<Imovel> imvs = queryDocumentSnapshots.toObjects(Imovel.class);
                    listaImoveis.addAll(imvs);

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("================", "falhou tudo");
            }
        });
/*
        db.collection("imovel").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                        String end = documentSnapshot.get("nomeProprietario").toString();
                        float valor = Float.parseFloat(documentSnapshot.get("nomeValor").toString());
                        String tipo = documentSnapshot.get("nomeTipo").toString();
                        int quantidadeBanheiros = Integer.parseInt(documentSnapshot.get("quantidadeBanheiros").toString());
                        int quantidadeQuartos = Integer.parseInt(documentSnapshot.get("quantidadeCarros").toString());


                        adicionarImovel(end,valor,tipo);

                        Log.v("========", end);
                    }
                } else {
                    Log.d("LISTA", "ERRRO");
                }
            }
        });



        db.collection("imovel").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.w("=======", "A escuta falhou");
                    return;
                }
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String end = documentSnapshot.get("nomeProprietario").toString();
                    float valor = Float.parseFloat(documentSnapshot.get("nomeValor").toString());
                    String tipo = documentSnapshot.get("nomeTipo").toString();
                    adicionarImovel(end,valor,tipo);

                    Log.v("========", end);

                }
            }
        });


*/
/*
            db.collection("imovel").addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                    if(e != null){
                        Log.w("===========", "deu ruim");
                    }
                    for(DocumentChange change : queryDocumentSnapshots.getDocumentChanges()){
                        String end = change.getDocument().getData().get("nomeProprietario").toString();
                        float valor = Float.parseFloat(change.getDocument().getData().get("nomeValor").toString());
                        String tipo = change.getDocument().getData().get("nomeTipo").toString();

                        adicionarImovel(end,valor,tipo);

                        Log.d("=======>",end);
                    }
                }
            });

 */
/*
        Imovel imovel = new Imovel("Rua do Sossego", 500f, "Casa");
        listaImoveis.add(imovel);

        imovel = new Imovel("Rua Oscar Barbosa", 600f, "Casa");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
        listaImoveis.add(imovel);

        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
        listaImoveis.add(imovel);

 */
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
