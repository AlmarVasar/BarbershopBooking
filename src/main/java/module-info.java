module com.almarvasar.barbershopbooking {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires java.naming;
    requires org.hibernate.orm.core;

    opens com.almarvasar.barbershopbooking to javafx.fxml,org.hibernate.orm.core;
    exports com.almarvasar.barbershopbooking;
}