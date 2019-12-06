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
    private String  idDono;
    //private String endereco;
    private String nomeProprietario;
    private String nomeTelefone;
    private String tipo;
    private float nomeValor;
    private int nomeTempo; //em dias
   // private List<String> fotos;
    private int quantidadeQuarto;
    private int quantidadeBanheiro;
    private boolean garagem;

    public Imovel(String idDono, String nomeProprietario, String nomeTelefone, String tipo, float nomeValor,
                  int nomeTempo, int quantidadeQuarto, int quantidadeBanheiro, boolean garagem) {
        this.idDono = idDono;
        this.nomeProprietario = nomeProprietario;
        this.nomeTelefone = nomeTelefone;
        this.tipo = tipo;
        this.nomeValor = nomeValor;
        this.nomeTempo = nomeTempo;
        this.quantidadeQuarto = quantidadeQuarto;
        this.quantidadeBanheiro = quantidadeBanheiro;
        this.garagem = garagem;
    }
/*
    public Imovel(String nomeProprietario, float nomeValor, String tipo){
        this.nomeProprietario = nomeProprietario;
        this.nomeValor = nomeValor;
        this.tipo = tipo;
    }
*/

}
