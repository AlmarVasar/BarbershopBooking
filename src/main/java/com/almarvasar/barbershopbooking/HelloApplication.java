package com.almarvasar.barbershopbooking;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        var session = HibernateDbConnection.getSessionFactory();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 350, 500);
        stage.setTitle("Book Your Appointment!");
        stage.setScene(scene);
        stage.show();

        HibernateDbConnection.shutdown();
    }

    public static void main(String[] args) {

        launch();
    }
}