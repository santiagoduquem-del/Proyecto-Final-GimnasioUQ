package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuViewController {

    public void loginReceptionist(ActionEvent event) {
        showLoginView("Receptionist", event);
    }

    public void loginAdministrator(ActionEvent event) {
        showLoginView("Administrator", event);
    }

    public void loginUser(ActionEvent event) {
        showLoginView("User", event);
    }

    public void registerUser(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/gimnasiouq/gimnasioapp/RegistrationView.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLoginView(String userType, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/co/edu/uniquindio/gimnasiouq/gimnasioapp/LoginView.fxml"));
            Scene scene = new Scene(loader.load());

            LoginViewController controller = loader.getController();
            controller.setUserType(userType);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
