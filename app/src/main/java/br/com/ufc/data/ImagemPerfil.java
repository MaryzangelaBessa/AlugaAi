package br.com.ufc.data;

import android.net.Uri;

import java.net.URI;
import java.util.ArrayList;

public class ImagemPerfil {
    private String id;
    private Uri caminho;

    public ImagemPerfil(String id, Uri caminho) {
        this.id = id;
        this.caminho = caminho;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Uri getCaminho() {
        return caminho;
    }

    public void setCaminho(Uri caminho) {
        this.caminho = caminho;
    }
}
