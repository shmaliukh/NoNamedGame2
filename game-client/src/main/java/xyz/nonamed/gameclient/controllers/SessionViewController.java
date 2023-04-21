package xyz.nonamed.gameclient.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Screen;
import javafx.util.Callback;
import xyz.nonamed.dto.Session;
import xyz.nonamed.gameclient.ClientApplication;
import xyz.nonamed.gameclient.SessionData;
import xyz.nonamed.gameclient.config.ScreenParam;
import xyz.nonamed.gameclient.config.SessionParam;
import xyz.nonamed.gameclient.config.UserParam;
import xyz.nonamed.gameclient.handlers.SessionHandler;
import xyz.nonamed.gameclient.handlers.UserHandler;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;

public class SessionViewController implements Initializable {
    @FXML
    public TextField sessionCodeTextField;
    @FXML
    public Button connectToSessionButton;
    @FXML
    public Button mainMenuButton;
    @FXML
    public AnchorPane mainView;
    @FXML
    public Label userInputAlert;
    @FXML
    public Label codeInputAlert;
    @FXML
    public TableView<SessionData> activeSessionInfoTable;
    @FXML
    public HBox bodyTitleTextHBox;
    @FXML
    public HBox headerBackgroundHBox;
    @FXML
    public Label connectionAlertMaxUsers;
    @FXML
    public Label userNameLabelText;
    public Label heroType;
    public TextField sessionMaxUsersTextField;

    private ObservableList<SessionData> tvObservableList = FXCollections.observableArrayList();

    public String userName = "User";
    public String userSessionCode = null;
    public int sessionMaxUsers = 2;

    private final TableColumn<SessionData, Integer> colCode = new TableColumn<>("Код сесії");
    private final TableColumn<SessionData, String> colName = new TableColumn<>("Гравці");
    private final TableColumn<SessionData, Integer> colMaxUsers = new TableColumn<>("Ліміт гравців");
    private final TableColumn<SessionData, Integer> colActiveUsers = new TableColumn<>("На сервері");
    TableColumn<SessionData, Void> colBtn = new TableColumn("");

    static UserHandler userHandler = new UserHandler();
    static SessionHandler sessionHandler = new SessionHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setScreenSize();


        userNameLabelText.setText(UserParam.USERNAME);

        codeInputAlert.setVisible(false);
        userInputAlert.setVisible(false);
        connectionAlertMaxUsers.setVisible(false);

        activeSessionInfoTable.getStylesheets().add("/xyz/vshmaliukh/gameclient/styles/session-view-style.css");
        activeSessionInfoTable.getStyleClass().add("table-view");



        addFilterToUserInputFields();

