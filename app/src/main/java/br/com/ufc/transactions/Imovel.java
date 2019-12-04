package br.com.ufc.transactions;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Imovel {

    // Falta os atributos localização, avaliação, lista de reserva
    private Long id;
    private String endereco;
    private String nomeDono;
    private String telefone;
    private String tipo;
    private float valor;
    private int tempo; //em dias
    private List<String> fotos;
    private int quantidadeQuarto;
    private int getQuantidadeBanheiro;
    private boolean garagem;

    public Imovel(Long id, String endereco, String nomeDono, String telefone, String tipo, float valor, int tempo, int quantidadeQuarto, int getQuantidadeBanheiro, boolean garagem) {
        this.id = id;
        this.endereco = endereco;
        this.nomeDono = nomeDono;
        this.telefone = telefone;
        this.tipo = tipo;
        this.valor = valor;
        this.tempo = tempo;
        this.quantidadeQuarto = quantidadeQuarto;
        this.getQuantidadeBanheiro = getQuantidadeBanheiro;
        this.garagem = garagem;
    }

    public Imovel(String endereco, float valor, String tipo){
        this.endereco = endereco;
        this.valor = valor;
        this.tipo = tipo;
    }

    public void addImagem(String foto){
        fotos.add(foto);
    }

}
