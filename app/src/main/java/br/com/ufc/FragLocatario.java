package br.com.ufc;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import br.com.ufc.transactions.Imovel;
import br.com.ufc.adapters.AdapterImoveis;

public class FragLocatario extends Fragment {

    public FragLocatario() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private List<Imovel> listaImoveis = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_locatario_layout, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerImoveis);

        this.getImoveis();

        AdapterImoveis adapterImoveis = new AdapterImoveis(listaImoveis);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(adapterImoveis);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }

    public void getImoveis(){


        CollectionReference imoveis = db.collection("imovel");



//        List<Imovel> imoveis = (List<Imovel>) db.collection("imovel");

//        for (Imovel imovel: imoveis) {
//            listaImoveis.add(imovel);
//        }

//        Imovel imovel = new Imovel("Rua do Sossego", 500f, "Casa");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Rua Oscar Barbosa", 600f, "Casa");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 3", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 4", 123f, "Apartamento");
//        listaImoveis.add(imovel);
//
//        imovel = new Imovel("Endereço 5", 123f, "Apartamento");
//        listaImoveis.add(imovel);
    }



}
