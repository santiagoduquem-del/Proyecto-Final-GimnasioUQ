package co.edu.uniquindio.gimnasiouq.gimnasioapp.controller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Entrenador;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.GimnasioUQ;

import java.util.List;

public class EntrenadorController {

    private GimnasioUQ gimnasio;

    public EntrenadorController() {
        this.gimnasio = GimnasioUQ.getInstance();
    }

    public List<Entrenador> obtenerEntrenadores() {
        return gimnasio.getEntrenadores();
    }

    public boolean crearEntrenador(Entrenador entrenador) {
        return gimnasio.crearEntrenador(entrenador);
    }

    public boolean actualizarEntrenador(Entrenador entrenador) {
        return gimnasio.actualizarEntrenador(entrenador);
    }

    public boolean eliminarEntrenador(String identificacion) {
        return gimnasio.eliminarEntrenador(identificacion);
    }
}
