package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.controller.UsuarioController;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccessControlViewController {

    @FXML
    private TextField userIdField;

    @FXML
    private Label accessStatusLabel;

    private UsuarioController usuarioController;

    @FXML
    void initialize() {
        usuarioController = new UsuarioController();
    }

    @FXML
    void verifyAccess(ActionEvent event) {
        String userId = userIdField.getText();
        if (userId.isEmpty()) {
            accessStatusLabel.setText("Please enter a user ID.");
            return;
        }

        Usuario usuario = usuarioController.buscarUsuario(userId);

        if (usuario != null) {
            accessStatusLabel.setText("Access Granted");
        } else {
            accessStatusLabel.setText("Access Denied");
        }
    }
}
