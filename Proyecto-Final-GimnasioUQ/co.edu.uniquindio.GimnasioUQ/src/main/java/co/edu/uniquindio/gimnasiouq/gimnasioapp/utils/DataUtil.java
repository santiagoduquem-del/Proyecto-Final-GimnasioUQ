package co.edu.uniquindio.gimnasiouq.gimnasioapp.utils;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DataUtil {

    private static final String FILE_PATH = "gimnasio.xml";

    public static GimnasioUQ inicializarDatos() {
        GimnasioUQ gimnasio = loadData();
        if (gimnasio == null) {
            gimnasio = new GimnasioUQ("Gimnasio UQ Fit");
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
            saveData(gimnasio);
        }
        return gimnasio;
    }

    public static void saveData(GimnasioUQ gimnasio) {
        XStream xstream = new XStream();
        xstream.alias("gimnasio", GimnasioUQ.class);
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            xstream.toXML(gimnasio, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static GimnasioUQ loadData() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return null;
        }
        XStream xstream = new XStream();
        xstream.alias("gimnasio", GimnasioUQ.class);
        xstream.addPermission(AnyTypePermission.ANY);
        try (FileReader reader = new FileReader(file)) {
            return (GimnasioUQ) xstream.fromXML(reader);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
