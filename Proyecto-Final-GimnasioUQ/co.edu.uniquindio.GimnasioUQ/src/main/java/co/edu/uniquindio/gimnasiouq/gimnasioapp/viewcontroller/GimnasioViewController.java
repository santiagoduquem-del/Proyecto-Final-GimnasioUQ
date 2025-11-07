package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GimnasioViewController implements Initializable {

    @FXML private SplitPane splitPaneReservas;

    private ReservaViewController reservaViewController;
    private boolean reservaCargada = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("🏋️ GimnasioViewController inicializado");
    }

    @FXML
    private void onReservaTabSelected() {
        System.out.println("📅 Tab de Reservas seleccionado");

        if (!reservaCargada) {
            cargarReservaController();
        } else if (reservaViewController != null) {
            reservaViewController.actualizarDatos();
        }
    }

    private void cargarReservaController() {
        try {
            // Cargar el contenido de reservas
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/co/edu/uniquindio/gimnasiouq/gimnasioapp/ReservaView.fxml"));
            AnchorPane reservaContent = loader.load();

            // Obtener el controlador
            reservaViewController = loader.getController();
            reservaCargada = true;

            System.out.println("✅ Controlador de reservas cargado manualmente");

            // Actualizar datos
            reservaViewController.actualizarDatos();

        } catch (Exception e) {
            System.out.println("❌ Error cargando reservas: " + e.getMessage());
            e.printStackTrace();
        }
    }
}