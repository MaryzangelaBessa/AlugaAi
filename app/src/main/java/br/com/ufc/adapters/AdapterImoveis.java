package br.com.ufc.adapters;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import br.com.ufc.R;
import br.com.ufc.VerImovel;
import br.com.ufc.transactions.Imovel;


public class AdapterImoveis extends RecyclerView.Adapter<AdapterImoveis.MyViewHolder> {

    private List<Imovel> listaImoveis;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista_imoveis, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.binding(listaImoveis.get(position), holder.listener);
        Imovel imovel = listaImoveis.get(position);

        holder.endereco.setText(imovel.getEndereco());
        holder.valor.setText("R$"+String.valueOf(imovel.getNomeValor()));
        holder.tipo.setText(imovel.getTipo());


    }

    public void setData(List<Imovel> data){
        this.listaImoveis = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return listaImoveis != null? listaImoveis.size():0;
    }


    // ////////////////////////////////////////////////////////////

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView endereco;
        TextView valor;
        TextView tipo;
        Context context;


        public final OnClickListener listener = new OnClickListener() {
            @Override
            public void onItemClick(Imovel imovel) {
                Intent intent = new Intent(context, VerImovel.class);
                intent.putExtra("imovel", imovel);
                context.startActivity(intent);

            }
        };

        public MyViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            endereco = itemView.findViewById(R.id.textViewEndereco);
            valor = itemView.findViewById(R.id.textViewValor);
            tipo = itemView.findViewById(R.id.textViewTipo);


        }

        public void binding(final Imovel imovel, final OnClickListener listener){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(imovel);
                }
            });

        }




    }
    public interface OnClickListener{
        void onItemClick(Imovel imovel);
    }
}


