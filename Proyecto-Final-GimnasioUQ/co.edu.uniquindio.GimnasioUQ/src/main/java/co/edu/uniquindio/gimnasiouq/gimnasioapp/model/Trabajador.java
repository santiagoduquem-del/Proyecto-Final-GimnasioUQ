package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Trabajador extends Usuario {

    private String tipoDeCargo;

    public Trabajador() {
    }

    public Trabajador(String nombre, String identificacion, String edad, String telefono,
                      TipoMembresiaDuracion tipoDeMembresia, String tipoDeCargo) {

        super(nombre, identificacion, edad, telefono, tipoDeMembresia);
        this.tipoDeCargo = tipoDeCargo;
    }

    public String getTipoDeCargo() {
        return tipoDeCargo;
    }

    public void setTipoDeCargo(String tipoDeCargo) {
        this.tipoDeCargo = tipoDeCargo;
    }
}