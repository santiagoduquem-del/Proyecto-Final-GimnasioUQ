package co.edu.uniquindio.gimnasiouq.gimnasioapp.controller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;

import java.util.List;

public class UsuarioController {

    ModelFactory modelFactory;

    public UsuarioController() {
        this.modelFactory = ModelFactory.getInstancia();
    }

    public List<Usuario> obtenerUsuarios() {
        return modelFactory.obtenerUsuarios();
    }

    // CORREGIDO: Cambiar tipo de retorno a boolean
    public boolean crearUsuario(Usuario usuario) {
        return modelFactory.crearUsuario(usuario);
    }

    public boolean actualizarUsuario(Usuario usuario) {
        return modelFactory.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(String identificacion) {
        return modelFactory.eliminarUsuario(identificacion);
    }

    public Usuario buscarUsuario(String identificacion) {
        return modelFactory.buscarUsuario(identificacion);
    }



    /**
     * Notifica que se creó un nuevo usuario
     */
    public void notificarNuevoUsuario() {
        modelFactory.actualizarListaUsuarios();
    }



}
