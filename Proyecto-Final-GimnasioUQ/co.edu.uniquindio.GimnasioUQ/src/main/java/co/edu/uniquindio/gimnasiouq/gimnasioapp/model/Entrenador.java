package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Entrenador {

    private String nombre;
    private String sueldo;
    private String especialidad;
    private String identificacion;

    public Entrenador() {
    }

    public Entrenador(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    public Entrenador(String nombre, String sueldo, String especialidad, String identificacion) {
        this.nombre = nombre;
        this.sueldo = sueldo;
        this.especialidad = especialidad;
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
}