package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Reserva {

    private String codigoReserva;
    private Usuario usuario;
    private String nombreClase;
    private String fecha;
    private String hora;

    public Reserva() {
    }

    public Reserva(String codigoReserva, Usuario usuario, String nombreClase, String fecha, String hora) {
        this.codigoReserva = codigoReserva;
        this.usuario = usuario;
        this.nombreClase = nombreClase;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getNombreUsuario() {
        return usuario.getNombre();
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}