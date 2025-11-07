package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Clase {

    private String nombre;
    private TipoClase tipo;
    private String horario;
    private String cupoMaximo;
    private Entrenador entrenador;

    public Clase() {
    }

    public Clase(String nombre, TipoClase tipo, String horario, String cupoMaximo, Entrenador entrenador) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.horario = horario;
        this.cupoMaximo = cupoMaximo;
        this.entrenador = entrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoClase getTipo() {
        return tipo;
    }

    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(String cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }
}