module com.almarvasar.barbershopbooking {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.almarvasar.barbershopbooking to javafx.fxml;
    exports com.almarvasar.barbershopbooking;
}