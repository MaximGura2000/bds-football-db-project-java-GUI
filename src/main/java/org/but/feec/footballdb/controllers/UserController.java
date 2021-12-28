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

public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @FXML
    public Button addPersonButton;
    @FXML
    public Button refreshButton;
    @FXML
    private TableColumn<UserBasicView, Long> user_id;
    @FXML
    private TableColumn<UserBasicView, String> personsCity;
    @FXML
    private TableColumn<UserBasicView, String> personsEmail;
    @FXML
    private TableColumn<UserBasicView, String> personsSurname;
    @FXML
    private TableColumn<UserBasicView, String> personsFirstname;
    @FXML
    private TableColumn<UserBasicView, String> personsUsername;
    @FXML
    private TableView<UserBasicView> systemPersonsTableView;


    private UserService userService;
    private UserRepository userRepository;

    public UserController() {
    }

    @FXML
    private void initialize() {
        userRepository = new UserRepository();
        userService = new UserService(userRepository);

        user_id.setCellValueFactory(new PropertyValueFactory<UserBasicView, Long>("user_id"));
        personsCity.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("city"));
        personsEmail.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("email"));
        personsSurname.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("surname"));
        personsFirstname.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("firstname"));
        personsUsername.setCellValueFactory(new PropertyValueFactory<UserBasicView, String>("username"));


        ObservableList<UserBasicView> observableUserList = initializeUserData();
        systemPersonsTableView.setItems(observableUserList);

        systemPersonsTableView.getSortOrder().add(user_id);

        initializeTableViewSelection();
        icon();

        logger.info("UserController initialized");
    }

    private void initializeTableViewSelection() {
        MenuItem edit = new MenuItem("Edit user");
        MenuItem detailedView = new MenuItem("Detailed user view");
        edit.setOnAction((ActionEvent event) -> {
            UserBasicView userView = systemPersonsTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("fxml/UserEdit.fxml"));
                Stage stage = new Stage();
                stage.setUserData(userView);
                stage.setTitle("Football Database Edit User");

                UserEditController controller = new UserEditController();
                controller.setStage(stage);
                fxmlLoader.setController(controller);

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }
        });

        detailedView.setOnAction((ActionEvent event) -> {
            UserBasicView personView = systemPersonsTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("fxml/UserDetailView.fxml"));
                Stage stage = new Stage();

                Long personId = personView.getId();
               UserDetailView userDetailView = userService.getPersonDetailView(personId);

                stage.setUserData(userDetailView);
                stage.setTitle("Football Database Users Detailed View");

                /*PersonsDetailViewController controller = new PersonsDetailViewController();
                controller.setStage(stage);
                fxmlLoader.setController(controller);*/

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }
      });


        ContextMenu menu = new ContextMenu();
        menu.getItems().add(edit);
        menu.getItems().addAll(detailedView);
        systemPersonsTableView.setContextMenu(menu);
    }

    private ObservableList<UserBasicView> initializeUserData() {
        List<UserBasicView> persons = userService.getPersonsBasicView();
        return FXCollections.observableArrayList(persons);
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

    public void handleAddPersonButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/PersonsCreate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("BDS JavaFX Create Person");
            stage.setScene(scene);

//            Stage stageOld = (Stage) signInButton.getScene().getWindow();
//            stageOld.close();
//
//            stage.getIcons().add(new Image(App.class.getResourceAsStream("logos/vut.jpg")));
//            authConfirmDialog();

            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void handleRefreshButton(ActionEvent actionEvent) {
        ObservableList<UserBasicView> observablePersonsList = initializeUserData();
        systemPersonsTableView.setItems(observablePersonsList);
        systemPersonsTableView.refresh();
        systemPersonsTableView.sort();
    }
}
