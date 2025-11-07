package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

import java.util.ArrayList;

public class GimnasioUQ {

    private static final GimnasioUQ instancia = new GimnasioUQ();

    public static GimnasioUQ getInstance() {
        return instancia;
    }

    private String nombre;

    private ArrayList<Usuario> listaUsuarios = new ArrayList<>();
    private ArrayList<Membresia> listaMembresias = new ArrayList<>();
    private ArrayList<Clase> listaClases = new ArrayList<>();
    private ArrayList<Entrenador> listaEntrenadores = new ArrayList<>();
    private ArrayList<Reserva> listaReservas = new ArrayList<>();
    private ArrayList<Recepcionista> listaRecepcionistas = new ArrayList<>();
    private ArrayList<Administrador> listaAdministradores = new ArrayList<>();

    public GimnasioUQ() {
    }

    public GimnasioUQ(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public ArrayList<Membresia> getListaMembresias() {
        return listaMembresias;
    }

    public ArrayList<Clase> getListaClases() {
        return listaClases;
    }

    public ArrayList<Entrenador> getEntrenadores() {
        return listaEntrenadores;
    }

    public boolean crearEntrenador(Entrenador entrenador) {
        return listaEntrenadores.add(entrenador);
    }

    public Entrenador buscarEntrenador(String identificacion) {
        for (Entrenador e : listaEntrenadores) {
            if (e.getIdentificacion().equals(identificacion)) {
                return e;
            }
        }
        return null;
    }

    public boolean eliminarEntrenador(String identificacion) {
        Entrenador e = buscarEntrenador(identificacion);
        if (e != null) {
            return listaEntrenadores.remove(e);
        }
        return false;
    }

    public boolean actualizarEntrenador(Entrenador entrenadorActualizado) {
        Entrenador existente = buscarEntrenador(entrenadorActualizado.getIdentificacion());
        if (existente != null) {
            existente.setNombre(entrenadorActualizado.getNombre());
            existente.setSueldo(entrenadorActualizado.getSueldo());
            existente.setEspecialidad(entrenadorActualizado.getEspecialidad());
            return true;
        }
        return false;
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public ArrayList<Recepcionista> getListaRecepcionistas() {
        return listaRecepcionistas;
    }

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public Administrador buscarAdministrador(String username) {
        for (Administrador a : listaAdministradores) {
            if (a.getUsername().equals(username)) {
                return a;
            }
        }
        return null;
    }

    public Recepcionista buscarRecepcionista(String username) {
        for (Recepcionista r : listaRecepcionistas) {
            if (r.getUsername().equals(username)) {
                return r;
            }
        }
        return null;
    }



// CRUD USUARIOS


    public boolean crearUsuario(Usuario usuario) {
        return listaUsuarios.add(usuario);
    }

    public Usuario buscarUsuario(String identificacion) {
        for (Usuario u : listaUsuarios) {
            if (u.getIdentificacion().equals(identificacion)) {
                return u;
            }
        }
        return null;
    }

    public boolean eliminarUsuario(String identificacion) {
        Usuario u = buscarUsuario(identificacion);
        if (u != null) {
            return listaUsuarios.remove(u);
        }
        return false;
    }

    public boolean actualizarUsuario(Usuario usuarioActualizado) {
        Usuario existente = buscarUsuario(usuarioActualizado.getIdentificacion());
        if (existente != null) {

            existente.setNombre(usuarioActualizado.getNombre());
            existente.setEdad(usuarioActualizado.getEdad());
            existente.setTelefono(usuarioActualizado.getTelefono());
            existente.setTipoDeMembresia(usuarioActualizado.getTipoDeMembresia());

            return true;
        }
        return false;
    }


    // ============================================================
//                     CRUD RESERVAS
// ============================================================

    public boolean crearReserva(Reserva reserva) {
        Usuario usuario = reserva.getUsuario();
        if (usuario.getTipoMembresia() != TipoMembresia.PREMIUM && usuario.getTipoMembresia() != TipoMembresia.VIP) {
            return false; // Membresía no válida
        }

        Clase clase = buscarClase(reserva.getNombreClase());
        if (clase == null) {
            return false; // Clase no encontrada
        }

        long count = listaReservas.stream().filter(r -> r.getNombreClase().equals(reserva.getNombreClase())).count();
        if (count >= Integer.parseInt(clase.getCupoMaximo())) {
            return false; // Clase llena
        }

        return listaReservas.add(reserva);
    }

    public boolean cancelarReserva(String codigoReserva) {
        Reserva reserva = buscarReserva(codigoReserva);
        if (reserva != null) {
            return listaReservas.remove(reserva);
        }
        return false;
    }

    public Reserva buscarReserva(String codigoReserva) {
        for (Reserva r : listaReservas) {
            if (r.getCodigoReserva().equals(codigoReserva)) {
                return r;
            }
        }
        return null;
    }

    public Clase buscarClase(String nombreClase) {
        for (Clase c : listaClases) {
            if (c.getNombre().equals(nombreClase)) {
                return c;
            }
        }
        return null;
    }

}
