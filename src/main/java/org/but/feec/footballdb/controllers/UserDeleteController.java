package org.but.feec.footballdb.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.but.feec.footballdb.App;
//import org.but.feec.footballdb.api.UserCreateView;
import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.exceptions.DataAccessException;
import org.but.feec.footballdb.exceptions.ExceptionHandler;
import org.but.feec.footballdb.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class UserDeleteController {
    private static final Logger logger = LoggerFactory.getLogger(UserDeleteController.class);

    @FXML
    public Button userDelete;
    @FXML
    public Button allUserButton;
    @FXML
    private TextField deleteUserEmail;

    private UserService userService;
    private UserRepository userRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        validation = new ValidationSupport();
        validation.registerValidator(deleteUserEmail, Validator.createEmptyValidator("The email must not be empty."));

        logger.info("UserInfoController initialized");
    }

    public void handleDeleteUser(ActionEvent actionEvent)
    {
        String deleteEmail = deleteUserEmail.getText();
        if (userRepository.UserDelete(deleteEmail))
        {
            showDelete();
            System.out.println("Usera udalili");

        }
        else
        {
            System.out.println("User ne najden v db");
            showNonDelete();

        }
    }

    private void showDelete()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/SuccessfullDelete.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 500);
            Stage stage = new Stage();
            stage.setTitle("Football Database GUI");
            stage.setScene(scene);

            Stage stageOld = (Stage) allUserButton.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        }
        catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    private void showNonDelete()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/UnsuccessfullDelete.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 450, 500);
            Stage stage = new Stage();
            stage.setTitle("Football Database GUI");
            stage.setScene(scene);

            Stage stageOld = (Stage) allUserButton.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void handleAllUsersButton(ActionEvent actionEvent)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/Users.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1050, 600);
            Stage stage = new Stage();
            stage.setTitle("Football Database GUI");
            stage.setScene(scene);

            Stage stageOld = (Stage) allUserButton.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
