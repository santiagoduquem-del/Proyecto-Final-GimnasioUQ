package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Premium extends Membresia {

    public Premium() {
        switch (getTipo()) {
            case MENSUAL:
                setCosto("80");
                break;
            case TRIMESTRAL:
                setCosto("220");
                break;
            case ANUAL:
                setCosto("800");
                break;
        }
    }

    public Premium(TipoMembresiaDuracion tipo, String costo, String fechaInicio,
                   String fechaVencimiento, EstadoMembresia estado) {
        super(tipo, costo, fechaInicio, fechaVencimiento, estado);
    }
}