package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.controller.ReservaController;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Clase;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.util.Callback;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.ResourceBundle;

public class ReservaViewController implements Initializable {

    @FXML private ComboBox<Usuario> comboUsuario;
    @FXML private ComboBox<Clase> comboClase;
    @FXML private DatePicker dateFecha;
    @FXML private TextField txtHora;
    @FXML private TableView<Reserva> tableReserva;
    @FXML private TableColumn<Reserva, String> tcUsuario;
    @FXML private TableColumn<Reserva, String> tcClase;
    @FXML private TableColumn<Reserva, String> tcFecha;
    @FXML private TableColumn<Reserva, String> tcHora;
    @FXML private TableColumn<Reserva, String> tcEstado;

    private ReservaController reservaController;
    private ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    private ObservableList<Clase> listaClases = FXCollections.observableArrayList();
    private ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("🔄 Inicializando ReservaViewController...");

        reservaController = new ReservaController();
        configurarComboBox();
        cargarDatos();
        configurarTabla();

        System.out.println("✅ ReservaViewController listo");
    }

    private void configurarComboBox() {
        // Configurar ComboBox de Usuarios
        comboUsuario.setCellFactory(new Callback<ListView<Usuario>, ListCell<Usuario>>() {
            @Override
            public ListCell<Usuario> call(ListView<Usuario> param) {
                return new ListCell<Usuario>() {
                    @Override
                    protected void updateItem(Usuario item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getNombre() + " (" + item.getIdentificacion() + ")");
                        }
                    }
                };
            }
        });

        comboUsuario.setConverter(new StringConverter<Usuario>() {
            @Override
            public String toString(Usuario usuario) {
                if (usuario == null) {
                    return "";
                }
                return usuario.getNombre() + " (" + usuario.getIdentificacion() + ")";
            }

            @Override
            public Usuario fromString(String string) {
                return null;
            }
        });

        // Configurar ComboBox de Clases
        comboClase.setCellFactory(new Callback<ListView<Clase>, ListCell<Clase>>() {
            @Override
            public ListCell<Clase> call(ListView<Clase> param) {
                return new ListCell<Clase>() {
                    @Override
                    protected void updateItem(Clase item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.getNombre() + " - " + item.getHorario());
                        }
                    }
                };
            }
        });

        comboClase.setConverter(new StringConverter<Clase>() {
            @Override
            public String toString(Clase clase) {
                if (clase == null) {
                    return "";
                }
                return clase.getNombre() + " - " + clase.getHorario();
            }

            @Override
            public Clase fromString(String string) {
                return null;
            }
        });
    }

    private void cargarDatos() {
        try {
            System.out.println("🔄 Cargando datos...");

            // Obtener datos frescos del controlador
            listaUsuarios.setAll(reservaController.obtenerUsuarios());
            listaClases.setAll(reservaController.obtenerClases());
            listaReservas.setAll(reservaController.obtenerReservas());

            // Asignar a los ComboBox y TableView
            comboUsuario.setItems(listaUsuarios);
            comboClase.setItems(listaClases);
            tableReserva.setItems(listaReservas);

            System.out.println("✅ Datos cargados - Usuarios: " + listaUsuarios.size() +
                    ", Clases: " + listaClases.size() +
                    ", Reservas: " + listaReservas.size());

        } catch (Exception e) {
            System.out.println("❌ Error en cargarDatos: " + e.getMessage());
            e.printStackTrace();
            mostrarAlerta("Error", "No se pudieron cargar los datos: " + e.getMessage());
        }
    }

    private void configurarTabla() {
        // Configurar las columnas de la tabla
        tcUsuario.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombreUsuario()));
        tcClase.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombreClase()));
        tcFecha.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getFecha()));
        tcHora.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getHora()));
        tcEstado.setCellValueFactory(cellData ->
                new SimpleStringProperty("Activa")); // Por defecto todas activas
    }

    /**
     * Método público para actualizar datos desde otros controladores
     */
    public void actualizarDatos() {
        System.out.println("🔄 Actualizando datos desde llamada externa...");
        cargarDatos();
    }

    @FXML
    private void onActionNuevaReserva() {
        // Limpiar formulario para nueva reserva
        comboUsuario.setValue(null);
        comboClase.setValue(null);
        dateFecha.setValue(null);
        txtHora.clear();
        System.out.println("📝 Formulario limpiado para nueva reserva");
    }

    @FXML
    private void onActionAgregarReserva() {
        try {
            if (validarFormulario()) {
                Usuario usuario = comboUsuario.getValue();
                Clase clase = comboClase.getValue();

                // Crear nueva reserva
                Reserva reserva = new Reserva(
                        generarCodigoReserva(),
                        usuario.getNombre(),
                        clase.getNombre(),
                        dateFecha.getValue().toString(),
                        txtHora.getText()
                );

                // Guardar en el controlador
                if (reservaController.crearReserva(reserva)) {
                    // Actualizar la lista local
                    listaReservas.add(reserva);
                    tableReserva.refresh();

                    mostrarAlerta("Éxito", "Reserva creada correctamente");
                    onActionNuevaReserva(); // Limpiar formulario

                    System.out.println("✅ Reserva creada: " + reserva.getCodigoReserva());
                } else {
                    mostrarAlerta("Error", "No se pudo crear la reserva");
                }
            }
        } catch (Exception e) {
            System.out.println("❌ Error al crear reserva: " + e.getMessage());
            mostrarAlerta("Error", "Error al crear reserva: " + e.getMessage());
        }
    }

    @FXML
    private void onActionCancelarReserva() {
        try {
            Reserva reservaSeleccionada = tableReserva.getSelectionModel().getSelectedItem();
            if (reservaSeleccionada != null) {
                // Confirmar cancelación
                Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
                confirmacion.setTitle("Confirmar cancelación");
                confirmacion.setHeaderText(null);
                confirmacion.setContentText("¿Está seguro de cancelar la reserva seleccionada?");

                if (confirmacion.showAndWait().get() == ButtonType.OK) {
                    if (reservaController.cancelarReserva(reservaSeleccionada.getCodigoReserva())) {
                        listaReservas.remove(reservaSeleccionada);
                        tableReserva.refresh();
                        mostrarAlerta("Éxito", "Reserva cancelada correctamente");
                        System.out.println("✅ Reserva cancelada: " + reservaSeleccionada.getCodigoReserva());
                    } else {
                        mostrarAlerta("Error", "No se pudo cancelar la reserva");
                    }
                }
            } else {
                mostrarAlerta("Advertencia", "Seleccione una reserva para cancelar");
            }
        } catch (Exception e) {
            System.out.println("❌ Error al cancelar reserva: " + e.getMessage());
            mostrarAlerta("Error", "Error al cancelar reserva: " + e.getMessage());
        }
    }

    private boolean validarFormulario() {
        if (comboUsuario.getValue() == null) {
            mostrarAlerta("Validación", "Seleccione un usuario");
            return false;
        }
        if (comboClase.getValue() == null) {
            mostrarAlerta("Validación", "Seleccione una clase");
            return false;
        }
        if (dateFecha.getValue() == null) {
            mostrarAlerta("Validación", "Seleccione una fecha");
            return false;
        }
        if (txtHora.getText().isEmpty()) {
            mostrarAlerta("Validación", "Ingrese la hora");
            return false;
        }
        // Validar formato de hora (opcional)
        if (!txtHora.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            mostrarAlerta("Validación", "Formato de hora inválido. Use HH:MM (ej: 14:30)");
            return false;
        }
        return true;
    }

    private String generarCodigoReserva() {
        return "RES" + System.currentTimeMillis();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}