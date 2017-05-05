package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.SQLException;

/**
 * Created by enesb on 5.05.2017.
 */
public class FXMLTableViewController {
    @FXML private TableView<Person> calisanlar;
    @FXML private TableView<Car> arabalar;

    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;

    @FXML private TextField plakaEnter;
    @FXML private TextField sahipEnter;

    @FXML private TableColumn<Person,String> name;
    @FXML private TableColumn<Person,String> surname;
    @FXML private TableColumn<Person,String> mail;
    @FXML private TableColumn<Person,String> personID;

    @FXML private TableColumn<Car,String> plaka;
    @FXML private TableColumn<Car,String> sahipAd;
    @FXML private TableColumn<Car,String> sahipSoyad;
    @FXML private TableColumn<Car,String> sahipID;

    private DatabaseConnector dataAccessor ;

    @FXML
    protected void addPerson(javafx.event.ActionEvent event) {
        ObservableList<Person> data = calisanlar.getItems();

        Person p = new Person(firstNameField.getText(), lastNameField.getText(), emailField.getText());

        try {
            dataAccessor.insertPerson(p);
            data.add(p);
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            calisanlar.getItems().clear();
            calisanlar.getItems().addAll(dataAccessor.getPersonList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    protected void addCar(javafx.event.ActionEvent event) {
        ObservableList<Car> data = arabalar.getItems();
        Person person = null;
        try {
             person = dataAccessor.getPerson(sahipEnter.getText());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if(person!=null)
        try {
            Car c = new Car(plakaEnter.getText(), person.getAd(), person.getSoyad(), sahipEnter.getText());
            dataAccessor.insertCar(c);
            data.add(c);
            plakaEnter.setText("");
            sahipEnter.setText("");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            arabalar.getItems().clear();
            arabalar.getItems().addAll(dataAccessor.getCarList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        name.setCellValueFactory(cellData -> cellData.getValue().adProperty());
        surname.setCellValueFactory(cellData -> cellData.getValue().soyadProperty());
        mail.setCellValueFactory(cellData -> cellData.getValue().login_mailProperty());
        personID.setCellValueFactory(cellData -> cellData.getValue().personIDProperty());

        plaka.setCellValueFactory(cellData -> cellData.getValue().plakaProperty());
        sahipAd.setCellValueFactory(cellData -> cellData.getValue().sahibiAdProperty());
        sahipSoyad.setCellValueFactory(cellData -> cellData.getValue().sahibiSoyadProperty());
        sahipID.setCellValueFactory(cellData -> cellData.getValue().sahipIDProperty());
        /*
        calisanlar.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Person>() {
            @Override
            public void changed(ObservableValue<? extends Person> observable, Person oldValue, Person newValue) {

            }
        });*/

    }

    public void setDataAccessor(DatabaseConnector dataAccessor) {
        this.dataAccessor = dataAccessor;
        try {
            calisanlar.getItems().addAll(dataAccessor.getPersonList());
            arabalar.getItems().addAll(dataAccessor.getCarList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}