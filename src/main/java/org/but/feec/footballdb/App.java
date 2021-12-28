package org.but.feec.footballdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.but.feec.footballdb.exceptions.ExceptionHandler;


public class App extends Application {

    private FXMLLoader loader;
    private VBox mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            mainStage = loader.load();

            primaryStage.setTitle("Football Database");
            Scene scene = new Scene(mainStage);
            setUserAgentStylesheet(STYLESHEET_MODENA);
            primaryStage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
           ExceptionHandler.handleException(ex);
        }
    }

}
