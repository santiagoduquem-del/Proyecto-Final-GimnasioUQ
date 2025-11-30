module co.edu.uniquindio.gimnasiouq.gimnasiouqapp {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires xstream;
    requires java.logging;


    opens co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller to javafx.fxml;
    opens co.edu.uniquindio.gimnasiouq.gimnasioapp.model to xstream;


    exports co.edu.uniquindio.gimnasiouq.gimnasioapp;
    exports co.edu.uniquindio.gimnasiouq.gimnasioapp.viewcontroller;
    exports co.edu.uniquindio.gimnasiouq.gimnasioapp.model;
    exports co.edu.uniquindio.gimnasiouq.gimnasioapp.factory;
    exports co.edu.uniquindio.gimnasiouq.gimnasioapp.utils;

}
