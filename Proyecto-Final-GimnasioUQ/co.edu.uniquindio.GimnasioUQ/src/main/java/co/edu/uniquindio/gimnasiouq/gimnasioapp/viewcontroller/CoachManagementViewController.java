package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.controller.EntrenadorController;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Entrenador;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CoachManagementViewController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TableView<Entrenador> coachTable;

    @FXML
    private TableColumn<Entrenador, String> nameColumn;

    @FXML
    private TableColumn<Entrenador, String> idColumn;

    private EntrenadorController entrenadorController;
    private ObservableList<Entrenador> listaEntrenadores = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        entrenadorController = new EntrenadorController();
        initDataBinding();
        obtenerEntrenadores();
    }

    private void initDataBinding() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        coachTable.setItems(listaEntrenadores);
    }

    private void obtenerEntrenadores() {
        listaEntrenadores.setAll(entrenadorController.obtenerEntrenadores());
    }

    @FXML
    void addCoach(ActionEvent event) {
        Entrenador entrenador = new Entrenador(nameField.getText(), idField.getText());
        if (entrenadorController.crearEntrenador(entrenador)) {
            obtenerEntrenadores();
        }
    }

    @FXML
    void updateCoach(ActionEvent event) {
        Entrenador entrenador = coachTable.getSelectionModel().getSelectedItem();
        if (entrenador != null) {
            entrenador.setNombre(nameField.getText());
            entrenador.setIdentificacion(idField.getText());
            if (entrenadorController.actualizarEntrenador(entrenador)) {
                coachTable.refresh();
            }
        }
    }

    @FXML
    void deleteCoach(ActionEvent event) {
        Entrenador entrenador = coachTable.getSelectionModel().getSelectedItem();
        if (entrenador != null) {
            if (entrenadorController.eliminarEntrenador(entrenador.getIdentificacion())) {
                listaEntrenadores.remove(entrenador);
            }
        }
    }
}
