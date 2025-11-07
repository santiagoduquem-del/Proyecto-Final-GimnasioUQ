package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Administrador;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Recepcionista;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginViewController {

    @FXML
    private Text loginTitle;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private String userType;
    private ModelFactory modelFactory;

    @FXML
    void initialize() {
        modelFactory = ModelFactory.getInstancia();
    }

    public void setUserType(String userType) {
        this.userType = userType;
        loginTitle.setText("Login as " + userType);
    }

    private boolean isValidLogin(String username, String password) {
        if ("Receptionist".equals(userType)) {
            Recepcionista recepcionista = modelFactory.obtenerGimnasio().buscarRecepcionista(username);
            return recepcionista != null && recepcionista.getPassword().equals(password);
        } else if ("Administrator".equals(userType)) {
            Administrador administrador = modelFactory.obtenerGimnasio().buscarAdministrador(username);
            return administrador != null && administrador.getPassword().equals(password);
        } else if ("User".equals(userType)) {
            Usuario usuario = modelFactory.obtenerGimnasio().buscarUsuario(username);
            return usuario != null && usuario.getIdentificacion().equals(password);
        }
        return false;
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (isValidLogin(username, password)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/gimnasiouq/gimnasioapp/GimnasioView.fxml"));
                Scene scene = new Scene(loader.load());
                GimnasioViewController controller = loader.getController();
                controller.initializeUserRole(userType);
                Stage stage = (Stage) loginTitle.getScene().getWindow();
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showAlert("Login Failed", "Invalid username or password.", Alert.AlertType.ERROR);
        }
    }
}
