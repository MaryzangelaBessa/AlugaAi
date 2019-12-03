package br.com.ufc.transactions;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    private Long id;
    private static Long contadorId = 0L;
    private String nome;
    private String email;
    private String senha;

    public Usuario(String nome, String email, String senha){
        this(0L, nome, email, senha);
    }

    public Usuario(Long id, String nome, String email, String senha){
        this.id = id;
        this.nome= nome;
        this.email = email;
        this.senha = senha;
        this.contadorId++;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + "****" + '\'' +
                '}';
    }
}
