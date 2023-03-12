package com.almarvasar.barbershopbooking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    private Label barber;

    @FXML

    private ChoiceBox<String> barberCB;

    private String[] barberNames ={"Funky Hair", "Slick Dude", "Hairy Beard"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        barberCB.getItems().addAll(barberNames);
    }
}