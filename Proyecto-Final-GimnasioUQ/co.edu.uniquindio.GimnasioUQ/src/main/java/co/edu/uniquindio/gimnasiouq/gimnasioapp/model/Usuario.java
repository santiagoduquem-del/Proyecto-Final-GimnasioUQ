package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public abstract class Usuario {

    private String nombre;
    private String identificacion;
    private String edad;
    private String telefono;
    private TipoMembresiaDuracion tipoDeMembresia;
    private TipoMembresia tipoMembresia;
    private Membresia membresia;

    public Usuario() {
    }

    public Usuario(String nombre, String identificacion, String edad, String telefono,
                   TipoMembresiaDuracion tipoDeMembresia, TipoMembresia tipoMembresia) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.edad = edad;
        this.telefono = telefono;
        this.tipoDeMembresia = tipoDeMembresia;
        this.tipoMembresia = tipoMembresia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public TipoMembresiaDuracion getTipoDeMembresia() {
        return tipoDeMembresia;
    }

    public void setTipoDeMembresia(TipoMembresiaDuracion tipoDeMembresia) {
        this.tipoDeMembresia = tipoDeMembresia;
    }

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }
}