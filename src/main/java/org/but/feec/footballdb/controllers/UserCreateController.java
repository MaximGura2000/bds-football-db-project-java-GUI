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
import org.but.feec.footballdb.api.UserCreateView;
import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.exceptions.ExceptionHandler;
import org.but.feec.footballdb.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Optional;

public class UserCreateController {

    private static final Logger logger = LoggerFactory.getLogger(UserCreateController.class);

    @FXML
    public Button newUserCreate;
    @FXML
    public Button allUserButton;
    @FXML
    private TextField newUserEmail;
    @FXML
    private TextField newUserFirstname;
    @FXML
    private TextField newUserSurname;
    @FXML
    private TextField newUserUsername;
    @FXML
    private TextField newUserPassword;

    private UserService userService;
    private UserRepository userRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        validation = new ValidationSupport();
        validation.registerValidator(newUserEmail, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(newUserFirstname, Validator.createEmptyValidator("The first name must not be empty."));
        validation.registerValidator(newUserSurname, Validator.createEmptyValidator("The last name must not be empty."));
        validation.registerValidator(newUserUsername, Validator.createEmptyValidator("The nickname must not be empty."));
        validation.registerValidator(newUserPassword, Validator.createEmptyValidator("The password must not be empty."));

        newUserCreate.disableProperty().bind(validation.invalidProperty());

        logger.info("PersonCreateController initialized");
    }

    @FXML
    void handleCreateNewUser(ActionEvent event) {
        String email = newUserEmail.getText();
        String firstname = newUserFirstname.getText();
        String surname = newUserSurname.getText();
        String username = newUserUsername.getText();
        String password = newUserPassword.getText();

        if (userRepository.createUser(email, firstname, username, surname, password))
        {
            System.out.println("User create");
            personCreatedConfirmationDialog();
        }
        else
        {
            System.out.println("User not create");
        }
    }

    private void personCreatedConfirmationDialog() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Person Created Confirmation");
        alert.setHeaderText("Your person was successfully created.");

        Timeline idlestage = new Timeline(new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                alert.setResult(ButtonType.CANCEL);
                alert.hide();
            }
        }));
        idlestage.setCycleCount(1);
        idlestage.play();
        Optional<ButtonType> result = alert.showAndWait();
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
