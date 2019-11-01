package br.com.ufc.transactions;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private static int contadorId = 0;
    private int id;
    public Usuario(){

    }

    public Usuario(String nome, String email, String senha){
        this.nome= nome;
        this.email = email;
        this.senha = senha;
        this.contadorId++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static int getContadorId() {
        return contadorId;
    }

    public static void setContadorId(int contadorId) {
        Usuario.contadorId = contadorId;
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

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
}
