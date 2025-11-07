package co.edu.uniquindio.gimnasiouq.gimnasioapp.controller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Reserva;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Clase;

import java.util.List;

public class ReservaController {

    ModelFactory modelFactory;

    public ReservaController() {
        this.modelFactory = ModelFactory.getInstancia();
    }

    // ============================================================
    //                     CRUD RESERVAS
    // ============================================================

    public List<Reserva> obtenerReservas() {
        return modelFactory.obtenerReservas();
    }

    public boolean crearReserva(Reserva reserva) {
        return modelFactory.crearReserva(reserva);
    }

    public boolean cancelarReserva(String codigoReserva) {
        return modelFactory.cancelarReserva(codigoReserva);
    }

    // ============================================================
    //                   DATOS ACTUALIZADOS PARA COMBOBOX
    // ============================================================

    public List<Usuario> obtenerUsuarios() {
        // ✅ Usar la lista MÁS RECIENTE
        return modelFactory.obtenerUsuariosActualizados();
    }

    public List<Clase> obtenerClases() {
        // ✅ Usar la lista MÁS RECIENTE
        return modelFactory.obtenerClasesActualizadas();
    }
}