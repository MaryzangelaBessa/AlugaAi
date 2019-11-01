package br.com.ufc.data;

import java.util.ArrayList;

import br.com.ufc.transactions.Usuario;

public class UsuarioDBMemory implements  UsuarioDAO{

    private static ArrayList<Usuario> listaUsuarios;
    private static UsuarioDBMemory usuarioDAO;

    private UsuarioDBMemory(){
        listaUsuarios = new ArrayList<Usuario>();
    }

    public static UsuarioDAO getInstance(){

        if( usuarioDAO == null ){
            usuarioDAO = new UsuarioDBMemory();
        }

        return usuarioDAO;
    }

    @Override
    public void addUsuario(Usuario c) {
        listaUsuarios.add(c);
    }

    @Override
    public void editUsuario(Usuario user) {
        for ( Usuario c : listaUsuarios) {
            if( c.getId().equals(user.getId())){
                c.setNome(user.getNome());
                c.setEmail(user.getEmail());
                c.setSenha(user.getSenha());
            }
        }
    }

    @Override
    public void deleteUsuario(int contatoId) {
        for ( Usuario user : listaUsuarios ) {
            if (user.getId().equals(contatoId)) {
                listaUsuarios.remove(user.getId());
                break;
            }
        }
    }

    @Override
    public Usuario getUsuario(String email) {
        for( Usuario user: listaUsuarios) if( user.getEmail().equals(email)) return user;
        return null;
    }

    @Override
    public ArrayList<Usuario> getListaUsuario() {
        return listaUsuarios;
    }
}
