package br.com.ufc.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import br.com.ufc.R;
import br.com.ufc.VerImovel;


public class AdpaterImagensImoveis extends RecyclerView.Adapter<AdpaterImagensImoveis.ViewHolder> {
    private List<String> imgs;
    public StorageReference storageReference;
    public FirebaseStorage storage;
    public ArrayList<Uri> mArrayUri;

    public AdpaterImagensImoveis(){
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference().child("Imoveis");
        mArrayUri = new ArrayList<Uri>();

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ver_imagens_imoveis,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
            StorageReference foto = storageReference.child(imgs.get(position).toString());
            foto.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                @Override
                public void onSuccess(Uri uri) {
                    Glide.with(holder.context).load(uri).into(holder.imageView);
                    Log.d("=========", "aquii");
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d("IMAGEM ERRO", e.getMessage());
                }
            });
    }


    @Override
    public int getItemCount() {
        return imgs != null ? imgs.size() : 0;
    }

    public void setData(List<String> data) {
        this.imgs = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            imageView = itemView.findViewById(R.id.img);

        }

    }

}