package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.controller.UsuarioController;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class CrudUsuarioViewController {

    UsuarioController usuarioController;
    ObservableList<Usuario> listaUsuarios = FXCollections.observableArrayList();
    Usuario usuarioSeleccionado;
    private String userType;

    @FXML private Label lblExtra1;
    @FXML private TextField txtExtra1;
    @FXML private Label lblExtra2;
    @FXML private TextField txtExtra2;


    public void setUserType(String userType) {
        this.userType = userType;
        obtenerUsuarios();

        switch (userType) {
            case "Student":
                lblExtra1.setText("Curso:");
                lblExtra2.setText("Programa:");
                lblExtra1.setVisible(true);
                txtExtra1.setVisible(true);
                lblExtra2.setVisible(true);
                txtExtra2.setVisible(true);
                lblExtra1.setManaged(true);
                txtExtra1.setManaged(true);
                lblExtra2.setManaged(true);
                txtExtra2.setManaged(true);
                break;
            case "UQ Worker":
                lblExtra1.setText("Dependencia:");
                lblExtra1.setVisible(true);
                txtExtra1.setVisible(true);
                lblExtra1.setManaged(true);
                txtExtra1.setManaged(true);
                break;
            case "External":
                lblExtra1.setText("Dirección:");
                lblExtra1.setVisible(true);
                txtExtra1.setVisible(true);
                lblExtra1.setManaged(true);
                txtExtra1.setManaged(true);
                break;
        }
    }
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnNuevo;

    @FXML
    private TableView<Usuario> tableUsuario;

    @FXML
    private TableColumn<Usuario, String> tcNombre;

    @FXML
    private TableColumn<Usuario, String> tcIdentificacion;

    @FXML
    private TableColumn<Usuario, String> tcEdad;

    @FXML
    private TableColumn<Usuario, String> tcTelefono;

    @FXML
    private TableColumn<Usuario, String> tcMembresia;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<TipoMembresiaDuracion> comboMembresia;

    // ============================================================
    //                  EVENTOS DE BOTONES
    // ============================================================

    @FXML
    void onActionActualizar(ActionEvent event) {
        actualizarUsuario();
    }

    @FXML
    void onActionAgregar(ActionEvent event) {
        crearUsuario();
    }

    @FXML
    void onActionNuevo(ActionEvent event) {
        limpiarCampos();
    }

    @FXML
    void onActionEliminar(ActionEvent event) {
        eliminarUsuario();
    }

    // ============================================================
    //                       INICIALIZACIÓN
    // ============================================================

    @FXML
    void initialize() {
        usuarioController = new UsuarioController();
        initView();
    }

    private void initView() {
        initComboBox();
        initDataBinding();
        tableUsuario.setItems(listaUsuarios);
        listenerSelection();
    }

    private void initComboBox() {
        comboMembresia.getItems().addAll(TipoMembresiaDuracion.values());
    }

    private void initDataBinding() {
        tcNombre.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getNombre()));

        tcIdentificacion.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getIdentificacion()));

        tcEdad.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getEdad()));

        tcTelefono.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getTelefono()));

        tcMembresia.setCellValueFactory(cellData ->
                new SimpleStringProperty(
                        cellData.getValue().getTipoDeMembresia().name()
                ));
    }

    private void listenerSelection() {
        tableUsuario.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            usuarioSeleccionado = newSelection;
            mostrarInformacion(usuarioSeleccionado);
        });
    }

    private void mostrarInformacion(Usuario usuario) {
        if (usuario != null) {
            txtNombre.setText(usuario.getNombre());
            txtIdentificacion.setText(usuario.getIdentificacion());
            txtEdad.setText(usuario.getEdad());
            txtTelefono.setText(usuario.getTelefono());
            comboMembresia.setValue(usuario.getTipoDeMembresia());
            if(usuario instanceof Estudiante){
                txtExtra1.setText(((Estudiante) usuario).getCurso());
                txtExtra2.setText(((Estudiante) usuario).getPrograma());
            } else if(usuario instanceof Trabajador){
                txtExtra1.setText(((Trabajador) usuario).getTipoDeCargo());
            } else if(usuario instanceof Externo){
                txtExtra1.setText(((Externo) usuario).getUniversidadEmpresa());
            }
        }
    }

    private void obtenerUsuarios() {
        listaUsuarios.clear();
        for(Usuario usuario : usuarioController.obtenerUsuarios()){
            if(userType.equals("Student") && usuario instanceof Estudiante){
                listaUsuarios.add(usuario);
            } else if(userType.equals("UQ Worker") && usuario instanceof Trabajador){
                listaUsuarios.add(usuario);
            } else if(userType.equals("External") && usuario instanceof Externo){
                listaUsuarios.add(usuario);
            }
        }
    }

    // ============================================================
    //                        CRUD
    // ============================================================

    private void crearUsuario() {
        // 1. Capturar datos
        String nombre = txtNombre.getText();
        String identificacion = txtIdentificacion.getText();
        String edad = txtEdad.getText();
        String telefono = txtTelefono.getText();
        TipoMembresiaDuracion membresia = comboMembresia.getValue();
        String extra1 = txtExtra1.getText();
        String extra2 = txtExtra2.getText();

        // 2. Validar campos
        if (!validarCampos(nombre, identificacion, edad, telefono, membresia)) {
            mostrarMensaje("Error", "Datos incompletos", "Por favor complete todos los campos", Alert.AlertType.WARNING);
            return;
        }

        // 3. DEBUG - Ver instancia actual
        System.out.println("=== 🔍 DEBUG - CREANDO USUARIO ===");
        System.out.println("Usuario a crear: " + nombre + " - " + identificacion);

        // 4. Crear usuario
        Usuario nuevoUsuario = null;

        switch (userType) {
            case "Student":
                nuevoUsuario = new Estudiante(nombre, identificacion, edad, telefono, membresia, extra1, extra2);
                break;
            case "UQ Worker":
                nuevoUsuario = new Trabajador(nombre, identificacion, edad, telefono, membresia, extra1);
                break;
            case "External":
                nuevoUsuario = new Externo(nombre, identificacion, edad, telefono, membresia, extra1);
                break;
        }


        // 5. Guardar usuario
        boolean creado = usuarioController.crearUsuario(nuevoUsuario);

        if (creado) {
            // 6. DEBUG - Verificar que se guardó
            System.out.println("✅ USUARIO CREADO EXITOSAMENTE");

            // Ver usuarios en el sistema
            int totalUsuarios = ModelFactory.getInstancia().obtenerUsuarios().size();
            System.out.println("📊 Total usuarios en sistema: " + totalUsuarios);

            // Mostrar todos los usuarios
            System.out.println("--- LISTA COMPLETA DE USUARIOS ---");
            for (Usuario u : ModelFactory.getInstancia().obtenerUsuarios()) {
                System.out.println("   - " + u.getNombre() + " - " + u.getIdentificacion());
            }

            listaUsuarios.add(nuevoUsuario);
            limpiarCampos();
            mostrarMensaje("Éxito", "Usuario creado", "Usuario creado correctamente", Alert.AlertType.INFORMATION);

        } else {
            System.out.println("❌ ERROR: No se pudo crear el usuario");
            mostrarMensaje("Error", "Fallo", "El usuario no pudo crearse", Alert.AlertType.ERROR);
        }
    }

    private void actualizarUsuario() {
        if (usuarioSeleccionado == null) {
            mostrarMensaje("Error", "Selección requerida", "Seleccione un usuario en la tabla", Alert.AlertType.WARNING);
            return;
        }

        // Capturar datos
        usuarioSeleccionado.setNombre(txtNombre.getText());
        usuarioSeleccionado.setIdentificacion(txtIdentificacion.getText());
        usuarioSeleccionado.setEdad(txtEdad.getText());
        usuarioSeleccionado.setTelefono(txtTelefono.getText());
        usuarioSeleccionado.setTipoDeMembresia(comboMembresia.getValue());
        if(usuarioSeleccionado instanceof Estudiante){
            ((Estudiante) usuarioSeleccionado).setCurso(txtExtra1.getText());
            ((Estudiante) usuarioSeleccionado).setPrograma(txtExtra2.getText());
        } else if(usuarioSeleccionado instanceof Trabajador){
            ((Trabajador) usuarioSeleccionado).setTipoDeCargo(txtExtra1.getText());
        } else if(usuarioSeleccionado instanceof Externo){
            ((Externo) usuarioSeleccionado).setUniversidadEmpresa(txtExtra1.getText());
        }

        // CORREGIDO: Usar el controlador para actualizar
        boolean actualizado = usuarioController.actualizarUsuario(usuarioSeleccionado);

        if (actualizado) {
            tableUsuario.refresh();
            mostrarMensaje("Actualización", "Usuario actualizado", "Los datos fueron actualizados", Alert.AlertType.INFORMATION);
        } else {
            mostrarMensaje("Error", "Error", "No se pudo actualizar el usuario", Alert.AlertType.ERROR);
        }
    }
    private void eliminarUsuario(){
        if(usuarioSeleccionado == null){
            mostrarMensaje("Error", "Usuario no seleccionado",
                    "Por favor seleccione un usuario de la tabla", Alert.AlertType.WARNING);
            return;
        }

        boolean confirmar = mostrarMensajeConfirmacion("¿Está seguro que desea eliminar este usuario?");
        if(!confirmar){
            return;
        }

        listaUsuarios.remove(usuarioSeleccionado);
        limpiarCampos();
        usuarioSeleccionado = null;
        mostrarMensaje("Información", "Usuario eliminado", "El usuario ha sido eliminado correctamente",
                Alert.AlertType.INFORMATION);
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtIdentificacion.clear();
        txtEdad.clear();
        txtTelefono.clear();
        comboMembresia.setValue(null);
        txtExtra1.clear();
        txtExtra2.clear();
    }

    // ============================================================
    //                      UTILIDADES
    // ============================================================

    private boolean validarCampos(String nombre, String identificacion,
                                  String edad, String telefono,
                                  TipoMembresiaDuracion membresia) {

        return !(nombre.isEmpty() ||
                identificacion.isEmpty() ||
                edad.isEmpty() ||
                telefono.isEmpty() ||
                membresia == null);
    }

    private void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    private boolean mostrarMensajeConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmación");
        alert.setContentText(mensaje);

        Optional<ButtonType> action = alert.showAndWait();

        return action.get() == ButtonType.OK;
    }
}
