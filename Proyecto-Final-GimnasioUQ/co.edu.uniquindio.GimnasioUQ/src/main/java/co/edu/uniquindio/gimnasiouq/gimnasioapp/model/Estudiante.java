package co.edu.uniquindio.gimnasiouq.gimnasioapp.model;

public class Estudiante extends Usuario {

    private String curso;
    private String programa;

    public Estudiante() {
    }

    public Estudiante(String nombre, String identificacion, String edad, String telefono,
                      TipoMembresiaDuracion tipoDeMembresia, String curso, String programa) {

        super(nombre, identificacion, edad, telefono, tipoDeMembresia);
        this.curso = curso;
        this.programa = programa;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }
}