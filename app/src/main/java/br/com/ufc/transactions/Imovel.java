package br.com.ufc.transactions;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Imovel {

    // Falta os atributos localização, avaliação, lista de reserva
    private Long id;
    private String endereço;
    private String nomeDono;
    private String telefone;
    private String tipo;
    private float valor;
    private int tempo; //em dias
    private List<String> fotos;
    private int quantidadeQuarto;
    private int getQuantidadeBanheiro;
    private boolean garagem;

    public Imovel(Long id, String endereço, String nomeDono, String telefone, String tipo, float valor, int tempo, int quantidadeQuarto, int getQuantidadeBanheiro, boolean garagem) {
        this.id = id;
        this.endereço = endereço;
        this.nomeDono = nomeDono;
        this.telefone = telefone;
        this.tipo = tipo;
        this.valor = valor;
        this.tempo = tempo;
        this.quantidadeQuarto = quantidadeQuarto;
        this.getQuantidadeBanheiro = getQuantidadeBanheiro;
        this.garagem = garagem;
    }

    public void addImagem(String foto){
        fotos.add(foto);
    }

}
