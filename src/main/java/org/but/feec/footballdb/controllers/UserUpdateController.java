package org.but.feec.footballdb.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.but.feec.footballdb.App;
//import org.but.feec.footballdb.api.UserCreateView;
import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.exceptions.ExceptionHandler;
import org.but.feec.footballdb.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class UserUpdateController {
    private static final Logger logger = LoggerFactory.getLogger(UserCreateController.class);

    @FXML
    public Button userUpdate;
    @FXML
    public Button allUserButton;
    @FXML
    private TextField userUpdateEmail;
    @FXML
    private TextField variable;
    @FXML
    private TextField newValue;

    private UserService userService;
    private UserRepository userRepository;
    private ValidationSupport validation;

    @FXML
    public void initialize() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        validation = new ValidationSupport();
        validation.registerValidator(userUpdateEmail, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(variable, Validator.createEmptyValidator("The email must not be empty."));
        validation.registerValidator(newValue, Validator.createEmptyValidator("The email must not be empty."));

        logger.info("UserInfoController initialized");
    }

    public void handleUserUpdate(ActionEvent actionEvent)
    {
        String column = variable.getText();
        String newInfo = newValue.getText();
        String email = userUpdateEmail.getText();
        if (userRepository.UpdateUser(column, newInfo, email))
        {
            showUpdate();
        }
        else
        {
            showNoUpdate();
        }

    }

    private void showUpdate()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/SUpdate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 600);
            Stage stage = new Stage();
            stage.setTitle("Football Database GUI");
            stage.setScene(scene);

            Stage stageOld = (Stage) userUpdate.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        }
        catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    private void showNoUpdate()
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/USUpdate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Football Database GUI");
            stage.setScene(scene);

            Stage stageOld = (Stage) userUpdate.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        }
        catch (IOException ex) {
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
