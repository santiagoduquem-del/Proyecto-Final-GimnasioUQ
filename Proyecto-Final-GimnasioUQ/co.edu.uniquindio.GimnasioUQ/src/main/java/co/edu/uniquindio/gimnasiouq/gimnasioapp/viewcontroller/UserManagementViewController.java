package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Administrador;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Recepcionista;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class UserManagementViewController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> userTypeComboBox;

    @FXML
    private TableView<Object> userTable;

    @FXML
    private TableColumn<Object, String> usernameColumn;

    @FXML
    private TableColumn<Object, String> userTypeColumn;

    private ModelFactory modelFactory;
    private ObservableList<Object> listaUsuarios = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        modelFactory = ModelFactory.getInstancia();
        initComboBox();
        initDataBinding();
        obtenerUsuarios();
    }

    private void initComboBox() {
        userTypeComboBox.getItems().addAll("Administrator", "Receptionist");
    }

    private void initDataBinding() {
        usernameColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Administrador) {
                return new SimpleStringProperty(((Administrador) cellData.getValue()).getUsername());
            } else if (cellData.getValue() instanceof Recepcionista) {
                return new SimpleStringProperty(((Recepcionista) cellData.getValue()).getUsername());
            }
            return null;
        });
        userTypeColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Administrador) {
                return new SimpleStringProperty("Administrator");
            } else if (cellData.getValue() instanceof Recepcionista) {
                return new SimpleStringProperty("Receptionist");
            }
            return null;
        });
        userTable.setItems(listaUsuarios);
    }

    private void obtenerUsuarios() {
        listaUsuarios.clear();
        listaUsuarios.addAll(modelFactory.obtenerGimnasio().getListaAdministradores());
        listaUsuarios.addAll(modelFactory.obtenerGimnasio().getListaRecepcionistas());
    }

    @FXML
    void addUser(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String userType = userTypeComboBox.getValue();

        if ("Administrator".equals(userType)) {
            modelFactory.obtenerGimnasio().getListaAdministradores().add(new Administrador(username, password));
        } else if ("Receptionist".equals(userType)) {
            modelFactory.obtenerGimnasio().getListaRecepcionistas().add(new Recepcionista(username, password));
        }
        obtenerUsuarios();
    }

    @FXML
    void updateUser(ActionEvent event) {
        Object selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (selectedUser instanceof Administrador) {
                ((Administrador) selectedUser).setUsername(username);
                ((Administrador) selectedUser).setPassword(password);
            } else if (selectedUser instanceof Recepcionista) {
                ((Recepcionista) selectedUser).setUsername(username);
                ((Recepcionista) selectedUser).setPassword(password);
            }
            userTable.refresh();
        }
    }

    @FXML
    void deleteUser(ActionEvent event) {
        Object selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            if (selectedUser instanceof Administrador) {
                modelFactory.obtenerGimnasio().getListaAdministradores().remove(selectedUser);
            } else if (selectedUser instanceof Recepcionista) {
                modelFactory.obtenerGimnasio().getListaRecepcionistas().remove(selectedUser);
            }
            listaUsuarios.remove(selectedUser);
        }
    }
}
