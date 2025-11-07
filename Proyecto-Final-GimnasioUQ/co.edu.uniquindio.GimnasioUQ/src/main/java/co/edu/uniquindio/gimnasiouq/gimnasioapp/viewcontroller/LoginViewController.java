package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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

    public void setUserType(String userType) {
        this.userType = userType;
        loginTitle.setText("Login as " + userType);
    }

    @FXML
    void login(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Hardcoded credentials for now
        if (("receptionist".equals(username) && "password".equals(password) && "Receptionist".equals(userType)) ||
            ("admin".equals(username) && "password".equals(password) && "Administrator".equals(userType)) ||
            ("user".equals(username) && "password".equals(password) && "User".equals(userType))) {

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
            // Show error message
        }
    }
}
