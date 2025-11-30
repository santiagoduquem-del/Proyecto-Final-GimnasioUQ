package co.edu.uniquindio.gimnasiouq.gimnasioapp.utils;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javafx.scene.control.Alert;

public class DataUtil {

    private static final String FILE_PATH = "gimnasio.xml";
    private static final Logger LOGGER = Logger.getLogger(DataUtil.class.getName());

    static {
        try {
            FileHandler fh = new FileHandler("gimnasio_errors.log", true);
            fh.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(fh);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Error al configurar el logger", e);
        }
    }

    private static void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

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

            // ==================== CREAR CUENTAS POR DEFECTO ====================
            Administrador admin = new Administrador("admin", "admin");
            Recepcionista receptionist = new Recepcionista("receptionist", "receptionist");
            Usuario user = new Externo("user", "user", "20", "3101234567", TipoMembresiaDuracion.ANUAL, "Test");

            gimnasio.getListaAdministradores().add(admin);
            gimnasio.getListaRecepcionistas().add(receptionist);
            gimnasio.getListaUsuarios().add(user);

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
            usuario1.setTipoMembresia(TipoMembresia.PREMIUM);
            usuario2.setTipoMembresia(TipoMembresia.VIP);

            Reserva reserva1 = new Reserva("RES001", usuario1, "Yoga Matutino", "2024-01-15", "08:00");
            Reserva reserva2 = new Reserva("RES002", usuario2, "Spinning Intenso", "2024-01-15", "18:00");

            gimnasio.crearReserva(reserva1);
            gimnasio.crearReserva(reserva2);
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
            LOGGER.log(Level.SEVERE, "Error al guardar los datos", e);
            showAlert("Error", "No se pudieron guardar los datos.", Alert.AlertType.ERROR);
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
            LOGGER.log(Level.SEVERE, "Error al cargar los datos", e);
            showAlert("Error", "No se pudieron cargar los datos.", Alert.AlertType.ERROR);
            return null;
        }
    }
}
