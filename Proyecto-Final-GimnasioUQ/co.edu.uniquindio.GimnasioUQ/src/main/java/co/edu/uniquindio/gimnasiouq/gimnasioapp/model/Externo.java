package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Externo extends Usuario {

    private String universidadEmpresa;

    public Externo() {
    }

    public Externo(String nombre, String identificacion, String edad, String telefono,
                   TipoMembresiaDuracion tipoDeMembresia, TipoMembresia tipoMembresia, String universidadEmpresa) {

        super(nombre, identificacion, edad, telefono, tipoDeMembresia, tipoMembresia);
        this.universidadEmpresa = universidadEmpresa;
    }

    public String getUniversidadEmpresa() {
        return universidadEmpresa;
    }

    public void setUniversidadEmpresa(String universidadEmpresa) {
        this.universidadEmpresa = universidadEmpresa;
    }
}
