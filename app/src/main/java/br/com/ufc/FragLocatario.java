package br.com.ufc;


import android.os.Bundle;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

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
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth auth;
    FirebaseFirestoreSettings settings;
    private AdapterImoveis adapterImoveis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_locatario_layout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerImoveis);

        this.getImoveis();
        return rootView;
    }

    public void getImoveis() {

        this.setupRecycler();
        db.collection("imovel").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if(e != null){
                    Log.w("=======", "A escuta falhou");
                    return;
                }

                List<Imovel> imoveis = new ArrayList<>();
                Gson gson = new Gson();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    JsonElement jsonElement = gson.toJsonTree(documentSnapshot.getData());
                    Imovel pojo = gson.fromJson(jsonElement,Imovel.class);
                    imoveis.add(pojo);
                }

                createData(imoveis);
            }
        });

    }

    public void createData(List<Imovel> data){
        adapterImoveis.setData(data);
    }

    public void setupRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        adapterImoveis = new AdapterImoveis();
        recyclerView.setAdapter(adapterImoveis);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void onStart() {
        super.onStart();
    }
}
