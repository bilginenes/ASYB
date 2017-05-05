package sample;

import javafx.application.Application ;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene ;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage ;

public class Main extends Application {
    private DatabaseConnector dataAccessor ;

    @Override
    public void start(Stage primaryStage) throws Exception {
        dataAccessor = new DatabaseConnector("com.mysql.jdbc.Driver","jdbc:mysql://localhost/softeng","root","1433"); // provide driverName, dbURL, user, password...

        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        primaryStage.setTitle("Araç Sizde Yükü Bizde A.Ş.");

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        GridPane gridPane = (GridPane)  loader.load();
        Scene scene = new Scene(gridPane);
        primaryStage.setScene(scene);
        FXMLTableViewController controller = loader.getController();
        controller.setDataAccessor(dataAccessor);
        primaryStage.show();

/*
        TableView<Person> tableView = (TableView<Person>) gridPane.lookup("tableView");
        tableView.getItems().addAll(dataAccessor.getPersonList());

        TableView<Person> personTable = new TableView<>();
        TableColumn<Person, String> firstNameCol = new TableColumn<>("ad");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("ad"));
        TableColumn<Person, String> lastNameCol = new TableColumn<>("soyad");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("soyad"));
        TableColumn<Person, String> emailCol = new TableColumn<>("login_mail");
        emailCol.setCellValueFactory(new PropertyValueFactory<>("login_mail"));
        personTable.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

        BorderPane root = new BorderPane();
        root.setCenter(personTable);
        */

    }


    @Override
    public void stop() throws Exception {
        if (dataAccessor != null) {
            dataAccessor.shutdown();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}