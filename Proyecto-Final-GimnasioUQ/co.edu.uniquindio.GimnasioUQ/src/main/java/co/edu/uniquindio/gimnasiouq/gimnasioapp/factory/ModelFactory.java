package co.edu.uniquindio.gimnasiouq.gimnasioapp.factory;


import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Clase;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.GimnasioUQ;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Reserva;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.utils.DataUtil;

import java.util.List;

public class ModelFactory {

    // Instancia Singleton
    private static ModelFactory instancia;

    // Modelo principal
    private GimnasioUQ gimnasio;

    // Método para obtener la instancia
    public static ModelFactory getInstancia() {
        if (instancia == null) {
            instancia = new ModelFactory();
        }
        return instancia;
    }

// ============================================================
    //          MÉTODOS PARA ACTUALIZAR DATOS EN TIEMPO REAL
    // ============================================================

    /**
     * Actualiza la lista de usuarios (para cuando se crean nuevos)
     */
    public void actualizarListaUsuarios() {
        // Este método fuerza la actualización de las listas
        // No necesita hacer nada especial porque las listas son las mismas
        System.out.println("=== Lista de usuarios actualizada ===");
        System.out.println("Total usuarios: " + gimnasio.getListaUsuarios().size());
    }

    /**
     * Obtiene la lista MÁS RECIENTE de usuarios
     */
    public List<Usuario> obtenerUsuariosActualizados() {
        return gimnasio.getListaUsuarios();
    }

    /**
     * Obtiene la lista MÁS RECIENTE de clases
     */
    public List<Clase> obtenerClasesActualizadas() {
        return gimnasio.getListaClases();
    }

    // Constructor privado
    private ModelFactory() {
        gimnasio = DataUtil.inicializarDatos();
    }

    // ============================================================
    //                     CRUD USUARIOS
    // ============================================================

    public List<Usuario> obtenerUsuarios() {
        return gimnasio.getListaUsuarios();
    }

    // CORREGIDO: Cambiar tipo de retorno a boolean
    public boolean crearUsuario(Usuario usuario) {
        return gimnasio.crearUsuario(usuario);
    }

    // AGREGAR ESTOS MÉTODOS:
    public boolean actualizarUsuario(Usuario usuario) {
        return gimnasio.actualizarUsuario(usuario);
    }

    public boolean eliminarUsuario(String identificacion) {
        return gimnasio.eliminarUsuario(identificacion);
    }

    public Usuario buscarUsuario(String identificacion) {
        return gimnasio.buscarUsuario(identificacion);
    }

    // ============================================================
//                     CRUD RESERVAS
// ============================================================

    public List<Reserva> obtenerReservas() {
        return gimnasio.getListaReservas();
    }

    public boolean crearReserva(Reserva reserva) {
        return gimnasio.crearReserva(reserva);
    }

    public boolean cancelarReserva(String codigoReserva) {
        return gimnasio.cancelarReserva(codigoReserva);
    }

// ============================================================
//                   DATOS PARA COMBOBOX
// ============================================================

    public List<Clase> obtenerClases() {
        return gimnasio.getListaClases();
    }

}