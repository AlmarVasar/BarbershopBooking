package com.almarvasar.barbershopbooking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private SessionFactory session;

    @FXML private DatePicker dateCB;
    @FXML
    private ChoiceBox<String> barberCB;
    @FXML
    private ChoiceBox<String> serviceCB;
    @FXML
    private ChoiceBox<String> timeCB;
    @FXML
    private Button insertButton;

    //@FXML private Button contactButton;

//Created arrays for choisebox
    private String[] barberNames = {"Funky Hair", "Slick Dude", "Hairy Beard"};
    private String[] service = {"Haircut", "Beard trim", "Haircut and beard trim"};
    private String[] time = {"09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00"};


    public HelloController(SessionFactory session) {
        this.session = session;
    }
    //Display my options in my JavaFX
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barberCB.getItems().addAll(barberNames);
        serviceCB.getItems().addAll(service);
        timeCB.getItems().addAll(time);

        //Open mysql and save the selected data in database
        this.insertButton.setOnAction(e -> {

            // Check if a date has been selected and if it is not in the past
            if (dateCB.getValue() == null || dateCB.getValue().isBefore(java.time.LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid date");
                alert.setHeaderText("Please select a date that is today or later.");
                alert.showAndWait();
                return;
            }
            var ses = session.openSession();
            var tx = ses.beginTransaction();

            var booking = new Booking();
            booking.setDate(java.sql.Date.valueOf(dateCB.getValue())); //To add date into db
            booking.setBarber(barberCB.getValue()); //To add barber into db
            booking.setService(serviceCB.getValue()); //To add service into db
            booking.setTime(Time.valueOf(timeCB.getValue() + ":00")); // To add time into db

            // check if the selected time slot is available for the selected barber on the selected date
            var query = ses.createQuery("SELECT COUNT(*) FROM Booking WHERE date = :date AND barber = :barber AND time = :time");
            query.setParameter("date", booking.getDate());
            query.setParameter("barber", booking.getBarber());
            query.setParameter("time", booking.getTime());
            Long count = (Long) query.uniqueResult();
            if (count > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "This time slot is already booked for this barber on this date.");
                alert.showAndWait();
                return;
            }

            ses.save(booking);
            tx.commit();
            ses.close();
        });
    }
}