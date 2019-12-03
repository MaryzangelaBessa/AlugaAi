package br.com.ufc.transactions;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import br.com.ufc.R;

public class LineAdpater extends RecyclerView.Adapter<LineHolder> {
    private final ArrayList<Uri> imgs;

    public LineAdpater(ArrayList<Uri> imgs){
        this.imgs = imgs;

    }

    @NonNull
    @Override
    public LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LineHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_add_imagem_imovel,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LineHolder holder, int position) {
        holder.imageView.setImageURI(imgs.get(position));
    }

    @Override
    public int getItemCount() {
        return imgs != null? imgs.size() : 0;
    }
}
