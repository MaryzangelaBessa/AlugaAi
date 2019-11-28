package br.com.ufc.transactions;

import android.widget.ImageView;

import java.sql.Blob;
import java.util.ArrayList;

public class Imovel {

    private Long id;
    private String nomeDono;
    private String telefone;
    private String tipo;
    private float valor;
    private int tempo; // em dias
    private ArrayList<ImageView> fotos;
    private int quantidadeQuarto;
    private int getQuantidadeBanheiro;
    private boolean garagem;
    // Falta os atributos localização, avaliação, lista de reserva



    public Imovel(Long id, String nomeDono, String telefone, String tipo, float valor, int tempo, int quantidadeQuarto, int getQuantidadeBanheiro, boolean garagem) {
        this.id = id;
        this.nomeDono = nomeDono;
        this.telefone = telefone;
        this.tipo = tipo;
        this.valor = valor;
        this.tempo = tempo;
        this.quantidadeQuarto = quantidadeQuarto;
        this.getQuantidadeBanheiro = getQuantidadeBanheiro;
        this.garagem = garagem;
        this.fotos = new ArrayList<ImageView>();
    }

    public void addImagem(ImageView foto){
        fotos.add(foto);
    }
    public String getNomeDono() {
        return nomeDono;
    }

    public void setNomeDono(String nomeDono) {
        this.nomeDono = nomeDono;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public ArrayList<ImageView> getFotos() {
        return fotos;
    }

    public void setFotos(ArrayList<ImageView> fotos) {
        this.fotos = fotos;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getQuantidadeQuarto() {
        return quantidadeQuarto;
    }

    public void setQuantidadeQuarto(int quantidadeQuarto) {
        this.quantidadeQuarto = quantidadeQuarto;
    }

    public int getGetQuantidadeBanheiro() {
        return getQuantidadeBanheiro;
    }

    public void setGetQuantidadeBanheiro(int getQuantidadeBanheiro) {
        this.getQuantidadeBanheiro = getQuantidadeBanheiro;
    }

    public boolean isGaragem() {
        return garagem;
    }

    public void setGaragem(boolean garagem) {
        this.garagem = garagem;
    }

}
