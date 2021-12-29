package org.but.feec.footballdb.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import org.but.feec.footballdb.api.UserCreateView;
import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class UserCreateController {

    private static final Logger logger = LoggerFactory.getLogger(UserCreateController.class);

    @FXML
    public Button newUserCreate;
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
    void handleCreateNewPerson(ActionEvent event) {
        String email = newUserEmail.getText();
        String firstName = newUserFirstname.getText();
        String lastName = newUserSurname.getText();
        String nickname = newUserUsername.getText();
        String password = newUserPassword.getText();

        UserCreateView userCreateView = new UserCreateView();
        userCreateView.setPassword(password.toCharArray());
        userCreateView.setEmail(email);
        userCreateView.setFirstname(firstName);
        userCreateView.setSurname(lastName);
        userCreateView.setUsername(nickname);

        userService.createUser(userCreateView);

        personCreatedConfirmationDialog();
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

}
