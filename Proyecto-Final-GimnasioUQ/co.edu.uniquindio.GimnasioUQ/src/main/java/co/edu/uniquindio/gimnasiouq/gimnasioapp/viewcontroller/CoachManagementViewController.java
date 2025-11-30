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
    private TextField specialtyField;

    @FXML
    private TextField salaryField;

    @FXML
    private TableView<Entrenador> coachTable;

    @FXML
    private TableColumn<Entrenador, String> nameColumn;

    @FXML
    private TableColumn<Entrenador, String> idColumn;

    @FXML
    private TableColumn<Entrenador, String> specialtyColumn;

    @FXML
    private TableColumn<Entrenador, String> salaryColumn;

    private EntrenadorController entrenadorController;
    private ObservableList<Entrenador> listaEntrenadores = FXCollections.observableArrayList();

    @FXML
    void initialize() {
        entrenadorController = new EntrenadorController();
        initDataBinding();
        obtenerEntrenadores();
        coachTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                nameField.setText(newSelection.getNombre());
                idField.setText(newSelection.getIdentificacion());
                specialtyField.setText(newSelection.getEspecialidad());
                salaryField.setText(newSelection.getSueldo());
            }
        });
    }

    private void initDataBinding() {
        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        idColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getIdentificacion()));
        specialtyColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEspecialidad()));
        salaryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getSueldo()));
        coachTable.setItems(listaEntrenadores);
    }

    private void obtenerEntrenadores() {
        listaEntrenadores.setAll(entrenadorController.obtenerEntrenadores());
    }

    @FXML
    void addCoach(ActionEvent event) {
        Entrenador entrenador = new Entrenador(nameField.getText(), salaryField.getText(), specialtyField.getText(), idField.getText());
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
            entrenador.setEspecialidad(specialtyField.getText());
            entrenador.setSueldo(salaryField.getText());
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