        fillTableDataFromServer();






    }


    private static void initGameSetting() {



        userHandler.postRegisterUser(UserParam.USERNAME);

        sessionHandler.postConnectUserToSession(UserParam.USERNAME, UserParam.SESSION_CODE);
        sessionHandler.postRunSession(UserParam.USERNAME, UserParam.SESSION_CODE);
    }



    private void fillTableDataFromServer(){
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colMaxUsers.setCellValueFactory(new PropertyValueFactory<>("maxUsers"));
        colActiveUsers.setCellValueFactory(new PropertyValueFactory<>("activeUsers"));

        activeSessionInfoTable.getColumns().addAll(colCode, colMaxUsers, colActiveUsers);

        fillTable();


        addButtonToTable();
    }

    @FXML
    public void connectToSession(){
        if (Integer.parseInt(sessionMaxUsersTextField.getText()) > 10 ||
        Integer.parseInt(sessionMaxUsersTextField.getText()) < 2){
            userInputAlert.setText("від 2 до 10");
            userInputAlert.setVisible(true);
            return;
        }

        if (UserParam.SESSION_CODE != null){
            SessionParam.SESSION_MAX_USERS = sessionMaxUsersTextField.getText();
            SessionParam.SESSION_CODE = sessionCodeTextField.getText();
            //initGameSetting();
//            System.out.println("<- connected ->  ");
//            System.out.println("Імʼя користувача: " + UserParam.USERNAME);
//            System.out.println("Код сесії: " + UserParam.SESSION_CODE);
//            System.out.println("Макс. гравців: " + SessionParam.SESSION_MAX_USERS);
        openGameView();


        } else {
            codeInputAlert.setText("Введіть код сесії!");
            codeInputAlert.setVisible(true);
        }
    }

    public void connectToAvailableServerFromTable(){
        openGameView();
    }

    private void openGameView() {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("views/game-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ClientApplication.mainStage.setTitle("Game view");
            ClientApplication.mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @FXML
    public void onRefreshButtonClick(){
        System.out.println("Action button");
        List<Session> sessionList = sessionHandler.getAllSessions();
        for (int i = 0; i < sessionList.size(); i++) {
            System.out.println("run:Session code is: " + sessionList.get(i).getSessionCode() + ":  " +
                    sessionList.get(i).getMaxUser());
        }
    }

    @FXML
    public void onMainMenuButtonClick(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(ClientApplication.class.getResource("main-menu-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            ClientApplication.mainStage.setTitle("Session view");
            ClientApplication.mainStage.setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void setScreenSize() {
        mainView.setPrefWidth(Screen.getPrimary().getBounds().getWidth() * ScreenParam.SCREEN_WIDTH_RATIO);
        mainView.setPrefHeight(Screen.getPrimary().getBounds().getHeight() * ScreenParam.SCREEN_HEIGHT_RATIO);
    }

    public void addFilterToUserInputFields() {
        // перевіка для спінера, на кількість користувачів для сесії
//        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, 10);
//        valueFactory.setValue(2);
//        maxUsersSpinner.setValueFactory(valueFactory);


        //Додаємо перевірку для обробки значення коду сесії
        UnaryOperator<TextFormatter.Change> filter = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {// дозволяємо введення лише цифр
                return change;
            }
            return null; // відхиляємо неприпустимі зміни
        };

        //Додаємо перевірку для обробки значення коду сесії
        UnaryOperator<TextFormatter.Change> filterMaxUsers = change -> {
            String text = change.getText();

            if (text.matches("[0-9]*")) {// дозволяємо введення лише цифр
                return change;
            }
            return null; // відхиляємо неприпустимі зміни
        };


        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        sessionCodeTextField.setTextFormatter(textFormatter);

        TextFormatter<String> textFormatter1 = new TextFormatter<>(filterMaxUsers);
        sessionMaxUsersTextField.setTextFormatter(textFormatter1);

    }

    private void fillTable() {

        List<Session> sessionListFromServer = sessionHandler.getAllSessions();

        for (Session session : sessionListFromServer) {
            tvObservableList.add(new SessionData(
                    Integer.parseInt(session.getSessionCode()),
                    "txt",
                    session.getMaxUser(),
                    session.getUserCounter()
            ));
        }

        activeSessionInfoTable.setItems(tvObservableList);
    }

    private void addButtonToTable() {


        Callback<TableColumn<SessionData, Void>, TableCell<SessionData, Void>> cellFactory = new Callback<TableColumn<SessionData, Void>, TableCell<SessionData, Void>>() {
            @Override
            public TableCell<SessionData, Void> call(final TableColumn<SessionData, Void> param) {
                final TableCell<SessionData, Void> cell = new TableCell<>() {

                    private final Button btn = new Button("Зайти");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            SessionData sessionData = getTableView().getItems().get(getIndex());
                            if (sessionData.getMaxUsers() > sessionData.getActiveUsers()){
                                UserParam.SESSION_CODE = String.valueOf(sessionData.getCode());
                                SessionParam.SESSION_CODE = UserParam.SESSION_CODE;
                                SessionParam.SESSION_MAX_USERS = String.valueOf(sessionData.getMaxUsers());
                                // TODO set max users
                                sessionMaxUsers = sessionData.getMaxUsers();
                                openGameView();
                                //connectToAvailableServerFromTable();
                                connectionAlertMaxUsers.setVisible(false);
                            } else {
                                connectionAlertMaxUsers.setVisible(true);
                            }

                        });


                        btn.getStylesheets().add("/xyz/vshmaliukh/gameclient/styles/session-view-style.css");
                        btn.getStyleClass().add("yellow");

                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);

        activeSessionInfoTable.getColumns().add(colBtn);

    }

    @FXML
    public void setValueFromSessionCodeTextField(){
        UserParam.SESSION_CODE = sessionCodeTextField.getText();
        // TODO change session max user on server
            if (sessionMaxUsersTextField.getText() != null){
                sessionMaxUsers = Integer.parseInt(sessionMaxUsersTextField.getText());
                SessionParam.SESSION_MAX_USERS = sessionMaxUsersTextField.getText();
            }

        if (codeInputAlert.isVisible()){
            codeInputAlert.setVisible(false);
        }
    }

    @FXML
    public void setValueFromMaxUserTextField(){
        // TODO change session max user on server
        if (sessionMaxUsersTextField.getText() != null){
            sessionMaxUsers = Integer.parseInt(sessionMaxUsersTextField.getText());
            SessionParam.SESSION_MAX_USERS = sessionMaxUsersTextField.getText();
        }

        if (userInputAlert.isVisible()){
            userInputAlert.setVisible(false);
        }
    }




}