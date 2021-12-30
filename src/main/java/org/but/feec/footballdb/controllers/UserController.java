package org.but.feec.footballdb.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.but.feec.footballdb.App;
import org.but.feec.footballdb.api.UserBasicView;
import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.exceptions.ExceptionHandler;
import org.but.feec.footballdb.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @FXML
    public Button addUserButton;
    @FXML
    public Button deleteUserButton;
    @FXML
    public Button detailUserViewButton;
    @FXML
    public Button UpdateUserButton;
    @FXML
    public Button refreshButton;
    @FXML
    private TableColumn<UserBasicView, Long> user_id;
    @FXML
    private TableColumn<UserBasicView, String> userCity;
    @FXML
    private TableColumn<UserBasicView, String> userEmail;
    @FXML
    private TableColumn<UserBasicView, String> userSurname;
    @FXML
    private TableColumn<UserBasicView, String> userFirstname;
    @FXML
    private TableColumn<UserBasicView, String> userUsername;
    @FXML
    private TableView<UserBasicView> systemUsersTableView;


    private UserService userService;
    private UserRepository userRepository;

    public UserController() {
    }

    @FXML
    private void initialize() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        user_id.setCellValueFactory(new PropertyValueFactory<UserBasicView, Long>("id"));
        userCity.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("city"));
        userEmail.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("email"));
        userSurname.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("surname"));
        userFirstname.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("firstname"));
        userUsername.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("username"));


        ObservableList<UserBasicView> observableUserList = initializeUserData();
        systemUsersTableView.setItems(observableUserList);

        systemUsersTableView.getSortOrder().add(user_id);

        icon();

        logger.info("UserController initialized");
    }

    private ObservableList<UserBasicView> initializeUserData() {
        List<UserBasicView> users = userService.getPersonsBasicView();
        return FXCollections.observableArrayList(users);
    }

    private void icon() {
        Image logoImage = new Image(App.class.getResourceAsStream("logos/ball-logo.png"));
        ImageView ballLogo = new ImageView(logoImage);
        ballLogo.setFitWidth(150);
        ballLogo.setFitHeight(50);
    }

    public void handleExitMenuItem(ActionEvent event) {
        System.exit(0);
    }

    public void handleAddUserButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/UserCreate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Football Database Create User");
            stage.setScene(scene);

            Stage stageOld = (Stage) addUserButton.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void handleRefreshButton(ActionEvent actionEvent) {
        ObservableList<UserBasicView> observablePersonsList = initializeUserData();
        systemUsersTableView.setItems(observablePersonsList);
        systemUsersTableView.refresh();
        systemUsersTableView.sort();
    }

    public void handleDeleteUserButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/UserDelete.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Football Database Delete User");
            stage.setScene(scene);

            Stage stageOld = (Stage) deleteUserButton.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void handleUserInfoViewButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/UserInfo.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Football Database User All Information View");
            stage.setScene(scene);

            Stage stageOld = (Stage) deleteUserButton.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void handleUserUpdateInfoButton(ActionEvent actionEvent)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/UserUpdate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("Football Database User Information Update");
            stage.setScene(scene);

            Stage stageOld = (Stage) deleteUserButton.getScene().getWindow();
            stageOld.close();
            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/ball.jpg")));

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }
}
