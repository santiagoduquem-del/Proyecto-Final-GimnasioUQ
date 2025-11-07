package co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;

import co.edu.uniquindio.gimnasiouq.gimnasioapp.factory.ModelFactory;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Clase;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Reserva;
import co.edu.uniquindio.gimnasiouq.gimnasioapp.model.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReportViewController {

    @FXML
    private TextArea reportTextArea;

    @FXML
    void generateActiveUsersReport(ActionEvent event) {
        List<Usuario> usuarios = ModelFactory.getInstancia().obtenerUsuarios();
        StringBuilder report = new StringBuilder("Active Users Report:\n\n");
        for (Usuario usuario : usuarios) {
            if (usuario.getMembresia() != null && usuario.getMembresia().getEstado() == co.edu.uniquindio.gimnasiouq.gimnasioapp.model.EstadoMembresia.ACTIVA) {
                report.append("Name: ").append(usuario.getNombre()).append("\n");
                report.append("ID: ").append(usuario.getIdentificacion()).append("\n");
                report.append("Membership: ").append(usuario.getTipoMembresia()).append(" - ").append(usuario.getTipoDeMembresia()).append("\n");
                report.append("Expires: ").append(usuario.getMembresia().getFechaVencimiento()).append("\n\n");
            }
        }
        reportTextArea.setText(report.toString());
    }

    @FXML
    void generateMembershipExpiryReport(ActionEvent event) {
        List<Usuario> usuarios = ModelFactory.getInstancia().obtenerUsuarios();
        StringBuilder report = new StringBuilder("Membership Expiry Report:\n\n");
        for (Usuario usuario : usuarios) {
            if (usuario.getMembresia() != null) {
                report.append("Name: ").append(usuario.getNombre()).append("\n");
                report.append("ID: ").append(usuario.getIdentificacion()).append("\n");
                report.append("Membership: ").append(usuario.getTipoMembresia()).append(" - ").append(usuario.getTipoDeMembresia()).append("\n");
                report.append("Expires: ").append(usuario.getMembresia().getFechaVencimiento()).append("\n\n");
            }
        }
        reportTextArea.setText(report.toString());
    }

    @FXML
    void generatePopularClassesReport(ActionEvent event) {
        List<Reserva> reservas = ModelFactory.getInstancia().obtenerReservas();
        Map<String, Long> classCounts = reservas.stream()
                .collect(Collectors.groupingBy(Reserva::getNombreClase, Collectors.counting()));

        StringBuilder report = new StringBuilder("Popular Classes Report:\n\n");
        classCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEach(entry -> report.append(entry.getKey()).append(": ").append(entry.getValue()).append(" reservations\n"));
        reportTextArea.setText(report.toString());
    }

    @FXML
    void generateMembershipRevenueReport(ActionEvent event) {
        List<Usuario> usuarios = ModelFactory.getInstancia().obtenerUsuarios();
        double totalRevenue = 0;
        for (Usuario usuario : usuarios) {
            if (usuario.getMembresia() != null) {
                totalRevenue += Double.parseDouble(usuario.getMembresia().getCosto());
            }
        }
        reportTextArea.setText("Total Membership Revenue: $" + totalRevenue);
    }
}
