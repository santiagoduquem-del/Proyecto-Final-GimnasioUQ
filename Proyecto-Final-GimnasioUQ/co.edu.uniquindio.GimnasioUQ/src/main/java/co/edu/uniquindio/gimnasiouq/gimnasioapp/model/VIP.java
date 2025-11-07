package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class VIP extends Membresia {

    public VIP() {
    }

    public VIP(TipoMembresiaDuracion tipo, String costo, String fechaInicio,
               String fechaVencimiento, EstadoMembresia estado) {
        super(tipo, costo, fechaInicio, fechaVencimiento, estado);
    }
}