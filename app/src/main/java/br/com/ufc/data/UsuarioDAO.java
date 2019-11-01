package br.com.ufc.data;

import java.util.ArrayList;

import br.com.ufc.transactions.Usuario;

public interface UsuarioDAO {

    public void addUsuario( Usuario c);
    public void editUsuario( Usuario c );
    public void deleteUsuario( int contatoId );
    public Usuario getUsuario( String email );
    public ArrayList<Usuario> getListaUsuario();
}
