package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Externo;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.TipoMembresiaDuracion;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationViewController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField ageField;

    @FXML
    private TextField phoneField;

    @FXML
    private ComboBox<TipoMembresiaDuracion> membershipTypeBox;

    private ModelFactory modelFactory;

    @FXML
    void initialize() {
        modelFactory = ModelFactory.getInstancia();
        membershipTypeBox.getItems().setAll(TipoMembresiaDuracion.values());
    }

    @FXML
    void register(ActionEvent event) {
        String name = nameField.getText();
        String id = idField.getText();
        String age = ageField.getText();
        String phone = phoneField.getText();
        TipoMembresiaDuracion membershipType = membershipTypeBox.getValue();

        if (name.isEmpty() || id.isEmpty() || age.isEmpty() || phone.isEmpty() || membershipType == null) {
            showAlert("Registration Failed", "All fields are required.", Alert.AlertType.ERROR);
            return;
        }

        Usuario newUser = new Externo(name, id, age, phone, membershipType, "N/A");

        if (modelFactory.crearUsuario(newUser)) {
            showAlert("Registration Successful", "You can now log in with your ID as username and password.", Alert.AlertType.INFORMATION);
            back(event);
        } else {
            showAlert("Registration Failed", "User with this ID already exists.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    void back(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/gimnasiouq/gimnasioapp/MainMenuView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
