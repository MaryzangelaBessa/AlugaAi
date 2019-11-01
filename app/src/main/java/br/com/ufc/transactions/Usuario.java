package br.com.ufc.transactions;

public class Usuario {

    private Long id;
    private static Long contadorId = 0L;
    private String nome;
    private String email;
    private String senha;

    public Usuario(){}

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Long getContadorId() {
        return contadorId;
    }

    public static void setContadorId(Long contadorId) {
        Usuario.contadorId = contadorId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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
