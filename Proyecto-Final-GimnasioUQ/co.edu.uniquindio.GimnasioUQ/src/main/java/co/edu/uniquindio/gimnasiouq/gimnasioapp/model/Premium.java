package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Premium extends Membresia {

    public Premium() {
    }

    public Premium(TipoMembresiaDuracion tipo, String costo, String fechaInicio,
                   String fechaVencimiento, EstadoMembresia estado) {
        super(tipo, costo, fechaInicio, fechaVencimiento, estado);
    }
}