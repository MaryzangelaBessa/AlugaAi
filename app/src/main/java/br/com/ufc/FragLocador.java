package br.com.ufc;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragLocador extends Fragment {

    public FragLocador() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.frag_locador_layout, container, false);
    }

    public void onCLickCreateCadastro(View view){

        Intent intent = new Intent(getActivity(), AddEditarActivity.class);
        startActivity(intent);
    }

}
