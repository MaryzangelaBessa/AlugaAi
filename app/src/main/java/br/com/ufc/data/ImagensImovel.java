package br.com.ufc.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImagensImovel implements Parcelable {

    @SerializedName("idDono")
    private String idDono;
    @SerializedName("caminhoImagem")
    private List<String> caminhoImagem;


    public ImagensImovel(String idDono, List<String> caminhos) {
        this.idDono = idDono;
        this.caminhoImagem = caminhos;
    }


    protected ImagensImovel(Parcel in) {
        idDono = in.readString();
        caminhoImagem = in.createStringArrayList();
    }

    public static final Creator<ImagensImovel> CREATOR = new Creator<ImagensImovel>() {
        @Override
        public ImagensImovel createFromParcel(Parcel in) {
            return new ImagensImovel(in);
        }

        @Override
        public ImagensImovel[] newArray(int size) {
            return new ImagensImovel[size];
        }
    };

    public String getIdDono() {
        return idDono;
    }

    public void setIdDono(String idDono) {
        this.idDono = idDono;
    }

    public List<String> getCaminhos() {
        return caminhoImagem;
    }

    public void setCaminhos(List<String> caminhos) {
        this.caminhoImagem = caminhos;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idDono);
        dest.writeStringList(caminhoImagem);
    }

    @Override
    public String toString() {
        return "ImagensImovel{" +
                "idDono='" + idDono + '\'' +
                ", caminhoImagem=" + caminhoImagem +
                '}';
    }
}
