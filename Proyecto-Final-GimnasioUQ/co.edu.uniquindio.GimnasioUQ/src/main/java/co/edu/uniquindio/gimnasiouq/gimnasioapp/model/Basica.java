package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;


public class Basica extends Membresia {

    public Basica() {
        switch (getTipo()) {
            case MENSUAL:
                setCosto("50");
                break;
            case TRIMESTRAL:
                setCosto("135");
                break;
            case ANUAL:
                setCosto("500");
                break;
        }
    }

    public Basica(TipoMembresiaDuracion tipo, String costo, String fechaInicio,
                  String fechaVencimiento, EstadoMembresia estado) {
        super(tipo, costo, fechaInicio, fechaVencimiento, estado);
    }
}