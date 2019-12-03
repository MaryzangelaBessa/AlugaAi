package br.com.ufc.transactions;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import br.com.ufc.R;

public class LineHolder extends RecyclerView.ViewHolder {
    public ImageView imageView;

    public LineHolder(@NonNull View itemView) {
        super(itemView);
        imageView = (ImageView) itemView.findViewById(R.id.imageView);

    }
}
