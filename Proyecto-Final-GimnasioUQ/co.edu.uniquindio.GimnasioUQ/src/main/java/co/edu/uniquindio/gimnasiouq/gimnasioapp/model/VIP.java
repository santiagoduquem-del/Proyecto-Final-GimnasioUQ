package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class VIP extends Membresia {

    public VIP() {
        switch (getTipo()) {
            case MENSUAL:
                setCosto("120");
                break;
            case TRIMESTRAL:
                setCosto("330");
                break;
            case ANUAL:
                setCosto("1200");
                break;
        }
    }

    public VIP(TipoMembresiaDuracion tipo, String costo, String fechaInicio,
               String fechaVencimiento, EstadoMembresia estado) {
        super(tipo, costo, fechaInicio, fechaVencimiento, estado);
    }
}