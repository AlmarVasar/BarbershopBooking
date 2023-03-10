package com.almarvasar.barbershopbooking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.hibernate.SessionFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private SessionFactory session;

    @FXML private DatePicker dateCB;
    @FXML
    private ChoiceBox<String> barberCB;
    @FXML
    private ChoiceBox<String> serviceCB;
   // @FXML
    //private ChoiceBox timeCB;
    @FXML
    private Button insertButton;

    //@FXML private Button contactButton;

//Created arrays for choisebox
    private String[] barberNames = {"Funky Hair", "Slick Dude", "Hairy Beard"};
    private String[] service = {"Haircut", "Beard trim", "Haircut and beard trim"};

   // private Integer[] time = {09:00, 10:00, 11:00, 12:00, 13:00, 14:00, 15:00, 16:00""};


    public HelloController(SessionFactory session) {
        this.session = session;
    }
    //Display my options in my JavaFX
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barberCB.getItems().addAll(barberNames);
        serviceCB.getItems().addAll(service);
        //timeCB.getItems().addAll(time);

        //Open mysql and save the selected data in database
        this.insertButton.setOnAction(e -> {
            var ses = session.openSession();
            var tx = ses.beginTransaction();

            var booking = new Booking();
            booking.setDate(java.sql.Date.valueOf(dateCB.getValue())); //
            booking.setBarber(barberCB.getValue());
            booking.setService(serviceCB.getValue());
            //booking.setTime((Integer) timeCB.getValue());
            ses.save(booking);

            tx.commit();
            ses.close();
        });
    }
}