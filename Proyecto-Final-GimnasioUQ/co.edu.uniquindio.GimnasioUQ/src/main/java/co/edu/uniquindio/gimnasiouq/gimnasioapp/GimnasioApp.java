package co.edu.uniquindio.gimnasiouq.gimnasioapp;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.utils.DataUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GimnasioApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                GimnasioApp.class.getResource("/co/edu/uniquindio/gimnasiouq/gimnasioapp/MainMenuView.fxml")
        );

        Scene scene = new Scene(loader.load());
        stage.setTitle("Gimnasio UQ Fit");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        DataUtil.saveData(ModelFactory.getInstancia().obtenerGimnasio());
    }

    public static void main(String[] args) {
        launch();
    }
}
