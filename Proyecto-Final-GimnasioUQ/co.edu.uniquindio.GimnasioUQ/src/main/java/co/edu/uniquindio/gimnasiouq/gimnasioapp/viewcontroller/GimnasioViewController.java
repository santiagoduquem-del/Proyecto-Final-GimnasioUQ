package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GimnasioViewController implements Initializable {

    @FXML private CrudUsuarioViewController studentTabController;
    @FXML private CrudUsuarioViewController workerTabController;
    @FXML private CrudUsuarioViewController externalTabContentController;

    @FXML private TabPane tabPane;
    @FXML private Tab studentsTab;
    @FXML private Tab workersTab;
    @FXML private Tab externalTab;
    @FXML private Tab accessControlTab;
    @FXML private Tab coachManagementTab;
    @FXML private Tab reservationsTab;
    @FXML private Tab reportsTab;
    @FXML private Tab userManagementTab;

    @FXML private SplitPane splitPaneReservas;

    private ReservaViewController reservaViewController;
    private boolean reservaCargada = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        studentTabController.setUserType("Student");
        workerTabController.setUserType("UQ Worker");
        externalTabContentController.setUserType("External");
        System.out.println("🏋️ GimnasioViewController inicializado");
    }

    public void initializeUserRole(String userType) {
        switch (userType) {
            case "Administrator":
                // Admin sees all
                break;
            case "Receptionist":
                tabPane.getTabs().remove(coachManagementTab);
                tabPane.getTabs().remove(userManagementTab);
                break;
            case "User":
                tabPane.getTabs().remove(studentsTab);
                tabPane.getTabs().remove(workersTab);
                tabPane.getTabs().remove(externalTab);
                tabPane.getTabs().remove(accessControlTab);
                tabPane.getTabs().remove(coachManagementTab);
                tabPane.getTabs().remove(reportsTab);
                tabPane.getTabs().remove(userManagementTab);
                break;
        }
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
