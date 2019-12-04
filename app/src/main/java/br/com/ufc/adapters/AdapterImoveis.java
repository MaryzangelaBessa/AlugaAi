package br.com.ufc.adapters;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.ufc.R;
import br.com.ufc.transactions.Imovel;


public class AdapterImoveis extends RecyclerView.Adapter<AdapterImoveis.MyViewHolder> {

    private List<Imovel> listaImoveis;

    public AdapterImoveis(List<Imovel> lista) {
        this.listaImoveis = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_imoveis, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Imovel imovel = listaImoveis.get(position);

        holder.endereco.setText(imovel.getEndereco());
        holder.valor.setText(String.valueOf(imovel.getValor()));
        holder.tipo.setText(imovel.getTipo());

    }

    @Override
    public int getItemCount() {
        return listaImoveis.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView endereco;
        TextView valor;
        TextView tipo;

        public MyViewHolder(View itemView) {
            super(itemView);

            endereco = itemView.findViewById(R.id.textViewEndereco);
            valor = itemView.findViewById(R.id.textViewValor);
            tipo = itemView.findViewById(R.id.textViewTipo);

        }
    }

}


