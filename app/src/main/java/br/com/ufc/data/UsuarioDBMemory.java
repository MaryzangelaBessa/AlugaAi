package br.com.ufc.data;

import java.util.ArrayList;

import br.com.ufc.transactions.Usuario;

public class UsuarioDBMemory implements  UsuarioDAO{

    private static ArrayList<Usuario> listaUsuario;
    private static UsuarioDBMemory usuarioDAO;

    private UsuarioDBMemory(){
        listaUsuario = new ArrayList<Usuario>();

    }

    public static UsuarioDAO getInstance(){

        if( usuarioDAO == null ){
            usuarioDAO = new UsuarioDBMemory();
        }

        return usuarioDAO;
    }

    @Override
    public void addUsuario(Usuario c) {
        listaUsuario.add(c);
    }

    @Override
    public void editUsuario(Usuario c) {

    }

    @Override
    public void deleteUsuario(int contatoId) {
        for( int i = 0; i < listaUsuario.size(); i++){

            if( listaUsuario.get(i).getId()  == contatoId ){
                listaUsuario.remove(i);
            }

        }
    }

    @Override
    public Usuario getUsuario(String email) {
        for( Usuario usuarioMemoria: listaUsuario ){

            if( usuarioMemoria.getEmail().equals(email)){
                return usuarioMemoria;
            }

        }

        return null;
    }

    @Override
    public ArrayList<Usuario> getListaUsuario() {
        return listaUsuario;
    }
}
