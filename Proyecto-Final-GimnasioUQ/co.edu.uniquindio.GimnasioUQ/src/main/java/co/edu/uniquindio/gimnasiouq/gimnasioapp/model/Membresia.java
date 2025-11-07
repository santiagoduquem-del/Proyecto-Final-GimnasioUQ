package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public abstract class Membresia {

    private TipoMembresiaDuracion tipo;
    private String costo;
    private String fechaInicio;
    private String fechaVencimiento;
    private EstadoMembresia estado;

    public Membresia() {
    }

    public Membresia(TipoMembresiaDuracion tipo, String costo, String fechaInicio,
                     String fechaVencimiento, EstadoMembresia estado) {
        this.tipo = tipo;
        this.costo = costo;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.estado = estado;
    }

    public TipoMembresiaDuracion getTipo() {
        return tipo;
    }

    public void setTipo(TipoMembresiaDuracion tipo) {
        this.tipo = tipo;
    }

    public String getCosto() {
        return costo;
    }

    public void setCosto(String costo) {
        this.costo = costo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public EstadoMembresia getEstado() {
        return estado;
    }

    public void setEstado(EstadoMembresia estado) {
        this.estado = estado;
    }
}