package br.com.ufc;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerViewAccessibilityDelegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.ufc.transactions.Imovel;

public class FragLocatario extends Fragment {

    public FragLocatario() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.frag_locatario_layout, container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view_Imoveis);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        Imovel imoveis[] = {
                new Imovel(1L, "endTest", "nomeDonoTest", "88996457874", "casa", 750, 14, 1, 1, false),
        };

//        MyAdapter mAdapter = new MyAdapter(imoveis);
//        recyclerView.setAdapter(mAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return rootView;
    }



}
