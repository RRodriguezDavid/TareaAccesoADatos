module org.example.ejerciciobiblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires javafx.graphics;
    requires java.desktop;

    opens org.example.ejerciciobiblioteca to javafx.fxml;
    exports org.example.ejerciciobiblioteca;
}
