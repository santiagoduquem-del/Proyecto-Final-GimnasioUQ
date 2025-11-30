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

    @FXML
    private javafx.scene.control.TextArea userInfoArea;

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
            userInfoArea.clear();
            return;
        }

        Usuario usuario = usuarioController.buscarUsuario(userId);

        if (usuario != null) {
            if (usuario.getMembresia() != null && usuario.getMembresia().getEstado() == co.edu.uniquindio.gimnasiouq.gimnasioapp.model.EstadoMembresia.ACTIVA) {
                accessStatusLabel.setText("Access Granted");
                userInfoArea.setText("User: " + usuario.getNombre() + "\n" +
                        "ID: " + usuario.getIdentificacion() + "\n" +
                        "Membership: " + usuario.getTipoMembresia() + " - " + usuario.getTipoDeMembresia() + "\n" +
                        "Expires: " + usuario.getMembresia().getFechaVencimiento());
            } else {
                accessStatusLabel.setText("Access Denied - Membership Inactive");
                userInfoArea.clear();
            }
        } else {
            accessStatusLabel.setText("Access Denied - User not found");
            userInfoArea.clear();
        }
    }
}
