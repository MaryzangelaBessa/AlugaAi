package br.com.ufc.transactions;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class Imovel implements Parcelable {

    // Falta os atributos localização, avaliação, lista de reserva ;
    @SerializedName("idDono")
    private String  idDono;
    @SerializedName("nomeEndereco")
    private String nomeEndereco;
    @SerializedName("nomeProprietario")
    private String nomeProprietario;
    @SerializedName("nomeTelefone")
    private String nomeTelefone;
    @SerializedName("nomeTipo")
    private String nomeTipo;
    @SerializedName("nomeValor")
    private float nomeValor;
    @SerializedName("nomeTempo")
    private int nomeTempo; //em dias
    @SerializedName("quantidadeQuartos")
    private int quantidadeQuartos;
    @SerializedName("quantidadeBanheiros")
    private int quantidadeBanheiros;
    @SerializedName("garagem")
    private boolean garagem;

    public Imovel(String idDono, String nomeEndereco, String nomeProprietario, String nomeTelefone, String nomeTipo, float nomeValor,
                  int nomeTempo, int quantidadeQuarto, int quantidadeBanheiro, boolean garagem) {

        this.idDono = idDono;
        this.nomeEndereco = nomeEndereco;
        this.nomeProprietario = nomeProprietario;
        this.nomeTelefone = nomeTelefone;
        this.nomeTipo = nomeTipo;
        this.nomeValor = nomeValor;
        this.nomeTempo = nomeTempo;
        this.quantidadeQuartos = quantidadeQuarto;
        this.quantidadeBanheiros= quantidadeBanheiro;
        this.garagem = garagem;
    }

    protected Imovel(Parcel in) {
        idDono = in.readString();
        nomeEndereco = in.readString();
        nomeProprietario = in.readString();
        nomeTelefone = in.readString();
        nomeTipo = in.readString();
        nomeValor = in.readFloat();
        nomeTempo = in.readInt();
        quantidadeQuartos = in.readInt();
        quantidadeBanheiros = in.readInt();
        garagem = in.readByte() != 0;
    }

    public static final Creator<Imovel> CREATOR = new Creator<Imovel>() {
        @Override
        public Imovel createFromParcel(Parcel in) {
            return new Imovel(in);
        }

        @Override
        public Imovel[] newArray(int size) {
            return new Imovel[size];
        }
    };

    public String getIdDono() {
        return idDono;
    }

    public void setIdDono(String idDono) {
        this.idDono = idDono;
    }

    public String getEndereco() {
        return nomeEndereco;
    }

    public void setEndereco(String endereco) {
        this.nomeEndereco = endereco;
    }

    public String getNomeProprietario() {
        return nomeProprietario;
    }

    public void setNomeProprietario(String nomeProprietario) {
        this.nomeProprietario = nomeProprietario;
    }

    public String getNomeTelefone() {
        return nomeTelefone;
    }

    public void setNomeTelefone(String nomeTelefone) {
        this.nomeTelefone = nomeTelefone;
    }

    public String getTipo() {
        return nomeTipo;
    }

    public void setTipo(String tipo) {
        this.nomeTipo = tipo;
    }

    public float getNomeValor() {
        return nomeValor;
    }

    public void setNomeValor(float nomeValor) {
        this.nomeValor = nomeValor;
    }

    public int getNomeTempo() {
        return nomeTempo;
    }

    public void setNomeTempo(int nomeTempo) {
        this.nomeTempo = nomeTempo;
    }

    public int getQuantidadeQuarto() {
        return quantidadeQuartos;
    }

    public void setQuantidadeQuarto(int quantidadeQuarto) {
        this.quantidadeQuartos = quantidadeQuarto;
    }

    public int getQuantidadeBanheiro() {
        return quantidadeBanheiros;
    }

    public void setQuantidadeBanheiro(int quantidadeBanheiro) {
        this.quantidadeBanheiros = quantidadeBanheiro;
    }

    public boolean isGaragem() {
        return garagem;
    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idDono);
        dest.writeString(nomeEndereco);
        dest.writeString(nomeProprietario);
        dest.writeString(nomeTelefone);
        dest.writeString(nomeTipo);
        dest.writeFloat(nomeValor);
        dest.writeInt(nomeTempo);
        dest.writeInt(quantidadeQuartos);
        dest.writeInt(quantidadeBanheiros);
        dest.writeByte((byte) (garagem ? 1 : 0));
    }

    @Override
    public String toString() {
        return "Imovel{" +
                "idDono='" + idDono + '\'' +
                ", endereco='" + nomeEndereco + '\'' +
                ", nomeProprietario='" + nomeProprietario + '\'' +
                ", nomeTelefone='" + nomeTelefone + '\'' +
                ", tipo='" + nomeTipo + '\'' +
                ", nomeValor=" + nomeValor +
                ", nomeTempo=" + nomeTempo +
                ", quantidadeQuartos=" + quantidadeQuartos +
                ", quantidadeBanheiros=" + quantidadeBanheiros +
                ", garagem=" + garagem +
                '}';
    }
}
