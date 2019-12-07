package sample;

import javafx.application.Application;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application  {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("Electricity Usage Billing System");
        primaryStage.setScene(new Scene(root, 818.0, 523.0));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


