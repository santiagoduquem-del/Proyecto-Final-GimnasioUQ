package co.edu.uniquindio.gimnasiouq.gimnasioapp.utils;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.*;

public class DataUtil {

    public static GimnasioUQ inicializarDatos() {
        GimnasioUQ gimnasio = new GimnasioUQ("Gimnasio UQ Fit");

        // ==================== CREAR USUARIOS DE PRUEBA ====================
        Usuario usuario1 = new Estudiante("Juan Pérez", "1001", "25", "3101111111",
                TipoMembresiaDuracion.MENSUAL, "5", "Ingeniería");

        Usuario usuario2 = new Trabajador("María García", "1002", "30", "3102222222",
                TipoMembresiaDuracion.TRIMESTRAL, "Recepcionista");

        Usuario usuario3 = new Externo("Carlos López", "1003", "28", "3103333333",
                TipoMembresiaDuracion.ANUAL, "Empresa ABC");

        gimnasio.getListaUsuarios().add(usuario1);
        gimnasio.getListaUsuarios().add(usuario2);
        gimnasio.getListaUsuarios().add(usuario3);

        // ==================== CREAR CLASES DE PRUEBA ====================
        Clase clase1 = new Clase("Yoga Matutino", TipoClase.YOGA, "08:00-09:00", "20", null);
        Clase clase2 = new Clase("Spinning Intenso", TipoClase.SPINNING, "18:00-19:00", "15", null);
        Clase clase3 = new Clase("Boxeo Básico", TipoClase.BOXEO, "17:00-18:00", "12", null);
        Clase clase4 = new Clase("Pilates Relax", TipoClase.PILATES, "10:00-11:00", "18", null);

        gimnasio.getListaClases().add(clase1);
        gimnasio.getListaClases().add(clase2);
        gimnasio.getListaClases().add(clase3);
        gimnasio.getListaClases().add(clase4);

        // ==================== CREAR RESERVAS DE PRUEBA ====================
        Reserva reserva1 = new Reserva("RES001", "Juan Pérez", "Yoga Matutino", "2024-01-15", "08:00");
        Reserva reserva2 = new Reserva("RES002", "María García", "Spinning Intenso", "2024-01-15", "18:00");

        gimnasio.getListaReservas().add(reserva1);
        gimnasio.getListaReservas().add(reserva2);

        System.out.println("=== DATOS INICIALIZADOS ===");
        System.out.println("Usuarios: " + gimnasio.getListaUsuarios().size());
        System.out.println("Clases: " + gimnasio.getListaClases().size());
        System.out.println("Reservas: " + gimnasio.getListaReservas().size());

        return gimnasio;
    }
}