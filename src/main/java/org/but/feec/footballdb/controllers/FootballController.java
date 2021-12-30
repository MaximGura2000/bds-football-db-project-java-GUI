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
import org.but.feec.footballdb.api.FootballTeamView;
import org.but.feec.footballdb.api.UserBasicView;
import org.but.feec.footballdb.api.UserDetailView;
import org.but.feec.footballdb.data.FootballTeam;
import org.but.feec.footballdb.data.UserRepository;
import org.but.feec.footballdb.exceptions.ExceptionHandler;
import org.but.feec.footballdb.services.UserService;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

public class FootballController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @FXML
    public Button allUserButton;
    @FXML
    private TableColumn<FootballTeamView, Long> numberOfTrophy;
    @FXML
    private TableColumn<FootballTeamView, String> footballTeamName;
    @FXML
    private TableView<FootballTeamView> FootballTableView;

    private FootballTeam footballTeam;

    @FXML
    private void initialize() {
        footballTeam = new FootballTeam();

        footballTeamName.setCellValueFactory(new PropertyValueFactory<FootballTeamView, String>("teamName"));
        numberOfTrophy.setCellValueFactory(new PropertyValueFactory<FootballTeamView, Long>("trophy"));

        ObservableList<FootballTeamView> observableUserList = initializeUserData();
        FootballTableView.setItems(observableUserList);

        FootballTableView.getSortOrder().add(numberOfTrophy);
        logger.info("Football Team View initialized");
    }



    private ObservableList<FootballTeamView> initializeUserData() {
        List<FootballTeamView> team = footballTeam.getTeamView();
        return FXCollections.observableArrayList(team);
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
