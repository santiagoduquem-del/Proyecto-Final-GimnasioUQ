package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;


public class Basica extends Membresia {

    public Basica() {
    }

    public Basica(TipoMembresiaDuracion tipo, String costo, String fechaInicio,
                  String fechaVencimiento, EstadoMembresia estado) {
        super(tipo, costo, fechaInicio, fechaVencimiento, estado);
    }
}