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
import org.but.feec.footballdb.api.UserDetailView;
import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.exceptions.ExceptionHandler;
import org.but.feec.footballdb.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
public class UserInfoTableController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @FXML
    public Button allUserButton;
    @FXML
    private TableColumn<UserDetailView, String> user_id;
    @FXML
    private TableColumn<UserDetailView, String> userCity;
    @FXML
    private TableColumn<UserDetailView, String> userEmail;
    @FXML
    private TableColumn<UserDetailView, String> userSurname;
    @FXML
    private TableColumn<UserDetailView, String> userFirstname;
    @FXML
    private TableColumn<UserDetailView, String> userUsername;
    @FXML
    private TableColumn<UserDetailView, String> userStreet;
    @FXML
    private TableColumn<UserDetailView, String> userHouseNumber;
    @FXML
    private TableView<UserDetailView> systemPersonsTableView;


    private UserService userService;
    private UserRepository userRepository;

    @FXML
    private void initialize() {
        userRepository = new UserRepository();

        user_id.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("id"));
        userEmail.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("email"));
        userFirstname.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("firstname"));
        userSurname.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("surname"));
        userUsername.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("username"));
        userCity.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("city"));
        userHouseNumber.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("houseNumber"));
        userStreet.setCellValueFactory(new PropertyValueFactory<UserDetailView, String>("street"));

        ObservableList<UserDetailView> observableUserList = initializeUserData();
        systemPersonsTableView.setItems(observableUserList);

        systemPersonsTableView.getSortOrder().add(user_id);
        logger.info("User Full View initialized");
    }



    private ObservableList<UserDetailView> initializeUserData() {
        List<UserDetailView> users = userRepository.UserInfo();
        return FXCollections.observableArrayList(users);
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
